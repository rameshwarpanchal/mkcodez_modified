//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ram.code;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class PnlCalculator {
    DefaultTableModel pnlModel = null;
    JLabel currPnl;
    controlPanel myUi;
    WebSocketClient ws;
    Map<String, Map<String, String>> indexToken;
    List<Map<String, Object>> stratList = new ArrayList();
    ExpressionEvaluator ev;
    private double totalPnl = 0.0;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    PnlCalculator(controlPanel myUi, WebSocketClient ws, Map<String, Map<String, String>> indexToken) {
        this.pnlModel = (DefaultTableModel)controlPanel.pnlTable.getModel();
        this.currPnl = myUi.currPnl;
        this.indexToken = indexToken;
        this.ws = ws;
        this.myUi = myUi;
        this.stratList = new ArrayList();
        this.ev = new ExpressionEvaluator(myUi.api);
    }

    public void startPnlCalculation() {
        this.scheduler.scheduleAtFixedRate(() -> {
            try {
                this.calculateTotalPnl();
            } catch (Exception var2) {
                System.err.println("Error calculating total PnL: " + var2.getMessage());
            }

        }, 0L, 1000L, TimeUnit.MILLISECONDS);
    }

    public void stopPnlCalculation() {
        this.scheduler.shutdown();

        try {
            if (!this.scheduler.awaitTermination(1L, TimeUnit.SECONDS)) {
                this.scheduler.shutdownNow();
            }
        } catch (InterruptedException var2) {
            this.scheduler.shutdownNow();
        }

    }

    private void calculateTotalPnl() {
        try {
            double newTotalPnl = 0.0;
            boolean result = false;
            Iterator<Map<String, Object>> iterator = this.stratList.iterator();

            while(true) {
                Map map;
                do {
                    if (!iterator.hasNext()) {
                        Map values;
                        for(Iterator var26 = this.myUi.api.ws.pnlData.entrySet().iterator(); var26.hasNext(); newTotalPnl += (Double)values.get("profit")) {
                            Map.Entry<String, Map<String, Object>> entry = (Map.Entry)var26.next();
                            values = (Map)entry.getValue();
                            if (values.get("profit") == null || (Double)values.get("profit") == 0.0) {
                                return;
                            }
                        }

                        if (this.myUi.lowIndex || this.myUi.upIndex) {
                            double ltp = (Double)this.ws.extractedData.get(((Map)this.indexToken.get(this.myUi.jComboBox7.getSelectedItem().toString())).get("token"));
                            if (this.myUi.lowIndex && ltp <= this.myUi.lowIndexValue) {
                                this.myUi.exitAll();
                                this.disablexits();
                            }

                            if (this.myUi.upIndex && ltp >= this.myUi.upIndexValue) {
                                this.myUi.exitAll();
                                this.disablexits();
                            }
                        }

                        if (this.myUi.lowPnl || this.myUi.upPnl) {
                            if (this.myUi.lowPnl && newTotalPnl <= this.myUi.lowPnlValue) {
                                this.myUi.exitAll();
                                this.disablexits();
                            }

                            if (this.myUi.upPnl && newTotalPnl >= this.myUi.upPnlValue) {
                                this.myUi.exitAll();
                                this.disablexits();
                            }
                        }

                        if (!this.myUi.disColors) {
                            if (newTotalPnl > 0.0) {
                                this.currPnl.setForeground(Color.green);
                                this.myUi.mini.jTextField2.setForeground(Color.green);
                            } else {
                                this.currPnl.setForeground(Color.red);
                                this.myUi.mini.jTextField2.setForeground(Color.red);
                            }
                        } else {
                            this.currPnl.setForeground(Color.white);
                        }

                        newTotalPnl = 0.0;

                        for(int i = 0; i < this.pnlModel.getRowCount(); ++i) {
                            newTotalPnl += Double.parseDouble(this.pnlModel.getValueAt(i, 3).toString());
                        }

                        this.currPnl.setText("" + newTotalPnl);
                        this.myUi.mini.jTextField2.setText("" + newTotalPnl);
                        return;
                    }

                    map = (Map)iterator.next();
                } while(!(Boolean)map.get("active"));

                Object result1;
                try {
                    result1 = this.ev.evaluateExpression((String)map.get("strategy"));
                } catch (Exception var24) {
                    iterator.remove();
                    this.myUi.updateStrategyTable();
                    return;
                }

                if (!(result1 instanceof Boolean)) {
                    iterator.remove();
                    this.myUi.updateStrategyTable();
                    return;
                }

                Boolean aBoolean = (Boolean)result1;
                result = aBoolean;
                if (map.get("outType").equals("EXIT") && this.myUi.api.ws.pnlData.get(map.get("id").toString()) == null) {
                    iterator.remove();
                    this.myUi.updateStrategyTable();
                    return;
                }

                if (result) {
                    if (map.get("outType").equals("ALERT")) {
                        map.put("active", false);
                        this.myUi.updateStrategyTable();
                        return;
                    }

                    if (map.get("outType").equals("EXIT")) {
                        this.myUi.exitOrder(map.get("id").toString(), (Integer)map.get("qty"), 0.0);
                        iterator.remove();
                        this.myUi.updateStrategyTable();
                        return;
                    }

                    map.put("active", false);
                    String strike = (String)map.get("strike");
                    String strikeType = (String)map.get("strikeType");
                    if (strikeType.equals("SELECTED")) {
                        int qty = (Integer)this.myUi.api.maxQty.get(this.myUi.api.oHandler.searchStrike(strike)) * (Integer)map.get("qty");

                        try {
                            if (!this.myUi.paper) {
                                this.myUi.api.placeOrder(strike, (String)map.get("side"), "M", (String)map.get("exch"), (String)map.get("orderType"), qty, (Double)map.get("price"));
                            } else {
                                double ltp = this.myUi.api.getLtp(strike, (String)map.get("exch"));
                                String token = (String)this.myUi.api.allTokens.get(strike);
                                if (token != null) {
                                    this.myUi.handlePaper(strike, (String)map.get("side"), qty, ltp, token, (String)map.get("exch"));
                                }
                            }
                        } catch (IOException var23) {
                            Logger.getLogger(PnlCalculator.class.getName()).log(Level.SEVERE, (String)null, var23);
                        }

                        this.myUi.updateStrategyTable();
                    } else {
                        int qty;
                        if (!Arrays.asList("ATMC", "ITMC", "OTMC", "ATMP", "ITMP", "OTMP").contains(strikeType) && !strikeType.startsWith("CE_LTP") && !strikeType.startsWith("PE_LTP")) {
                            if (strikeType.equals("TABLE")) {
                                this.myUi.placeTable();
                            } else {
                                String orderType;
                                ArrayList sellOrders;
                                if (strikeType.equals("MANUAL")) {
                                    List<Object[]> buyOrders = new ArrayList();
                                    sellOrders = new ArrayList();
                                    qty = 0;
                                    Iterator var38;
                                    Object obj;
                                    String[] tempArray;
                                    String orderType1;
                                    if (this.myUi.paper) {
                                        var38 = ((List)map.get("strikes")).iterator();

                                        while(var38.hasNext()) {
                                            obj = var38.next();
                                            if (obj instanceof String[]) {
                                                tempArray = (String[])obj;
                                                orderType1 = tempArray[1];
                                                orderType1 = tempArray[0];
                                                qty = Integer.parseInt(tempArray[2]) * (Integer)map.get("qty");
                                                double ltp = this.myUi.api.getLtp(orderType1, (String)map.get("exch"));
                                                String token = (String)this.myUi.api.allTokens.get(orderType1);
                                                if (token != null) {
                                                    qty *= (Integer)this.myUi.api.maxQty.get(this.myUi.api.oHandler.searchStrike(tempArray[0]));
                                                    this.myUi.handlePaper(orderType1, orderType1, qty, ltp, token, (String)map.get("exch"));
                                                }
                                            }
                                        }
                                    } else {
                                        var38 = ((List)map.get("strikes")).iterator();

                                        while(var38.hasNext()) {
                                            obj = var38.next();
                                            if (obj instanceof String[]) {
                                                tempArray = (String[])obj;
                                                orderType = tempArray[1];
                                                Object[] rowData = new Object[]{tempArray[0], orderType, Integer.valueOf(tempArray[2])};
                                                if ("B".equalsIgnoreCase(orderType)) {
                                                    buyOrders.add(rowData);
                                                    qty += (Integer)rowData[2] * (Integer)this.myUi.api.maxQty.get(this.myUi.api.oHandler.searchStrike(tempArray[0]));
                                                } else if ("S".equalsIgnoreCase(orderType)) {
                                                    sellOrders.add(rowData);
                                                }
                                            }
                                        }

                                        this.myUi.placeMultiple(buyOrders, sellOrders, qty, true);
                                        this.myUi.updateStrategyTable();
                                    }
                                } else
                                {
                                    List<Object> strikes = this.myUi.getdbStrikes(strikeType);
                                    sellOrders = new ArrayList();
                                    List<Object[]> sellOrders1 = new ArrayList();
                                    int qty1 = 0;
                                    Iterator var14;
                                    String[] tempArray;
                                    Object obj;
                                    if (this.myUi.paper) {
                                        var14 = strikes.iterator();

                                        while(var14.hasNext()) {
                                            obj = var14.next();
                                            if (obj instanceof String[]) {
                                                tempArray = (String[])obj;
                                                orderType = tempArray[1];
                                                String strike1 = tempArray[0];
                                                qty1 = Integer.parseInt(tempArray[2]) * (Integer)map.get("qty");
                                                double ltp = this.myUi.api.getLtp(strike1, (String)map.get("exch"));
                                                String token = (String)this.myUi.api.allTokens.get(strike1);
                                                if (token != null) {
                                                    qty1 *= (Integer)this.myUi.api.maxQty.get(this.myUi.api.oHandler.searchStrike(tempArray[0]));
                                                    this.myUi.handlePaper(strike1, orderType, qty1, ltp, token, (String)map.get("exch"));
                                                }
                                            }
                                        }
                                    } else {
                                        var14 = strikes.iterator();

                                        while(var14.hasNext()) {
                                            obj = var14.next();
                                            if (obj instanceof String[]) {
                                                tempArray = (String[])obj;
                                                orderType = tempArray[1];
                                                Object[] rowData = new Object[]{tempArray[0], orderType, Integer.parseInt(tempArray[2]) * (Integer)map.get("qty")};
                                                if ("B".equalsIgnoreCase(orderType)) {
                                                    sellOrders1.add(rowData);
                                                    qty1 += (Integer)rowData[2] * (Integer)this.myUi.api.maxQty.get(this.myUi.api.oHandler.searchStrike(tempArray[0]));
                                                } else if ("S".equalsIgnoreCase(orderType)) {
                                                    sellOrders1.add(rowData);
                                                }
                                            }
                                        }

                                        this.myUi.placeMultiple(sellOrders1, sellOrders1, qty1, true);
                                        this.myUi.updateStrategyTable();
                                    }
                                }
                            }
                        } else {
                            String index = (String)map.get("index");
                            String expiry2 = (String)((List)this.myUi.api.expiries.get(index)).get(this.myUi.jComboBox2.getSelectedIndex());
                            if (!strikeType.startsWith("CE_LTP") && !strikeType.startsWith("PE_LTP")) {
                                strike = this.myUi.api.quickStrike(index, expiry2, (String)map.get("strikeType"), 1);
                                strike = strike.split(":")[0];
                            } else {
                                strike = this.myUi.api.quickStrike(index, expiry2, (String)map.get("strikeType"), 1);
                            }

                            qty = (Integer)this.myUi.api.maxQty.get(this.myUi.api.oHandler.searchStrike(strike)) * (Integer)map.get("qty");

                            try {
                                if (!this.myUi.paper) {
                                    this.myUi.api.placeOrder(strike, (String)map.get("side"), "M", (String)map.get("exch"), (String)map.get("orderType"), qty, (Double)map.get("price"));
                                } else {
                                    double ltp = this.myUi.api.getLtp(strike, (String)map.get("exch"));
                                    String token = (String)this.myUi.api.allTokens.get(strike);
                                    if (token != null) {
                                        this.myUi.handlePaper(strike, (String)map.get("side"), qty, ltp, token, (String)map.get("exch"));
                                    }
                                }
                            } catch (IOException var22) {
                                Logger.getLogger(PnlCalculator.class.getName()).log(Level.SEVERE, (String)null, var22);
                            }

                            this.myUi.updateStrategyTable();
                        }
                    }
                }
            }
        } catch (Exception var25) {
            System.err.println("An error occurred: " + var25.getMessage());
            var25.printStackTrace();
        }
    }

    private void disablexits() {
        this.myUi.jCheckBox14.setSelected(false);
        this.myUi.jCheckBox15.setSelected(false);
    }

    private void playMusic() {
        try {
            InputStream audioStream = this.getClass().getClassLoader().getResourceAsStream("strategy.wav");

            label61: {
                try {
                    if (audioStream != null) {
                        AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioStream);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInput);
                        clip.start();
                        System.out.println("Playing embedded audio alert...");

                        while(!clip.isRunning()) {
                            Thread.sleep(10L);
                        }

                        while(clip.isRunning()) {
                            Thread.sleep(10L);
                        }

                        clip.close();
                        break label61;
                    }

                    System.err.println("Audio file not found!");
                } catch (Throwable var5) {
                    if (audioStream != null) {
                        try {
                            audioStream.close();
                        } catch (Throwable var4) {
                            var5.addSuppressed(var4);
                        }
                    }

                    throw var5;
                }

                if (audioStream != null) {
                    audioStream.close();
                }

                return;
            }

            if (audioStream != null) {
                audioStream.close();
            }
        } catch (IOException | LineUnavailableException | InterruptedException | UnsupportedAudioFileException var6) {
            System.err.println("Error playing the embedded audio: " + var6.getMessage());
        }

    }
}
