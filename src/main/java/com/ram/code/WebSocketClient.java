//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ram.code;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebSocketClient {
    String user_name = "";
    String suserToken = "";
    WebSocket webSocket = null;
    double pnl = 0.0;
    NorenApi api = null;
    Map<String, Double> extractedData = null;
    controlPanel myUi = null;
    DefaultTableModel optionModel = null;
    Map<String, int[]> tokenMap = null;
    Map<String, Map<String, Object>> buyOrders = new HashMap();
    Map<String, List<Object>> sellOrders = new HashMap();
    List<JSONObject> order_list = new ArrayList();
    Map<String, Map<String, Object>> pnlData = new HashMap();
    ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> allPositions;
    Map<String, String> pnlCache = new HashMap();
    Map<String, String> posCache = new HashMap();
    Map<String, Integer> allOrders = new HashMap();
    DefaultTableModel pnlModel;
    DefaultTableModel pnlModel2;
    DefaultTableModel allPosModel;
    Map<String, Integer> timeCache = new HashMap();
    String URL = "wss://api.shoonya.com/NorenWSTP";

    WebSocketClient(NorenApi api, String user_name, String suserToken, Map<String, Double> extractedData) {
        this.api = api;
        this.allPositions = this.api.allPositions;
        this.myUi = api.myUi;
        controlPanel var10001 = api.myUi;
        this.pnlModel = (DefaultTableModel) controlPanel.pnlTable.getModel();
        this.pnlModel2 = (DefaultTableModel) api.myUi.mini.jTable1.getModel();
        this.allPosModel = (DefaultTableModel) api.myUi.allPosTable.getModel();
        this.optionModel = (DefaultTableModel) api.myUi.optionChain.getModel();
        this.user_name = user_name;
        this.suserToken = suserToken;
        this.extractedData = extractedData;
    }

    public void connectClient() throws InterruptedException {
        if (this.api.broker.equals("FLATTRADE")) {
            this.URL = "wss://piconnect.flattrade.in/PiConnectWSTp";
        }

        OkHttpClient client = (new OkHttpClient.Builder()).connectTimeout(30L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).pingInterval(10L, TimeUnit.SECONDS).build();
        Request request = (new Request.Builder()).url(this.URL).build();
        WebSocketListener listener = new WebSocketListener() {
            public void onOpen(WebSocket webSocket, Response response) {

                try {
                    System.out.println("*********************----inside onOpen method--**************************");
//                    System.out.println("WebSocket connected");
                    JSONObject values = new JSONObject();
                    values.put("t", "c");
                    values.put("uid", WebSocketClient.this.user_name);
                    values.put("actid", WebSocketClient.this.user_name);
                    values.put("susertoken", WebSocketClient.this.suserToken);
                    values.put("source", "API");
                    String payload = values.toString();

                    if (webSocket != null) {
                        logWebSocketActivity("Payload Sent", payload);
                        webSocket.send(payload);
                        WebSocketClient.this.subscribe();

                        try {
                            WebSocketClient.this.api.oHandler.getAllPositions();
                            System.out.println("*********************----end onOpen method--**************************");

                        } catch (IOException var6) {
                            Logger.getLogger(WebSocketClient.class.getName()).log(Level.SEVERE, (String) null, var6);
                        }
                    } else {
                        System.out.println("WebSocket is not connected.");
                    }
                } catch (Exception var7) {
                    var7.printStackTrace();
                }

            }

            public void onMessage(WebSocket webSocket, String text) {
                System.out.println("---------------inside of On message method-------------------");
                try {
                    JSONObject jsonResponse = new JSONObject(text);
                    if (jsonResponse.has("lp")) {
                        logWebSocketActivity("Response Received", jsonResponse.toString());
                        double lpValue = Double.parseDouble(jsonResponse.getString("lp"));
                        String token = jsonResponse.getString("tk");
                        if (WebSocketClient.this.pnlCache.containsKey(token)) {
                            WebSocketClient.this.processPnl((String) WebSocketClient.this.pnlCache.get(token), lpValue);
                        }

                        if (WebSocketClient.this.posCache.containsKey(token)) {
                            WebSocketClient.this.processPnl2((String) WebSocketClient.this.posCache.get(token), lpValue);
                        }

                        WebSocketClient.this.extractedData.put(token, lpValue);
                        if (WebSocketClient.this.tokenMap != null) {
                            int[] values = (int[]) WebSocketClient.this.tokenMap.get(token);
                            if (values != null) {
                                int i = values[0];
                                int j = values[1];
                                WebSocketClient.this.optionModel.setValueAt("" + lpValue, i, j);
                                if (jsonResponse.has("oi") && jsonResponse.has("poi")) {
                                    double oiChange = 0.0;
                                    int oi = Integer.parseInt(jsonResponse.getString("oi"));
                                    oiChange = (double) ((oi - Integer.parseInt(jsonResponse.getString("poi"))) / Integer.parseInt(jsonResponse.getString("poi")));
                                    oiChange = (double) (Math.round(oiChange * 100.0) / 100L);
                                    if (j <= 1) {
                                        System.out.println("inside on message method :: OI ::" + oi + " and oi change: " + oiChange);
                                        WebSocketClient.this.optionModel.setValueAt("" + oi + "(" + oiChange + ")", i, 0);
                                    } else {
                                        System.out.println("else block :: inside on message method :: OI ::" + oi + " and oi change: " + oiChange);

                                        WebSocketClient.this.optionModel.setValueAt("" + oi + "(" + oiChange + ")", i, 8);
                                    }
                                }
                            }
                        }

                        if (WebSocketClient.this.api.hisData.get(token) != null && jsonResponse.has("ft")) {
                            try {
                                WebSocketClient.this.updateHistoricalData(token, jsonResponse);
                            } catch (ParseException var13) {
                                Logger.getLogger(WebSocketClient.class.getName()).log(Level.SEVERE, (String) null, var13);
                            }
                        }
                    } else if (jsonResponse.has("norenordno")) {
                        System.out.println(jsonResponse);
                        WebSocketClient.this.handleOrder(jsonResponse);
                    }
                } catch (Exception var14) {
                    var14.printStackTrace();
                }

            }

            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                System.out.println(WebSocketClient.this.pnlCache);
                System.out.println("CONNECTION FAILED");
                WebSocketClient.this.reconnect();
            }

            public void onClosing(WebSocket webSocket, int code, String reason) {
                System.out.println("Closing WebSocket: " + reason);
            }

            public void onClosed(WebSocket webSocket, int code, String reason) {
                System.out.println("WebSocket closed");
            }
        };
        this.webSocket = client.newWebSocket(request, listener);
        client.dispatcher().executorService().awaitTermination(1L, TimeUnit.SECONDS);
    }

    private void logWebSocketActivity(String type, String content) {
        System.out.println("---- WebSocket " + type + " ----");
        System.out.println(content);
        System.out.println("----------------------------");
    }


    private void updateHistoricalData(String token, JSONObject message) throws ParseException {
        System.out.println("inside updateHistoricalData method");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US);
        System.out.println("will get historical data of token");
        JSONArray data = (JSONArray) this.api.hisData.get(token);
        System.out.println("got historical data of token");
        if (data != null && data.length() != 0) {
            JSONObject arr = data.getJSONObject(0);
            Date d2 = sdf.parse(arr.getString("time"));
            long unixTimestamp = message.getLong("ft");
            Date d1 = new Date(TimeUnit.SECONDS.toMillis(unixTimestamp));
            LocalDateTime ldt2 = d2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plus(1L, ChronoUnit.MINUTES).truncatedTo(ChronoUnit.MINUTES);
            LocalDateTime ldt1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            if (!ldt1.isAfter(ldt2) && !ldt1.equals(ldt2)) {
                double lp = message.getDouble("lp");
                double inth = arr.getDouble("inth");
                double intl = arr.getDouble("intl");
                if (lp > inth) {
                    arr.put("inth", "" + lp);
                } else if (lp < intl) {
                    arr.put("intl", "" + lp);
                }

                arr.put("intc", "" + lp);
                arr.put("time", sdf.format(d1));
                this.api.hisData.put(token, data);
            } else {
                JSONObject temp1 = new JSONObject();
                temp1.put("time", sdf.format(d1));
                temp1.put("into", "" + String.valueOf(message.get("lp")));
                temp1.put("inth", "" + String.valueOf(message.get("lp")));
                temp1.put("intl", "" + String.valueOf(message.get("lp")));
                temp1.put("intc", "" + String.valueOf(message.get("lp")));
                JSONArray newData = new JSONArray();
                newData.put(temp1);

                int y;
                for (y = 0; y < data.length(); ++y) {
                    newData.put(data.getJSONObject(y));
                }

                this.api.hisData.put(token, newData);
                if (this.timeCache.get(token) != null) {
                    y = d1.getHours() * 60 + d1.getMinutes();
                    int tf1 = (Integer) this.timeCache.get(token);
                    if ((y - 555) % tf1 == 0) {
                        this.updateAutoSl(token, tf1);
                    }
                }
            }

        }
    }

    private void updateAutoSl(String token, int tf1) {
        try {
            Iterator var3 = this.pnlData.entrySet().iterator();

            while (var3.hasNext()) {
                Map.Entry<String, Map<String, Object>> entry = (Map.Entry) var3.next();
                String key = (String) entry.getKey();
                Map<String, Object> values = (Map) entry.getValue();
                Object tokenObj = values.get("token");
                if (tokenObj != null && tokenObj.toString().equals(token)) {
                    if ((Boolean) values.get("candleTrail")) {
                        String tranType = values.get("tranType").toString();
                        String cpType = values.get("cpType").toString();
                        String index = values.get("index").toString();
                        Map niftyMap;
                        if ((Boolean) values.get("sl")) {
                            List<Map<String, Object>> aggregateData = this.api.pnlCalculator.ev.parseData(token, tf1);
                            niftyMap = (Map) aggregateData.get(aggregateData.size() - 2);
                            if (tranType.equals("B")) {
                                values.put("liveSl", Double.valueOf(niftyMap.get("intl").toString()));
                            } else {
                                values.put("liveSl", Double.valueOf(niftyMap.get("inth").toString()));
                            }
                        } else if ((Boolean) values.get("indexSl")) {
                            String key1 = (String) this.api.pnlCalculator.ev.indexShorts.entrySet().stream().filter((entry1) -> {
                                return ((String) entry1.getValue()).equals(index);
                            }).map(Map.Entry::getKey).findFirst().orElse(null);
                            niftyMap = (Map) this.api.indexToken.get(index);
                            if (niftyMap != null) {
                                List<Map<String, Object>> aggregateData = this.api.pnlCalculator.ev.parseData(key1, tf1);
                                Map<String, Object> record = (Map) aggregateData.get(aggregateData.size() - 2);
                                if ((!tranType.equals("B") || !cpType.equals("CE")) && (!tranType.equals("S") || !cpType.equals("PE"))) {
                                    values.put("liveSl", Double.valueOf(record.get("inth").toString()));
                                } else {
                                    values.put("liveSl", Double.valueOf(record.get("intl").toString()));
                                }
                            }
                        }

                        for (int i = 0; i < this.pnlModel.getRowCount(); ++i) {
                            if (this.pnlModel.getValueAt(i, 0).toString().equals(key)) {
                                this.pnlModel.setValueAt((Double) values.get("liveSl"), i, 7);
                                return;
                            }
                        }

                        return;
                    } else {
                        this.timeCache.remove(token);
                        break;
                    }
                }
            }
        } catch (Exception var15) {
        }

    }

    private void reconnect() {
        try {
            Thread.sleep(5000L);
            System.out.println("Reconnecting...");
            this.connectClient();
        } catch (InterruptedException var2) {
            System.out.println("Reconnection interrupted: " + var2.getMessage());
        }

    }

    private String getToken(String exch, String strike) {
        try {
            if (this.api.allTokens.containsKey(strike)) {
                return (String) this.api.allTokens.get(strike);
            }

            JSONObject res = this.api.oHandler.searchOption(exch, strike);
            if (res.has("token")) {
                return res.getString("token");
            }
        } catch (IOException var5) {
            Logger.getLogger(WebSocketClient.class.getName()).log(Level.SEVERE, (String) null, var5);
        }

        return null;
    }

    public void handleOrder(JSONObject message) {
        try {
            this.order_list.add(message);
            String status = message.getString("status");
            String order_no = message.getString("norenordno");
            String side = message.getString("trantype");
            int qty = Integer.parseInt(message.getString("qty"));
            String strike = message.getString("tsym");
            String product = message.getString("pcode");
            String exch = message.getString("exch");
            String token = this.getToken(exch, strike);
            if (!status.equals("PENDING")) {
                String order_id = strike + product;
                double avgPrice = 0.0;
                int avgQty = 0;
                int pendingQty = 0;
                String tranType = "";
                Map tempData;
                if (this.pnlData.containsKey(order_id)) {
                    tempData = (Map) this.pnlData.get(order_id);
                    avgPrice = (Double) tempData.get("avgPrice");
                    avgQty = (Integer) tempData.get("avgQty");
                    tranType = (String) tempData.get("tranType");
                    pendingQty = (Integer) tempData.get("pendingQty");
                } else if (this.allPositions.containsKey(order_id) && (Integer) ((ConcurrentHashMap) this.allPositions.get(order_id)).get("avgQty") > 0) {
                    tempData = (Map) this.allPositions.get(order_id);
                    avgPrice = (Double) tempData.get("avgPrice");
                    avgQty = (Integer) tempData.get("avgQty");
                    tranType = (String) tempData.get("tranType");
                    pendingQty = (Integer) tempData.get("pendingQty");
                } else {
                    tranType = side;
                }

                if ((!status.equals("OPEN") && !status.equals("CANCELED") || !side.equals(tranType)) && !status.equals("REJECTED")) {
                    if (status.equals("OPEN") && !side.equals(tranType) && message.getString("reporttype").equals("New") && !this.allOrders.containsKey(order_no)) {
                        avgQty -= qty;
                        pendingQty += qty;
                    }

                    if (status.equals("COMPLETE") && side.equals(tranType)) {
                        double avg = Double.parseDouble(message.getString("avgprc"));
                        int fQty = Integer.parseInt(message.getString("fillshares"));
                        avgPrice = ((double) avgQty * avgPrice + avg * (double) fQty) / (double) (fQty + avgQty);
                        avgQty += fQty;
                    }

                    if (status.equals("COMPLETE") && !side.equals(tranType)) {
                        if (!this.allOrders.containsKey(order_no)) {
                            avgQty -= Integer.parseInt(message.getString("fillshares"));
                            this.allOrders.put(order_no, Integer.parseInt(message.getString("fillshares")));
                        } else if (pendingQty > 0) {
                            pendingQty -= Integer.parseInt(message.getString("fillshares"));
                        }
                    }

                    if (status.equals("CANCELED") && !side.equals(tranType) && this.allOrders.containsKey(order_no)) {
                        avgQty += qty;
                        pendingQty -= qty;
                        this.allOrders.put(order_no, qty);
                    }

                    if (avgQty < 0) {
                        avgQty = -avgQty;
                        tranType = side;
                    }

                    this.api.oHandler.applyRisk(strike, product, token, avgQty, avgPrice, exch, tranType, pendingQty);
                    Thread.sleep(1000L);
                    this.api.oHandler.addPosition(message.getString("tsym"), message.getString("pcode"));
                    this.checkOrder();
                } else {
                    this.checkOrder();
                    this.api.oHandler.getOrders();
                    this.api.oHandler.addOpenOrders();
                }
            }
        } catch (Exception var19) {
            var19.printStackTrace();
        }

    }

    public void checkOrder() {
        for (int i = 0; i < this.order_list.size(); ++i) {
            JSONObject obj1 = (JSONObject) this.order_list.get(i);
            String order_id = obj1.getString("norenordno");
            Iterator var4 = this.buyOrders.entrySet().iterator();

            while (var4.hasNext()) {
                Map.Entry<String, Map<String, Object>> entry = (Map.Entry) var4.next();
                String uuid = (String) entry.getKey();
                Map<String, Object> value = (Map) entry.getValue();
                List<String> orders = (List) value.get("ORDERS");
                int count = (Integer) value.get("COUNT");

                for (int j = 0; j < orders.size(); ++j) {
                    if (order_id.equals(orders.get(j))) {
                        String status = obj1.getString("status");
                        if (status.equals("COMPLETE")) {
                            count -= Integer.parseInt(obj1.getString("fillshares"));
                            value.put("COUNT", count);
                            if (count <= 0) {
                                this.buyOrders.remove(uuid);
                                this.placeSell(uuid);
                                return;
                            }
                        } else if (status.equals("REJECTED")) {
                            this.buyOrders.remove(uuid);
                            if (this.sellOrders.containsKey(uuid)) {
                                System.out.println(this.sellOrders.get(uuid));
                            }
                        }
                    }
                }
            }
        }

        System.out.println(this.buyOrders);
    }

    public void placeSell(String uuid) {
        List<Object> orders = (List) this.sellOrders.get(uuid);
        System.out.println(orders);

        for (int i = 0; i < orders.size(); ++i) {
            List<Object> order = (List) orders.get(i);
            System.out.println("Strike: " + String.valueOf(order.get(0)));
            String strike = order.get(0).toString();
            String side = order.get(1).toString();
            String product = order.get(2).toString();
            String exch = order.get(3).toString();
            String orderType = order.get(4).toString();
            double price = 0.0;
            int lots = Integer.parseInt(order.get(5).toString());
            lots = lots;

            try {
                if (this.api.myUi.jCheckBox12.isSelected()) {
                    price = this.api.myUi.getLimit(strike, exch, side);
                    if (price != 0.0) {
                        orderType = "LMT";
                    }
                }

                int freeze = (Integer) this.api.maxFreeze.get(this.api.oHandler.searchStrike(strike));
                int n = lots / freeze;
                int bal = lots % freeze;

                for (int j = 0; j < n; ++j) {
                    this.api.placeOrder(strike, side, product, exch, orderType, freeze, price);
                }

                if (bal > 0) {
                    this.api.placeOrder(strike, side, product, exch, orderType, bal, price);
                }
            } catch (IOException var17) {
                Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var17);
            }
        }

    }

    public void subscribe() {
        System.out.println("inside subscribe()method which takes no args and create instruments list");
        String instruments = "";
        Iterator var2 = this.api.indexToken.entrySet().iterator();

        Map.Entry entry;
        String key;
        while (var2.hasNext()) {
            entry = (Map.Entry) var2.next();
            key = (String) entry.getKey();
            Map<String, String> innerMap = (Map) entry.getValue();
            System.out.println(innerMap);
            if (!instruments.equals("")) {
                instruments = instruments + "#" + (String) innerMap.get("exch") + "|" + (String) innerMap.get("token");
            } else {
                String var10000 = (String) innerMap.get("exch");
                instruments = var10000 + "|" + (String) innerMap.get("token");
            }
        }

        var2 = this.api.allTokens.entrySet().iterator();

        while (var2.hasNext()) {
            entry = (Map.Entry) var2.next();
            key = (String) entry.getKey();
            String value = (String) entry.getValue();
            String exch = "NFO";
            if (key.contains("SENSEX")) {
                exch = "BFO";
            }

            if (!instruments.equals("")) {
                instruments = instruments + "#" + exch + "|" + value;
            } else {
                instruments = exch + "|" + value;
            }
        }

        this.subscribe(instruments);
        System.out.println(instruments);
        this.checkHisdata();
    }

    public void subscribe(String instrument) {
        System.out.println("--***** inside of subscribe method which take list of insturments and sent to websocket----****");
        JSONObject values = new JSONObject();

        values.put("t", "d");
        values.put("k", instrument);
        String data = values.toString();
        System.out.println("Send to websocket ::" + data);
        if (this.webSocket != null) {
            this.webSocket.send(data);
        } else {
            System.out.println("WebSocket is not connected.");
        }

        System.out.println(2);
        System.out.println(data);
        this.checkHisdata();
        System.out.println("end of subscribe method which take list of instruments and send to websocket");
    }

        public void checkHisdata() {
        System.out.println("********* inside of check Historical Data method  *************");
        try {
            Iterator var1 = this.api.indexToken.entrySet().iterator();
            this.api.indexToken.forEach((x,y)-> System.out.println(x +" : "+y));

            Map.Entry entry;
            while(var1.hasNext()) {
                entry = (Map.Entry)var1.next();
                Map<String, String> innerMap = (Map)entry.getValue();
                if (this.api.hisData.get(innerMap.get("token")) == null) {
                    System.out.println((String)entry.getKey());
                    System.out.println("---------------------------------------------------------");
                    System.out.println("sending call to historical data method when token is null: ");
                    this.api.historicalData((String)innerMap.get("exch"), (String)innerMap.get("token"));
                }
            }

            var1 = this.api.allTokens.entrySet().iterator();
            System.out.println("All Tookens ---**********");

            this.api.allTokens.forEach((x,y)-> System.out.println(x +" : "+y));

            while(var1.hasNext()) {
                entry = (Map.Entry)var1.next();
                System.out.println("strike :: "+(String)entry.getKey());
                String key = (String)entry.getKey();
                String value = (String)entry.getValue();
                String exch = "NFO";
                if (key.contains("SENSEX")) {
                    exch = "BFO";
                }

                if (this.api.hisData.get(value) == null) {
                    System.out.println("---------------------------------------------------------");
                    System.out.println("sending call to historical data method : "+ exch + " " + value );
                    this.api.historicalData(exch, value);
                }
            }
            System.out.println("printing historical data map");
            this.api.hisData.forEach((a,b)-> System.out.println(a +" "+ b.toString()));
            System.out.println("********* end of check Historical Data method  *************");

        } catch (Exception var6) {
        }

    }


    // chat gpt code for checkHisdata method
//    public void checkHisdata() {
//        System.out.println("********* Inside checkHisData method *************");
//
//        try {
//            // Check indexToken entries
//            System.out.println("Index Tokens:");
//            for (Map.Entry<String, Map<String, String>> entry : api.indexToken.entrySet()) {
//                String indexName = entry.getKey();
//                Map<String, String> innerMap = entry.getValue();
//                System.out.println(indexName + " : " + innerMap);
//
//                String token = innerMap.get("token");
//                String exch = innerMap.get("exch");
//
//                if (token != null && !api.hisData.containsKey(token)) {
//                    System.out.println("---------------------------------------------------------");
//                    System.out.println("Requesting historical data for Index: " + indexName + ", Token: " + token);
//                    api.historicalData(exch, token);
//                }
//            }
//
//            // Check allTokens entries
//            System.out.println("\nAll Option Tokens:");
//            for (Map.Entry<String, String> entry : api.allTokens.entrySet()) {
//                String strikeName = entry.getKey();
//                String token = entry.getValue();
//
//                System.out.println("Strike: " + strikeName);
//
//                String exch = strikeName.contains("SENSEX") ? "BFO" : "NFO";
//
//                if (token != null && !api.hisData.containsKey(token)) {
//                    System.out.println("---------------------------------------------------------");
//                    System.out.println("Requesting historical data for Strike: " + strikeName + ", Token: " + token);
//                    api.historicalData(exch, token);
//                }
//            }
//
//            // Print final hisData
//            System.out.println("\nHistorical Data Map:");
//            api.hisData.forEach((token, data) -> System.out.println(token + " -> " + data));
//
//        } catch (Exception e) {
//            System.err.println("Error occurred in checkHisData: " + e.getMessage());
//            e.printStackTrace(); // This will help you find bugs
//        }
//
//        System.out.println("********* End of checkHisData method *************");
//    }


    public void processPnl(String token, double lpValue) {
        String[] pnlArray = token.split("_");
        String order_id = pnlArray[0] + "M";
        boolean tokenFound = false;
        if (this.pnlData.containsKey(order_id)) {
            this.livePnl(order_id, Integer.parseInt(pnlArray[1]), lpValue);
            tokenFound = true;
        }

        order_id = pnlArray[0] + "I";
        if (this.pnlData.containsKey(order_id)) {
            this.livePnl(order_id, Integer.parseInt(pnlArray[2]), lpValue);
            tokenFound = true;
        }

        if (!tokenFound) {
            order_id = pnlArray[0] + "PAPER";
            if (this.pnlData.containsKey(order_id)) {
                this.livePnl(order_id, Integer.parseInt(pnlArray[1]), lpValue);
            }
        }

    }

    public void processPnl2(String token, double lpValue) {
        try {
            String[] pnlArray = token.split("_");
            String order_id = pnlArray[0] + "M";
            Map fields;
            int avgQty;
            double avgPrice;
            String side;
            double profit;
            double maxQty;
            if (this.allPositions.containsKey(order_id)) {
                fields = (Map) this.allPositions.get(order_id);
                avgQty = (Integer) fields.get("avgQty");
                avgPrice = (Double) fields.get("avgPrice");
                side = fields.get("tranType").toString();
                profit = (double) Math.round((lpValue - avgPrice) * (double) avgQty);
                if (side.equals("S")) {
                    profit = -profit;
                }

                profit += (Double) fields.get("pnl");
                if (!this.myUi.pnlPoints && !this.myUi.lot1Pnl) {
                    fields.put("totPnl", (double) Math.round(profit * 100.0) / 100.0);
                    this.allPosModel.setValueAt((double) Math.round(profit * 100.0) / 100.0, Integer.parseInt(pnlArray[1]), 4);
                } else if (this.myUi.pnlPoints) {
                    this.allPosModel.setValueAt((double) Math.round(profit / (double) avgQty * 100.0) / 100.0, Integer.parseInt(pnlArray[1]), 4);
                } else {
                    maxQty = (double) (Integer) this.api.maxQty.get(this.api.oHandler.searchStrike(order_id));
                    this.allPosModel.setValueAt((double) Math.round(profit / (double) avgQty * maxQty * 100.0) / 100.0, Integer.parseInt(pnlArray[1]), 4);
                }
            }

            order_id = pnlArray[0] + "I";
            if (this.allPositions.containsKey(order_id)) {
                fields = (Map) this.allPositions.get(order_id);
                avgQty = (Integer) fields.get("avgQty");
                avgPrice = (Double) fields.get("avgPrice");
                side = fields.get("tranType").toString();
                profit = (double) Math.round((lpValue - avgPrice) * (double) avgQty);
                if (side.equals("S")) {
                    profit = -profit;
                }

                profit += (Double) fields.get("pnl");
                if (!this.myUi.pnlPoints && !this.myUi.lot1Pnl) {
                    fields.put("totPnl", (double) Math.round(profit * 100.0) / 100.0);
                    this.allPosModel.setValueAt((double) Math.round(profit * 100.0) / 100.0, Integer.parseInt(pnlArray[2]), 4);
                } else if (this.myUi.pnlPoints) {
                    this.allPosModel.setValueAt((double) Math.round(profit / (double) avgQty * 100.0) / 100.0, Integer.parseInt(pnlArray[2]), 4);
                } else {
                    maxQty = (double) (Integer) this.api.maxQty.get(this.api.oHandler.searchStrike(order_id));
                    this.allPosModel.setValueAt((double) Math.round(profit / (double) avgQty * maxQty * 100.0) / 100.0, Integer.parseInt(pnlArray[2]), 4);
                }
            }

            double[] pnlWrapper = new double[]{0.0};
            this.allPositions.forEach((key, value) -> {
                pnlWrapper[0] += Double.parseDouble(value.get("totPnl").toString());
            });
            this.pnl = pnlWrapper[0];
            if (!this.myUi.pnlPoints) {
                this.allPosModel.setValueAt((double) Math.round(this.pnl * 100.0) / 100.0, this.allPosModel.getRowCount() - 1, 4);
            }

            this.mtmCheck();
        } catch (Exception var15) {
            System.out.println(var15);
            var15.printStackTrace();
        }

    }

    public void mtmCheck() {
        if (this.myUi.mtmSL && this.pnl <= this.myUi.mtmSLValue) {
            this.myUi.exitAll();
        }

        if (this.myUi.mtmTARGET && this.pnl >= this.myUi.mtmTARGETValue) {
            this.myUi.exitAll();
        }

    }

    public void livePnl(String order_id, int index, double lpValue) {
        Map<String, Object> fields = (Map) this.pnlData.get(order_id);
        this.processRisks(fields, lpValue, index, order_id);
        int avgQty = (Integer) fields.get("avgQty");
        double avgPrice = (Double) fields.get("avgPrice");
        String side = fields.get("tranType").toString();
        double profit = (double) Math.round((lpValue - avgPrice) * (double) avgQty);
        if (side.equals("S")) {
            profit = -profit;
        }

        fields.put("profit", profit);

        try {
            if (!this.myUi.pnlPoints && !this.myUi.lot1Pnl) {
                this.pnlModel.setValueAt(lpValue, index, 2);
                this.pnlModel.setValueAt(profit, index, 3);
                this.pnlModel2.setValueAt(profit, index, 5);
            } else if (this.myUi.pnlPoints) {
                this.pnlModel.setValueAt(lpValue, index, 2);
                this.pnlModel.setValueAt(Math.round(profit / (double) avgQty), index, 3);
                this.pnlModel2.setValueAt(Math.round(profit / (double) avgQty), index, 5);
            } else {
                double maxQty = (double) (Integer) this.api.maxQty.get(this.api.oHandler.searchStrike(order_id));
                this.pnlModel.setValueAt((double) Math.round(profit / (double) avgQty * maxQty * 100.0) / 100.0, index, 3);
                this.pnlModel2.setValueAt((double) Math.round(profit / (double) avgQty * maxQty * 100.0) / 100.0, index, 5);
            }
        } catch (Exception var14) {
        }

    }

    public void processRisks(Map<String, Object> fields, double ltp, int posIndex, String order_id) {
        try {
            boolean active = (Boolean) fields.get("active");
            if (!active) {
                return;
            }

            boolean sl = (Boolean) fields.get("sl");
            boolean indexSl = (Boolean) fields.get("indexSl");
            boolean slTrail = (Boolean) fields.get("slTrail");
            double liveSl = (Double) fields.get("liveSl");
            double indexAvg = (Double) fields.get("indexAvg");
            double indexSlPoints = (Double) fields.get("indexSlPoints");
            double slPoints = (Double) fields.get("slPoints");
            double avgPrice = (Double) fields.get("avgPrice");
            int avgQty = (Integer) fields.get("avgQty");
            String cpType = (String) fields.get("cpType");
            String tranType = (String) fields.get("tranType");
            boolean slUpdated = false;
            double indexLp = (Double) this.extractedData.get((String) fields.get("indexToken"));
            if (sl) {
                if (tranType.equals("B")) {
                    if (ltp <= liveSl) {
                        fields.put("active", false);
                        this.exitOrder(fields, tranType);
                        return;
                    }

                    if (slTrail && ltp > liveSl + slPoints) {
                        fields.put("liveSl", ltp - slPoints);
                        liveSl = ltp - slPoints;
                        slUpdated = true;
                    }
                } else if (tranType.equals("S")) {
                    if (ltp >= liveSl) {
                        fields.put("active", false);
                        this.exitOrder(fields, tranType);
                        return;
                    }

                    if (slTrail && ltp < liveSl - slPoints) {
                        fields.put("liveSl", ltp + slPoints);
                        liveSl = ltp + slPoints;
                        slUpdated = true;
                    }
                }
            } else if (indexSl) {
                if ((!cpType.equals("CE") || !tranType.equals("B")) && (!cpType.equals("PE") || !tranType.equals("S"))) {
                    if (cpType.equals("PE") && tranType.equals("B") || cpType.equals("CE") && tranType.equals("S")) {
                        if (indexLp >= liveSl) {
                            fields.put("active", false);
                            this.exitOrder(fields, tranType);
                            return;
                        }

                        if (slTrail && indexLp < liveSl - indexSlPoints) {
                            fields.put("liveSl", indexLp + indexSlPoints);
                            liveSl = indexLp + indexSlPoints;
                            slUpdated = true;
                        }
                    }
                } else {
                    if (indexLp <= liveSl) {
                        fields.put("active", false);
                        this.exitOrder(fields, tranType);
                        return;
                    }

                    if (slTrail && indexLp > liveSl + indexSlPoints) {
                        fields.put("liveSl", indexLp - indexSlPoints);
                        liveSl = indexLp - indexSlPoints;
                        slUpdated = true;
                    }
                }
            }

            boolean target = (Boolean) fields.get("target");
            boolean indexTarget = (Boolean) fields.get("indexTarget");
            double liveTarget = (Double) fields.get("liveTarget");
            boolean trgtTrail = (Boolean) fields.get("trgtTrail");
            boolean trailStarted = (Boolean) fields.get("trailStarted");
            double trailPoints = (Double) fields.get("trailValue");
            if (!trgtTrail) {
                if (target) {
                    if (tranType.equals("B")) {
                        if (ltp >= liveTarget) {
                            fields.put("active", false);
                            this.exitOrder(fields, tranType);
                            return;
                        }
                    } else if (tranType.equals("S") && ltp <= liveTarget) {
                        fields.put("active", false);
                        this.exitOrder(fields, tranType);
                        return;
                    }
                } else if (indexTarget) {
                    if (cpType.equals("CE") && tranType.equals("B") || cpType.equals("PE") && tranType.equals("S")) {
                        if (indexLp >= liveTarget) {
                            fields.put("active", false);
                            this.exitOrder(fields, tranType);
                            return;
                        }
                    } else if ((cpType.equals("PE") && tranType.equals("B") || cpType.equals("CE") && tranType.equals("S")) && indexLp <= liveTarget) {
                        fields.put("active", false);
                        this.exitOrder(fields, tranType);
                        return;
                    }
                }
            } else if (trgtTrail) {
                if (!trailStarted) {
                    if (target) {
                        if (tranType.equals("B")) {
                            if (ltp >= liveTarget) {
                                fields.put("liveTarget", ltp);
                                fields.put("liveSl", ltp - trailPoints);
                                fields.put("trailStarted", true);
                                liveTarget = ltp;
                                liveSl = ltp - trailPoints;
                                slUpdated = true;
                            }
                        } else if (tranType.equals("S") && ltp <= liveTarget) {
                            fields.put("liveTarget", ltp);
                            fields.put("liveSl", ltp + trailPoints);
                            fields.put("trailStarted", true);
                            liveTarget = ltp;
                            liveSl = ltp + trailPoints;
                            slUpdated = true;
                        }
                    } else if (indexTarget) {
                        if ((!cpType.equals("CE") || !tranType.equals("B")) && (!cpType.equals("PE") || !tranType.equals("S"))) {
                            if ((cpType.equals("PE") && tranType.equals("B") || cpType.equals("CE") && tranType.equals("S")) && indexLp <= liveTarget) {
                                fields.put("liveTarget", indexLp);
                                fields.put("liveSl", indexLp + trailPoints);
                                fields.put("trailStarted", true);
                                liveTarget = indexLp;
                                liveSl = indexLp + trailPoints;
                                slUpdated = true;
                            }
                        } else if (indexLp >= liveTarget) {
                            fields.put("liveTarget", indexLp);
                            fields.put("liveSl", indexLp - trailPoints);
                            fields.put("trailStarted", true);
                            liveTarget = indexLp;
                            liveSl = indexLp - trailPoints;
                            slUpdated = true;
                        }
                    }
                } else if (target) {
                    if (tranType.equals("B")) {
                        if (ltp > liveTarget) {
                            fields.put("liveTarget", ltp);
                            fields.put("liveSl", ltp - trailPoints);
                            liveTarget = ltp;
                            liveSl = ltp - trailPoints;
                            slUpdated = true;
                        } else if (ltp <= liveSl) {
                            fields.put("active", false);
                            this.exitOrder(fields, tranType);
                            return;
                        }
                    } else if (tranType.equals("S")) {
                        if (ltp < liveTarget) {
                            fields.put("liveTarget", ltp);
                            fields.put("liveSl", ltp + trailPoints);
                            liveTarget = ltp;
                            liveSl = ltp + trailPoints;
                            slUpdated = true;
                        } else if (ltp >= liveSl) {
                            fields.put("active", false);
                            this.exitOrder(fields, tranType);
                            return;
                        }
                    }
                } else if (indexTarget) {
                    if ((!cpType.equals("CE") || !tranType.equals("B")) && (!cpType.equals("PE") || !tranType.equals("S"))) {
                        if (cpType.equals("PE") && tranType.equals("B") || cpType.equals("CE") && tranType.equals("S")) {
                            if (indexLp < liveTarget) {
                                fields.put("liveTarget", indexLp);
                                fields.put("liveSl", indexLp + trailPoints);
                                liveTarget = indexLp;
                                liveSl = indexLp + trailPoints;
                                slUpdated = true;
                            } else if (indexLp >= liveSl) {
                                fields.put("active", false);
                                this.exitOrder(fields, tranType);
                                return;
                            }
                        }
                    } else if (indexLp > liveTarget) {
                        fields.put("liveTarget", indexLp);
                        fields.put("liveSl", indexLp - trailPoints);
                        liveTarget = indexLp;
                        liveSl = indexLp - trailPoints;
                        slUpdated = true;
                    } else if (indexLp <= liveSl) {
                        fields.put("active", false);
                        this.exitOrder(fields, tranType);
                        return;
                    }
                }
            }

            if (!trailStarted) {
                double avgPoints;
                if (this.myUi.lock.isSelected()) {
                    if (tranType.equals("B")) {
                        if (this.myUi.jComboBox13.getSelectedItem().toString().equals("PNTS")) {
                            if (ltp >= avgPrice + (Double) this.myUi.lockTarget.getValue() && (!sl || sl && liveSl < avgPrice + (Double) this.myUi.lockPoints.getValue())) {
                                liveSl = avgPrice + (Double) this.myUi.lockPoints.getValue();
                                fields.put("liveSl", liveSl);
                                fields.put("sl", true);
                                fields.put("locked", true);
                                slUpdated = true;
                            }
                        } else {
                            avgPoints = (ltp - avgPrice) * (double) avgQty;
                            if ((Double) this.myUi.lockTarget.getValue() <= avgPoints && (!sl || sl && liveSl < (Double) this.myUi.lockPoints.getValue() / (double) avgQty + avgPrice)) {
                                liveSl = (Double) this.myUi.lockPoints.getValue() / (double) avgQty + avgPrice;
                                fields.put("liveSl", liveSl);
                                fields.put("sl", true);
                                fields.put("locked", true);
                                slUpdated = true;
                            }
                        }
                    } else if (tranType.equals("S")) {
                        if (this.myUi.jComboBox13.getSelectedItem().toString().equals("PNTS")) {
                            if (ltp <= avgPrice - (Double) this.myUi.lockTarget.getValue() && (!sl || sl && liveSl < avgPrice - (Double) this.myUi.lockPoints.getValue())) {
                                liveSl = avgPrice - (Double) this.myUi.lockPoints.getValue();
                                fields.put("liveSl", liveSl);
                                fields.put("sl", true);
                                fields.put("locked", true);
                                slUpdated = true;
                            }
                        } else {
                            avgPoints = (avgPrice - ltp) * (double) avgQty;
                            if ((Double) this.myUi.lockTarget.getValue() <= avgPoints && (!sl || sl && liveSl < avgPrice - (Double) this.myUi.lockPoints.getValue() / (double) avgQty)) {
                                liveSl = avgPrice - (Double) this.myUi.lockPoints.getValue() / (double) avgQty;
                                fields.put("liveSl", liveSl);
                                fields.put("sl", true);
                                fields.put("locked", true);
                                slUpdated = true;
                            }
                        }
                    }
                }

                if (this.myUi.jCheckBox13.isSelected()) {
                    avgPoints = (Double) this.myUi.jSpinner16.getValue();
                    boolean addAvg = false;
                    if (fields.get("avgedLtp") != null) {
                        avgPrice = (Double) fields.get("avgedLtp");
                    }

                    switch (this.myUi.jComboBox6.getSelectedItem().toString()) {
                        case ">=":
                            if (ltp >= avgPrice + avgPoints) {
                                addAvg = true;
                            }
                            break;
                        case "<=":
                            if (ltp <= avgPrice - avgPoints) {
                                addAvg = true;
                            }
                            break;
                        default:
                            if (ltp <= avgPrice - avgPoints) {
                                addAvg = true;
                            } else if (ltp >= avgPrice + avgPoints) {
                                addAvg = true;
                            }
                    }

                    if (addAvg && !this.myUi.avgIndex.equals("None") && this.isAvgStopped(indexLp, ltp, cpType, tranType) && (Integer) fields.get("initQty") > 0 && (double) (Integer) fields.get("avgedOrders") < (Double) this.myUi.jSpinner21.getValue()) {
                        fields.put("avgedOrders", (Integer) fields.get("avgedOrders") + 1);
                        fields.put("avgedLtp", ltp);
                        if (order_id.endsWith("PAPER")) {
                            this.myUi.handlePaper(fields.get("strike").toString(), fields.get("tranType").toString(), (Integer) fields.get("initQty"), (Double) fields.get("avgPrice"), fields.get("token").toString(), fields.get("exch").toString());
                        } else {
                            this.avgOrder(fields, fields.get("tranType").toString(), (Integer) fields.get("initQty"));
                        }
                    }
                }
            }

            if (slUpdated) {
                this.pnlModel.setValueAt(liveSl, posIndex, 7);
                this.pnlModel.setValueAt(liveTarget, posIndex, 11);
                this.pnlModel2.setValueAt(liveSl, posIndex, 3);
                this.pnlModel2.setValueAt(liveTarget, posIndex, 7);
            }
        } catch (Exception var39) {
            var39.printStackTrace();
        }

    }

    public boolean isAvgStopped(double indexLtp, double ltp, String cpType, String tranType) {
        if (this.myUi.avgIndex.equals("INDEX")) {
            if (this.myUi.avgDir.equals("<=")) {
                if (indexLtp >= this.myUi.avgIndexValue) {
                    return true;
                }
            } else if (this.myUi.avgDir.equals(">=") && indexLtp <= this.myUi.avgIndexValue) {
                return true;
            }
        } else if (this.myUi.avgDir.equals("<=")) {
            if (ltp >= this.myUi.avgIndexValue) {
                return true;
            }
        } else if (this.myUi.avgDir.equals(">=") && ltp <= this.myUi.avgIndexValue) {
            return true;
        }

        return false;
    }

    public void exitOrder(Map<String, Object> fields, String tranType) {
        String side = "B";
        if (tranType.equals("B")) {
            side = "S";
        }

        try {
            String strike = (String) fields.get("strike");
            String exch = (String) fields.get("exch");
            String orderType = "MKT";
            double price = 0.0;
            String product = (String) fields.get("product");
            if (product.equals("PAPER")) {
                this.pnlData.remove(strike + product);
                this.api.oHandler.updatePnlTable();
            } else {
                if (this.api.myUi.jCheckBox12.isSelected()) {
                    price = this.api.myUi.getLimit(strike, exch, side);
                    if (price != 0.0) {
                        orderType = "LMT";
                    }
                }

                int freeze = (Integer) this.api.maxFreeze.get(this.api.oHandler.searchStrike(strike));
                int qty = (Integer) fields.get("avgQty");
                int n = qty / freeze;
                int bal = qty % freeze;

                for (int j = 0; j < n; ++j) {
                    this.api.placeOrder(strike, side, (String) fields.get("product"), exch, orderType, freeze, price);
                }

                if (bal > 0) {
                    this.api.placeOrder(strike, side, product, exch, orderType, bal, price);
                }
            }
        } catch (IOException var15) {
            Logger.getLogger(WebSocketClient.class.getName()).log(Level.SEVERE, (String) null, var15);
        }

    }

    public void avgOrder(Map<String, Object> fields, String side, int qty) {
        System.out.println("AVG RECEIVED");

        try {
            String strike = (String) fields.get("strike");
            String exch = (String) fields.get("exch");
            String orderType = "MKT";
            double price = 0.0;
            String product = (String) fields.get("product");
            if (this.api.myUi.jCheckBox11.isSelected()) {
                price = this.api.myUi.getLimit(strike, exch, side);
                if (price != 0.0) {
                    orderType = "LMT";
                }
            }

            int freeze = (Integer) this.api.maxFreeze.get(this.api.oHandler.searchStrike(strike));
            int n = qty / freeze;
            int bal = qty % freeze;

            for (int j = 0; j < n; ++j) {
                this.api.placeOrder(strike, side, product, exch, orderType, freeze, price);
            }

            if (bal > 0) {
                this.api.placeOrder(strike, side, product, exch, orderType, bal, price);
            }
        } catch (IOException var14) {
            Logger.getLogger(WebSocketClient.class.getName()).log(Level.SEVERE, (String) null, var14);
        }

    }
}
