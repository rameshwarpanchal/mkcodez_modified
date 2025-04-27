//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ram.code;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class controlPanel extends JFrame {
    NorenApi api = null;
    String posIndex = "NIFTY";
    String avgIndex = "None";
    String avgDir = "";
    boolean lowIndex = false;
    boolean upIndex = false;
    boolean lowPnl = false;
    boolean upPnl = false;
    boolean mtmSL = false;
    boolean mtmTARGET = false;
    boolean shortcut = false;
    boolean paper = false;
    boolean pnlPoints = false;
    boolean lot1Pnl = false;
    boolean disColors = false;
    double lowIndexValue = 0.0;
    double upIndexValue = 0.0;
    double lowPnlValue = 0.0;
    double upPnlValue = 0.0;
    double mtmSLValue = 0.0;
    double mtmTARGETValue = 0.0;
    double avgIndexValue = 0.0;
    String currIndex = "";
    String expiry = "";
    String currToken = "";
    String tf = "1";
    Map<String, Map<String, String>> indexToken;
    Map<String, String> allTokens;
    Map<String, Double> extractedData;
    DefaultTableModel optionModel = null;
    DefaultTableModel allPosModel = null;
    Map<String, int[]> tokenMap;
    Map<String, Map<String, Object>> riskFields = new HashMap();
    Map<String, String> defaultStrats;
    Map<Integer, Double> ceLtps;
    Map<Integer, Double> peLtps;
    private static final String JDBC_URL = "jdbc:hsqldb:file:db/hsqldbexample;shutdown=true";
    private static final String USER = "SA";
    private static final String PASSWORD = "";
    miniPanel mini;
    hideUi hideui;
    public transient JTable allPosTable;
    public JTextField ceName;
    public JSpinner ceQty;
    public JLabel currPnl;
    private JButton jButton1;
    private JButton jButton10;
    private JButton jButton11;
    private JButton jButton12;
    private JButton jButton13;
    private JButton jButton14;
    private JButton jButton15;
    private JButton jButton16;
    private JButton jButton17;
    private JButton jButton18;
    private JButton jButton19;
    private JButton jButton2;
    private JButton jButton20;
    private JButton jButton21;
    private JButton jButton22;
    private JButton jButton23;
    private JButton jButton24;
    private JButton jButton25;
    private JButton jButton26;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JButton jButton8;
    private JButton jButton9;
    private JCheckBox jCheckBox1;
    private JCheckBox jCheckBox10;
    public JCheckBox jCheckBox11;
    public JCheckBox jCheckBox12;
    public JCheckBox jCheckBox13;
    public JCheckBox jCheckBox14;
    public JCheckBox jCheckBox15;
    public JCheckBox jCheckBox16;
    public JCheckBox jCheckBox17;
    private JCheckBox jCheckBox2;
    private JCheckBox jCheckBox3;
    private JCheckBox jCheckBox4;
    private JCheckBox jCheckBox5;
    private JCheckBox jCheckBox6;
    private JCheckBox jCheckBox7;
    private JCheckBox jCheckBox8;
    private JCheckBox jCheckBox9;
    public JComboBox<String> jComboBox1;
    private JComboBox<String> jComboBox10;
    private JComboBox<String> jComboBox11;
    private JComboBox<String> jComboBox12;
    public JComboBox<String> jComboBox13;
    public JComboBox<String> jComboBox2;
    private JComboBox<String> jComboBox3;
    private JComboBox<String> jComboBox4;
    private JComboBox<String> jComboBox5;
    public JComboBox<String> jComboBox6;
    public JComboBox<String> jComboBox7;
    private JComboBox<String> jComboBox8;
    private JComboBox<String> jComboBox9;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JPanel jPanel1;
    private JPanel jPanel10;
    private JPanel jPanel11;
    private JPanel jPanel12;
    private JPanel jPanel13;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JScrollPane jScrollPane5;
    private JScrollPane jScrollPane6;
    private JSlider jSlider1;
    private JSpinner jSpinner1;
    private JSpinner jSpinner10;
    private JSpinner jSpinner11;
    private JSpinner jSpinner12;
    private JSpinner jSpinner13;
    private JSpinner jSpinner14;
    public JSpinner jSpinner15;
    public JSpinner jSpinner16;
    public JSpinner jSpinner17;
    public JSpinner jSpinner18;
    public JSpinner jSpinner19;
    private JSpinner jSpinner2;
    public JSpinner jSpinner20;
    public JSpinner jSpinner21;
    private JSpinner jSpinner22;
    private JSpinner jSpinner3;
    private JSpinner jSpinner4;
    private JSpinner jSpinner5;
    public JSpinner jSpinner6;
    private JSpinner jSpinner7;
    public JSpinner jSpinner8;
    private JSpinner jSpinner9;
    private JTabbedPane jTabbedPane1;
    private JTable jTable2;
    private JTable jTable3;
    private JTextField jTextField1;
    private JTextField jTextField2;
    public JCheckBox lock;
    public JSpinner lockPoints;
    public JSpinner lockTarget;
    public JCheckBox mtmSl;
    public JSpinner mtmSlValue;
    public JCheckBox mtmTarget;
    public JSpinner mtmTargetValue;
    public JTable optionChain;
    public static JTable orderTable;
    public JTextField peName;
    public JSpinner peQty;
    public static JTable pnlTable;
    private JCheckBox slTrail;
    private JCheckBox trgtTrail;

    public controlPanel(NorenApi api) {
        this.api = api;
        this.indexToken = this.api.indexToken;
        this.allTokens = this.api.allTokens;
        this.extractedData = this.api.extractedData;
        this.tokenMap = new HashMap();
        this.ceLtps = new HashMap();
        this.peLtps = new HashMap();
        this.initComponents();
        this.optionModel = (DefaultTableModel) this.optionChain.getModel();
        this.allPosModel = (DefaultTableModel) this.allPosTable.getModel();
        TableColumnModel columnModel = this.jTable2.getColumnModel();

        int i;
        for (i = 6; i <= 8; ++i) {
            columnModel.getColumn(i).setCellRenderer(new ButtonRenderer());
            columnModel.getColumn(i).setCellEditor(new ButtonEditor(new JCheckBox()));
        }

        columnModel = orderTable.getColumnModel();

        for (i = 4; i <= 5; ++i) {
            columnModel.getColumn(i).setCellRenderer(new ButtonRenderer());
            columnModel.getColumn(i).setCellEditor(new ButtonEditor(new JCheckBox()));
        }

        columnModel = this.allPosTable.getColumnModel();
        columnModel.getColumn(5).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));
        columnModel = pnlTable.getColumnModel();
        columnModel.getColumn(4).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
        columnModel.getColumn(6).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()));
        columnModel.getColumn(8).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(8).setCellEditor(new ButtonEditor(new JCheckBox()));
        columnModel.getColumn(10).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(10).setCellEditor(new ButtonEditor(new JCheckBox()));
        columnModel.getColumn(12).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(12).setCellEditor(new ButtonEditor(new JCheckBox()));
        columnModel.getColumn(13).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(13).setCellEditor(new ButtonEditor(new JCheckBox()));
        this.tableColor();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(0);
        pnlTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        pnlTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        pnlTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        pnlTable.getColumnModel().getColumn(6).setPreferredWidth(20);
        pnlTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        pnlTable.getColumnModel().getColumn(8).setPreferredWidth(20);
        pnlTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        pnlTable.getColumnModel().getColumn(10).setPreferredWidth(20);
        pnlTable.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
        pnlTable.getColumnModel().getColumn(12).setPreferredWidth(20);
        pnlTable.getColumnModel().getColumn(12).setPreferredWidth(20);

        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new GlobalKeyboardShortcut(this));
        } catch (NativeHookException var5) {
            Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var5);
        }

        this.mini = new miniPanel(this);
        this.hideui = new hideUi(this);
        this.applySettings();
    }

    public void defaultStrategies() {
        this.defaultStrats = new HashMap();
        this.defaultStrats.put("CE LTP", "ltp('" + this.ceName.getText() + "')");
        this.defaultStrats.put("PE LTP", "ltp('" + this.peName.getText() + "')");
        this.defaultStrats.put("NIFTY LTP", "nClose()");
        this.defaultStrats.put("BANK LTP", "bnClose()");
        this.defaultStrats.put("FINNIFTY LTP", "fClose()");
        this.defaultStrats.put("MIDCPNIFTY LTP", "mClose()");
        this.defaultStrats.put("SENSEX LTP", "sClose()");
        this.defaultStrats.put("NIFTY FUT LTP", "nfClose()");
        this.defaultStrats.put("BANK FUT LTP", "bnfClose()");
        this.defaultStrats.put("FINNIFTY FUT LTP", "ffClose()");
        this.defaultStrats.put("MIDCPNIFTY FUT LTP", "mfClose()");
    }

    public void tableColor() {
        pnlTable.setDefaultRenderer(Object.class, new TableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                label.setText(value != null ? value.toString() : "");
                label.setOpaque(true);
                if (!controlPanel.this.disColors && column == 3 && !value.equals("")) {
                    try {
                        if ((Double) value > 0.0) {
                            label.setForeground(new Color(34, 179, 34));
                        } else {
                            label.setForeground(new Color(245, 92, 92));
                        }
                    } catch (Exception var9) {
                    }
                }

                return label;
            }
        });
        this.allPosTable.setDefaultRenderer(Object.class, new TableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                label.setText(value != null ? value.toString() : "");
                label.setOpaque(true);
                if (!controlPanel.this.disColors) {
                    switch (column) {
                        case 2:
                            if (!value.equals("") && (Integer) value > 0) {
                                label.setForeground(new Color(34, 179, 34));
                            }
                            break;
                        case 3:
                            if (!value.equals("") && (Double) value > 0.0) {
                                label.setForeground(new Color(34, 179, 34));
                            }
                            break;
                        case 4:
                            if ((Double) value > 0.0) {
                                label.setForeground(new Color(34, 179, 34));
                            } else {
                                label.setForeground(new Color(245, 92, 92));
                            }
                    }
                }

                return label;
            }
        });
        this.optionChain.setDefaultRenderer(Object.class, new TableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                label.setText(value != null ? value.toString() : "");
                label.setOpaque(true);
                double prevLtp;
                if (column == 1) {
                    if (controlPanel.this.ceLtps.containsKey(column)) {
                        prevLtp = (Double) controlPanel.this.ceLtps.get(column);
                        if (Double.parseDouble(value.toString()) > prevLtp) {
                            label.setForeground(new Color(245, 92, 92));
                        } else {
                            label.setForeground(new Color(34, 179, 34));
                        }
                    }

                    if (!value.equals("")) {
                        controlPanel.this.ceLtps.put(column, Double.parseDouble(value.toString()));
                    }
                } else if (column == 5) {
                    if (controlPanel.this.peLtps.containsKey(column)) {
                        prevLtp = (Double) controlPanel.this.peLtps.get(column);
                        if (Double.parseDouble(value.toString()) > prevLtp) {
                            label.setForeground(new Color(245, 92, 92));
                        } else {
                            label.setForeground(new Color(34, 179, 34));
                        }
                    }

                    if (!value.equals("")) {
                        controlPanel.this.peLtps.put(column, Double.parseDouble(value.toString()));
                    }
                }

                if (column == 4) {
                    int modulass = (Integer) controlPanel.this.api.modulas.get(controlPanel.this.currIndex);
                    int var10000 = (int) (Math.round((Double) controlPanel.this.api.ws.extractedData.get(controlPanel.this.currToken) / (double) modulass) * (long) modulass);
                    String LTP = "" + var10000;
                    if (LTP.equals(value.toString())) {
                        label.setBackground(new Color(5, 66, 129));
                    }
                }

                return label;
            }
        });
    }

    private void initComponents() {
        this.jPanel8 = new JPanel();
        this.jPanel6 = new JPanel();
        this.jComboBox1 = new JComboBox();
        this.jButton2 = new JButton();
        this.jSpinner1 = new JSpinner();
        this.currPnl = new JLabel();
        this.jButton1 = new JButton();
        this.jComboBox2 = new JComboBox();
        this.jButton21 = new JButton();
        this.jButton23 = new JButton();
        this.jPanel7 = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.optionChain = new JTable();
        this.jPanel12 = new JPanel();
        this.jTabbedPane1 = new JTabbedPane();
        this.jPanel1 = new JPanel();
        this.jPanel9 = new JPanel();
        this.jSpinner4 = new JSpinner();
        this.jCheckBox4 = new JCheckBox();
        this.jSpinner6 = new JSpinner();
        this.peName = new JTextField();
        this.jSpinner5 = new JSpinner();
        this.jCheckBox1 = new JCheckBox();
        this.jCheckBox5 = new JCheckBox();
        this.jButton3 = new JButton();
        this.jCheckBox3 = new JCheckBox();
        this.jButton4 = new JButton();
        this.jSpinner3 = new JSpinner();
        this.jCheckBox2 = new JCheckBox();
        this.jSpinner2 = new JSpinner();
        this.peQty = new JSpinner();
        this.jComboBox10 = new JComboBox();
        this.jTextField2 = new JTextField();
        this.jButton17 = new JButton();
        this.jButton18 = new JButton();
        this.jButton26 = new JButton();
        this.jPanel10 = new JPanel();
        this.jSpinner7 = new JSpinner();
        this.jCheckBox6 = new JCheckBox();
        this.jSpinner8 = new JSpinner();
        this.ceName = new JTextField();
        this.jSpinner9 = new JSpinner();
        this.jCheckBox7 = new JCheckBox();
        this.jCheckBox8 = new JCheckBox();
        this.jButton5 = new JButton();
        this.jCheckBox9 = new JCheckBox();
        this.jButton6 = new JButton();
        this.jSpinner10 = new JSpinner();
        this.jCheckBox10 = new JCheckBox();
        this.jSpinner11 = new JSpinner();
        this.ceQty = new JSpinner();
        this.jComboBox9 = new JComboBox();
        this.jTextField1 = new JTextField();
        this.jButton15 = new JButton();
        this.jButton16 = new JButton();
        this.jButton24 = new JButton();
        this.jPanel11 = new JPanel();
        this.jCheckBox12 = new JCheckBox();
        this.jSpinner15 = new JSpinner();
        this.jSpinner14 = new JSpinner();
        this.slTrail = new JCheckBox();
        this.jCheckBox11 = new JCheckBox();
        this.trgtTrail = new JCheckBox();
        this.lock = new JCheckBox();
        this.lockPoints = new JSpinner();
        this.jComboBox13 = new JComboBox();
        this.lockTarget = new JSpinner();
        this.jPanel2 = new JPanel();
        this.jScrollPane5 = new JScrollPane();
        pnlTable = new JTable();
        this.jComboBox7 = new JComboBox();
        this.jCheckBox14 = new JCheckBox();
        this.jSpinner17 = new JSpinner();
        this.jCheckBox15 = new JCheckBox();
        this.jSpinner18 = new JSpinner();
        this.jCheckBox16 = new JCheckBox();
        this.jSpinner19 = new JSpinner();
        this.jCheckBox17 = new JCheckBox();
        this.jSpinner20 = new JSpinner();
        this.jButton19 = new JButton();
        this.jCheckBox13 = new JCheckBox();
        this.jComboBox6 = new JComboBox();
        this.jSpinner16 = new JSpinner();
        this.jSpinner21 = new JSpinner();
        this.jComboBox11 = new JComboBox();
        this.jSpinner22 = new JSpinner();
        this.jButton25 = new JButton();
        this.jComboBox12 = new JComboBox();
        this.jLabel1 = new JLabel();
        this.jPanel3 = new JPanel();
        this.jScrollPane4 = new JScrollPane();
        this.allPosTable = new JTable();
        this.mtmSl = new JCheckBox();
        this.mtmSlValue = new JSpinner();
        this.mtmTarget = new JCheckBox();
        this.mtmTargetValue = new JSpinner();
        this.jButton20 = new JButton();
        this.jButton22 = new JButton();
        this.jPanel4 = new JPanel();
        this.jScrollPane3 = new JScrollPane();
        orderTable = new JTable();
        this.jButton10 = new JButton();
        this.jButton11 = new JButton();
        this.jButton12 = new JButton();
        this.jPanel13 = new JPanel();
        this.jScrollPane6 = new JScrollPane();
        this.jTable3 = new JTable();
        this.jPanel5 = new JPanel();
        this.jScrollPane2 = new JScrollPane();
        this.jTable2 = new JTable();
        this.jComboBox3 = new JComboBox();
        this.jComboBox4 = new JComboBox();
        this.jComboBox5 = new JComboBox();
        this.jSpinner12 = new JSpinner();
        this.jSpinner13 = new JSpinner();
        this.jButton7 = new JButton();
        this.jButton8 = new JButton();
        this.jLabel7 = new JLabel();
        this.jLabel8 = new JLabel();
        this.jButton9 = new JButton();
        this.jComboBox8 = new JComboBox();
        this.jButton13 = new JButton();
        this.jButton14 = new JButton();
        this.jSlider1 = new JSlider();
        this.jLabel6 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.jLabel5 = new JLabel();
        this.jLabel2 = new JLabel();
        this.setDefaultCloseOperation(3);
        this.jComboBox1.setModel(new DefaultComboBoxModel(new String[]{"NIFTY", "BANKNIFTY", "FINNIFTY", "MIDCPNIFTY", "SENSEX", "NIFTY FUT", "BANKNIFTY FUT", "FINNIFTY FUT", "MIDCPNIFTY FUT"}));
        this.jButton2.setText("SQR OFF");
        this.jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton2ActionPerformed(evt);
            }
        });
        this.jSpinner1.setModel(new SpinnerNumberModel(6, 2, 40, 2));
        this.currPnl.setFont(new Font("Segoe UI", 1, 18));
        this.jButton1.setText("LOAD");
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton1ActionPerformed(evt);
            }
        });
        this.jComboBox2.setModel(new DefaultComboBoxModel(new String[]{"EXPIRY 0", "EXPIRY 1"}));
        this.jButton21.setText("MINI");
        this.jButton21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton21ActionPerformed(evt);
            }
        });
        this.jButton23.setText("OPTIONS");
        this.jButton23.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton23ActionPerformed(evt);
            }
        });
        GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
        this.jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addContainerGap().addComponent(this.jComboBox1, -2, 72, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jComboBox2, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSpinner1, -2, 47, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 63, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton21, -2, 63, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton23, -2, 99, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.currPnl, -2, 104, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jButton2, -2, 99, -2).addContainerGap(-1, 32767)));
        jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addContainerGap().addGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING, false).addGroup(jPanel6Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jComboBox1, -2, -1, -2).addComponent(this.jComboBox2, -2, -1, -2).addComponent(this.jSpinner1, -2, -1, -2).addComponent(this.jButton1, -1, -1, 32767)).addGroup(jPanel6Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton21).addComponent(this.jButton23)).addComponent(this.jButton2).addComponent(this.currPnl, Alignment.TRAILING, -1, -1, 32767)).addContainerGap()));
        this.optionChain.setBorder(BorderFactory.createEtchedBorder());
        this.optionChain.setModel(new DefaultTableModel(new Object[0][], new String[]{"OI", "CE LTP", "BUY", "SELL", "STRIKE", "PE LTP", "BUY", "BUY", "OI"}));
        this.optionChain.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                controlPanel.this.optionChainMouseClicked(evt);
            }

            public void mouseReleased(MouseEvent evt) {
                controlPanel.this.optionChainMouseReleased(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.optionChain);
        GroupLayout jPanel7Layout = new GroupLayout(this.jPanel7);
        this.jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel7Layout.createSequentialGroup().addComponent(this.jScrollPane1).addContainerGap()));
        jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane1, -2, 188, -2));
        this.jTabbedPane1.setBorder(BorderFactory.createEtchedBorder());
        this.jPanel9.setBorder(BorderFactory.createEtchedBorder());
        this.jSpinner4.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.jCheckBox4.setText("IND TRGT");
        this.jCheckBox4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jCheckBox4ActionPerformed(evt);
            }
        });
        this.jSpinner6.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.jSpinner5.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.jCheckBox1.setText("SL");
        this.jCheckBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jCheckBox1ActionPerformed(evt);
            }
        });
        this.jCheckBox5.setText("LIMIT");
        this.jButton3.setText("BUY");
        this.jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton3ActionPerformed(evt);
            }
        });
        this.jCheckBox3.setText("INDEX SL");
        this.jCheckBox3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jCheckBox3ActionPerformed(evt);
            }
        });
        this.jButton4.setText("SELL");
        this.jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton4ActionPerformed(evt);
            }
        });
        this.jSpinner3.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.jCheckBox2.setText("TARGET");
        this.jCheckBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jCheckBox2ActionPerformed(evt);
            }
        });
        this.jSpinner2.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.peQty.setModel(new SpinnerNumberModel(1, 1, (Comparable) null, 1));
        this.jComboBox10.setModel(new DefaultComboBoxModel(new String[]{"SELECTED", "ATMP", "ITMP", "OTMP"}));
        this.jTextField2.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                controlPanel.this.jTextField2MouseReleased(evt);
            }
        });
        this.jButton17.setText("B");
        this.jButton17.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton17ActionPerformed(evt);
            }
        });
        this.jButton18.setText("S");
        this.jButton18.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton18ActionPerformed(evt);
            }
        });
        this.jButton26.setText("AMT");
        this.jButton26.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton26ActionPerformed(evt);
            }
        });
        GroupLayout jPanel9Layout = new GroupLayout(this.jPanel9);
        this.jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(jPanel9Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel9Layout.createSequentialGroup().addContainerGap().addGroup(jPanel9Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel9Layout.createSequentialGroup().addComponent(this.jCheckBox3).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel9Layout.createParallelGroup(Alignment.TRAILING, false).addGroup(jPanel9Layout.createSequentialGroup().addComponent(this.jSpinner4, -2, 77, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jCheckBox4, -2, 80, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jSpinner5, -2, 1, 32767)).addGroup(jPanel9Layout.createSequentialGroup().addGroup(jPanel9Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jSpinner2, -2, 77, -2).addComponent(this.jSpinner6, -2, 77, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel9Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel9Layout.createSequentialGroup().addComponent(this.jCheckBox2, -2, 86, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSpinner3, -2, 61, -2)).addGroup(Alignment.TRAILING, jPanel9Layout.createSequentialGroup().addComponent(this.jButton3, -2, 61, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton4, -2, 56, -2)))))).addComponent(this.jCheckBox5).addComponent(this.jCheckBox1, -2, 71, -2).addGroup(jPanel9Layout.createSequentialGroup().addComponent(this.peName, -2, 186, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton26, -2, 59, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.peQty, -2, 56, -2)).addGroup(jPanel9Layout.createSequentialGroup().addComponent(this.jComboBox10, -2, 71, -2).addGap(5, 5, 5).addComponent(this.jTextField2, -2, 170, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton17, -2, 27, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton18, -2, 29, -2))).addContainerGap(-1, 32767)));
        jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel9Layout.createSequentialGroup().addContainerGap().addGroup(jPanel9Layout.createParallelGroup(Alignment.LEADING).addComponent(this.peName).addGroup(jPanel9Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.peQty, -2, -1, -2).addComponent(this.jButton26, -1, -1, 32767))).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel9Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel9Layout.createSequentialGroup().addGroup(jPanel9Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton3).addComponent(this.jButton4).addComponent(this.jCheckBox5).addComponent(this.jSpinner6, -2, -1, -2)).addGap(44, 44, 44)).addGroup(Alignment.TRAILING, jPanel9Layout.createSequentialGroup().addGroup(jPanel9Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jSpinner2, -2, -1, -2).addComponent(this.jCheckBox2).addComponent(this.jSpinner3, -2, -1, -2).addComponent(this.jCheckBox1)).addPreferredGap(ComponentPlacement.UNRELATED))).addGroup(jPanel9Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jCheckBox3).addComponent(this.jSpinner4, -2, -1, -2).addComponent(this.jCheckBox4).addComponent(this.jSpinner5, -2, -1, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel9Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jComboBox10, -2, -1, -2).addComponent(this.jTextField2, -2, -1, -2).addComponent(this.jButton17).addComponent(this.jButton18)).addGap(8, 8, 8)));
        this.jPanel10.setBorder(BorderFactory.createEtchedBorder());
        this.jSpinner7.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.jCheckBox6.setText("IND TRGT");
        this.jCheckBox6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jCheckBox6ActionPerformed(evt);
            }
        });
        this.jSpinner8.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.jSpinner9.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.jCheckBox7.setText("SL");
        this.jCheckBox7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jCheckBox7ActionPerformed(evt);
            }
        });
        this.jCheckBox8.setText("LIMIT");
        this.jButton5.setText("BUY");
        this.jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton5ActionPerformed(evt);
            }
        });
        this.jCheckBox9.setText("IND SL");
        this.jCheckBox9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jCheckBox9ActionPerformed(evt);
            }
        });
        this.jButton6.setText("SELL");
        this.jButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton6ActionPerformed(evt);
            }
        });
        this.jSpinner10.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.jCheckBox10.setText("TARGET");
        this.jCheckBox10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jCheckBox10ActionPerformed(evt);
            }
        });
        this.jSpinner11.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.ceQty.setModel(new SpinnerNumberModel(1, 1, (Comparable) null, 1));
        this.jComboBox9.setModel(new DefaultComboBoxModel(new String[]{"SELECTED", "ATMC", "ITMC", "OTMC"}));
        this.jTextField1.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                controlPanel.this.jTextField1MouseReleased(evt);
            }
        });
        this.jButton15.setText("B");
        this.jButton15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton15ActionPerformed(evt);
            }
        });
        this.jButton16.setText("S");
        this.jButton16.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton16ActionPerformed(evt);
            }
        });
        this.jButton24.setText("AMT");
        this.jButton24.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton24ActionPerformed(evt);
            }
        });
        GroupLayout jPanel10Layout = new GroupLayout(this.jPanel10);
        this.jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel10Layout.createSequentialGroup().addGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel10Layout.createSequentialGroup().addContainerGap().addGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jCheckBox8, -2, 65, -2).addComponent(this.jCheckBox7, -2, 71, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jSpinner8).addComponent(this.jSpinner11)).addGap(6, 6, 6).addGroup(jPanel10Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel10Layout.createSequentialGroup().addComponent(this.jCheckBox10, -2, 80, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jSpinner10, -2, 67, -2)).addGroup(jPanel10Layout.createSequentialGroup().addComponent(this.jButton5, -2, 61, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton6, -2, 56, -2)))).addGroup(jPanel10Layout.createSequentialGroup().addGap(6, 6, 6).addGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel10Layout.createSequentialGroup().addComponent(this.ceName, -2, 192, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton24, -2, 59, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.ceQty, -2, 56, -2)).addGroup(jPanel10Layout.createSequentialGroup().addGroup(jPanel10Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel10Layout.createSequentialGroup().addComponent(this.jComboBox9, -2, 64, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jTextField1, -2, 164, -2)).addGroup(jPanel10Layout.createSequentialGroup().addComponent(this.jCheckBox9, -2, 70, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSpinner7, -2, 79, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jCheckBox6, -2, 79, -2))).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel10Layout.createSequentialGroup().addComponent(this.jButton15, -1, -1, 32767).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jButton16, -2, 29, -2)).addComponent(this.jSpinner9, -2, 67, -2)))))).addContainerGap()));
        jPanel10Layout.setVerticalGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel10Layout.createSequentialGroup().addContainerGap().addGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.ceName).addComponent(this.ceQty)).addComponent(this.jButton24)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jSpinner8, -2, -1, -2).addGroup(Alignment.TRAILING, jPanel10Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton5).addComponent(this.jButton6)).addComponent(this.jCheckBox8)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jSpinner11, -2, -1, -2).addComponent(this.jSpinner10, -2, -1, -2).addGroup(jPanel10Layout.createSequentialGroup().addGap(1, 1, 1).addGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jCheckBox7).addComponent(this.jCheckBox10)))).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jCheckBox9).addGroup(jPanel10Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jSpinner9, -2, -1, -2).addComponent(this.jCheckBox6).addComponent(this.jSpinner7, -2, -1, -2))).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel10Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jComboBox9, -2, -1, -2).addComponent(this.jTextField1, -2, -1, -2).addComponent(this.jButton15).addComponent(this.jButton16)).addContainerGap(-1, 32767)));
        this.jCheckBox12.setText("EX LMT");
        this.jSpinner15.setModel(new SpinnerNumberModel(1.0, (Comparable) null, (Comparable) null, 1.0));
        this.jSpinner14.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.slTrail.setText("SL TRL");
        this.jCheckBox11.setText("EN LMT");
        this.trgtTrail.setText("TGT TRL");
        this.lock.setText("LOCK ");
        this.lockPoints.setModel(new SpinnerNumberModel(5.0, (Comparable) null, (Comparable) null, 1.0));
        this.jComboBox13.setModel(new DefaultComboBoxModel(new String[]{"PNTS", "PNL"}));
        this.lockTarget.setModel(new SpinnerNumberModel(10.0, (Comparable) null, (Comparable) null, 1.0));
        GroupLayout jPanel11Layout = new GroupLayout(this.jPanel11);
        this.jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(jPanel11Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel11Layout.createSequentialGroup().addGap(5, 5, 5).addComponent(this.slTrail).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.trgtTrail).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSpinner14, -2, 46, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jCheckBox11).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jCheckBox12).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSpinner15, -2, 46, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lock).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lockPoints).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jComboBox13, -2, 63, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lockTarget, -2, 70, -2).addContainerGap()));
        jPanel11Layout.setVerticalGroup(jPanel11Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel11Layout.createSequentialGroup().addGap(9, 9, 9).addGroup(jPanel11Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.slTrail).addComponent(this.trgtTrail).addComponent(this.jSpinner14, -2, -1, -2).addComponent(this.jCheckBox11).addComponent(this.jCheckBox12).addComponent(this.jSpinner15, -2, -1, -2).addComponent(this.lock).addComponent(this.lockPoints, -2, -1, -2).addComponent(this.jComboBox13, -2, -1, -2).addComponent(this.lockTarget, -2, -1, -2))));
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jPanel11, -1, -1, 32767).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jPanel10, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPanel9, -2, -1, -2))).addContainerGap(-1, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.jPanel9, Alignment.LEADING, -1, -1, 32767).addComponent(this.jPanel10, Alignment.LEADING, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPanel11, -2, -1, -2).addContainerGap(18, 32767)));
        this.jTabbedPane1.addTab("RISK PANEL", this.jPanel1);
        pnlTable.setModel(new DefaultTableModel(new Object[0][], new String[]{"ORDER_ID", "SIDE", "LTP", "PNL", "EXIT", "QTY", "-", "SL", "+", "AVG", "-", "TARGET", "+", "H/L"}));
        pnlTable.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                controlPanel.this.pnlTableMouseReleased(evt);
            }
        });
        this.jScrollPane5.setViewportView(pnlTable);
        this.jComboBox7.setModel(new DefaultComboBoxModel(new String[]{"NIFTY", "BANKNIFTY", "FINNIFTY", "MIDCPNIFTY", "SENSEX", "NIFTY FUT", "BANKNIFTY FUT", "MIDCPNIFTY FUT", "FINNIFTY FUT"}));
        this.jCheckBox14.setText("LOW");
        this.jSpinner17.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.jCheckBox15.setText("UP");
        this.jSpinner18.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.jCheckBox16.setText("PN SL");
        this.jSpinner19.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.jCheckBox17.setText("PNL TGT");
        this.jSpinner20.setModel(new SpinnerNumberModel(0.0, (Comparable) null, (Comparable) null, 1.0));
        this.jButton19.setText("SV");
        this.jButton19.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton19ActionPerformed(evt);
            }
        });
        this.jCheckBox13.setText("AVG");
        this.jComboBox6.setModel(new DefaultComboBoxModel(new String[]{">=", "<=", "><"}));
        this.jSpinner16.setModel(new SpinnerNumberModel(5.0, (Comparable) null, (Comparable) null, 1.0));
        this.jSpinner21.setModel(new SpinnerNumberModel(1.0, 1.0, (Comparable) null, 1.0));
        this.jComboBox11.setModel(new DefaultComboBoxModel(new String[]{"None", "INDEX", "STRIKE"}));
        this.jSpinner22.setModel(new SpinnerNumberModel(1.0, (Comparable) null, (Comparable) null, 1.0));
        this.jButton25.setText("SV");
        this.jButton25.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton25ActionPerformed(evt);
            }
        });
        this.jComboBox12.setModel(new DefaultComboBoxModel(new String[]{"<=", ">="}));
        this.jLabel1.setText("ONLY IF");
        GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jComboBox7, -2, 85, -2).addComponent(this.jCheckBox13, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jComboBox6, 0, 1, 32767).addComponent(this.jCheckBox14, -1, 55, 32767)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jSpinner16, -2, 1, 32767).addComponent(this.jSpinner17, -2, 58, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jCheckBox15).addPreferredGap(ComponentPlacement.RELATED, 9, 32767).addComponent(this.jSpinner18, -2, 61, -2)).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jSpinner21, -2, 49, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jLabel1).addGap(0, 0, 32767))).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jCheckBox16).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSpinner19, -2, 72, -2)).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jComboBox11, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jComboBox12, 0, 1, 32767))).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jCheckBox17).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSpinner20, -2, 74, -2)).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jSpinner22, -2, 74, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton25, -2, 1, 32767))).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton19, -2, 52, -2).addGap(9, 9, 9)).addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addComponent(this.jScrollPane5, -1, 677, 32767).addContainerGap())));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addContainerGap(168, 32767).addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jComboBox7, -2, -1, -2).addComponent(this.jSpinner17, -2, -1, -2).addComponent(this.jSpinner18, -2, -1, -2).addComponent(this.jSpinner19, -2, -1, -2).addComponent(this.jSpinner20, -2, -1, -2).addComponent(this.jCheckBox14).addComponent(this.jCheckBox15).addComponent(this.jCheckBox16).addComponent(this.jCheckBox17).addComponent(this.jButton19)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jCheckBox13).addComponent(this.jComboBox6, -2, -1, -2).addComponent(this.jSpinner16, -2, -1, -2).addComponent(this.jSpinner21, -2, -1, -2).addComponent(this.jComboBox11, -2, -1, -2).addComponent(this.jSpinner22, -2, -1, -2).addComponent(this.jButton25).addComponent(this.jComboBox12, -2, -1, -2).addComponent(this.jLabel1)).addContainerGap()).addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane5, -2, 153, -2).addContainerGap(67, 32767))));
        this.jTabbedPane1.addTab("POSITION", this.jPanel2);
        this.allPosTable.setModel(new DefaultTableModel(new Object[0][], new String[]{"NAME", "PRODUCT", "QTY", "PRICE", "PNL", "ACTION"}));
        this.allPosTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                controlPanel.this.allPosTableMouseClicked(evt);
            }
        });
        this.jScrollPane4.setViewportView(this.allPosTable);
        this.mtmSl.setText("MTM SL");
        this.mtmTarget.setText("MTM TARGET");
        this.jButton20.setText("SAVE");
        this.jButton20.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton20ActionPerformed(evt);
            }
        });
        this.jButton22.setText("REFRESH POSITION");
        this.jButton22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton22ActionPerformed(evt);
            }
        });
        GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
        this.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGap(6, 6, 6).addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane4, -2, 673, -2).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.mtmSl).addGap(12, 12, 12).addComponent(this.mtmSlValue, -2, 92, -2).addGap(12, 12, 12).addComponent(this.mtmTarget).addGap(12, 12, 12).addComponent(this.mtmTargetValue, -2, 95, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jButton20).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jButton22)))));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane4, -2, 175, -2).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addComponent(this.mtmSlValue, -2, -1, -2).addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.mtmTargetValue, -2, -1, -2).addComponent(this.jButton20).addComponent(this.jButton22)).addGroup(jPanel3Layout.createSequentialGroup().addGap(1, 1, 1).addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addComponent(this.mtmSl).addComponent(this.mtmTarget))))));
        this.jTabbedPane1.addTab("ALL POSITION", this.jPanel3);
        this.jPanel4.setLayout(new GridBagLayout());
        orderTable.setModel(new DefaultTableModel(new Object[0][], new String[]{"NAME", "ORDER_ID", "QTY", "LIMIT", "MARKET", "CANCEL"}));
        orderTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                controlPanel.this.orderTableMouseClicked(evt);
            }
        });
        this.jScrollPane3.setViewportView(orderTable);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.ipadx = 639;
        gridBagConstraints.ipady = 104;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(6, 6, 0, 6);
        this.jPanel4.add(this.jScrollPane3, gridBagConstraints);
        this.jButton10.setText("CANCEL ALL OPEN ORDERS");
        this.jButton10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(2, 6, 0, 0);
        this.jPanel4.add(this.jButton10, gridBagConstraints);
        this.jButton11.setText("CONVERT ALL TO MARKET");
        this.jButton11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton11ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(2, 6, 0, 0);
        this.jPanel4.add(this.jButton11, gridBagConstraints);
        this.jButton12.setText("REFRESH ALL OPEN ORDERS");
        this.jButton12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton12ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(2, 6, 0, 0);
        this.jPanel4.add(this.jButton12, gridBagConstraints);
        this.jTabbedPane1.addTab("ORDERS", this.jPanel4);
        this.jTable3.setModel(new DefaultTableModel(new Object[0][], new String[]{"STRIKE", "TYPE", "SIDE", "LOTS", "STRATEGY", "STATUS", "DELETE"}));
        this.jTable3.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                controlPanel.this.jTable3MouseReleased(evt);
            }
        });
        this.jScrollPane6.setViewportView(this.jTable3);
        GroupLayout jPanel13Layout = new GroupLayout(this.jPanel13);
        this.jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(jPanel13Layout.createParallelGroup(Alignment.LEADING).addGap(0, 683, 32767).addGroup(jPanel13Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel13Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane6).addContainerGap())));
        jPanel13Layout.setVerticalGroup(jPanel13Layout.createParallelGroup(Alignment.LEADING).addGap(0, 226, 32767).addGroup(jPanel13Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel13Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane6, -1, 214, 32767).addContainerGap())));
        this.jTabbedPane1.addTab("STRATEGIES", this.jPanel13);
        this.jTable2.setModel(new DefaultTableModel(new Object[0][], new String[]{"TYPE", "NAME", "STRIKE", "SIDE", "LOTS", "LTP", "DELETE", "-", "+", "REF"}));
        this.jTable2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                controlPanel.this.jTable2MouseClicked(evt);
            }

            public void mouseReleased(MouseEvent evt) {
                controlPanel.this.jTable2MouseReleased(evt);
            }
        });
        this.jScrollPane2.setViewportView(this.jTable2);
        this.jComboBox3.setModel(new DefaultComboBoxModel(new String[]{"NIFTY", "BANKNIFTY", "FINNIFTY", "MIDCPNIFTY", "SENSEX"}));
        this.jComboBox3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jComboBox3ActionPerformed(evt);
            }
        });
        this.jComboBox4.setModel(new DefaultComboBoxModel(new String[]{"0", "1"}));
        this.jComboBox5.setModel(new DefaultComboBoxModel(new String[]{"ATMC", "ATMP", "ITMC", "ITMP", "OTMC", "OTMP"}));
        this.jSpinner12.setModel(new SpinnerNumberModel(1, (Comparable) null, (Comparable) null, 1));
        this.jSpinner13.setModel(new SpinnerNumberModel(1, 1, (Comparable) null, 1));
        this.jButton7.setText("B");
        this.jButton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton7ActionPerformed(evt);
            }
        });
        this.jButton8.setText("S");
        this.jButton8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton8ActionPerformed(evt);
            }
        });
        this.jButton9.setText("PLACE");
        this.jButton9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton9ActionPerformed(evt);
            }
        });
        this.jComboBox8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jComboBox8ActionPerformed(evt);
            }
        });
        this.jButton13.setText("+");
        this.jButton13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton13ActionPerformed(evt);
            }
        });
        this.jButton14.setText("-");
        this.jButton14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controlPanel.this.jButton14ActionPerformed(evt);
            }
        });
        this.jSlider1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                controlPanel.this.jSlider1StateChanged(evt);
            }
        });
        this.jLabel3.setForeground(new Color(0, 153, 0));
        this.jLabel2.setForeground(new Color(0, 153, 0));
        GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
        this.jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel5Layout.createSequentialGroup().addGroup(jPanel5Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jComboBox3, -2, 71, -2).addComponent(this.jLabel4, -2, 71, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addComponent(this.jComboBox4, -2, 42, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jComboBox5, -2, 72, -2)).addGroup(jPanel5Layout.createSequentialGroup().addComponent(this.jLabel5, -2, 59, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jLabel2, -2, 55, -2))).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addComponent(this.jSlider1, -2, 244, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jLabel3, -2, 54, -2)).addGroup(jPanel5Layout.createSequentialGroup().addComponent(this.jSpinner12, -2, 46, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jSpinner13, -2, 46, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jLabel6, -2, 61, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton7).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton8).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton9))).addGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addGap(12, 12, 12).addComponent(this.jLabel7, -2, 62, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jLabel8, -1, -1, 32767)).addGroup(jPanel5Layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jComboBox8, -2, 70, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton13, -2, 29, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton14, -2, 29, -2).addGap(0, 3, 32767))).addGap(24, 24, 24)).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane2).addContainerGap()));
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane2, -2, 146, -2).addGap(6, 6, 6).addGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jSlider1, -2, -1, -2).addComponent(this.jLabel4, Alignment.TRAILING, -1, -1, 32767).addComponent(this.jLabel5, -1, -1, 32767).addComponent(this.jLabel7, -1, 23, 32767).addComponent(this.jLabel8, -1, 23, 32767).addComponent(this.jLabel3, -1, -1, 32767).addComponent(this.jLabel2, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel5Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jComboBox3, -2, -1, -2).addComponent(this.jComboBox4, -2, -1, -2)).addGroup(jPanel5Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jComboBox8, -2, -1, -2).addComponent(this.jButton13).addComponent(this.jButton14).addComponent(this.jButton9).addComponent(this.jButton8).addComponent(this.jButton7).addComponent(this.jSpinner13, -2, -1, -2).addComponent(this.jSpinner12, -2, -1, -2).addComponent(this.jComboBox5, -2, -1, -2)).addComponent(this.jLabel6, -2, 23, -2)).addContainerGap()));
        this.jTabbedPane1.addTab("SELLING", this.jPanel5);
        GroupLayout jPanel12Layout = new GroupLayout(this.jPanel12);
        this.jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(jPanel12Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jTabbedPane1, Alignment.TRAILING, -2, 0, 32767));
        jPanel12Layout.setVerticalGroup(jPanel12Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel12Layout.createSequentialGroup().addContainerGap().addComponent(this.jTabbedPane1, -1, 265, 32767)));
        GroupLayout jPanel8Layout = new GroupLayout(this.jPanel8);
        this.jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel8Layout.createSequentialGroup().addGap(6, 6, 6).addGroup(jPanel8Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jPanel6, -1, -1, 32767).addComponent(this.jPanel7, -1, -1, 32767))).addGroup(Alignment.TRAILING, jPanel8Layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel12, -1, -1, 32767).addContainerGap()));
        jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addGap(6, 6, 6).addComponent(this.jPanel6, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPanel7, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPanel12, -2, -1, -2).addContainerGap()));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.jPanel8, -2, -1, -2)));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.jPanel8, -2, -1, -2)));
        this.pack();
        this.setLocationRelativeTo((Component) null);
    }

        private void jButton1ActionPerformed(ActionEvent evt) {
        System.out.println("** inside jButton1ActionPerformed method********");
        while(this.optionModel.getRowCount() > 0) {
            this.optionModel.removeRow(0);
        }

        this.tokenMap = new HashMap();
        this.api.ws.tokenMap = this.tokenMap;
        String index = this.jComboBox1.getSelectedItem().toString();
        index = index.split(" ")[0];
        this.expiry = (String)((List)this.api.expiries.get(index)).get(this.jComboBox2.getSelectedIndex());
        int modulas = (Integer)this.api.modulas.get(index);
        int count = (Integer)this.jSpinner1.getValue() / 2;
        String instruments = "";
        this.currIndex = index;
        Map<String, String> niftyMap = (Map)this.indexToken.get(index);
        if (niftyMap != null) {
            String token = (String)niftyMap.get("token");
            this.currToken = token;
            String exch = (String)niftyMap.get("exch");
            if (this.extractedData.containsKey(token)) {
                double ltp = (Double)this.extractedData.get(token);
                int LTP = (int)(Math.round(ltp / (double)modulas) * (long)modulas);
                LTP -= modulas * count;
                int Strike = LTP;

                for(int i = 0; i < count * 2; ++i) {
                    this.optionModel.addRow(new Object[]{"0", "0", "BUY", "SELL", Strike, "0", "BUY", "SELL", "0"});
                    String ceStrike;
                    String peStrike;
                    if (exch.equals("NSE")) {
                        ceStrike = index + this.expiry + "C" + Strike;
                        peStrike = index + this.expiry + "P" + Strike;
                    } else {
                        ceStrike = index + this.expiry + Strike + "CE";
                        peStrike = index + this.expiry + Strike + "PE";
                    }

                    try {
                        String tempExch = exch.charAt(0) + "FO";
                        JSONObject jo = this.api.oHandler.searchOption(tempExch, ceStrike);
                        String token1 = jo.getString("token");
                        jo = this.api.oHandler.searchOption(tempExch, peStrike);
                        String token2 = jo.getString("token");
                        this.allTokens.put(ceStrike, token1);
                        this.allTokens.put(peStrike, token2);
                        if (!instruments.equals("")) {
                            instruments = instruments + "#" + tempExch + "|" + token1;
                            instruments = instruments + "#" + tempExch + "|" + token2;
                        } else {
                            instruments = tempExch + "|" + token1;
                            instruments = instruments + "#" + tempExch + "|" + token2;
                        }

                        this.tokenMap.put(token1, new int[]{i, 1});
                        this.tokenMap.put(token2, new int[]{i, 5});
                    } catch (IOException var20) {
                        Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String)null, var20);
                    }

                    Strike += modulas;
                }

                if (!instruments.equals("")) {
                    System.out.println(" sending instruments from jButton1ActionPerformed method: instruments : "+instruments);
                    System.out.println("calling subscribe metthod which takes instruments from jButton1ActionPerformed method");
                    this.api.ws.subscribe(instruments);
                }
            }
        }

    }

    // chat gpt code for  jButton1ActionPerformed method
//    private void jButton1ActionPerformed(ActionEvent evt) {
//        System.out.println("** Inside jButton1ActionPerformed method **");
//
//        // Clear existing data
//        optionModel.setRowCount(0);
//        tokenMap.clear();
//        api.ws.tokenMap = tokenMap;
//
//        // Get selected index and expiry
//        String selectedIndex = jComboBox1.getSelectedItem().toString();
//        String baseIndex = selectedIndex.split(" ")[0];
//        expiry = (String) ((List<?>) api.expiries.get(baseIndex)).get(jComboBox2.getSelectedIndex());
//        int strikeStep = (Integer) api.modulas.get(baseIndex);
//        int strikesToDisplay = (Integer) jSpinner1.getValue();
//        int strikesHalf = strikesToDisplay / 2;
//        currIndex = baseIndex;
//
//        // Prepare index details
//        Map<String, String> indexDetails = (Map<String, String>) indexToken.get(baseIndex);
//        if (indexDetails == null) {
//            System.out.println("Index details not found for: " + baseIndex);
//            return;
//        }
//
//        currToken = indexDetails.get("token");
//        String exchange = indexDetails.get("exch");
//
//        if (!extractedData.containsKey(currToken)) {
//            System.out.println("No extracted data found for token: " + currToken);
//            return;
//        }
//
//        double currentLTP = (Double) extractedData.get(currToken);
//        int roundedLTP = (int) (Math.round(currentLTP / (double) strikeStep) * strikeStep);
//        int startStrike = roundedLTP - (strikeStep * strikesHalf);
//        int currentStrike = startStrike;
//
//        String tempExchangeCode = exchange.charAt(0) + "FO";
//        StringBuilder instrumentsBuilder = new StringBuilder();
//
//        for (int i = 0; i < strikesToDisplay; ++i) {
//            // Add new row to table
//            optionModel.addRow(new Object[]{"0", "0", "BUY", "SELL", currentStrike, "0", "BUY", "SELL", "0"});
//
//            // Prepare call and put option symbols
//            String callOptionSymbol;
//            String putOptionSymbol;
//            if ("NSE".equals(exchange)) {
//                callOptionSymbol = baseIndex + expiry + "C" + currentStrike;
//                putOptionSymbol = baseIndex + expiry + "P" + currentStrike;
//            } else {
//                callOptionSymbol = baseIndex + expiry + currentStrike + "CE";
//                putOptionSymbol = baseIndex + expiry + currentStrike + "PE";
//            }
//
//            try {
//                // Search tokens for CE and PE
//                JSONObject callOptionData = api.oHandler.searchOption(tempExchangeCode, callOptionSymbol);
//                String callOptionToken = callOptionData.getString("token");
//
//                JSONObject putOptionData = api.oHandler.searchOption(tempExchangeCode, putOptionSymbol);
//                String putOptionToken = putOptionData.getString("token");
//
//                allTokens.put(callOptionSymbol, callOptionToken);
//                allTokens.put(putOptionSymbol, putOptionToken);
//
//                instrumentsBuilder.append(tempExchangeCode).append("|").append(callOptionToken).append("#")
//                        .append(tempExchangeCode).append("|").append(putOptionToken).append("#");
//
//                tokenMap.put(callOptionToken, new int[]{i, 1});
//                tokenMap.put(putOptionToken, new int[]{i, 5});
//
//            } catch (IOException e) {
//                Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, null, e);
//            }
//
//            currentStrike += strikeStep;
//        }
//
//        // Subscribe to instruments
//        if (!instrumentsBuilder.isEmpty()) {
//            instrumentsBuilder.setLength(instrumentsBuilder.length() - 1); // Remove trailing '#'
//            String instrumentsToSubscribe = instrumentsBuilder.toString();
//            System.out.println("Sending instruments from jButton1ActionPerformed method: " + instrumentsToSubscribe);
//            System.out.println("Calling subscribe method with instruments from jButton1ActionPerformed method");
//            api.ws.subscribe(instrumentsToSubscribe);
//        }
//    }


    public List<Map<String, Object>> aggregateData(JSONArray jsonArray, int timeframe) throws ParseException {
        List<Map<String, Object>> upData = new ArrayList();
        String dateFormat = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

        try {
            int length = jsonArray.length() - 1;
            int startMinuteOfDay = 555;

            int i;
            for (i = 0; i <= length; ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Date date = sdf.parse(jsonObject.getString("time"));
                int y = date.getHours() * 60 + date.getMinutes();
                if ((y - startMinuteOfDay) % timeframe == 0) {
                    break;
                }
            }

            int lastIndex = i;
            i = length;

            while (true) {
                while (i >= lastIndex) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Date date = sdf.parse(jsonObject.getString("time"));
                    int y = date.getHours() * 60 + date.getMinutes();
                    if (date.getHours() == 9 && date.getMinutes() < 15 || date.getHours() == 15 && date.getMinutes() > 29) {
                        --i;
                    } else {
                        if ((y - startMinuteOfDay) % timeframe == 0) {
                            Map<String, Object> data = new HashMap();
                            data.put("time", date);
                            data.put("into", Double.valueOf(jsonObject.getString("into")));
                            data.put("inth", Double.valueOf(jsonObject.getString("inth")));
                            data.put("intl", Double.valueOf(jsonObject.getString("intl")));
                            data.put("intc", Double.valueOf(jsonObject.getString("intc")));
                            upData.add(data);
                        } else if (!upData.isEmpty()) {
                            Map<String, Object> tempData = (Map) upData.get(upData.size() - 1);
                            double currentHigh = Double.parseDouble(jsonObject.getString("inth"));
                            double currentLow = Double.parseDouble(jsonObject.getString("intl"));
                            tempData.put("inth", Math.max(currentHigh, (Double) tempData.get("inth")));
                            tempData.put("intl", Math.min(currentLow, (Double) tempData.get("intl")));
                            tempData.put("intc", Double.valueOf(jsonObject.getString("intc")));
                        }

                        --i;
                    }
                }

                return upData;
            }
        } catch (Exception var18) {
            var18.printStackTrace();
            return upData;
        }
    }

    private void jCheckBox2ActionPerformed(ActionEvent evt) {
        if (this.jCheckBox2.isSelected() && this.jCheckBox4.isSelected()) {
            this.jCheckBox4.setSelected(false);
        }

    }

    private void jCheckBox10ActionPerformed(ActionEvent evt) {
        if (this.jCheckBox6.isSelected()) {
            this.jCheckBox6.setSelected(false);
        }

    }

    private void optionChainMouseClicked(MouseEvent evt) {
    }

    private void jButton5ActionPerformed(ActionEvent evt) {
        if (!this.paper) {
            this.buyTrigger("B");
        } else {
            this.buyPaper("B");
        }

    }

    public double getLimit(String strike, String exch, String side) {
        try {
            double price = this.api.getLtp(strike, exch);
            System.out.println(price);
            double buffer;
            BigDecimal roundedPrice;
            if (side.equals("B")) {
                buffer = (Double) this.jSpinner15.getValue();
                price += buffer;
                roundedPrice = (new BigDecimal(price)).setScale(2, RoundingMode.HALF_UP);
                price = roundedPrice.doubleValue();
                if (price < 0.0) {
                    price = 0.05;
                }

                return price;
            } else {
                buffer = (Double) this.jSpinner15.getValue();
                price -= buffer;
                roundedPrice = (new BigDecimal(price)).setScale(2, RoundingMode.HALF_UP);
                price = roundedPrice.doubleValue();
                if (price < 0.0) {
                    price = 0.05;
                }

                return price;
            }
        } catch (IOException var9) {
            Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var9);
            return 0.0;
        }
    }

    public void buyTrigger(String side) {
        try {
            this.updateRisks("CE", side);
            String strike = this.ceName.getText();
            String index = this.api.oHandler.searchStrike(strike);
            int lots = (Integer) this.ceQty.getValue() * (Integer) this.api.maxQty.get(index);
            String orderType = "MKT";
            double price = 0.0;
            String product = "M";
            String exch = "NFO";
            if (strike.contains("SENSEX")) {
                exch = "BFO";
            }

            if (this.jCheckBox8.isSelected()) {
                orderType = "LMT";
                price = (Double) this.jSpinner8.getValue();
            } else if (this.jCheckBox11.isSelected()) {
                price = this.getLimit(strike, exch, side);
                if (price != 0.0) {
                    orderType = "LMT";
                }
            }

            int freeze = (Integer) this.api.maxFreeze.get(this.api.oHandler.searchStrike(strike));
            int n = lots / freeze;
            int bal = lots % freeze;

            try {
                for (int i = 0; i < n; ++i) {
                    this.api.placeOrder(strike, side, product, exch, orderType, freeze, price);
                }

                if (bal > 0) {
                    this.api.placeOrder(strike, side, product, exch, orderType, bal, price);
                }
            } catch (IOException var14) {
                Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var14);
            }
        } catch (Exception var15) {
        }

    }

    public void buyPaper(String side) {
        try {
            this.updateRisks("CE", side);
            String strike = this.ceName.getText();
            String index = this.api.oHandler.searchStrike(strike);
            int avgQty = (Integer) this.ceQty.getValue() * (Integer) this.api.maxQty.get(index);
            String token = (String) this.allTokens.get(strike);
            double avgPrice = (Double) this.extractedData.get(token);
            String exch = "NFO";
            if (strike.contains("SENSEX")) {
                exch = "BFO";
            }

            this.handlePaper(strike, side, avgQty, avgPrice, token, exch);
        } catch (Exception var9) {
        }

    }

    public void handlePaper(String strike, String side, int avgQty, double avgPrice, String token, String exch) {
        Map<String, Map<String, Object>> pnlData = this.api.ws.pnlData;
        if (pnlData.containsKey(strike + "PAPER")) {
            Map<String, Object> pnlData2 = (Map) pnlData.get(strike + "PAPER");
            String tranType = (String) pnlData2.get("tranType");
            int qty = (Integer) pnlData2.get("avgQty");
            double avg = (Double) pnlData2.get("avgPrice");
            if (tranType.equals(side)) {
                avgPrice = ((double) qty * avg + (double) avgQty * avgPrice) / (double) (qty + avgQty);
                avgQty += qty;
            } else {
                avgQty -= qty;
                if (avgQty <= 0) {
                    avgQty = 0;
                    pnlData.remove(strike + "PAPER");
                }
            }
        }

        if (avgQty > 0) {
            this.api.oHandler.applyRisk(strike, "PAPER", token, avgQty, avgPrice, exch, side, 0);
        } else {
            this.api.oHandler.updatePnlTable();
        }

    }

    public void buyPaper2(String side) {
        try {
            this.updateRisks("PE", side);
            String strike = this.peName.getText();
            String index = this.api.oHandler.searchStrike(strike);
            int avgQty = (Integer) this.peQty.getValue() * (Integer) this.api.maxQty.get(index);
            String token = (String) this.allTokens.get(strike);
            double avgPrice = (Double) this.extractedData.get(token);
            String exch = "NFO";
            if (strike.contains("SENSEX")) {
                exch = "BFO";
            }

            Map<String, Map<String, Object>> pnlData = this.api.ws.pnlData;
            if (pnlData.containsKey(strike + "PAPER")) {
                Map<String, Object> pnlData2 = (Map) pnlData.get(strike + "PAPER");
                String tranType = (String) pnlData2.get("tranType");
                int qty = (Integer) pnlData2.get("avgQty");
                double avg = (Double) pnlData2.get("avgPrice");
                if (tranType.equals(side)) {
                    avgPrice = ((double) qty * avg + (double) avgQty * avgPrice) / (double) (qty + avgQty);
                    avgQty += qty;
                } else {
                    avgQty -= qty;
                    if (avgQty <= 0) {
                        avgQty = 0;
                        pnlData.remove(strike + "PAPER");
                    }
                }
            }

            if (avgQty > 0) {
                this.api.oHandler.applyRisk(strike, "PAPER", token, avgQty, avgPrice, exch, side, 0);
            } else {
                this.api.oHandler.updatePnlTable();
            }
        } catch (Exception var15) {
        }

    }

    private void jButton7ActionPerformed(ActionEvent evt) {
        this.addStrikes("B", "", -1, -1);
    }

    private void jButton8ActionPerformed(ActionEvent evt) {
        this.addStrikes("S", "", -1, -1);
    }

    private void jTable2MouseClicked(MouseEvent evt) {
        DefaultTableModel temp = (DefaultTableModel) this.jTable2.getModel();
        if (SwingUtilities.isRightMouseButton(evt)) {
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem menuItem1 = new JMenuItem("ADD AS STRATEGY");
            menuItem1.addActionListener((e) -> {
                final JDialog dialog = new JDialog(this, "Edit Value", true);
                final JTextField textField = new JTextField("", 40);
                textField.setColumns(20);
                dialog.setPreferredSize(new Dimension(300, 120));
                JButton okButton = new JButton("OK");
                JButton cancelButton = new JButton("Cancel");
                JPanel panel = new JPanel(new BorderLayout(5, 5));
                panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panel.add(textField, "Center");
                JPanel buttonPanel = new JPanel(new FlowLayout(2));
                buttonPanel.add(okButton);
                buttonPanel.add(cancelButton);
                panel.add(buttonPanel, "South");
                dialog.getContentPane().add(panel);
                dialog.pack();
                dialog.setLocationRelativeTo(this);
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Map<String, Object> fields = controlPanel.this.updateRisks("CE", "B");
                        fields = controlPanel.this.updateFields(fields, "CE");
                        fields.put("strategy", textField.getText());
                        fields.put("side", "B");
                        fields.put("outType", "ORDER");
                        fields.put("strikeType", "MANUAL");
                        List<Object> strikes = new ArrayList();
                        DefaultTableModel tempModel = (DefaultTableModel) controlPanel.this.jTable2.getModel();

                        for (int i = 0; i < tempModel.getRowCount(); ++i) {
                            String[] temps = new String[]{tempModel.getValueAt(i, 1).toString(), tempModel.getValueAt(i, 3).toString(), tempModel.getValueAt(i, 4).toString()};
                            strikes.add(temps);
                        }

                        fields.put("strikes", strikes);
                        controlPanel.this.api.pnlCalculator.stratList.add(fields);
                        controlPanel.this.updateStrategyTable();
                        dialog.dispose();
                    }
                });
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });
                dialog.setVisible(true);
            });
            popupMenu.add(menuItem1);
            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        } else {
            String name;
            String index;
            int modulass;
            String exch;
            double ltp;
            int row;
            int strike;
            switch (this.jTable2.getSelectedColumn()) {
                case 6:
                    temp.removeRow(this.jTable2.getSelectedRow());
                    break;
                case 7:
                    row = this.jTable2.getSelectedRow();
                    strike = Integer.parseInt(temp.getValueAt(row, 2).toString());
                    name = temp.getValueAt(row, 1).toString();
                    index = this.api.oHandler.searchStrike(name);
                    modulass = (Integer) this.api.modulas.get(index);
                    exch = "NFO";
                    if (index.equals("SENSEX")) {
                        exch = "BFO";
                    }

                    name = name.replace("" + strike, "" + (strike - modulass));
                    temp.setValueAt(name, row, 1);
                    temp.setValueAt("" + (strike - modulass), row, 2);

                    try {
                        ltp = this.api.getLtp(name, exch);
                        temp.setValueAt("" + ltp, row, 5);
                        this.calcProfit();
                    } catch (IOException var12) {
                        Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var12);
                    }
                    break;
                case 8:
                    row = this.jTable2.getSelectedRow();
                    strike = Integer.parseInt(temp.getValueAt(row, 2).toString());
                    name = temp.getValueAt(row, 1).toString();
                    index = this.api.oHandler.searchStrike(name);
                    modulass = (Integer) this.api.modulas.get(index);
                    exch = "NFO";
                    if (index.equals("SENSEX")) {
                        exch = "BFO";
                    }

                    name = name.replace("" + strike, "" + (strike + modulass));
                    temp.setValueAt(name, row, 1);
                    temp.setValueAt("" + (strike + modulass), row, 2);

                    try {
                        ltp = this.api.getLtp(name, exch);
                        temp.setValueAt("" + ltp, row, 5);
                        this.calcProfit();
                    } catch (IOException var11) {
                        Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var11);
                    }
                    break;
                case 9:
                    this.refreshStrikes();
            }
        }

    }

    private void refreshStrikes() {
        DefaultTableModel temp = (DefaultTableModel) this.jTable2.getModel();

        for (int i = 0; i < temp.getRowCount(); ++i) {
            String name = temp.getValueAt(i, 1).toString();
            String index = this.api.oHandler.searchStrike(name);
            String exch = "NFO";
            if (index.equals("SENSEX")) {
                exch = "BFO";
            }

            try {
                double ltp = this.api.getLtp(name, exch);
                temp.setValueAt("" + ltp, i, 5);
            } catch (IOException var8) {
                Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var8);
            }
        }

        this.calcProfit();
    }

    private void jButton9ActionPerformed(ActionEvent evt) {
        this.placeTable();
    }

    public void placeTable() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) this.jTable2.getModel();
            List<Object[]> buyOrders = new ArrayList();
            List<Object[]> sellOrders = new ArrayList();
            int qty = 0;
            int i;
            String orderType;
            if (!this.paper) {
                for (i = 0; i < tableModel.getRowCount(); ++i) {
                    orderType = (String) tableModel.getValueAt(i, 3);
                    Object[] rowData = new Object[]{tableModel.getValueAt(i, 1), orderType, Integer.valueOf(tableModel.getValueAt(i, 4).toString())};
                    if ("B".equalsIgnoreCase(orderType)) {
                        buyOrders.add(rowData);
                        qty += (Integer) rowData[2] * (Integer) this.api.maxQty.get(this.api.oHandler.searchStrike(tableModel.getValueAt(i, 1).toString()));
                    } else if ("S".equalsIgnoreCase(orderType)) {
                        sellOrders.add(rowData);
                    }
                }

                this.placeMultiple(buyOrders, sellOrders, qty, true);
            } else {
                for (i = 0; i < tableModel.getRowCount(); ++i) {
                    orderType = (String) tableModel.getValueAt(i, 3);
                    String strike1 = tableModel.getValueAt(i, 1).toString();
                    qty = Integer.parseInt(tableModel.getValueAt(i, 4).toString());
                    String exch = "NFO";
                    if (strike1.contains("SENSEX")) {
                        exch = "BFO";
                    }

                    double ltp = this.api.getLtp(strike1, exch);
                    String token = (String) this.api.allTokens.get(strike1);
                    if (token != null) {
                        qty *= (Integer) this.api.maxQty.get(this.api.oHandler.searchStrike(strike1));
                        this.handlePaper(strike1, orderType, qty, ltp, token, exch);
                    }
                }
            }
        } catch (Exception var12) {
        }

    }

    private void orderTableMouseClicked(MouseEvent evt) {
        DefaultTableModel tableModel = (DefaultTableModel) orderTable.getModel();
        if (orderTable.getSelectedColumn() == 5) {
            this.api.oHandler.cancelOrder(tableModel.getValueAt(orderTable.getSelectedRow(), 1).toString());
        } else {
            String strike = tableModel.getValueAt(orderTable.getSelectedRow(), 0).toString();
            String exch = "NFO";
            if (strike.contains("SENSEX")) {
                exch = "BFO";
            }

            if (orderTable.getSelectedColumn() == 4) {
                this.api.oHandler.marketOrder(tableModel.getValueAt(orderTable.getSelectedRow(), 1).toString(), exch, strike, tableModel.getValueAt(orderTable.getSelectedRow(), 2).toString());
            } else if (orderTable.getSelectedColumn() == 3) {
                String input = JOptionPane.showInputDialog(this, "Enter Limit Price");
                if (input != null) {
                    this.api.oHandler.modifyLimit(tableModel.getValueAt(orderTable.getSelectedRow(), 1).toString(), exch, strike, tableModel.getValueAt(orderTable.getSelectedRow(), 2).toString(), Double.parseDouble(input));
                }
            }
        }

    }

    private void jButton10ActionPerformed(ActionEvent evt) {
        this.api.oHandler.cancelAll();
    }

    private void jButton11ActionPerformed(ActionEvent evt) {
        this.api.oHandler.marketAll();
    }

    private void jButton12ActionPerformed(ActionEvent evt) {
        try {
            this.api.oHandler.getAllPositions();
        } catch (IOException var3) {
            Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

    }

    private void allPosTableMouseClicked(MouseEvent evt) {
        DefaultTableModel model1 = (DefaultTableModel) this.allPosTable.getModel();
        int row = this.allPosTable.getSelectedRow();
        int column = this.allPosTable.getSelectedColumn();
        if (column == 5) {
            this.api.oHandler.addPosition(model1.getValueAt(row, 0).toString(), model1.getValueAt(row, 1).toString());
        }

    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        if (!this.paper) {
            this.buyTrigger2("B");
        } else {
            this.buyPaper2("B");
        }

    }

    public void buyTrigger2(String side) {
        this.updateRisks("PE", side);
        String strike = this.peName.getText();
        String index = this.api.oHandler.searchStrike(strike);
        int lots = (Integer) this.peQty.getValue() * (Integer) this.api.maxQty.get(index);
        String orderType = "MKT";
        double price = 0.0;
        String product = "M";
        String exch = "NFO";
        if (strike.contains("SENSEX")) {
            exch = "BFO";
        }

        if (this.jCheckBox5.isSelected()) {
            orderType = "LMT";
            price = (Double) this.jSpinner6.getValue();
        } else if (this.jCheckBox11.isSelected()) {
            price = this.getLimit(strike, exch, side);
            if (price != 0.0) {
                orderType = "LMT";
            }
        }

        try {
            int freeze = (Integer) this.api.maxFreeze.get(this.api.oHandler.searchStrike(strike));
            int n = lots / freeze;
            int bal = lots % freeze;

            for (int i = 0; i < n; ++i) {
                this.api.placeOrder(strike, side, product, exch, orderType, lots, price);
            }

            if (bal > 0) {
                this.api.placeOrder(strike, side, product, exch, orderType, bal, price);
            }
        } catch (IOException var14) {
            Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var14);
        }

    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        this.exitAll();
    }

    public void exitAll() {
        System.out.println("EXITED");
        Map<String, Map<String, Object>> pnlData = this.api.ws.pnlData;
        Iterator<Map.Entry<String, Map<String, Object>>> iterator = pnlData.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Map<String, Object>> entry = (Map.Entry) iterator.next();
            String outerKey = (String) entry.getKey();
            if (outerKey.endsWith("PAPER")) {
                iterator.remove();
                this.api.oHandler.updatePnlTable();
            }
        }

        List<Object[]> buyOrders = new ArrayList();
        List<Object[]> sellOrders = new ArrayList();
        int qty = 0;
        Iterator var6 = pnlData.entrySet().iterator();

        while (var6.hasNext()) {
            Map.Entry<String, Map<String, Object>> entry = (Map.Entry) var6.next();
            String outerKey = (String) entry.getKey();
            Map<String, Object> innerMap = (Map) entry.getValue();
            innerMap.put("active", false);
            String tranType = "B";
            if (innerMap.get("tranType").toString().equals("B")) {
                tranType = "S";
            }

            Object[] rowData = new Object[]{innerMap.get("strike").toString(), tranType, (Integer) innerMap.get("avgQty")};
            if ("B".equalsIgnoreCase(tranType)) {
                buyOrders.add(rowData);
                qty += (Integer) rowData[2];
            } else if ("S".equalsIgnoreCase(tranType)) {
                sellOrders.add(rowData);
            }
        }

        this.placeMultiple(buyOrders, sellOrders, qty, false);
    }

    public void updateSl(MouseEvent evt, String order_id, String slType, int slDirection) {
        if (this.api.ws.pnlData.containsKey(order_id)) {
            Map<String, Object> tempData = (Map) this.api.ws.pnlData.get(order_id);
            int row = pnlTable.rowAtPoint(evt.getPoint());
            int column = pnlTable.columnAtPoint(evt.getPoint());
            double liveSl = (Double) tempData.get(slType);
            liveSl += (double) slDirection;
            if (liveSl < 0.0) {
                liveSl = 0.0;
            }

            tempData.put(slType, liveSl);
            DefaultTableModel model = (DefaultTableModel) pnlTable.getModel();
            model.setValueAt(liveSl, row, column - slDirection);
        }

    }

    public void pnlTableMouseClicked2(MouseEvent evt, JTable pnlTable11, String order_id, String check1, String check2, String check1Value, boolean sl) {
        int row = pnlTable11.rowAtPoint(evt.getPoint());
        int column = pnlTable11.columnAtPoint(evt.getPoint());
        if (this.api.ws.pnlData.containsKey(order_id)) {
            Map<String, Object> tempData = (Map) this.api.ws.pnlData.get(order_id);
            String checked = "NONE";
            double checkValue = (Double) tempData.get(check1Value);
            double trailValue = (Double) tempData.get("trailValue");
            double avgPrice = (Double) tempData.get("avgPrice");
            double liveSl = (Double) tempData.get("liveSl");
            if ((Boolean) tempData.get(check1)) {
                checked = check1;
            } else if ((Boolean) tempData.get(check2)) {
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
                slTrail.setSelected((Boolean) tempData.get("slTrail"));
                JCheckBox trgtTrail = new JCheckBox("TARGET TRAIL");
                trgtTrail.setSelected((Boolean) tempData.get("trgtTrail"));
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
                    String riskType = (String) comboBox.getSelectedItem();
                    double value = (Double) spinner.getValue();
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

                    DefaultTableModel model = (DefaultTableModel) pnlTable11.getModel();
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

    private void jButton6ActionPerformed(ActionEvent evt) {
        if (!this.paper) {
            this.buyTrigger("S");
        } else {
            this.buyPaper("S");
        }

    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        if (!this.paper) {
            this.buyTrigger2("S");
        } else {
            this.buyPaper2("S");
        }

    }

    private void optionChainMouseReleased(MouseEvent evt) {
        int row = this.optionChain.getSelectedRow();
        int column = this.optionChain.getSelectedColumn();
        String LTP = this.optionModel.getValueAt(row, 4).toString();
        Map<String, String> niftyMap = (Map) this.indexToken.get(this.currIndex);
        String strike = "";
        if (niftyMap != null) {
            String exch = (String) niftyMap.get("exch");
            String cpType = "C";
            if (column > 4) {
                cpType = "P";
            }

            if (exch.equals("NSE")) {
                strike = this.currIndex + this.expiry + cpType + LTP;
            } else {
                strike = this.currIndex + this.expiry + LTP + cpType + "E";
            }

            if (column > 4) {
                this.peName.setText(strike);
                this.mini.jTextField1.setText(strike);
            } else {
                this.ceName.setText(strike);
                this.mini.jTextField1.setText(strike);
            }

            switch (column) {
                case 2:
                    if (!this.paper) {
                        this.buyTrigger("B");
                    } else {
                        this.buyPaper("B");
                    }
                    break;
                case 3:
                    if (!this.paper) {
                        this.buyTrigger("S");
                    } else {
                        this.buyPaper("S");
                    }
                case 4:
                case 5:
                default:
                    break;
                case 6:
                    if (!this.paper) {
                        this.buyTrigger2("B");
                    } else {
                        this.buyPaper2("B");
                    }
                    break;
                case 7:
                    if (!this.paper) {
                        this.buyTrigger2("S");
                    } else {
                        this.buyPaper2("S");
                    }
            }
        }

    }

    private void pnlTableMouseReleased(MouseEvent evt) {
        DefaultTableModel tempModel = (DefaultTableModel) pnlTable.getModel();
        String order_id = tempModel.getValueAt(pnlTable.getSelectedRow(), 0).toString();
        Map tempData;
        switch (pnlTable.getSelectedColumn()) {
            case 0:
                if (this.api.ws.pnlData.containsKey(order_id)) {
                    tempData = (Map) this.api.ws.pnlData.get(order_id);
                    JTextField quantityField = new JTextField("" + String.valueOf(tempData.get("avgQty")));
                    JTextField limitPriceField = new JTextField("0.0");
                    JPanel panel = new JPanel(new GridLayout(3, 2));
                    panel.add(new JLabel("Quantity:"));
                    panel.add(quantityField);
                    panel.add(new JLabel("Limit Price:"));
                    panel.add(limitPriceField);
                    String[] options = new String[]{"EXIT", "MULTI TARGETS"};
                    int result = JOptionPane.showOptionDialog((Component) null, panel, "Enter Order Details", -1, -1, (Icon) null, options, options[0]);
                    if (result == 0) {
                        try {
                            int quantity = Integer.parseInt(quantityField.getText());
                            double limitPrice = Double.parseDouble(limitPriceField.getText());
                            this.exitOrder(order_id, quantity, limitPrice);
                        } catch (NumberFormatException var13) {
                            JOptionPane.showMessageDialog((Component) null, "Invalid input. Please enter valid numbers for quantity and limit price.", "Input Error", 0);
                        }
                    } else if (result == 1) {
                        this.addTargets(order_id, pnlTable);
                    }
                }
            case 1:
            case 2:
            case 3:
            case 5:
            case 9:
            default:
                break;
            case 4:
                if (this.api.ws.pnlData.containsKey(order_id)) {
                    this.exitOrder(order_id, 0, 0.0);
                }
                break;
            case 6:
                this.updateSl(evt, order_id, "liveSl", -1);
                break;
            case 7:
                this.pnlTableMouseClicked2(evt, pnlTable, order_id, "sl", "indexSl", "liveSl", true);
                break;
            case 8:
                this.updateSl(evt, order_id, "liveSl", 1);
                break;
            case 10:
                this.updateSl(evt, order_id, "liveTarget", -1);
                break;
            case 11:
                this.pnlTableMouseClicked2(evt, pnlTable, order_id, "target", "indexTarget", "liveTarget", false);
                break;
            case 12:
                this.updateSl(evt, order_id, "liveTarget", 1);
                break;
            case 13:
                if (this.api.ws.pnlData.containsKey(order_id)) {
                    tempData = (Map) this.api.ws.pnlData.get(order_id);
                    JDialog dialog = new JDialog(this, "Update Position", true);
                    JComboBox<String> comboBox = new JComboBox(new String[]{"SL", "INDEX SL"});
                    JComboBox<String> comboBox2 = new JComboBox(new String[]{"1", "2", "3", "5", "10", "15", "30"});
                    JCheckBox candleTrial = new JCheckBox("AUTO TRAIL");
                    candleTrial.setSelected((Boolean) tempData.get("candleTrail"));
                    comboBox.setSelectedItem("SL");
                    comboBox2.setSelectedItem(this.tf);
                    if ((Boolean) tempData.get("indexSl")) {
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
                    dialog.setLocationRelativeTo(pnlTable);
                    dialog.setVisible(true);
                }
        }

    }

    public void addTargets(String order_id, JTable table1) {
        try {
            Map<String, Object> tempData = (Map) this.api.ws.pnlData.get(order_id);
            JComboBox<String> comboBox = new JComboBox(new String[]{"TARGET", "INDEX TARGET"});
            JTextField quantityField = new JTextField("" + String.valueOf(tempData.get("avgQty")));
            JTextField limitPriceField = new JTextField("0.0");
            JPanel panel = new JPanel(new GridLayout(3, 2));
            panel.add(new JLabel("TYPE"));
            panel.add(comboBox);
            panel.add(new JLabel("Quantity:"));
            panel.add(quantityField);
            panel.add(new JLabel("Target"));
            panel.add(limitPriceField);
            int result = JOptionPane.showConfirmDialog(table1, panel, "Enter Order Details", 2);
            double price = Double.parseDouble(limitPriceField.getText());
            String strategy = "";
            String strike = tempData.get("strike").toString();
            if (result == 0) {
                if (!comboBox.getSelectedItem().toString().equals("INDEX TARGET")) {
                    String tranType = tempData.get("tranType").toString();
                    double avgPrice = (Double) tempData.get("avgPrice");
                    if (tranType.equals("B")) {
                        if (price > 0.0) {
                            strategy = "ltp('" + strike + "')>=" + (avgPrice + price);
                        } else {
                            strategy = "ltp('" + strike + "')<=" + (avgPrice + price);
                        }
                    } else if (tranType.equals("S")) {
                        if (price > 0.0) {
                            strategy = "ltp('" + strike + "')<=" + (avgPrice - price);
                        } else {
                            strategy = "ltp('" + strike + "')>=" + (avgPrice - price);
                        }
                    }

                    System.out.println(strategy);
                } else {
                    String[] indices = new String[]{"NIFTY", "BANKNIFTY", "FINNIFTY", "MIDCPNIFTY", "SENSEX", "NIFTY FUT", "BANKNIFTY FUT", "FINNIFTY FUT", "MIDCPNIFTY FUT"};
                    String[] strats = new String[]{"nClose()", "bnClose()", "fClose()", "mClose()", "sClose", "nfClose()", "bnfClose()", "ffClose()", "mfClose()"};
                    String index = tempData.get("index").toString();
                    String tranType = tempData.get("tranType").toString();
                    String cpType = tempData.get("cpType").toString();

                    for (int i = 0; i < 9; ++i) {
                        if (indices[i].equals(index)) {
                            if (cpType.equals("CE") && tranType.equals("B") || cpType.equals("PE") && tranType.equals("S")) {
                                if (price > 0.0) {
                                    strategy = strats[i] + ">=" + price;
                                } else {
                                    strategy = strats[i] + "<=" + -price;
                                }
                            } else if (cpType.equals("CE") && tranType.equals("S") || cpType.equals("PE") && tranType.equals("B")) {
                                if (price > 0.0) {
                                    strategy = strats[i] + "<=" + price;
                                } else {
                                    strategy = strats[i] + ">=" + -price;
                                }
                            }
                            break;
                        }
                    }

                    System.out.println(strategy);
                }

                Map<String, Object> fields = new HashMap();
                fields.put("active", true);
                fields.put("strategy", strategy);
                fields.put("side", "EXIT");
                fields.put("strike", strike);
                fields.put("qty", Integer.valueOf(quantityField.getText()));
                fields.put("id", order_id);
                fields.put("outType", "EXIT");
                this.api.pnlCalculator.stratList.add(fields);
                this.updateStrategyTable();
            }
        } catch (Exception var19) {
            JOptionPane.showMessageDialog(this, "ERROR ON ADDING TARGETS");
        }

    }

    public void prevLowSl(MouseEvent evt, String order_id, JComboBox slType, JComboBox slTf, JCheckBox check1) {
        Map<String, Object> tempData = (Map) this.api.ws.pnlData.get(order_id);
        String indexType = slType.getSelectedItem().toString();
        int tf1 = Integer.parseInt(slTf.getSelectedItem().toString());
        this.tf = slTf.getSelectedItem().toString();
        String index = tempData.get("index").toString();
        String strike;
        String exch;
        List jsonArray;
        Map record;
        double high;
        if (indexType.equals("SL")) {
            strike = tempData.get("strike").toString();
            String token = "";
            exch = "NFO";
            if (strike.contains("SENSEX")) {
                exch = "BFO";
            }

            if (this.api.allTokens.get(strike) == null) {
                token = this.api.oHandler.searchStrike(strike);
                this.allTokens.put(strike, token);
                this.api.ws.subscribe(exch + "|" + token);
            } else {
                token = (String) this.api.allTokens.get(strike);
            }

            if (this.api.hisData.get(token) == null) {
                this.api.historicalData(exch, token);
            }

            jsonArray = this.api.pnlCalculator.ev.parseData(strike, tf1);
            record = (Map) jsonArray.get(jsonArray.size() - 2);
            high = Double.parseDouble(record.get("intl").toString());
            if (tempData.get("tranType").toString().equals("S")) {
                high = Double.parseDouble(record.get("inth").toString());
            }

            tempData.put("liveSl", high);
            tempData.put("sl", true);
            tempData.put("indexSl", false);
            tempData.put("tf", tf1);
            tempData.put("candleTrail", check1.isSelected());
            this.api.ws.timeCache.put(tempData.get("token").toString(), tf1);
            DefaultTableModel model = (DefaultTableModel) pnlTable.getModel();
            model.setValueAt(high, pnlTable.rowAtPoint(evt.getPoint()), 7);
        } else if (indexType.equals("INDEX SL")) {
            strike = (String) this.api.pnlCalculator.ev.indexShorts.entrySet().stream().filter((entry) -> {
                return ((String) entry.getValue()).equals(index);
            }).map(Map.Entry::getKey).findFirst().orElse((null));
            Map<String, String> niftyMap = (Map) this.indexToken.get(index);
            if (niftyMap != null) {
                exch = (String) niftyMap.get("token");
                jsonArray = this.api.pnlCalculator.ev.parseData(strike, tf1);
                record = (Map) jsonArray.get(jsonArray.size() - 2);
                high = Double.parseDouble(record.get("inth").toString());
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
                this.api.ws.timeCache.put(tempData.get("token").toString(), tf1);
                DefaultTableModel model = (DefaultTableModel) pnlTable.getModel();
                model.setValueAt(slValue, pnlTable.rowAtPoint(evt.getPoint()), 7);
            }
        }

    }

    public void exitOrder(String order_id, int qty, double price) {
        Map<String, Object> tempData = (Map) this.api.ws.pnlData.get(order_id);
        if ((Boolean) tempData.get("active")) {
            tempData.put("active", false);
            String strike = tempData.get("strike").toString();
            String side = "B";
            if (tempData.get("tranType").equals("B")) {
                side = "S";
            }

            String product = tempData.get("product").toString();
            String exch = tempData.get("exch").toString();
            String orderType = "MKT";
            if (price > 0.0) {
                orderType = "LMT";
            } else if (this.jCheckBox12.isSelected()) {
                orderType = "LMT";
                price = this.getLimit(strike, exch, side);
            }

            if (qty == 0) {
                qty = (Integer) tempData.get("avgQty");
            }

            if (!order_id.endsWith("PAPER")) {
                try {
                    int freeze = (Integer) this.api.maxFreeze.get(this.api.oHandler.searchStrike(strike));
                    int n = qty / freeze;
                    int bal = qty % freeze;

                    for (int i = 0; i < n; ++i) {
                        this.api.placeOrder(strike, side, product, exch, orderType, freeze, price);
                    }

                    if (bal > 0) {
                        this.api.placeOrder(strike, side, product, exch, orderType, bal, price);
                    }
                } catch (IOException var15) {
                    Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var15);
                }
            } else {
                qty = (Integer) tempData.get("avgQty") - qty;
                if (qty <= 0) {
                    this.api.ws.pnlData.remove(order_id);
                } else {
                    tempData.put("avgQty", qty);
                    tempData.put("active", true);
                }

                this.api.oHandler.updatePnlTable();
            }
        }

    }

    private void jButton13ActionPerformed(ActionEvent evt) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:db/hsqldbexample;shutdown=true", "SA", "");

            try {
                ArrayList<Object[]> data = new ArrayList();
                String name = JOptionPane.showInputDialog(this, "ENTER NAME");
                this.createTableIfNotExists();
                DefaultTableModel tempModel = (DefaultTableModel) this.jTable2.getModel();

                Object[] row;
                for (int i = 0; i < tempModel.getRowCount(); ++i) {
                    row = new Object[]{tempModel.getValueAt(i, 0).toString(), (String) tempModel.getValueAt(i, 3), (Integer) tempModel.getValueAt(i, 4), name};
                    data.add(row);
                }

                System.out.println(data);
                Iterator var11 = data.iterator();

                while (true) {
                    if (!var11.hasNext()) {
                        connection.close();
                        break;
                    }

                    row = (Object[]) var11.next();
                    this.insertRow(connection, (String) row[0], (String) row[1], (Integer) row[2], (String) row[3]);
                }
            } catch (Throwable var9) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException var10) {
            var10.printStackTrace();
        }

    }

    private void jButton14ActionPerformed(ActionEvent evt) {
        String createTableSQL = "delete from Inventory where name='" + this.jComboBox8.getSelectedItem().toString() + "'";

        try {
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:db/hsqldbexample;shutdown=true", "SA", "");
            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);
            this.getData();
            connection.close();
        } catch (Exception var5) {
        }

    }

    private void jComboBox8ActionPerformed(ActionEvent evt) {
        this.getdbStrikes("");
    }

    public List<Object> getdbStrikes(String input) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:db/hsqldbexample;shutdown=true", "SA", "");
            List<Object> strikes = new ArrayList();
            if (this.jComboBox8.getSelectedItem() != null || !input.equals("")) {
                DefaultTableModel tempModel = (DefaultTableModel) this.jTable2.getModel();

                while (tempModel.getRowCount() > 0) {
                    tempModel.removeRow(0);
                }

                String querySQL;
                if (input.equals("")) {
                    String selectedName = this.jComboBox8.getSelectedItem().toString();
                    querySQL = "SELECT * FROM Inventory WHERE name='" + selectedName + "'";
                } else {
                    querySQL = "SELECT * FROM Inventory WHERE name='" + input + "'";
                }

                System.out.println("Executing query: " + querySQL);

                try {
                    Statement statement = connection.createStatement();

                    ArrayList var19;
                    try {
                        ResultSet resultSet = statement.executeQuery(querySQL);

                        try {
                            int rowCount = 0;

                            while (resultSet.next()) {
                                ++rowCount;
                                String moneyNes = resultSet.getString("strike");
                                int gaps = Integer.parseInt(moneyNes.substring(4, moneyNes.length()));
                                String[] temps = this.addStrikes(resultSet.getString("side"), moneyNes.substring(0, 4), gaps, resultSet.getInt("qty") * (Integer) this.jSpinner13.getValue());
                                strikes.add(temps);
                            }

                            System.out.println("Total rows fetched: " + rowCount);
                            var19 = (ArrayList) strikes;
                        } catch (Throwable var14) {
                            if (resultSet != null) {
                                try {
                                    resultSet.close();
                                } catch (Throwable var13) {
                                    var14.addSuppressed(var13);
                                }
                            }

                            throw var14;
                        }

                        if (resultSet != null) {
                            resultSet.close();
                        }
                    } catch (Throwable var15) {
                        if (statement != null) {
                            try {
                                statement.close();
                            } catch (Throwable var12) {
                                var15.addSuppressed(var12);
                            }
                        }

                        throw var15;
                    }

                    if (statement != null) {
                        statement.close();
                    }

                    return var19;
                } catch (SQLException var16) {
                    var16.printStackTrace();
                }
            }
        } catch (SQLException var17) {
            Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var17);
        }

        return null;
    }

    private void jTextField1MouseReleased(MouseEvent evt) {
        JPopupMenu popupMenu = new JPopupMenu();
        this.defaultStrategies();
        Iterator var4 = this.defaultStrats.entrySet().iterator();

        while (var4.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) var4.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            JMenuItem menuItem = new JMenuItem(key);
            menuItem.addActionListener((e) -> {
                this.handleMenuAction(value, this.jTextField1);
            });
            popupMenu.add(menuItem);
            System.out.println("Key: " + key + ", Value: " + value);
        }

        if (evt.isPopupTrigger()) {
            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }

    }

    private void jTextField2MouseReleased(MouseEvent evt) {
        JPopupMenu popupMenu = new JPopupMenu();
        this.defaultStrategies();
        Iterator var4 = this.defaultStrats.entrySet().iterator();

        while (var4.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) var4.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            JMenuItem menuItem = new JMenuItem(key);
            menuItem.addActionListener((e) -> {
                this.handleMenuAction(value, this.jTextField2);
            });
            popupMenu.add(menuItem);
            System.out.println("Key: " + key + ", Value: " + value);
        }

        if (evt.isPopupTrigger()) {
            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }

    }

    private void jButton15ActionPerformed(ActionEvent evt) {
        if (this.jComboBox9.getSelectedItem().toString().equals("SELECTED") && this.ceName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SELECT A STRIKE");
        } else {
            String outType = "ORDER";
            String[] options = new String[]{"Order", "Alerts Only"};
            int choice = JOptionPane.showOptionDialog((Component) null, "Please choose an option:", "Choose Input", -1, 1, (Icon) null, options, options[1]);
            if (choice != -1) {
                if (choice == 1) {
                    outType = "ALERT";
                }

                Map<String, Object> fields = this.updateRisks("CE", "B");
                fields = this.updateFields(fields, "CE");
                fields.put("strategy", this.jTextField1.getText());
                fields.put("side", "B");
                fields.put("outType", outType);
                this.api.pnlCalculator.stratList.add(fields);
                this.updateStrategyTable();
            }
        }
    }

    public void updateStrategyTable() {
        DefaultTableModel tempModel = (DefaultTableModel) this.jTable3.getModel();

        while (tempModel.getRowCount() > 0) {
            tempModel.removeRow(0);
        }

        Iterator var2 = this.api.pnlCalculator.stratList.iterator();

        while (var2.hasNext()) {
            Map<String, Object> map = (Map) var2.next();
            tempModel.addRow(new Object[]{map.get("strike"), map.get("strikeType"), map.get("side"), map.get("qty"), map.get("strategy"), map.get("active"), "DELETE"});
        }

    }

    private Map<String, Object> updateFields(Map<String, Object> fields, String cpType) {
        fields.put("strikeType", this.jComboBox9.getSelectedItem().toString());
        if (cpType.equals("PE")) {
            fields.put("strikeType", this.jComboBox10.getSelectedItem().toString());
        }

        fields.put("paper", this.paper);
        fields.put("side", "B");
        fields.put("product", "M");
        String exch = "NFO";
        if (this.currIndex.contains("SENSEX")) {
            exch = "BFO";
        }

        fields.put("exch", exch);
        String orderType = "MKT";
        double price = 0.0;
        if (cpType.equals("CE")) {
            if (this.jCheckBox8.isSelected()) {
                orderType = "LMT";
                price = (Double) this.jSpinner8.getValue();
            }
        } else if (cpType.equals("PE") && this.jCheckBox5.isSelected()) {
            orderType = "LMT";
            price = (Double) this.jSpinner6.getValue();
        }

        fields.put("orderType", orderType);
        fields.put("price", price);
        fields.put("active", true);
        return fields;
    }

    private void jButton16ActionPerformed(ActionEvent evt) {
        if (this.jComboBox9.getSelectedItem().toString().equals("SELECTED") && this.ceName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SELECT A STRIKE");
        } else {
            String outType = "ORDER";
            String[] options = new String[]{"Order", "Alerts Only"};
            int choice = JOptionPane.showOptionDialog((Component) null, "Please choose an option:", "Choose Input", -1, 1, (Icon) null, options, options[1]);
            if (choice != -1) {
                if (choice == 1) {
                    outType = "ALERT";
                }

                Map<String, Object> fields = this.updateRisks("CE", "S");
                fields = this.updateFields(fields, "CE");
                fields.put("strategy", this.jTextField1.getText());
                fields.put("side", "S");
                fields.put("outType", outType);
                this.api.pnlCalculator.stratList.add(fields);
                this.updateStrategyTable();
                System.out.println(fields);
            }
        }
    }

    private void jButton17ActionPerformed(ActionEvent evt) {
        if (this.jComboBox10.getSelectedItem().toString().equals("SELECTED") && this.peName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SELECT A STRIKE");
        } else {
            String outType = "ORDER";
            String[] options = new String[]{"Order", "Alerts Only"};
            int choice = JOptionPane.showOptionDialog((Component) null, "Please choose an option:", "Choose Input", -1, 1, (Icon) null, options, options[1]);
            if (choice != -1) {
                if (choice == 1) {
                    outType = "ALERT";
                }

                Map<String, Object> fields = this.updateRisks("PE", "B");
                fields = this.updateFields(fields, "PE");
                fields.put("strategy", this.jTextField2.getText());
                fields.put("side", "B");
                fields.put("outType", outType);
                this.api.pnlCalculator.stratList.add(fields);
                this.updateStrategyTable();
            }
        }
    }

    private void jButton18ActionPerformed(ActionEvent evt) {
        if (this.jComboBox10.getSelectedItem().toString().equals("SELECTED") && this.peName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SELECT A STRIKE");
        } else {
            String outType = "ORDER";
            String[] options = new String[]{"Order", "Alerts Only"};
            int choice = JOptionPane.showOptionDialog((Component) null, "Please choose an option:", "Choose Input", -1, 1, (Icon) null, options, options[1]);
            if (choice != -1) {
                if (choice == 1) {
                    outType = "ALERT";
                }

                Map<String, Object> fields = this.updateRisks("PE", "S");
                fields = this.updateFields(fields, "PE");
                fields.put("side", "S");
                fields.put("strategy", this.jTextField2.getText());
                fields.put("outType", outType);
                this.api.pnlCalculator.stratList.add(fields);
                this.updateStrategyTable();
            }
        }
    }

    private void jTable3MouseReleased(MouseEvent evt) {
        try {
            DefaultTableModel tempModel = (DefaultTableModel) this.jTable3.getModel();
            int row = this.jTable3.getSelectedRow();
            int column = this.jTable3.getSelectedColumn();
            if (column == 6) {
                this.api.pnlCalculator.stratList.remove(row);
            } else {
                String strategy;
                if (column == 5) {
                    strategy = tempModel.getValueAt(row, column).toString();
                    if (strategy.equals("true")) {
                        ((Map) this.api.pnlCalculator.stratList.get(row)).put("active", false);
                    } else {
                        ((Map) this.api.pnlCalculator.stratList.get(row)).put("active", true);
                    }
                } else if (column == 4) {
                    strategy = JOptionPane.showInputDialog(this, "ENTER STRATEGY", ((Map) this.api.pnlCalculator.stratList.get(row)).get("strategy"));
                    if (strategy == null) {
                        return;
                    }

                    ((Map) this.api.pnlCalculator.stratList.get(row)).put("strategy", strategy);
                    ((Map) this.api.pnlCalculator.stratList.get(row)).put("active", true);
                }
            }

            this.updateStrategyTable();
        } catch (Exception var6) {
        }

    }

    private void jButton19ActionPerformed(ActionEvent evt) {
        this.lowIndex = this.jCheckBox14.isSelected();
        this.upIndex = this.jCheckBox15.isSelected();
        this.lowPnl = this.jCheckBox16.isSelected();
        this.upPnl = this.jCheckBox17.isSelected();
        this.lowIndexValue = (Double) this.jSpinner17.getValue();
        this.upIndexValue = (Double) this.jSpinner18.getValue();
        this.lowPnlValue = (Double) this.jSpinner19.getValue();
        this.upPnlValue = (Double) this.jSpinner20.getValue();
        JOptionPane.showMessageDialog(this, "SAVE SUCCESS");
    }

    private void jButton20ActionPerformed(ActionEvent evt) {
        this.mtmSL = this.mtmSl.isSelected();
        this.mtmTARGET = this.mtmTarget.isSelected();
        this.mtmSLValue = (Double) this.mtmSlValue.getValue();
        this.mtmTARGETValue = (Double) this.mtmTargetValue.getValue();
        JOptionPane.showMessageDialog(this, "SAVE SUCCESS");
    }

    private void jButton21ActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        this.mini.setVisible(true);
        this.api.oHandler.updatePnlTable();
    }

    private void jCheckBox7ActionPerformed(ActionEvent evt) {
        if (this.jCheckBox9.isSelected() && this.jCheckBox7.isSelected()) {
            this.jCheckBox9.setSelected(false);
        }

    }

    private void jCheckBox9ActionPerformed(ActionEvent evt) {
        if (this.jCheckBox9.isSelected() && this.jCheckBox7.isSelected()) {
            this.jCheckBox7.setSelected(false);
        }

    }

    private void jCheckBox6ActionPerformed(ActionEvent evt) {
        if (this.jCheckBox10.isSelected() && this.jCheckBox6.isSelected()) {
            this.jCheckBox10.setSelected(false);
        }

    }

    private void jCheckBox1ActionPerformed(ActionEvent evt) {
        if (this.jCheckBox1.isSelected() && this.jCheckBox3.isSelected()) {
            this.jCheckBox3.setSelected(false);
        }

    }

    private void jCheckBox3ActionPerformed(ActionEvent evt) {
        if (this.jCheckBox1.isSelected() && this.jCheckBox3.isSelected()) {
            this.jCheckBox1.setSelected(false);
        }

    }

    private void jCheckBox4ActionPerformed(ActionEvent evt) {
        if (this.jCheckBox2.isSelected() && this.jCheckBox4.isSelected()) {
            this.jCheckBox2.setSelected(false);
        }

    }

    private void jButton22ActionPerformed(ActionEvent evt) {
        try {
            this.api.oHandler.getAllPositions();
        } catch (IOException var3) {
            Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

    }

    private void jButton23ActionPerformed(ActionEvent evt) {
        JDialog dialog = new JDialog(this, "Select Options", true);
        dialog.setLayout(new GridLayout(0, 1));
        JCheckBox option1 = new JCheckBox("PAPER");
        JCheckBox option2 = new JCheckBox("SHORTCUTS");
        JCheckBox option3 = new JCheckBox("PROFIT IN POINTS");
        JCheckBox option4 = new JCheckBox("1 LOT PROFIT");
        JCheckBox option5 = new JCheckBox("DISABLE COLORS");
        JButton hides = new JButton("HIDE");
        JButton option6 = new JButton("SAVE");
        option1.setSelected(this.paper);
        option2.setSelected(this.shortcut);
        option3.setSelected(this.pnlPoints);
        option4.setSelected(this.lot1Pnl);
        option5.setSelected(this.disColors);
        dialog.add(option1);
        dialog.add(option2);
        dialog.add(option3);
        dialog.add(option4);
        dialog.add(option5);
        dialog.add(option6);
        dialog.add(hides);
        JButton okButton = new JButton("OK");
        okButton.addActionListener((e) -> {
            this.paper = option1.isSelected();
            this.shortcut = option2.isSelected();
            this.pnlPoints = option3.isSelected();
            this.lot1Pnl = option4.isSelected();
            this.disColors = option5.isSelected();
            dialog.dispose();
        });
        option6.addActionListener((e) -> {
            this.paper = option1.isSelected();
            this.shortcut = option2.isSelected();
            this.pnlPoints = option3.isSelected();
            this.lot1Pnl = option4.isSelected();
            this.disColors = option5.isSelected();
            this.saveSettings();
            dialog.dispose();
        });
        hides.addActionListener((e) -> {
            dialog.dispose();
            this.setVisible(false);
            this.hideui.setVisible(true);
        });
        dialog.add(okButton);
        dialog.setSize(150, 250);
        dialog.setLocationRelativeTo(this.jButton23);
        dialog.setVisible(true);
    }

    private void jSlider1StateChanged(ChangeEvent evt) {
        this.jLabel6.setText("" + this.jSlider1.getValue());
        this.calcProfit();
    }

    private void jComboBox3ActionPerformed(ActionEvent evt) {
        String index = this.jComboBox3.getSelectedItem().toString();
        DefaultTableModel temp = (DefaultTableModel) this.jTable2.getModel();
        Map<String, String> niftyMap = (Map) this.indexToken.get(index);
        if (niftyMap != null) {
            String token = (String) niftyMap.get("token");
            if (this.extractedData.containsKey(token)) {
                int indexLtp = ((Double) this.extractedData.get(token)).intValue();
                this.jLabel6.setText("" + indexLtp);
                this.jSlider1.setMinimum(indexLtp - 2000);
                this.jSlider1.setMaximum(indexLtp + 2000);
                this.jSlider1.setValue(indexLtp);
                this.calcProfit();
            }
        }

    }

    private void jTable2MouseReleased(MouseEvent evt) {
    }

    private void jButton24ActionPerformed(ActionEvent evt) {
        String amount1 = JOptionPane.showInputDialog(this.jButton24, "ENTER AMOUNT");

        try {
            double amount = Double.parseDouble(amount1);
            if (this.ceName.getText().equals("")) {
                JOptionPane.showMessageDialog(this.jButton24, "SELECT A STRIKE");
            } else {
                String strike = this.ceName.getText();
                if (this.allTokens.containsKey(strike)) {
                    String token = (String) this.allTokens.get(strike);
                    if (this.extractedData.containsKey(token)) {
                        double ltp = (Double) this.extractedData.get(token);
                        double maxQty = (double) (Integer) this.api.maxQty.get(this.api.oHandler.searchStrike(strike));
                        int qty = (int) ((double) ((int) amount) / (maxQty * ltp));
                        if (qty <= 0) {
                            qty = 1;
                        }

                        this.ceQty.setValue(qty);
                    }
                }
            }
        } catch (Exception var12) {
            JOptionPane.showMessageDialog(this.jButton24, "AMOUNT IS NOT VALID");
        }

    }

    private void jButton26ActionPerformed(ActionEvent evt) {
        String amount1 = JOptionPane.showInputDialog(this.jButton26, "ENTER AMOUNT");

        try {
            double amount = Double.parseDouble(amount1);
            if (this.peName.getText().equals("")) {
                JOptionPane.showMessageDialog(this.jButton26, "SELECT A STRIKE");
            } else {
                String strike = this.peName.getText();
                if (this.allTokens.containsKey(strike)) {
                    String token = (String) this.allTokens.get(strike);
                    if (this.extractedData.containsKey(token)) {
                        double ltp = (Double) this.extractedData.get(token);
                        double maxQty = (double) (Integer) this.api.maxQty.get(this.api.oHandler.searchStrike(strike));
                        int qty = (int) ((double) ((int) amount) / (maxQty * ltp));
                        if (qty <= 0) {
                            qty = 1;
                        }

                        this.peQty.setValue(qty);
                    }
                }
            }
        } catch (Exception var12) {
            JOptionPane.showMessageDialog(this.jButton26, "AMOUNT IS NOT VALID");
        }

    }

    private void jButton25ActionPerformed(ActionEvent evt) {
        this.avgIndex = this.jComboBox11.getSelectedItem().toString();
        this.avgIndexValue = (Double) this.jSpinner22.getValue();
        this.avgDir = this.jComboBox12.getSelectedItem().toString();
    }

    private void saveSettings() {
        String filePath = "settings.json";
        JSONObject data = new JSONObject();
        data.put("ceQty", this.ceQty.getValue());
        data.put("ceSl", this.jCheckBox7.isSelected());
        data.put("ceSlValue", this.jSpinner11.getValue());
        data.put("ceTarget", this.jCheckBox10.isSelected());
        data.put("ceTargetValue", this.jSpinner10.getValue());
        data.put("peQty", this.peQty.getValue());
        data.put("peSl", this.jCheckBox1.isSelected());
        data.put("peSlValue", this.jSpinner2.getValue());
        data.put("peTarget", this.jCheckBox2.isSelected());
        data.put("peTargetValue", this.jSpinner3.getValue());
        data.put("slTrail", this.slTrail.isSelected());
        data.put("trgtTrail", this.trgtTrail.isSelected());
        data.put("trailValue", this.jSpinner14.getValue());
        data.put("enLimit", this.jCheckBox11.isSelected());
        data.put("exLimit", this.jCheckBox12.isSelected());
        data.put("buffer", this.jSpinner15.getValue());
        data.put("shortcut", this.shortcut);
        data.put("paper", this.paper);
        data.put("pnlPoints", this.pnlPoints);
        data.put("lot1Pnl", this.lot1Pnl);
        data.put("disColors", this.disColors);

        try {
            Files.write(Paths.get(filePath), data.toString(4).getBytes(), new OpenOption[0]);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    private void applySettings() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("settings.json")));
            JSONObject savedData = new JSONObject(content);
            this.ceQty.setValue(savedData.optInt("ceQty", (Integer) this.ceQty.getModel().getValue()));
            this.jCheckBox7.setSelected(savedData.optBoolean("ceSl"));
            this.jSpinner11.setValue(savedData.optDouble("ceSlValue", (Double) this.jSpinner11.getModel().getValue()));
            this.jCheckBox10.setSelected(savedData.optBoolean("ceTarget"));
            this.jSpinner10.setValue(savedData.optDouble("ceTargetValue", (Double) this.jSpinner10.getModel().getValue()));
            this.peQty.setValue(savedData.optInt("peQty", (Integer) this.peQty.getModel().getValue()));
            this.jCheckBox1.setSelected(savedData.optBoolean("peSl"));
            this.jSpinner2.setValue(savedData.optDouble("peSlValue", (Double) this.jSpinner2.getModel().getValue()));
            this.jCheckBox2.setSelected(savedData.optBoolean("peTarget"));
            this.jSpinner3.setValue(savedData.optDouble("peTargetValue", (Double) this.jSpinner3.getModel().getValue()));
            this.slTrail.setSelected(savedData.optBoolean("slTrail"));
            this.trgtTrail.setSelected(savedData.optBoolean("trgtTrail"));
            this.jSpinner14.setValue(savedData.optDouble("trailValue", (Double) this.jSpinner14.getModel().getValue()));
            this.jCheckBox11.setSelected(savedData.optBoolean("enLimit"));
            this.jCheckBox12.setSelected(savedData.optBoolean("exLimit"));
            this.jSpinner15.setValue(savedData.optDouble("buffer", (Double) this.jSpinner15.getModel().getValue()));
            this.shortcut = savedData.optBoolean("shortcut");
            this.paper = savedData.optBoolean("paper");
            this.pnlPoints = savedData.optBoolean("pnlPoints");
            this.lot1Pnl = savedData.optBoolean("lot1Pnl");
            this.disColors = savedData.optBoolean("disColors");
        } catch (IOException var3) {
        }

    }

    private void handleMenuAction(String value, JTextField jTextField1) {
        jTextField1.setText(value);
    }

    private void createTableIfNotExists() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:db/hsqldbexample;shutdown=true", "SA", "");

            try {
                String createTableSQL = "    CREATE TABLE IF NOT EXISTS Inventory (\n        Strike VARCHAR(10) NOT NULL,\n        Side VARCHAR(10) NOT NULL,\n        Qty INTEGER NOT NULL,\n        name VARCHAR(255) NOT NULL\n    )\n";
                Statement statement = connection.createStatement();

                try {
                    statement.execute(createTableSQL);
                    connection.close();
                } catch (Throwable var8) {
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                if (statement != null) {
                    statement.close();
                }
            } catch (Throwable var9) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var6) {
                        var9.addSuppressed(var6);
                    }
                }

                throw var9;
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException var10) {
            var10.printStackTrace();
        }

    }

    private void insertRow(Connection connection, String strike, String side, int qty, String name) throws SQLException {
        String insertSQL = "INSERT INTO Inventory (Strike,Side,Qty,name) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

        try {
            preparedStatement.setString(1, strike);
            preparedStatement.setString(2, side);
            preparedStatement.setInt(3, qty);
            preparedStatement.setString(4, name);
            preparedStatement.executeUpdate();
            this.getData();
        } catch (Throwable var11) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var10) {
                    var11.addSuppressed(var10);
                }
            }

            throw var11;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

    }

    public void getData() throws SQLException {
        try {
            this.jComboBox8.removeAllItems();
            this.jComboBox9.removeAllItems();
            this.jComboBox10.removeAllItems();
            String[] items = new String[]{"SELECTED", "ATMC", "ITMC", "OTMC", "TABLE"};
            String[] var2 = items;
            int var3 = items.length;

            int var4;
            String item;
            for (var4 = 0; var4 < var3; ++var4) {
                item = var2[var4];
                this.jComboBox9.addItem(item);
            }

            items = new String[]{"SELECTED", "ATMP", "ITMP", "OTMP", "TABLE"};
            var2 = items;
            var3 = items.length;

            for (var4 = 0; var4 < var3; ++var4) {
                item = var2[var4];
                this.jComboBox10.addItem(item);
            }

            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:db/hsqldbexample;shutdown=true", "SA", "");
            String querySQL = "SELECT DISTINCT  Name FROM Inventory";
            Statement statement = connection.createStatement();

            try {
                ResultSet resultSet = statement.executeQuery(querySQL);

                try {
                    while (resultSet.next()) {
                        String name = resultSet.getString("Name");
                        this.jComboBox9.addItem(name);
                        this.jComboBox10.addItem(name);
                        this.jComboBox8.addItem(name);
                    }

                    connection.close();
                } catch (Throwable var10) {
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                        } catch (Throwable var9) {
                            var10.addSuppressed(var9);
                        }
                    }

                    throw var10;
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Throwable var11) {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (Throwable var8) {
                        var11.addSuppressed(var8);
                    }
                }

                throw var11;
            }

            if (statement != null) {
                statement.close();
            }
        } catch (Exception var12) {
            System.out.println(var12);
        }

    }

    public void addSells(String mul_key, List<Object[]> sellOrders, boolean entry) {
        Map<String, List<Object>> req_sells = this.api.ws.sellOrders;
        List<Object> sell_list = new ArrayList();

        for (int i = 0; i < sellOrders.size(); ++i) {
            String strike = ((Object[]) sellOrders.get(i))[0].toString();
            String side = ((Object[]) sellOrders.get(i))[1].toString();
            int lots = Integer.parseInt(((Object[]) sellOrders.get(i))[2].toString());
            String exch = "NFO";
            if (strike.contains("SENSEX")) {
                exch = "BFO";
            }

            if (entry) {
                lots *= (Integer) this.api.maxQty.get(this.api.oHandler.searchStrike(strike));
            }

            sell_list.add(Arrays.asList(strike, side, "M", exch, "MKT", lots, 0));
        }

        req_sells.put(mul_key, sell_list);
    }

    public void placeMultiple(List<Object[]> buyOrders, List<Object[]> sellOrders, int qty, boolean entry) {
        String mul_key = UUID.randomUUID().toString();
        this.addSells(mul_key, sellOrders, entry);
        if (qty == 0) {
            this.api.ws.placeSell(mul_key);
        }

        Map<String, Map<String, Object>> req_buys = this.api.ws.buyOrders;
        Map<String, Object> elems = new HashMap();
        elems.put("COUNT", qty);
        ArrayList tempList = new ArrayList();

        for (int i = 0; i < buyOrders.size(); ++i) {
            String strike = ((Object[]) buyOrders.get(i))[0].toString();
            String side = ((Object[]) buyOrders.get(i))[1].toString();
            int lots = Integer.parseInt(((Object[]) buyOrders.get(i))[2].toString());
            String exch = "NFO";
            if (strike.contains("SENSEX")) {
                exch = "BFO";
            }

            if (entry) {
                lots *= (Integer) this.api.maxQty.get(this.api.oHandler.searchStrike(strike));
            }

            try {
                String orderType = "MKT";
                double price = 0.0;
                if (this.jCheckBox12.isSelected()) {
                    price = this.getLimit(strike, exch, side);
                    if (price != 0.0) {
                        orderType = "LMT";
                    }
                }

                int freeze = (Integer) this.api.maxFreeze.get(this.api.oHandler.searchStrike(strike));
                int n = lots / freeze;
                int bal = lots % freeze;

                for (int j = 0; j < n; ++j) {
                    String order_id = this.api.placeOrder(strike, side, "M", exch, orderType, freeze, price);
                    tempList.add(order_id);
                }

                if (bal > 0) {
                    String order_id = this.api.placeOrder(strike, side, "M", exch, orderType, bal, price);
                    tempList.add(order_id);
                }
            } catch (IOException var22) {
                Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var22);
            }
        }

        if (!tempList.isEmpty()) {
            elems.put("ORDERS", tempList);
            req_buys.put(mul_key, elems);
            this.api.ws.checkOrder();
        }

    }

    private String[] addStrikes(String side, String moneyNes, int gaps, int lots) {
        System.out.println("inside of add strike method ::");
        System.out.println("Adding strikes for side: " + side + ", moneyNes: " + moneyNes + ", gaps: " + gaps + ", lots: " + lots);
        try {
            String[] tempStrikes = null;
            String index = this.jComboBox3.getSelectedItem().toString();
            String expiry2 = (String) ((List) this.api.expiries.get(index)).get(this.jComboBox4.getSelectedIndex());
            if (moneyNes.equals("")) {
                moneyNes = this.jComboBox5.getSelectedItem().toString();
            }

            if (gaps == -1) {
                gaps = (Integer) this.jSpinner12.getValue();
            }

            if (lots == -1) {
                lots = (Integer) this.jSpinner13.getValue();
            }

            String strike = this.api.quickStrike(index, expiry2, moneyNes, gaps);
            String exch = "NFO";
            if (index.equals("SENSEX")) {
                exch = "BFO";
            }

            String[] elms = strike.split(":");

            try {
                double ltp = this.api.getLtp(elms[0], exch);
                if (ltp != 0.0) {
                    DefaultTableModel temp = (DefaultTableModel) this.jTable2.getModel();
                    temp.addRow(new Object[]{moneyNes + gaps, elms[0], elms[1], side, lots, ltp, "DELETE", "PREV", "NEXT"});
                    tempStrikes = new String[]{elms[0], side, "" + lots};
                    this.calcProfit();
                }
            } catch (IOException var14) {
                Logger.getLogger(controlPanel.class.getName()).log(Level.SEVERE, (String) null, var14);
            }

            return tempStrikes;
        } catch (Exception var15) {
            var15.printStackTrace();
            return null;
        }
    }

    private void calcProfit() {
        String index = this.jComboBox3.getSelectedItem().toString();
        DefaultTableModel temp = (DefaultTableModel) this.jTable2.getModel();
        Map<String, String> niftyMap = (Map) this.indexToken.get(index);
        if (niftyMap != null) {
            String token = (String) niftyMap.get("token");
            String exch = (String) niftyMap.get("exch");
            if (this.extractedData.containsKey(token)) {
                int indexLtp = ((Double) this.extractedData.get(token)).intValue();
                if (this.jLabel6.getText().equals("")) {
                    this.jLabel6.setText("" + indexLtp);
                    this.jSlider1.setMinimum(indexLtp - 2000);
                    this.jSlider1.setMaximum(indexLtp + 2000);
                    this.jSlider1.setValue(indexLtp);
                }

                double n = (double) (indexLtp + 2000);
                indexLtp -= 1000;
                double maxProfit = 0.0;
                double minProfit = 0.0;
                double maxProfit2 = 0.0;

                double minProfit2;
                for (minProfit2 = 0.0; (double) indexLtp <= n; ++indexLtp) {
                    double profit = 0.0;
                    double profit2 = 0.0;

                    for (int i = 0; i < temp.getRowCount(); ++i) {
                        int strike = Integer.parseInt(temp.getValueAt(i, 2).toString());
                        double ltp = Double.parseDouble(temp.getValueAt(i, 5).toString());
                        String cpType = "CE";
                        double profit1 = 0.0;
                        if (temp.getValueAt(i, 0).toString().charAt(3) == 'P') {
                            cpType = "PE";
                        }

                        if (cpType.equals("CE")) {
                            if (temp.getValueAt(i, 3).toString().equals("B")) {
                                profit1 = (double) (indexLtp - strike) - ltp;
                                if (profit1 < -ltp) {
                                    profit1 = -ltp;
                                }
                            } else {
                                profit1 = (double) (strike - indexLtp) + ltp;
                                if (profit1 > ltp) {
                                    profit1 = ltp;
                                }
                            }
                        } else if (cpType.equals("PE")) {
                            if (temp.getValueAt(i, 3).toString().equals("B")) {
                                profit1 = (double) (strike - indexLtp) - ltp;
                                if (profit1 < -ltp) {
                                    profit1 = -ltp;
                                }
                            } else {
                                profit1 = (double) (indexLtp - strike) + ltp;
                                if (profit1 > ltp) {
                                    profit1 = ltp;
                                }
                            }
                        }

                        profit += profit1 * (double) Integer.parseInt(temp.getValueAt(i, 4).toString()) * (double) (Integer) this.api.maxQty.get(this.api.oHandler.searchStrike(temp.getValueAt(i, 1).toString()));
                        profit2 = profit;
                    }

                    profit2 += this.livePnl((double) indexLtp);
                    if (profit > maxProfit) {
                        maxProfit = profit;
                    } else if (profit < minProfit) {
                        minProfit = profit;
                    }

                    if (profit2 > maxProfit2) {
                        maxProfit2 = profit2;
                    } else if (profit2 < minProfit2) {
                        minProfit2 = profit2;
                    }

                    if (("" + indexLtp).equals(this.jLabel6.getText())) {
                        this.jLabel3.setText("" + Math.round(profit));
                        this.jLabel2.setText("" + Math.round(profit2));
                    }
                }

                this.jLabel7.setText("" + Math.round(minProfit));
                this.jLabel8.setText("" + Math.round(maxProfit));
                this.jLabel4.setText("" + Math.round(minProfit2));
                this.jLabel5.setText("" + Math.round(maxProfit2));
            }
        }

    }

    public double livePnl(double indexLtp) {
        double profit = 0.0;

        double profit1;
        int avgQty;
        for (Iterator var5 = this.api.ws.pnlData.entrySet().iterator(); var5.hasNext(); profit += profit1 * (double) avgQty) {
            Map.Entry<String, Map<String, Object>> entry = (Map.Entry) var5.next();
            Map<String, Object> values = (Map) entry.getValue();
            String strike = values.get("strike").toString();
            String temp = strike.replace(this.api.oHandler.searchStrike(strike), "");
            double avgPrice = (Double) values.get("avgPrice");
            profit1 = 0.0;
            String cpType = "CE";
            int LTP = 0;
            avgQty = (Integer) values.get("avgQty");
            String tranType = values.get("tranType").toString();
            if (!strike.contains("SENSEX")) {
                if (temp.charAt(7) == 'P') {
                    cpType = "PE";
                }

                temp = temp.substring(8, temp.length());
                LTP = Integer.parseInt(temp);
            }

            if (cpType.equals("CE")) {
                if (tranType.equals("B")) {
                    profit1 = indexLtp - (double) LTP - avgPrice;
                    if (profit1 < -avgPrice) {
                        profit1 = -avgPrice;
                    }
                } else {
                    profit1 = (double) LTP - indexLtp + avgPrice;
                    if (profit1 > avgPrice) {
                        profit1 = avgPrice;
                    }
                }
            } else if (cpType.equals("PE")) {
                if (tranType.equals("B")) {
                    profit1 = (double) LTP - indexLtp - avgPrice;
                    if (profit1 < -avgPrice) {
                        profit1 = -avgPrice;
                    }
                } else {
                    profit1 = indexLtp - (double) LTP + avgPrice;
                    if (profit1 > avgPrice) {
                        profit1 = avgPrice;
                    }
                }
            }
        }

        return profit;
    }

    public Map<String, Object> updateRisks(String cpType, String side) {
        Map<String, Object> fields = new HashMap();
        String strike;
        String fut;
        if (cpType.equals("CE")) {
            strike = this.ceName.getText();
            fields.put("strike", strike);
            fields.put("qty", this.ceQty.getValue());
            fields.put("limit", this.jCheckBox8.isSelected());
            fields.put("price", this.jSpinner8.getValue());
            fields.put("side", side);
            fields.put("sl", this.jCheckBox7.isSelected());
            fields.put("slValue", this.jSpinner11.getValue());
            fields.put("target", this.jCheckBox10.isSelected());
            fields.put("targetValue", this.jSpinner10.getValue());
            fields.put("indexSl", this.jCheckBox9.isSelected());
            fields.put("indexSlValue", this.jSpinner7.getValue());
            fields.put("indexTarget", this.jCheckBox6.isSelected());
            fields.put("indexTargetValue", this.jSpinner9.getValue());
            fields.put("slTrail", this.slTrail.isSelected());
            fields.put("trgtTrail", this.trgtTrail.isSelected());
            fields.put("trailValue", this.jSpinner14.getValue());
            fut = "";
            if (this.jComboBox1.getSelectedItem().toString().endsWith("FUT")) {
                fut = " FUT";
            }

            fields.put("index", this.currIndex + fut);
            this.riskFields.put(strike, fields);
        } else if (cpType.equals("PE")) {
            strike = this.peName.getText();
            fields.put("strike", strike);
            fields.put("qty", this.peQty.getValue());
            fields.put("limit", this.jCheckBox5.isSelected());
            fields.put("price", this.jSpinner6.getValue());
            fields.put("side", side);
            fields.put("sl", this.jCheckBox1.isSelected());
            fields.put("slValue", this.jSpinner2.getValue());
            fields.put("target", this.jCheckBox2.isSelected());
            fields.put("targetValue", this.jSpinner3.getValue());
            fields.put("indexSl", this.jCheckBox3.isSelected());
            fields.put("indexSlValue", this.jSpinner4.getValue());
            fields.put("indexTarget", this.jCheckBox4.isSelected());
            fields.put("indexTargetValue", this.jSpinner5.getValue());
            fields.put("slTrail", this.slTrail.isSelected());
            fields.put("trgtTrail", this.trgtTrail.isSelected());
            fields.put("trailValue", this.jSpinner14.getValue());
            fut = "";
            if (this.jComboBox1.getSelectedItem().toString().endsWith("FUT")) {
                fut = " FUT";
            }

            fields.put("index", this.currIndex + fut);
            this.riskFields.put(strike, fields);
        }

        return fields;
    }
}
