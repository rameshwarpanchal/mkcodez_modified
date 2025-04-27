//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ram.code;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class NorenApi {
    String suserToken = null;
    String userName = "";
    String broker = "FINVASIA";
    //
    com.ram.code.WebSocketClient ws = null;
    Map<String, Map<String, String>> indexToken = new HashMap();
    Map<String, JSONArray> hisData = new HashMap();
    Map<String, Map<String, List<Map<String, Object>>>> parsedData = new HashMap();
    Map<String, String> allTokens = new HashMap();
    Map<String, Double> extractedData = new HashMap();
    Map<String, Integer> modulas = new HashMap();
    Map<String, List<String>> expiries;
    Map<String, Integer> maxQty;
    Map<String, Integer> maxFreeze;
    com.ram.code.PnlCalculator pnlCalculator;
    ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> allPositions = new ConcurrentHashMap();
    orderHandler oHandler = null;
    controlPanel myUi = null;
    OkHttpClient client = new OkHttpClient();

    public NorenApi(Map<String, Integer> maxQty, Map<String, Integer> maxFreeze, Map<String, List<String>> expiries) {
        this.maxQty = maxQty;
        this.maxFreeze = maxFreeze;
        this.expiries = expiries;
        this.indexToken.put("NIFTY", createTokenMap("26000", "NSE"));
        this.indexToken.put("BANKNIFTY", createTokenMap("26009", "NSE"));
        this.indexToken.put("FINNIFTY", createTokenMap("26037", "NSE"));
        this.indexToken.put("MIDCPNIFTY", createTokenMap("26074", "NSE"));
        this.indexToken.put("SENSEX", createTokenMap("1", "BSE"));
        this.modulas.put("NIFTY", 50);
        this.modulas.put("BANKNIFTY", 100);
        this.modulas.put("FINNIFTY", 50);
        this.modulas.put("MIDCPNIFTY", 25);
        this.modulas.put("SENSEX", 100);
    }

    private static Map<String, String> createTokenMap(String token, String exch) {
        Map<String, String> tokenMap = new HashMap();
        tokenMap.put("token", token);
        tokenMap.put("exch", exch);
        return tokenMap;
    }

    private static String hashSha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            byte[] var4 = hashBytes;
            int var5 = hashBytes.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                byte b = var4[var6];
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException var8) {
            var8.printStackTrace();
            return null;
        }
    }

    //    public boolean login(String userid, String password, String twoFA, String vendorCode, String apiSecret, String imei) throws IOException, InterruptedException {
//        String pwd = hashSha256(password);
//        String uAppKey = userid + "|" + apiSecret;
//        String appKey = hashSha256(uAppKey);
//        Gson gson = new Gson();
//        Data data = new Data("API", "1.0.0", userid, pwd, twoFA, vendorCode, appKey, imei);
//        String jData = gson.toJson(data);
//        String payload = "jData=" + jData;
//        System.out.println("Request Payload: " + payload);
//        String url = "https://api.shoonya.com/NorenWClientTP/QuickAuth";
//        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), payload);
//        Request request = (new Request.Builder()).url(url).post(body).header("Content-Type", "application/x-www-form-urlencoded").build();
//
//        try {
//            Response response = this.client.newCall(request).execute();
//
//
//            boolean var21;
//            label65:
//            {
//                label70:
//                {
//                    try {
//                        if (response.isSuccessful()) {
//                            String responseBody = response.body().string();
//                            System.out.println("Response: " + responseBody);
//                            JSONObject jsonResponse = new JSONObject(responseBody);
//                            System.out.println("Rameshwar : " + jsonResponse.toString());
//                            String susertoken = jsonResponse.optString("susertoken", "default_susertoken");
//                            if ("default_susertoken".equals(susertoken)) {
//                                var21 = false;
//                                break label70;
//                            }
//
//                            this.suserToken = susertoken;
//                            this.userName = userid;
//                            System.out.println(this.suserToken);
//                            this.oHandler = new orderHandler(this);
//                            this.startUi();
//                            this.ws = new com.ram.code.WebSocketClient(this, this.userName, this.suserToken, this.extractedData);
//                            this.ws.connectClient();
//                            this.ws.optionModel = this.myUi.optionModel;
//                            this.getFutures();
//                            this.pnlCalculator = new com.ram.code.PnlCalculator(this.myUi, this.ws, this.indexToken);
//                            this.pnlCalculator.startPnlCalculation();
//                            Runtime var10000 = Runtime.getRuntime();
//                            com.ram.code.PnlCalculator var10003 = this.pnlCalculator;
//                            Objects.requireNonNull(var10003);
//                            var10000.addShutdownHook(new Thread(var10003::stopPnlCalculation));
//                            this.myUi.getData();
//                            var21 = true;
//                            break label65;
//                        }
//
//                        System.out.println("Request failed. Response Code: " + response.code());
//                        System.out.println("Response Body: " + response.body().string());
//                    } catch (Throwable var23) {
//                        if (response != null) {
//                            try {
//                                response.close();
//                            } catch (Throwable var22) {
//                                var23.addSuppressed(var22);
//                            }
//                        }
//
//                        throw var23;
//                    }
//
//                    if (response != null) {
//                        response.close();
//                    }
//
//                    return false;
//                }
//
//                if (response != null) {
//                    response.close();
//                }
//
//                return var21;
//            }
//
//            if (response != null) {
//                response.close();
//            }
//
//            return var21;
//        } catch (SQLException var24) {
//            Logger.getLogger(NorenApi.class.getName()).log(Level.SEVERE, (String) null, var24);
//            return false;
//        }
//    }
    public boolean login(String userid, String password, String twoFA, String vendorCode, String apiSecret, String imei) throws IOException, InterruptedException {
        // Section 1: Prepare Authentication Data
        String hashedPassword = hashSha256(password);
        String userAppKey = userid + "|" + apiSecret;
        String appKey = hashSha256(userAppKey);

        // Section 2: Construct Request Payload
        Gson gson = new Gson();
        Data loginData = new Data("API", "1.0.0", userid, hashedPassword, twoFA, vendorCode, appKey, imei);
        String jsonData = gson.toJson(loginData);
        String payload = "jData=" + jsonData;
        System.out.println("Request Payload: " + payload);

        // Section 3: Define API Endpoint and Build HTTP Request
        String url = "https://api.shoonya.com/NorenWClientTP/QuickAuth";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), payload);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        Response response = null;
        try {
            // Section 4: Execute HTTP Request and Process Response
            response = this.client.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println("Response: " + responseBody);
                JSONObject jsonResponse = new JSONObject(responseBody);
                System.out.println("Authentication Response: " + jsonResponse.toString());

                String sessionToken = jsonResponse.optString("susertoken");
                if (sessionToken != null && !sessionToken.isEmpty() && !"default_susertoken".equals(sessionToken)) {
                    this.suserToken = sessionToken;
                    this.userName = userid;
                    System.out.println("Session Token: " + this.suserToken);

                    // Section 5: Initialize and Start Post-Login Components (Consider Asynchronous Initialization for Performance)
                    this.oHandler = new orderHandler(this);
                    this.startUi(); // This might involve UI updates, ensure it's efficient
                    this.ws = new com.ram.code.WebSocketClient(this, this.userName, this.suserToken, this.extractedData);
                    this.ws.connectClient(); // Consider making this asynchronous
                    this.ws.optionModel = this.myUi.optionModel;
                    this.getFutures(); // Consider making this asynchronous
                    this.pnlCalculator = new com.ram.code.PnlCalculator(this.myUi, this.ws, this.indexToken);
                    this.pnlCalculator.startPnlCalculation();
                    Runtime.getRuntime().addShutdownHook(new Thread(this.pnlCalculator::stopPnlCalculation));
                    this.myUi.getData(); // Consider making this asynchronous

                    return true;
                } else {
                    System.out.println("Authentication failed: Invalid or missing session token.");
                    return false;
                }
            } else {
                System.out.println("Request failed. Response Code: " + response.code());
                System.out.println("Response Body: " + response.body().string());
                return false;
            }

        } catch (IOException e) {
            Logger.getLogger(NorenApi.class.getName()).log(Level.SEVERE, "IO Exception during login", e);
            return false;
        } catch (SQLException e) {
            Logger.getLogger(NorenApi.class.getName()).log(Level.SEVERE, "SQL Exception during login", e);
            return false;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public boolean login2(String userid, String password, String totp, String api_key, String api_secret, String imei) throws IOException, InterruptedException {
        try {
            this.broker = "FLATTRADE";
            String conString = api_key + totp + api_secret;
            MessageDigest digest = null;

            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException var21) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String) null, var21);
            }

            byte[] hashedBytes = digest.digest(conString.getBytes());
            String hashedApiSecret = this.bytesToHex(hashedBytes);
            JSONObject myObj = new JSONObject();
            myObj.put("api_key", api_key);
            myObj.put("request_code", totp);
            myObj.put("api_secret", hashedApiSecret);
            String url = "https://authapi.flattrade.in/trade/apitoken";
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").POST(BodyPublishers.ofString(myObj.toString())).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = null;

            try {
                response = client.send(request, BodyHandlers.ofString());
            } catch (IOException var19) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String) null, var19);
            } catch (InterruptedException var20) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, (String) null, var20);
            }

            JSONObject responseJson = new JSONObject((String) response.body());
            String token = responseJson.getString("token");
            if (!token.equals("")) {
                System.out.println(token);
                this.suserToken = token;
                this.userName = userid;
                this.startUi();
                this.oHandler = new orderHandler(this);
                this.ws = new WebSocketClient(this, this.userName, this.suserToken, this.extractedData);
                this.ws.connectClient();
                this.getFutures();
                PnlCalculator pnlCalculator = new com.ram.code.PnlCalculator(this.myUi, this.ws, this.indexToken);
                pnlCalculator.startPnlCalculation();
                Runtime var10000 = Runtime.getRuntime();
                Objects.requireNonNull(pnlCalculator);
                var10000.addShutdownHook(new Thread(pnlCalculator::stopPnlCalculation));
                this.myUi.getData();
                return true;
            }
        } catch (Exception var22) {
            System.out.println(var22);
        }

        return false;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        byte[] var3 = bytes;
        int var4 = bytes.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            byte b = var3[var5];
            String hex = Integer.toHexString(255 & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }

            hexString.append(hex);
        }

        return hexString.toString();
    }

    public void startUi() {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        } catch (ClassNotFoundException var2) {
            Logger.getLogger(NorenApi.class.getName()).log(Level.SEVERE, (String) null, var2);
        } catch (InstantiationException var3) {
            Logger.getLogger(NorenApi.class.getName()).log(Level.SEVERE, (String) null, var3);
        } catch (IllegalAccessException var4) {
            Logger.getLogger(NorenApi.class.getName()).log(Level.SEVERE, (String) null, var4);
        } catch (UnsupportedLookAndFeelException var5) {
            Logger.getLogger(NorenApi.class.getName()).log(Level.SEVERE, (String) null, var5);
        }

        this.myUi = new controlPanel(this);
        this.myUi.setVisible(true);
    }

    public void getFutures() throws IOException {
        String[] indices = new String[]{"NIFTY", "BANKNIFTY", "FINNIFTY", "MIDCPNIFTY"};
        StringBuilder instruments = new StringBuilder("NSE|26000#NSE|26037#NSE|26009#NSE|26074#BSE|1");

        String[] var3 = indices;
        int var4 = indices.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            String index = var3[var5];
            JSONObject res = this.oHandler.searchOption("NFO", index + " FUT");
            System.out.println(res);
            if (res.has("token")) {
                String token = res.getString("token");
                String symbol = res.getString("tsym");
                this.indexToken.put(index + " FUT", createTokenMap(token, "NFO"));
                if (!instruments.toString().isEmpty()) {
                    instruments.append("#NFO|").append(token);
                }
            }
        }

        if (!instruments.toString().isEmpty()) {
            this.ws.subscribe(instruments.toString());
        }

        System.out.println(this.indexToken);
    }

    public String placeOrder(String strike, String side, String product, String exch, String orderType, int qty, double price) throws IOException {
        JSONObject values = new JSONObject();
        values.put("uid", this.userName);
        values.put("actid", this.userName);
        values.put("trantype", side);
        values.put("prd", product);
        values.put("exch", exch);
        values.put("tsym", strike);
        values.put("qty", "" + qty);
        values.put("dscqty", "0");
        values.put("prctyp", orderType);
        values.put("prc", "" + price);
        values.put("trgprc", "0");
        values.put("ret", "DAY");
        values.put("remarks", "MKCODEZ");
        System.out.println(values);
        String var10000 = values.toString();
        String payload = "jData=" + var10000 + "&jKey=" + this.suserToken;
        String url = "https://api.shoonya.com/NorenWClientTP/PlaceOrder";
        if (this.broker.equals("FLATTRADE")) {
            url = "https://piconnect.flattrade.in/PiConnectTP/PlaceOrder";
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), payload);
        Request request = (new Request.Builder()).url(url).post(body).header("Content-Type", "application/x-www-form-urlencoded").build();

        try {
            Response response = this.client.newCall(request).execute();

            label51:
            {
                String var17;
                try {
                    if (!response.isSuccessful()) {
                        break label51;
                    }

                    String responseBody = response.body().string();
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    var17 = jsonResponse.getString("norenordno");
                } catch (Throwable var19) {
                    if (response != null) {
                        try {
                            response.close();
                        } catch (Throwable var18) {
                            var19.addSuppressed(var18);
                        }
                    }

                    throw var19;
                }

                if (response != null) {
                    response.close();
                }

                return var17;
            }

            if (response != null) {
                response.close();
            }

            return null;
        } catch (Exception var20) {
            var20.printStackTrace();
            return null;
        }
    }

    public String quickStrike(String index, String expiry, String strike, int gaps) {
        System.out.println("Inside quickStrike method ");
        System.out.println("Quick strike calculation for index: " + index + ", expiry: " + expiry + ", strike: " + strike + ", with gaps: " + gaps);
        int modulass = (Integer) this.modulas.get(index);
        Map<String, String> niftyMap = (Map) this.indexToken.get(index);
        if (niftyMap != null) {
            String token = (String) niftyMap.get("token");
            String exch = (String) niftyMap.get("exch");
            int modulas1 = (Integer) this.modulas.get(index);
            if (this.extractedData.containsKey(token)) {
                double ltp = (Double) this.extractedData.get(token);
                int LTP = (int) (Math.round(ltp / (double) modulas1) * (long) modulas1);
                if (ltp > (double) (LTP + modulass)) {
                    LTP += modulass;
                }

                if (strike.equals("ATMC")) {
                    strike = index + expiry + "C" + LTP;
                    if (index.equals("SENSEX")) {
                        strike = index + expiry + LTP + "CE";
                    }

                    return strike + ":" + LTP;
                }

                if (strike.equals("ATMP")) {
                    strike = index + expiry + "P" + LTP;
                    if (index.equals("SENSEX")) {
                        strike = index + expiry + LTP + "PE";
                    }

                    return strike + ":" + LTP;
                }

                if (strike.equals("ITMC")) {
                    strike = index + expiry + "C" + (LTP - modulass * gaps);
                    if (index.equals("SENSEX")) {
                        strike = index + expiry + (LTP - modulass * gaps) + "CE";
                    }

                    return strike + ":" + (LTP - modulass * gaps);
                }

                if (strike.equals("OTMC")) {
                    strike = index + expiry + "C" + (LTP + modulass * gaps);
                    if (index.equals("SENSEX")) {
                        strike = index + expiry + (LTP + modulass * gaps) + "CE";
                    }

                    return strike + ":" + (LTP + modulass * gaps);
                }

                if (strike.equals("ITMP")) {
                    strike = index + expiry + "P" + (LTP + modulass * gaps);
                    if (index.equals("SENSEX")) {
                        strike = index + expiry + (LTP + modulass * gaps) + "PE";
                    }

                    return strike + ":" + (LTP + modulass * gaps);
                }

                if (strike.equals("OTMP")) {
                    strike = index + expiry + "P" + (LTP - modulass * gaps);
                    if (index.equals("SENSEX")) {
                        strike = index + expiry + (LTP - modulass * gaps) + "PE";
                    }

                    return strike + ":" + (LTP - modulass * gaps);
                }

                if (strike.startsWith("CE_LTP") || strike.startsWith("PE_LTP")) {
                    double reqLtp = 0.0;
                    double prevLtp = 0.0;
                    String cpType = "CE";
                    if (strike.startsWith("PE_LTP")) {
                        cpType = "PE";
                    }

                    exch = exch.charAt(0) + "FO";

                    try {
                        prevLtp = this.getLtp(this.getStrike(index, expiry, LTP, cpType, exch), exch);
                    } catch (IOException var21) {
                        Logger.getLogger(NorenApi.class.getName()).log(Level.SEVERE, (String) null, var21);
                    }

                    if (strike.contains("<=")) {
                        reqLtp = Double.parseDouble(strike.substring(strike.indexOf("<=") + 2));
                    } else {
                        reqLtp = Double.parseDouble(strike.substring(strike.indexOf(">=") + 2));
                    }

                    if (reqLtp > 0.0) {
                        if (cpType.equals("CE")) {
                            if (prevLtp <= reqLtp) {
                                modulass += LTP;
                            }
                        } else if (prevLtp >= reqLtp) {
                            modulass = LTP - modulass;
                        }

                        if (!(prevLtp > reqLtp)) {
                            if (prevLtp < reqLtp) {
                                while (prevLtp <= reqLtp) {
                                    LTP += modulass;
                                    strike = this.getStrike(index, expiry, LTP, cpType, exch);

                                    try {
                                        prevLtp = this.getLtp(strike, exch);
                                    } catch (IOException var19) {
                                        Logger.getLogger(NorenApi.class.getName()).log(Level.SEVERE, (String) null, var19);
                                    }
                                }

                                if (strike.contains("<=")) {
                                    LTP -= modulass;
                                }
                            }
                        } else {
                            while (prevLtp >= reqLtp) {
                                LTP += modulass;
                                strike = this.getStrike(index, expiry, LTP, cpType, exch);

                                try {
                                    prevLtp = this.getLtp(strike, exch);
                                } catch (IOException var20) {
                                    Logger.getLogger(NorenApi.class.getName()).log(Level.SEVERE, (String) null, var20);
                                }
                            }

                            if (strike.contains(">=")) {
                                LTP -= modulass;
                            }
                        }

                        System.out.println(this.getStrike(index, expiry, LTP, cpType, exch));
                        return this.getStrike(index, expiry, LTP, cpType, exch);
                    }

                    return null;
                }
            }
        }

        return "";
    }

    public String getStrike(String index, String expiry, int ltp, String cpType, String exch) {
        System.out.println("inside getStrike method :");
        System.out.println("index :" + index + " expiry" + expiry + " ltp:" + ltp + " cpType: " + cpType + " exchange : " + exch);
        String strike = index + expiry + cpType.charAt(0) + ltp;
        if (index.equals("SENSEX")) {
            strike = index + expiry + ltp + cpType;
        }
        System.out.println("returning strike: " + strike);

        return strike;
    }

    public double getLtp(String strike, String exch) throws IOException {
        System.out.println("inside get Ltp method :");
        System.out.println("Strike : " + strike + " exchange : " + exch);
        Response response;
        double var13;
        label64:
        {
            if (!strike.equals("")) {
                System.out.println(strike);
                System.out.println(exch);
                String token = "";
                if (this.allTokens.containsKey(strike)) {
                    token = (String) this.allTokens.get(strike);
                    if (this.extractedData.containsKey(token)) {
                        return (Double) this.extractedData.get(token);
                    }
                }

                JSONObject values;
                if (token.equals("")) {
                    values = this.oHandler.searchOption(exch, strike);
                    if (values.has("token")) {
                        token = values.getString("token");
                    }
                }

                values = new JSONObject();
                values.put("uid", this.userName);
                values.put("exch", exch);
                values.put("token", token);
                String url = "https://api.shoonya.com/NorenWClientTP/GetQuotes";
                if (this.broker.equals("FLATTRADE")) {
                    url = "https://piconnect.flattrade.in/PiConnectTP/GetQuotes";
                }

                String var10000 = values.toString();
                String payload = "jData=" + var10000 + "&jKey=" + this.suserToken;
                RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), payload);
                Request request = (new Request.Builder()).url(url).post(body).header("Content-Type", "application/x-www-form-urlencoded").build();
                response = this.client.newCall(request).execute();

                try {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        JSONObject jsonResponse = new JSONObject(responseBody);
                        String ltp = jsonResponse.optString("lp", "");
                        if (!ltp.equals("")) {
                            this.allTokens.put(strike, token);
                            this.ws.subscribe(exch + "|" + token);
                            var13 = Double.parseDouble(ltp);
                            break label64;
                        }
                    }
                } catch (Throwable var16) {
                    if (response != null) {
                        try {
                            response.close();
                        } catch (Throwable var15) {
                            var16.addSuppressed(var15);
                        }
                    }

                    throw var16;
                }

                if (response != null) {
                    response.close();
                }
            }

            return 0.0;
        }

        if (response != null) {
            response.close();
        }
        System.out.println("end of get Ltp method :" + " value :: " + var13);
        return var13;
    }

    //    public void historicalData(String exchange, String token) {
//        System.out.println("inside historical data method");
//        System.out.println("exchange " + exchange + " token: " + token);
//        try {
//            int interval = 3;
//            String urlString = "https://api.shoonya.com/NorenWClientTP/TPSeries";
//            if (this.broker.equals("FLATTRADE")) {
//                urlString = "https://piconnect.flattrade.in/PiConnectTP/TPSeries";
//            }
//
//            System.out.println("********* api URL: " + urlString);
//            LocalDate endDate = LocalDate.now();
//            endDate = endDate.plusDays(1L);
//            LocalDate startDate = endDate.minusDays(2L);
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//            startDate.format(formatter);
//            endDate.format(formatter);
//            System.out.println("start date: " + startDate.format(formatter));
//            System.out.println("start date: " + endDate.format(formatter));
//            JSONObject values = new JSONObject();
//            values.put("ordersource", "API");
//            values.put("uid", this.userName);
//            values.put("exch", exchange);
//            values.put("token", token);
//            values.put("st", "" + startDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond());
//            values.put("et", "" + endDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond());
//            values.put("intrv", "" + interval);
//            String var10000 = values.toString();
//            String payload = "jData=" + var10000 + "&jKey=" + this.suserToken;
//            System.out.println("Payload: " + payload);
//            URL url = new URL(urlString);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            conn.setDoOutput(true);
//            OutputStream os = conn.getOutputStream();
//
//            try {
//                os.write(payload.getBytes());
//                os.flush();
//            } catch (Throwable var21) {
//                if (os != null) {
//                    try {
//                        os.close();
//                    } catch (Throwable var20) {
//                        var21.addSuppressed(var20);
//                    }
//                }
//
//                throw var21;
//            }
//
//            if (os != null) {
//                os.close();
//            }
//
//            int responseCode = conn.getResponseCode();
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//            try {
//                StringBuilder response = new StringBuilder();
//
//                String line;
//                while ((line = br.readLine()) != null) {
//                    response.append(line);
//                }
//
//                JSONArray responseJson = new JSONArray(response.toString());
//                System.out.println("Token:: " + token + " response full :: " + responseJson.toString());
//                System.out.println("--------------------------------------------------------------------");
//                this.hisData.put(token, responseJson);
//            } catch (Throwable var22) {
//                try {
//                    br.close();
//                } catch (Throwable var19) {
//                    var22.addSuppressed(var19);
//                }
//
//                throw var22;
//            }
//
//            br.close();
//        } catch (Exception var23) {
//            var23.printStackTrace();
//        }
//
//    }
    public void historicalData(String exchange, String token) {
        System.out.println("📈 Inside historicalData method for exchange: " + exchange + ", token: " + token);

        try {
            int interval = 3;

            // Prepare API URL
            String urlString = broker.equals("FLATTRADE")
                    ? "https://piconnect.flattrade.in/PiConnectTP/TPSeries"
                    : "https://api.shoonya.com/NorenWClientTP/TPSeries";
            System.out.println("🔗 API URL: " + urlString);

            // Prepare start and end dates
            LocalDate endDate = LocalDate.now().plusDays(1);  // End date is tomorrow
            LocalDate startDate = endDate.minusDays(2);       // Start date is two days ago
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            System.out.println("📅 Start Date: " + startDate.format(formatter));
            System.out.println("📅 End Date: " + endDate.format(formatter));

            // Create request payload
            JSONObject values = new JSONObject();
            values.put("ordersource", "API");
            values.put("uid", userName);
            values.put("exch", exchange);
            values.put("token", token);
            values.put("st", String.valueOf(startDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond()));
            values.put("et", String.valueOf(endDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond()));
            values.put("intrv", String.valueOf(interval));

            String payload = "jData=" + values.toString() + "&jKey=" + suserToken;
            System.out.println("📝 Payload: " + payload);

            // Send HTTP POST request
            HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.err.println("❗ Failed to fetch historical data. Response code: " + responseCode);
                return;
            }

            // Read the response
            StringBuilder responseBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    responseBuilder.append(line);
                }
            }

            JSONArray responseJson = new JSONArray(responseBuilder.toString());
            System.out.println("📦 Received historical data for token " + token + ": " + responseJson);
            System.out.println("--------------------------------------------------------------------");

            // Save data
            hisData.put(token, responseJson);

        } catch (Exception e) {
            System.err.println("❗ Exception in historicalData: " + e.getMessage());
            e.printStackTrace();
        }
    }


    static class Data {
        String source;
        String apkversion;
        String uid;
        String pwd;
        String factor2;
        String vc;
        String appkey;
        String imei;

        public Data(String source, String apkversion, String uid, String pwd, String factor2, String vc, String appkey, String imei) {
            this.source = source;
            this.apkversion = apkversion;
            this.uid = uid;
            this.pwd = pwd;
            this.factor2 = factor2;
            this.vc = vc;
            this.appkey = appkey;
            this.imei = imei;
        }
    }
}
