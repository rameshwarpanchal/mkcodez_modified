//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ram.code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class miniPanel extends JFrame {
    controlPanel myUi;
    public JButton jButton1;
    public JButton jButton2;
    public JButton jButton3;
    public JButton jButton4;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    public JSpinner jSpinner1;
    public JSpinner jSpinner2;
    public JTable jTable1;
    public JTextField jTextField1;
    public JTextField jTextField2;

    public miniPanel(controlPanel myUi) {
        this.initComponents();
        this.myUi = myUi;
        TableColumnModel columnModel = this.jTable1.getColumnModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(0);
        this.jTable1.getColumnModel().getColumn(2).setPreferredWidth(20);
        this.jTable1.getColumnModel().getColumn(1).setPreferredWidth(40);
        this.jTable1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        this.jTable1.getColumnModel().getColumn(4).setPreferredWidth(20);
        this.jTable1.getColumnModel().getColumn(6).setPreferredWidth(20);
        this.jTable1.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        this.jTable1.getColumnModel().getColumn(8).setPreferredWidth(20);
        this.jTable1.getColumnModel().getColumn(9).setPreferredWidth(20);
        columnModel.getColumn(1).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(1).setCellEditor(new ButtonEditor(new JCheckBox()));
        columnModel.getColumn(2).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox()));
        columnModel.getColumn(4).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
        columnModel.getColumn(6).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()));
        columnModel.getColumn(8).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(8).setCellEditor(new ButtonEditor(new JCheckBox()));
        columnModel.getColumn(9).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(9).setCellEditor(new ButtonEditor(new JCheckBox()));
        this.jTextField2.setBackground(Color.black);
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.jButton1 = new JButton();
        this.jTextField1 = new JTextField();
        this.jScrollPane1 = new JScrollPane();
        this.jTable1 = new JTable();
        this.jButton3 = new JButton();
        this.jSpinner1 = new JSpinner();
        this.jButton2 = new JButton();
        this.jSpinner2 = new JSpinner();
        this.jTextField2 = new JTextField();
        this.jButton4 = new JButton();
        this.setDefaultCloseOperation(0);
        this.setAlwaysOnTop(true);
        this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
        this.jButton1.setText("BUY");
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                miniPanel.this.jButton1ActionPerformed(evt);
            }
        });
        this.jTextField1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                miniPanel.this.jTextField1KeyReleased(evt);
            }
        });
        this.jTable1.setModel(new DefaultTableModel(new Object[0][], new String[]{"ID", "EXIT", "-", "SL", "+", "PNL", "-", "TARGET", "+", "H/L"}));
        this.jTable1.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                miniPanel.this.jTable1MouseReleased(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.jTable1);
        this.jButton3.setText("HOME");
        this.jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                miniPanel.this.jButton3ActionPerformed(evt);
            }
        });
        this.jSpinner1.setModel(new SpinnerNumberModel(0.0, (Comparable)null, (Comparable)null, 1.0));
        this.jSpinner1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                miniPanel.this.jSpinner1StateChanged(evt);
            }
        });
        this.jButton2.setText("SELL");
        this.jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                miniPanel.this.jButton2ActionPerformed(evt);
            }
        });
        this.jSpinner2.setModel(new SpinnerNumberModel(1, 1, (Comparable)null, 1));
        this.jSpinner2.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                miniPanel.this.jSpinner2StateChanged(evt);
            }
        });
        this.jButton4.setText("EXIT");
        this.jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                miniPanel.this.jButton4ActionPerformed(evt);
            }
        });
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jScrollPane1, -2, 0, 32767).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jTextField1, -2, 190, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSpinner2)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jButton3, -2, 66, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSpinner1, -2, 80, -2).addGap(10, 10, 10).addComponent(this.jTextField2, -2, 100, -2))).addGap(6, 6, 6).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jButton1, -2, 56, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jButton2, -2, 66, -2)).addComponent(this.jButton4, -2, 134, -2)))).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jTextField1, -2, -1, -2).addComponent(this.jButton1).addComponent(this.jSpinner2, -2, -1, -2).addComponent(this.jButton2)).addGap(6, 6, 6).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jButton3).addComponent(this.jSpinner1, -2, -1, -2).addComponent(this.jTextField2, -2, -1, -2).addComponent(this.jButton4)).addGap(6, 6, 6).addComponent(this.jScrollPane1, -2, 128, -2).addContainerGap()));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.jPanel1, -2, -1, -2).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 6, 32767).addComponent(this.jPanel1, -2, -1, -2)));
        this.pack();
        this.setLocationRelativeTo((Component)null);
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        this.myUi.setVisible(true);
        this.myUi.api.oHandler.updatePnlTable();
    }

    private void jTextField1KeyReleased(KeyEvent evt) {
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        if (this.myUi.api.oHandler.getCpType(this.jTextField1.getText()).equals("CE")) {
            this.myUi.ceName.setText(this.jTextField1.getText());
            if (!this.myUi.paper) {
                this.myUi.buyTrigger("B");
            } else {
                this.myUi.buyPaper("B");
            }
        } else if (this.myUi.api.oHandler.getCpType(this.jTextField1.getText()).equals("PE")) {
            this.myUi.peName.setText(this.jTextField1.getText());
            if (!this.myUi.paper) {
                this.myUi.buyTrigger2("B");
            } else {
                this.myUi.buyPaper2("B");
            }
        } else {
            JOptionPane.showMessageDialog(this, "STRIKE ERROR");
        }

    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        if (this.myUi.api.oHandler.getCpType(this.jTextField1.getText()).equals("CE")) {
            this.myUi.ceName.setText(this.jTextField1.getText());
            if (!this.myUi.paper) {
                this.myUi.buyTrigger("S");
            } else {
                this.myUi.buyPaper("S");
            }
        } else if (this.myUi.api.oHandler.getCpType(this.jTextField1.getText()).equals("PE")) {
            this.myUi.peName.setText(this.jTextField1.getText());
            if (!this.myUi.paper) {
                this.myUi.buyTrigger2("S");
            } else {
                this.myUi.buyPaper2("S");
            }
        } else {
            JOptionPane.showMessageDialog(this, "STRIKE ERROR");
        }

    }

    private void jSpinner1StateChanged(ChangeEvent evt) {
        this.myUi.jSpinner8.setValue(this.jSpinner1.getValue());
        this.myUi.jSpinner6.setValue(this.jSpinner1.getValue());
    }

    private void jSpinner2StateChanged(ChangeEvent evt) {
        this.myUi.ceQty.setValue(this.jSpinner2.getValue());
        this.myUi.peQty.setValue(this.jSpinner2.getValue());
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        this.myUi.exitAll();
    }

    private void jTable1MouseReleased(MouseEvent evt) {
        DefaultTableModel tempModel = (DefaultTableModel)this.jTable1.getModel();
        String order_id = tempModel.getValueAt(this.jTable1.getSelectedRow(), 0).toString();
        Map tempData;
        switch (this.jTable1.getSelectedColumn()) {
            case 0:
                if (this.myUi.api.ws.pnlData.containsKey(order_id)) {
                    tempData = (Map)this.myUi.api.ws.pnlData.get(order_id);
                    JTextField quantityField = new JTextField("" + String.valueOf(tempData.get("avgQty")));
                    JTextField limitPriceField = new JTextField("0.0");
                    JPanel panel = new JPanel(new GridLayout(3, 2));
                    panel.add(new JLabel("Quantity:"));
                    panel.add(quantityField);
                    panel.add(new JLabel("Limit Price:"));
                    panel.add(limitPriceField);
                    String[] options = new String[]{"EXIT", "MULTI TARGETS"};
                    int result = JOptionPane.showOptionDialog(this, panel, "Enter Order Details", -1, -1, (Icon)null, options, options[0]);
                    if (result == 0) {
                        try {
                            int quantity = Integer.parseInt(quantityField.getText());
                            double limitPrice = Double.parseDouble(limitPriceField.getText());
                            this.myUi.exitOrder(order_id, quantity, limitPrice);
                        } catch (NumberFormatException var13) {
                            JOptionPane.showMessageDialog((Component)null, "Invalid input. Please enter valid numbers for quantity and limit price.", "Input Error", 0);
                        }
                    } else if (result == 1) {
                        this.myUi.addTargets(order_id, this.jTable1);
                    }
                }
                break;
            case 1:
                if (this.myUi.api.ws.pnlData.containsKey(order_id)) {
                    this.myUi.exitOrder(order_id, 0, 0.0);
                }
                break;
            case 2:
                this.updateSl(evt, order_id, "liveSl", -1);
                break;
            case 3:
                this.pnlTableMouseClicked2(evt, this.jTable1, order_id, "sl", "indexSl", "liveSl", true);
                break;
            case 4:
                this.updateSl(evt, order_id, "liveSl", 1);
            case 5:
            default:
                break;
            case 6:
                this.updateSl(evt, order_id, "liveTarget", -1);
                break;
            case 7:
                this.pnlTableMouseClicked2(evt, this.jTable1, order_id, "target", "indexTarget", "liveTarget", false);
                break;
            case 8:
                this.updateSl(evt, order_id, "liveTarget", 1);
                break;
            case 9:
                if (this.myUi.api.ws.pnlData.containsKey(order_id)) {
                    tempData = (Map)this.myUi.api.ws.pnlData.get(order_id);
                    JDialog dialog = new JDialog(this, "Update Position", true);
                    JComboBox<String> comboBox = new JComboBox(new String[]{"SL", "INDEX SL"});
                    JComboBox<String> comboBox2 = new JComboBox(new String[]{"1", "2", "3", "5", "10", "15", "30"});
                    JCheckBox candleTrial = new JCheckBox("AUTO TRAIL");
                    candleTrial.setSelected((Boolean)tempData.get("candleTrail"));
                    comboBox.setSelectedItem("SL");
                    comboBox2.setSelectedItem(this.myUi.tf);
                    if ((Boolean)tempData.get("indexSl")) {
                        comboBox.setSelectedItem("INDEX SL");
                    }

                    JButton updateButton = new JButton("Update");
                    updateButton.addActionListener((e) -> {
                        this.prevLowSl(evt, order_id, comboBox, comboBox2, candleTrial);
                    });
                    JPanel panel = new JPanel(new GridLayout(3, 2));
                    panel.add(new JLabel("RISK TYPE"));
                    panel.add(comboBox);
                    panel.add(new JLabel("TIMEFRAME"));
                    panel.add(comboBox2);
                    panel.add(candleTrial);
                    panel.add(updateButton);
                    dialog.setLayout(new BorderLayout());
                    dialog.add(panel, "Center");
                    dialog.setSize(300, 100);
                    dialog.setLocationRelativeTo(this.jTable1);
                    dialog.setVisible(true);
                }
        }

    }

    public void prevLowSl(MouseEvent evt, String order_id, JComboBox slType, JComboBox slTf, JCheckBox check1) {
        Map<String, Object> tempData = (Map)this.myUi.api.ws.pnlData.get(order_id);
        String indexType = slType.getSelectedItem().toString();
        int tf1 = Integer.parseInt(slTf.getSelectedItem().toString());
        this.myUi.tf = slTf.getSelectedItem().toString();
        String index = tempData.get("index").toString();
        String strike;
        if (indexType.equals("SL")) {
            strike = tempData.get("strike").toString();
            String exch = "NFO";
            if (strike.contains("SENSEX")) {
                exch = "BFO";
            }

            String token;
            if (this.myUi.api.allTokens.get(strike) == null) {
                token = this.myUi.api.oHandler.searchStrike(strike);
                this.myUi.allTokens.put(strike, token);
                this.myUi.api.ws.subscribe(exch + "|" + token);
            } else {
                token = (String)this.myUi.api.allTokens.get(strike);
            }

            if (this.myUi.api.hisData.get(token) == null) {
                this.myUi.api.historicalData(exch, token);
            }

            List<Map<String, Object>> jsonArray = this.myUi.api.pnlCalculator.ev.parseData(strike, tf1);
            Map<String, Object> record = (Map)jsonArray.get(jsonArray.size() - 2);
            double slValue = Double.parseDouble(record.get("intl").toString());
            if (tempData.get("tranType").toString().equals("S")) {
                slValue = Double.parseDouble(record.get("inth").toString());
            }

            tempData.put("liveSl", slValue);
            tempData.put("sl", true);
            tempData.put("indexSl", false);
            tempData.put("tf", tf1);
            tempData.put("candleTrail", check1.isSelected());
            this.myUi.api.ws.timeCache.put(tempData.get("token").toString(), tf1);
            DefaultTableModel model = (DefaultTableModel)this.jTable1.getModel();
            model.setValueAt(slValue, this.jTable1.rowAtPoint(evt.getPoint()), 3);
        } else if (indexType.equals("INDEX SL")) {
            strike = (String)this.myUi.api.pnlCalculator.ev.indexShorts.entrySet().stream().filter((entry) -> {
                return ((String)entry.getValue()).equals(index);
            }).map(Map.Entry::getKey).findFirst().orElse((null));
            Map<String, String> niftyMap = (Map)this.myUi.indexToken.get(index);
            if (niftyMap != null) {
                List<Map<String, Object>> jsonArray = this.myUi.api.pnlCalculator.ev.parseData(strike, tf1);
                Map<String, Object> record = (Map)jsonArray.get(jsonArray.size() - 2);
                double high = Double.parseDouble(record.get("inth").toString());
                double low = Double.parseDouble(record.get("intl").toString());
                double slValue = low;
                String cpType = tempData.get("cpType").toString();
                String tranType = tempData.get("tranType").toString();
                if (tranType.equals("S") && cpType.equals("CE")) {
                    slValue = high;
                } else if (tranType.equals("S") && cpType.equals("PE")) {
                    slValue = low;
                } else if (tranType.equals("B") && cpType.equals("PE")) {
                    slValue = high;
                }

                tempData.put("liveSl", slValue);
                tempData.put("indexSl", true);
                tempData.put("sl", false);
                tempData.put("tf", tf1);
                tempData.put("candleTrail", check1.isSelected());
                this.myUi.api.ws.timeCache.put(tempData.get("token").toString(), tf1);
                DefaultTableModel model = (DefaultTableModel)this.jTable1.getModel();
                model.setValueAt(slValue, this.jTable1.rowAtPoint(evt.getPoint()), 3);
            }
        }

    }

    public void pnlTableMouseClicked2(MouseEvent evt, JTable pnlTable11, String order_id, String check1, String check2, String check1Value, boolean sl) {
        int row = pnlTable11.rowAtPoint(evt.getPoint());
        int column = pnlTable11.columnAtPoint(evt.getPoint());
        if (this.myUi.api.ws.pnlData.containsKey(order_id)) {
            Map<String, Object> tempData = (Map)this.myUi.api.ws.pnlData.get(order_id);
            String checked = "NONE";
            double checkValue = (Double)tempData.get(check1Value);
            double trailValue = (Double)tempData.get("trailValue");
            double avgPrice = (Double)tempData.get("avgPrice");
            double liveSl = (Double)tempData.get("liveSl");
            if ((Boolean)tempData.get(check1)) {
                checked = check1;
            } else if ((Boolean)tempData.get(check2)) {
                checked = check2;
            }

            if (row >= 0 && column >= 0) {
                JDialog dialog = new JDialog(this, "Update Position", true);
                JComboBox<String> comboBox = new JComboBox(new String[]{"NONE", check1, check2});
                comboBox.setSelectedItem(checked);
                JSpinner spinner = new JSpinner(new SpinnerNumberModel(checkValue, 0.0, 1000000.0, 1.0));
                JSpinner spinner2 = new JSpinner(new SpinnerNumberModel(trailValue, 0.0, 1000.0, 1.0));
                int height = 125;
                int rows = 3;
                if (!sl) {
                    height = 150;
                    rows = 4;
                }

                JPanel panel = new JPanel(new GridLayout(rows, 2));
                panel.add(new JLabel("Transaction Type:"));
                panel.add(comboBox);
                panel.add(new JLabel("PRICE"));
                panel.add(spinner);
                JCheckBox slTrail = new JCheckBox("SL TRAIL");
                slTrail.setSelected((Boolean)tempData.get("slTrail"));
                JCheckBox trgtTrail = new JCheckBox("TARGET TRAIL");
                trgtTrail.setSelected((Boolean)tempData.get("trgtTrail"));
                if (sl) {
                    panel.add(new JLabel("SL TRAIL"));
                    panel.add(slTrail);
                } else {
                    panel.add(new JLabel("TARGET TRAIL"));
                    panel.add(trgtTrail);
                    panel.add(new JLabel("TRAIL POINTS"));
                    panel.add(spinner2);
                }

                JButton updateButton = new JButton("Update");
                updateButton.addActionListener((e) -> {
                    String riskType = (String)comboBox.getSelectedItem();
                    double value = (Double)spinner.getValue();
                    if (riskType.equals("NONE")) {
                        tempData.put(check1, false);
                        tempData.put(check2, false);
                        tempData.put(check1Value, 0.0);
                        value = 0.0;
                    } else {
                        tempData.put(riskType, true);
                        tempData.put(check1Value, value);
                    }

                    if (sl) {
                        tempData.put("slTrail", slTrail.isSelected());
                        tempData.put("slPoints", Math.abs(avgPrice - liveSl));
                    } else {
                        tempData.put("trgtTrail", trgtTrail.isSelected());
                        tempData.put("trailValue", spinner2.getValue());
                    }

                    DefaultTableModel model = (DefaultTableModel)pnlTable11.getModel();
                    model.setValueAt(value, row, column);
                    dialog.dispose();
                });
                dialog.setLayout(new BorderLayout());
                dialog.add(panel, "Center");
                dialog.add(updateButton, "South");
                dialog.setSize(300, height);
                dialog.setLocationRelativeTo(this);
                dialog.setVisible(true);
            }
        }

    }

    public void updateSl(MouseEvent evt, String order_id, String slType, int slDirection) {
        if (this.myUi.api.ws.pnlData.containsKey(order_id)) {
            Map<String, Object> tempData = (Map)this.myUi.api.ws.pnlData.get(order_id);
            int row = this.jTable1.rowAtPoint(evt.getPoint());
            int column = this.jTable1.columnAtPoint(evt.getPoint());
            double liveSl = (Double)tempData.get(slType);
            liveSl += (double)slDirection;
            if (liveSl < 0.0) {
                liveSl = 0.0;
            }

            tempData.put(slType, liveSl);
            DefaultTableModel model = (DefaultTableModel)this.jTable1.getModel();
            model.setValueAt(liveSl, row, column - slDirection);
        }

    }
}
