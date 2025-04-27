//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ram.code;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.json.JSONArray;
import org.json.JSONObject;

public class login extends JFrame {
    private final Map<String, List<String>> expiries;
    private final Map<String, Integer> maxQty;
    private final Map<String, Integer> maxFreeze;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPasswordField jPasswordField1;
    private JPasswordField jPasswordField2;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;

    public login() {
        this.initComponents();
        this.expiries = new HashMap();
        this.maxQty = new HashMap();
        this.maxFreeze = new HashMap();
    }

    private void initComponents() {
        this.jPanel3 = new JPanel();
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jTextField2 = new JTextField();
        this.jLabel2 = new JLabel();
        this.jPasswordField1 = new JPasswordField();
        this.jLabel3 = new JLabel();
        this.jTextField3 = new JTextField();
        this.jLabel4 = new JLabel();
        this.jTextField4 = new JTextField();
        this.jLabel5 = new JLabel();
        this.jPasswordField2 = new JPasswordField();
        this.jButton3 = new JButton();
        this.jPanel2 = new JPanel();
        this.jTextField1 = new JTextField();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton4 = new JButton();
        this.setDefaultCloseOperation(2);
        this.jPanel3.setLayout(new GridBagLayout());
        this.jLabel1.setText("CLIENT ID");
        this.jLabel2.setText("PASSWORD");
        this.jLabel3.setText("API KEY");
        this.jLabel4.setText("MK EMAIL");
        this.jLabel5.setText("MK PASS");
        this.jButton3.setText("UPDATE ALL");
        this.jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                login.this.jButton3ActionPerformed(evt);
            }
        });
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel1, -2, 77, -2).addComponent(this.jLabel2).addComponent(this.jLabel3).addComponent(this.jLabel4).addComponent(this.jLabel5, -2, 62, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPasswordField2).addComponent(this.jTextField2).addComponent(this.jPasswordField1).addComponent(this.jTextField3).addComponent(this.jTextField4).addComponent(this.jButton3, -1, 196, 32767)).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jTextField2, -2, -1, -2)).addGap(15, 15, 15).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jPasswordField1, -2, -1, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.jTextField3, -2, -1, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.jTextField4, -2, -1, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel5).addComponent(this.jPasswordField2, -2, -1, -2)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton3)));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(6, 6, 0, 6);
        this.jPanel3.add(this.jPanel1, gridBagConstraints);
        this.jPanel1.setVisible(false);
        this.jTextField1.setToolTipText("TOTP");
        this.jButton1.setText("LOGIN");
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                login.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton2.setText("CONNECT");
        this.jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                login.this.jButton2ActionPerformed(evt);
            }
        });
        this.jButton4.setText("ADD/EDIT API DETAILS");
        this.jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                login.this.jButton4ActionPerformed(evt);
            }
        });
        GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jButton4, -2, 188, -2).addComponent(this.jTextField1, -2, 188, -2)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jButton2, -1, -1, 32767).addComponent(this.jButton1, -1, -1, 32767)).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton4).addComponent(this.jButton1)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton2).addComponent(this.jTextField1, -2, -1, -2)).addContainerGap()));
        this.jTextField1.setVisible(false);
        this.jButton2.setVisible(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(12, 6, 6, 6);
        this.jPanel3.add(this.jPanel2, gridBagConstraints);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel3, -1, 303, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel3, -1, 332, 32767).addContainerGap()));
        this.pack();
        this.setLocationRelativeTo((Component) null);
    }

    //    private void jButton1ActionPerformed(ActionEvent evt) {
//        try {
//            String content = null;
//
//            try {
//                content = new String(Files.readAllBytes(Paths.get("finvasia.json")));
//            } catch (IOException var18) {
//                JOptionPane.showMessageDialog(this, "API DETAILS NOT FOUND");
//                return;
//            }
//
//            JSONObject savedData = new JSONObject(content);
//            JSONObject requestBody = new JSONObject();
//            requestBody.put("email", savedData.getString("email"));
//            requestBody.put("pass", savedData.getString("mk_pass"));
//            requestBody.put("broker", "FINVASIA");
//            String clientId = savedData.getString("client_id");
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://mkcodez.com:3010/getApi/getUser")).header("Content-Type", "application/json").POST(BodyPublishers.ofString(requestBody.toString())).build();
//
//            try {
//                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
//                JSONArray jsonResponse = new JSONArray((String)response.body());
//                if (jsonResponse.getInt(0) != 1) {
//                    JOptionPane.showMessageDialog(this, "TRY ADDING EMAIL AND PASSWORD UNDER EDIT API DETAILS / ADD CORRECT CLIENT ID IN DOWNLOAD PAGE OF MKCODEZ.COM");
//                } else {
//                    boolean isBrokerValid = jsonResponse.getBoolean(1);
//                    boolean isPlanValid = jsonResponse.getBoolean(2);
//                    String planType = jsonResponse.getString(3);
////                    if ((!isBrokerValid || !isPlanValid) && !"PREMIUM".equals(planType)) {
////                        if (!isBrokerValid) {
////                            JOptionPane.showMessageDialog(this, "INVALID CREDENTIALS");
////                        } else {
////                            JOptionPane.showMessageDialog(this, "PLAN EXPIRED");
////                        }
////                    } else
////                    {
//                        JSONArray expiryArray = jsonResponse.optJSONArray(5);
//                        if (expiryArray != null && !expiryArray.isEmpty()) {
//                            for(int i = 0; i < expiryArray.length(); ++i) {
//                                JSONObject exp = expiryArray.getJSONObject(i);
//                                String instrument = exp.getString("instrument");
//                                String expiry = exp.getString("expiry");
//                                ((List)this.expiries.computeIfAbsent(instrument, (k) -> {
//                                    return new ArrayList();
//                                })).add(expiry);
//                            }
//
//                            this.expiries.put("BANKNIFTY", (List)this.expiries.remove("BANK"));
//                        }
//
//                        String serverClientId = jsonResponse.optString(4);
////                        if (clientId.equals(serverClientId)) {
//                            this.maxQty.put("NIFTY", jsonResponse.getInt(6));
//                            this.maxQty.put("BANKNIFTY", jsonResponse.getInt(7));
//                            this.maxQty.put("FINNIFTY", jsonResponse.getInt(8));
//                            this.maxQty.put("MIDCPNIFTY", jsonResponse.getInt(12));
//                            this.maxQty.put("SENSEX", jsonResponse.getInt(14));
//                            this.maxFreeze.put("NIFTY", jsonResponse.getInt(9));
//                            this.maxFreeze.put("BANKNIFTY", jsonResponse.getInt(10));
//                            this.maxFreeze.put("FINNIFTY", jsonResponse.getInt(11));
//                            this.maxFreeze.put("MIDCPNIFTY", jsonResponse.getInt(13));
//                            this.maxFreeze.put("SENSEX", jsonResponse.getInt(15));
//                            this.jTextField1.setVisible(true);
//                            this.jButton2.setVisible(true);
//                            this.jPanel1.setVisible(false);
//                            this.jButton4.setVisible(false);
//                            this.jButton1.setVisible(false);
//                            this.setSize(350, 100);
////                        } else if (clientId.isEmpty()) {
////                            JOptionPane.showMessageDialog(this, "PLEASE ADD CLIENT UNDER EDIT API DETAILS");
////                        } else {
////                            JOptionPane.showMessageDialog(this, "TRY ADD CORRECT CLIENT ID IN DOWNLOAD PAGE OF MKCODEZ.COM / UPDATE CLIENT ID UNDER EDIT API DETAILS");
////                        }
////                    }
//                }
//            } catch (IOException var19) {
//                Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String)null, var19);
//            } catch (InterruptedException var20) {
//                Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String)null, var20);
//            }
//
//            System.out.println(this.maxFreeze);
//            System.out.println(this.maxQty);
//            System.out.println(this.expiries);
//        } catch (Exception var21) {
//            JOptionPane.showMessageDialog(this, "" + String.valueOf(var21));
//        }
//
//    }
    private void jButton1ActionPerformed(ActionEvent evt) {
        try {
            // Section 1: Load API Details from JSON File
            String content = null;
            try {
                content = new String(Files.readAllBytes(Paths.get("finvasia.json")));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "API DETAILS NOT FOUND");
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, "Error reading API details file", e);
                return;
            }

            JSONObject savedData = new JSONObject(content);

            // Section 2: Prepare Authentication Request Body
            JSONObject requestBody = new JSONObject();
            requestBody.put("email", savedData.getString("email"));
            requestBody.put("pass", savedData.getString("mk_pass"));
            requestBody.put("broker", "FINVASIA");
            String clientId = savedData.getString("client_id");

            // Section 3: Execute HTTP POST Request for Authentication
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://mkcodez.com:3010/getApi/getUser"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    .build();
            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, "Error sending or receiving authentication request", e);
                JOptionPane.showMessageDialog(this, "Error communicating with the authentication server.");
                return;
            }

            // Section 4: Process Authentication Response
            JSONArray jsonResponse = new JSONArray(response.body());
            if (jsonResponse.getInt(0) != 1) {
                JOptionPane.showMessageDialog(this, "TRY ADDING EMAIL AND PASSWORD UNDER EDIT API DETAILS / ADD CORRECT CLIENT ID IN DOWNLOAD PAGE OF MKCODEZ.COM");
            } else {
                // boolean isBrokerValid = jsonResponse.getBoolean(1);
                // boolean isPlanValid = jsonResponse.getBoolean(2);
                String planType = jsonResponse.getString(3);
                // if ((!isBrokerValid || !isPlanValid) && !"PREMIUM".equals(planType)) {
                //     if (!isBrokerValid) {
                //         JOptionPane.showMessageDialog(this, "INVALID CREDENTIALS");
                //     } else {
                //         JOptionPane.showMessageDialog(this, "PLAN EXPIRED");
                //     }
                // } else {
                JSONArray expiryArray = jsonResponse.optJSONArray(5);
                if (expiryArray != null && !expiryArray.isEmpty()) {
                    for (int i = 0; i < expiryArray.length(); i++) {
                        JSONObject exp = expiryArray.getJSONObject(i);
                        String instrument = exp.getString("instrument");
                        String expiry = exp.getString("expiry");
                        ((List<String>) this.expiries.computeIfAbsent(instrument, k -> new ArrayList<>())).add(expiry);
                    }
                    this.expiries.put("BANKNIFTY", this.expiries.remove("BANK"));
                }

                String serverClientId = jsonResponse.optString(4);
                // if (clientId.equals(serverClientId)) {
                this.maxQty.put("NIFTY", jsonResponse.getInt(6));
                this.maxQty.put("BANKNIFTY", jsonResponse.getInt(7));
                this.maxQty.put("FINNIFTY", jsonResponse.getInt(8));
                this.maxQty.put("MIDCPNIFTY", jsonResponse.getInt(12));
                this.maxQty.put("SENSEX", jsonResponse.getInt(14));
                this.maxFreeze.put("NIFTY", jsonResponse.getInt(9));
                this.maxFreeze.put("BANKNIFTY", jsonResponse.getInt(10));
                this.maxFreeze.put("FINNIFTY", jsonResponse.getInt(11));
                this.maxFreeze.put("MIDCPNIFTY", jsonResponse.getInt(13));
                this.maxFreeze.put("SENSEX", jsonResponse.getInt(15));
                this.jTextField1.setVisible(true);
                this.jButton2.setVisible(true);
                this.jPanel1.setVisible(false);
                this.jButton4.setVisible(false);
                this.jButton1.setVisible(false);
                this.setSize(350, 100);
                // } else if (clientId.isEmpty()) {
                //     JOptionPane.showMessageDialog(this, "PLEASE ADD CLIENT UNDER EDIT API DETAILS");
                // } else {
                //     JOptionPane.showMessageDialog(this, "TRY ADD CORRECT CLIENT ID IN DOWNLOAD PAGE OF MKCODEZ.COM / UPDATE CLIENT ID UNDER EDIT API DETAILS");
                // }
                // }
            }

            // Section 5: Print Debug Information
            System.out.println(this.maxFreeze);
            System.out.println(this.maxQty);
            System.out.println(this.expiries);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage());
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, "Unexpected error during login", e);
        }
    }

    //    private void jButton2ActionPerformed(ActionEvent evt) {
//        NorenApi api = new NorenApi(this.maxQty, this.maxFreeze, this.expiries);
//        String content = null;
//
//        try {
//            content = new String(Files.readAllBytes(Paths.get("finvasia.json")));
//        } catch (IOException var14) {
//            JOptionPane.showMessageDialog(this, "API DETAILS NOT FOUND");
//            return;
//        }
//
//        JSONObject savedData = new JSONObject(content);
//        String userid = savedData.getString("client_id");
//        String password = savedData.getString("broker_pass");
//        String twoFA = this.jTextField1.getText();
//        String vendorCode = userid + "_U";
//        String apiSecret = savedData.getString("api_key");
//        String imei = "abc1234";
//
//        try {
//            boolean status = api.login(userid, password, twoFA, vendorCode, apiSecret, imei);
//            if (status) {
//                this.dispose();
//            } else {
//                JOptionPane.showMessageDialog(this, "WRONG TOTP/PASSWORD");
//            }
//        } catch (IOException var12) {
//            var12.printStackTrace();
//        } catch (InterruptedException var13) {
//            Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String) null, var13);
//        }
//
//    }
    private void jButton2ActionPerformed(ActionEvent evt) {
        // Section 1: Initialize NorenApi
        NorenApi api = new NorenApi(this.maxQty, this.maxFreeze, this.expiries);

        // Section 2: Load API Details from JSON File
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get("finvasia.json")));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "API DETAILS NOT FOUND");
            return;
        }

        JSONObject savedData = new JSONObject(content);

        // Section 3: Extract Login Credentials and Parameters
        String userid = savedData.getString("client_id");
        String password = savedData.getString("broker_pass");
        String twoFA = this.jTextField1.getText();
        String vendorCode = userid + "_U";
        String apiSecret = savedData.getString("api_key");
        String imei = "abc1234"; // Consider if this should be configurable

        // Section 4: Attempt Login
        boolean status = false;
        try {
            status = api.login(userid, password, twoFA, vendorCode, apiSecret, imei);
            if (status) {
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "WRONG TOTP/PASSWORD");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "IO Error during login.");
        } catch (InterruptedException e) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, "Login process interrupted.", e);
            Thread.currentThread().interrupt(); // Re-interrupt the current thread
            JOptionPane.showMessageDialog(this, "Login process was interrupted.");
        }
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        String filePath = "finvasia.json";
        JSONObject data = new JSONObject();
        data.put("client_id", this.jTextField2.getText());
        data.put("email", this.jTextField4.getText());
        data.put("api_key", this.jTextField3.getText());
        data.put("broker_pass", this.jPasswordField1.getText());
        data.put("mk_pass", this.jPasswordField2.getText());

        try {
            Files.write(Paths.get(filePath), data.toString(4).getBytes(), new OpenOption[0]);
            this.jPanel1.setVisible(false);
            this.setSize(350, 100);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        String content = null;

        try {
            content = new String(Files.readAllBytes(Paths.get("finvasia.json")));
            JSONObject savedData = new JSONObject(content);
            this.jTextField2.setText(savedData.getString("client_id"));
            this.jPasswordField1.setText(savedData.getString("broker_pass"));
            this.jTextField3.setText(savedData.getString("api_key"));
            this.jTextField4.setText(savedData.getString("email"));
            this.jPasswordField2.setText(savedData.getString("mk_pass"));
        } catch (IOException var4) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.jPanel1.setVisible(true);
        this.setSize(350, 400);
    }

    public static void main(String[] args) {
        try {
            UIManager.LookAndFeelInfo[] var1 = UIManager.getInstalledLookAndFeels();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                UIManager.LookAndFeelInfo info = var1[var3];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    (new login()).setVisible(true);
                    break;
                }
            }
        } catch (ClassNotFoundException var5) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String) null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String) null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String) null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String) null, var8);
        }

    }
}
