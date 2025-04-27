//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ram.code;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class orderHandler {
    NorenApi api = null;
    String userName = "";
    String suserToken = "";
    OkHttpClient client = null;
    JSONArray positions = null;
    JSONArray orders = null;
    DefaultTableModel allPosModel = null;

    orderHandler(NorenApi api) {
        this.api = api;
        this.userName = this.api.userName;
        this.suserToken = this.api.suserToken;
        this.client = this.api.client;
    }

    public String searchStrike(String strike) {
        Pattern pattern = Pattern.compile("NIFTY|BANKNIFTY|FINNIFTY|MIDCPNIFTY|SENSEX");
        Matcher matcher = pattern.matcher(strike);
        return matcher.find() ? matcher.group() : null;
    }

    public void getPositions() {
        JSONObject values = new JSONObject();
        values.put("uid", this.userName);
        values.put("actid", this.userName);
        String var10000 = values.toString();
        String payload = "jData=" + var10000 + "&jKey=" + this.suserToken;
        System.out.println("Request Payload: " + payload);
        String url = "https://api.shoonya.com/NorenWClientTP/PositionBook";
        if (this.api.broker.equals("FLATTRADE")) {
            url = "https://piconnect.flattrade.in/PiConnectTP/PositionBook";
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), payload);
        Request request = (new Request.Builder()).url(url).post(body).header("Content-Type", "application/x-www-form-urlencoded").build();

        try {
            Response response = this.client.newCall(request).execute();

            try {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    if (responseBody.startsWith("[")) {
                        this.positions = new JSONArray(responseBody);
                    }
                } else {
                    this.getPositions();
                }
            } catch (Throwable var10) {
                if (response != null) {
                    try {
                        response.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }
                }

                throw var10;
            }

            if (response != null) {
                response.close();
            }
        } catch (IOException var11) {
            Logger.getLogger(orderHandler.class.getName()).log(Level.SEVERE, (String) null, var11);
        }

    }

    public void getOrders() throws IOException {
        JSONObject values = new JSONObject();
        values.put("uid", this.userName);
        String var10000 = values.toString();
        String payload = "jData=" + var10000 + "&jKey=" + this.suserToken;
        String url = "https://api.shoonya.com/NorenWClientTP/OrderBook";
        if (this.api.broker.equals("FLATTRADE")) {
            url = "https://piconnect.flattrade.in/PiConnectTP/OrderBook";
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), payload);
        Request request = (new Request.Builder()).url(url).post(body).header("Content-Type", "application/x-www-form-urlencoded").build();
        Response response = this.client.newCall(request).execute();

        try {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println(responseBody);
                if (responseBody.startsWith("[")) {
                    JSONArray responseArray = new JSONArray(responseBody);
                    this.orders = responseArray;
                }
            }
        } catch (Throwable var10) {
            if (response != null) {
                try {
                    response.close();
                } catch (Throwable var9) {
                    var10.addSuppressed(var9);
                }
            }

            throw var10;
        }

        if (response != null) {
            response.close();
        }

    }

    //    public JSONObject searchOption(String exchange, String searchText) throws UnsupportedEncodingException, IOException {
//        System.out.println("inside search option method ***********");
//        System.out.println(searchText);
//        JSONObject values = new JSONObject();
//        values.put("uid", this.userName);
//        values.put("exch", exchange);
//        values.put("stext", searchText);
//        String var10000 = values.toString();
//        String payload = "jData=" + var10000 + "&jKey=" + this.suserToken;
//        String url = "https://api.shoonya.com/NorenWClientTP/SearchScrip";
//        if (this.api.broker.equals("FLATTRADE")) {
//            url = "https://piconnect.flattrade.in/PiConnectTP/SearchScrip";
//        }
//
//        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), payload);
//        System.out.println("before :: method inside method call");
//        Request request = (new Request.Builder()).url(url).post(body).header("Content-Type", "application/x-www-form-urlencoded").build();
//        Response response = this.client.newCall(request).execute();
//        System.out.println("after inside method call");
////        System.out.println("Rameshwar :: Response Body: " + response.);
//
//        label56: {
//            JSONObject var13;
//            try {
//                if (!response.isSuccessful()) {
//                    System.out.println("Request failed. Response Code: " + response.code());
//                    System.out.println("Response Body: " + response.body().string());
//                    break label56;
//                }
////                System.out.println("Rameshwar :: Response Body: " + response.body().string());
//                String responseBody = response.body().string();
//                JSONObject responseObject = new JSONObject(responseBody);
//                if (!"Ok".equals(responseObject.getString("stat"))) {
//                    break label56;
//                }
//
//                JSONArray valuesArray = responseObject.getJSONArray("values");
//                JSONObject valueObject = valuesArray.getJSONObject(0);
//                if (searchText.equals("NIFTY FUT")) {
//                    valueObject = valuesArray.getJSONObject(1);
//
//                }
//
//                var13 = valueObject;
//            } catch (Throwable var15) {
//                if (response != null) {
//                    try {
//                        response.close();
//                    } catch (Throwable var14) {
//                        var15.addSuppressed(var14);
//                    }
//                }
//
//                throw var15;
//            }
//
//            if (response != null) {
//                response.close();
//            }
//
//            return var13;
//        }
//
//        if (response != null) {
//            response.close();
//        }
//
//        return null;
//    }
    public JSONObject searchOption(String exchange, String searchText) throws UnsupportedEncodingException, IOException {
        System.out.println("🔍 Inside searchOption method: Searching for " + searchText);

        // Prepare request payload
        JSONObject requestData = new JSONObject();
        requestData.put("uid", userName);
        requestData.put("exch", exchange);
        requestData.put("stext", searchText);

        String payload = "jData=" + requestData.toString() + "&jKey=" + suserToken;

        // Select API URL based on broker
        String url = api.broker.equals("FLATTRADE")
                ? "https://piconnect.flattrade.in/PiConnectTP/SearchScrip"
                : "https://api.shoonya.com/NorenWClientTP/SearchScrip";

        // Create HTTP request
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), payload);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                System.err.println("❗ Request failed. Response Code: " + response.code());
                System.err.println("❗ Response Body: " + (response.body() != null ? response.body().string() : "null"));
                return null;
            }

            if (response.body() == null) {
                System.err.println("❗ Response body is empty.");
                return null;
            }

            String responseBody = response.body().string();
            JSONObject responseObject = new JSONObject(responseBody);

            if (!"Ok".equalsIgnoreCase(responseObject.optString("stat"))) {
                System.err.println("❗ API returned error status: " + responseObject.optString("stat"));
                return null;
            }

            JSONArray valuesArray = responseObject.optJSONArray("values");
            if (valuesArray == null || valuesArray.isEmpty()) {
                System.err.println("❗ No option data found in response.");
                return null;
            }

            // Default select the first value
            JSONObject selectedOption = valuesArray.getJSONObject(0);

            // Special case: for "NIFTY FUT", select second entry if exists
            if ("NIFTY FUT".equalsIgnoreCase(searchText) && valuesArray.length() > 1) {
                selectedOption = valuesArray.getJSONObject(1);
            }
            System.out.println("select option mehod returns ::"+selectedOption.toString());
            return selectedOption;

        } catch (IOException e) {
            System.err.println("❗ IOException in searchOption: " + e.getMessage());
            throw e;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }


    public void getAllPositions() throws IOException {
        System.out.println("*********************----inside getAllPositions method--**************************");

        this.getPositions();
        this.getOrders();
        if (this.positions != null) {
            ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> allPositions = this.api.allPositions;

            for (int i = 0; i < this.positions.length(); ++i) {
                JSONObject myPos = this.positions.getJSONObject(i);
                System.out.println("***************************************");
                System.out.println(myPos);
                String strike = myPos.getString("tsym");
                String product = myPos.getString("prd");
                String token = myPos.getString("token");
                String exch = myPos.getString("exch");
//                int avgQty = 0;
                double avgPrice = 0.0;
                String tranType = "";
                if (this.searchStrike(strike) != null) {
                    int avgQty = Integer.parseInt(myPos.getString("cfbuyqty"));
                    if (avgQty > 0) {
                        avgPrice = Double.parseDouble(myPos.getString("cfbuyavgprc"));
                        tranType = "B";
                    } else {
                        avgQty = Math.abs(Integer.parseInt(myPos.getString("cfsellqty")));
                        if (avgQty > 0) {
                            avgPrice = Double.parseDouble(myPos.getString("cfsellavgprc"));
                            tranType = "S";
                        }
                    }

                    this.updatePosition(strike, product, token, avgQty, avgPrice, exch, tranType);
                }
            }

            System.out.println("All Positions: " + String.valueOf(allPositions));
            this.updateTable();
            this.addOpenOrders();
            System.out.println("*********************----end of getAllPositions method--**************************");

        }

    }

    public void updatePosition(String strike, String product, String token, int avgQty, double avgPrice, String exch, String tranType) {
        double pnl = 0.0;
        int pendingQty = 0;
        if (this.orders != null) {
            for (int i = this.orders.length() - 1; i >= 0; --i) {
                JSONObject myPos = this.orders.getJSONObject(i);
                String status = myPos.getString("status");
                String prd = myPos.getString("prd");
                String name = myPos.getString("tsym");
                String side = myPos.getString("trantype");
                if (status.equals("COMPLETE") && prd.equals(product) && name.equals(strike)) {
                    double price = Double.parseDouble(myPos.getString("avgprc"));
                    int qty = Integer.parseInt(myPos.getString("fillshares"));
                    if (tranType.equals("") || avgQty == 0) {
                        tranType = side;
                    }

                    if (side.equals(tranType)) {
                        avgPrice = avgPrice * (double) avgQty + price * (double) qty;
                        avgQty += qty;
                        if (avgQty == 0) {
                            tranType = "";
                            avgPrice = 0.0;
                        } else {
                            avgPrice /= (double) avgQty;
                        }
                    } else if (!side.equals(tranType)) {
                        double profit = (double) qty * (price - avgPrice);
                        if (tranType.equals("S")) {
                            profit = -profit;
                        }

                        pnl += profit;
                        avgQty -= qty;
                        if (avgQty == 0) {
                            avgPrice = 0.0;
                        } else if (avgQty < 0) {
                            avgQty = Math.abs(avgQty);
                            avgPrice = price;
                            tranType = side;
                        }
                    }
                } else if (status.equals("OPEN") && prd.equals(product) && name.equals(strike)) {
                    if (tranType.equals("")) {
                        tranType = side;
                    }

                    if (!side.equals(tranType)) {
                        pendingQty += Integer.parseInt(myPos.getString("qty"));
                    }
                }
            }
        }

        avgQty -= pendingQty;
        String order_id = strike + product;
        ConcurrentHashMap<String, Object> positionDetails = new ConcurrentHashMap();
        positionDetails.put("strike", strike);
        positionDetails.put("token", token);
        positionDetails.put("product", product);
        positionDetails.put("tranType", tranType);
        positionDetails.put("avgQty", avgQty);
        positionDetails.put("avgPrice", (double) Math.round(avgPrice * 100.0) / 100.0);
        positionDetails.put("pendingQty", pendingQty);
        positionDetails.put("pnl", (double) Math.round(pnl * 100.0) / 100.0);
        positionDetails.put("totPnl", (double) Math.round(pnl * 100.0) / 100.0);
        ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> allPositions = this.api.allPositions;
        allPositions.put(order_id, positionDetails);
        if (!this.api.allTokens.containsKey(token)) {
            this.api.allTokens.put(strike, token);
            this.api.ws.subscribe(exch + "|" + token);
        }

    }

    public void addPosition(String strike, String product) {
        try {
            this.getAllPositions();
            if (this.positions != null) {
                ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> allPositions = this.api.allPositions;

                for (int i = 0; i < this.positions.length(); ++i) {
                    JSONObject myPos = this.positions.getJSONObject(i);
                    String name = myPos.getString("tsym");
                    String prod = myPos.getString("prd");
                    String token = myPos.getString("token");
                    String exch = myPos.getString("exch");
//                    int avgQty = false;
                    double avgPrice = 0.0;
                    String tranType = "";
                    if (this.searchStrike(strike) != null && strike.equals(name) && prod.equals(product)) {
                        int avgQty = Integer.parseInt(myPos.getString("cfbuyqty"));
                        if (avgQty > 0) {
                            avgPrice = Double.parseDouble(myPos.getString("cfbuyavgprc"));
                            tranType = "B";
                        } else {
                            avgQty = Integer.parseInt(myPos.getString("cfsellqty"));
                            if (avgQty > 0) {
                                avgPrice = Double.parseDouble(myPos.getString("cfsellavgprc"));
                                tranType = "S";
                            }
                        }

                        this.addtoTool(strike, product, token, avgQty, avgPrice, exch, tranType);
                    }
                }
            }
        } catch (IOException var14) {
        }

    }

    public void addtoTool(String strike, String product, String token, int avgQty, double avgPrice, String exch, String tranType) {
        int pendingQty = 0;
        if (this.orders != null) {
            for (int i = this.orders.length() - 1; i >= 0; --i) {
                JSONObject myPos = this.orders.getJSONObject(i);
                String status = myPos.getString("status");
                String prd = myPos.getString("prd");
                String name = myPos.getString("tsym");
                String side = myPos.getString("trantype");
                if (status.equals("COMPLETE") && prd.equals(product) && name.equals(strike)) {
                    double price = Double.parseDouble(myPos.getString("avgprc"));
                    int qty = Integer.parseInt(myPos.getString("fillshares"));
                    if (tranType.equals("") || avgQty == 0) {
                        tranType = side;
                    }

                    if (side.equals(tranType)) {
                        avgPrice = avgPrice * (double) avgQty + price * (double) qty;
                        avgQty += qty;
                        if (avgQty == 0) {
                            tranType = "";
                            avgPrice = 0.0;
                        } else {
                            avgPrice /= (double) avgQty;
                        }
                    } else if (!side.equals(tranType)) {
                        avgQty -= qty;
                        if (avgQty == 0) {
                            avgPrice = 0.0;
                        } else if (avgQty < 0) {
                            avgQty = Math.abs(avgQty);
                            avgPrice = price;
                            tranType = side;
                        }
                    }
                } else if (status.equals("OPEN") && prd.equals(product) && name.equals(strike)) {
                    if (tranType.equals("")) {
                        tranType = side;
                    }

                    if (!side.equals(tranType)) {
                        pendingQty += Integer.parseInt(myPos.getString("qty"));
                    }
                }
            }
        }

        avgQty -= pendingQty;
        if (avgQty + pendingQty <= 0) {
            Map<String, Map<String, Object>> pnlData = this.api.ws.pnlData;
            if (pnlData.containsKey(strike + product)) {
                pnlData.remove(strike + product);
                this.updatePnlTable();
            }
        } else {
            this.applyRisk(strike, product, token, avgQty, avgPrice, exch, tranType, pendingQty);
            this.api.allTokens.put(strike, token);
            this.api.ws.subscribe(exch + "|" + token);
        }

    }

    public void applyRisk(String strike, String product, String token, int avgQty, double avgPrice, String exch, String tranType, int pendingQty) {
        Map<String, Object> riskFields = this.defaultFields(strike);
        if (this.api.myUi.riskFields.containsKey(strike)) {
            riskFields = (Map) this.api.myUi.riskFields.get(strike);
        }

        System.out.println(riskFields);
        double liveSl = 0.0;
        double liveTarget = 0.0;
        boolean trailStarted = false;
        boolean sl = (Boolean) riskFields.get("sl");
        double slValue = (Double) riskFields.get("slValue");
        boolean target = (Boolean) riskFields.get("target");
        double targetValue = (Double) riskFields.get("targetValue");
        boolean indexSl = (Boolean) riskFields.get("indexSl");
        double indexSlValue = (Double) riskFields.get("indexSlValue");
        boolean indexTarget = (Boolean) riskFields.get("indexTarget");
        double indexTargetValue = (Double) riskFields.get("indexTargetValue");
        boolean slTrail = (Boolean) riskFields.get("slTrail");
        boolean trgtTrail = (Boolean) riskFields.get("trgtTrail");
        double trailValue = (Double) riskFields.get("trailValue");
        Map<String, Map<String, Object>> pnlData = this.api.ws.pnlData;
        String index = this.searchStrike(strike);
        double slPoints = 0.0;
        double indexSlPoints = 0.0;
        if (this.api.myUi.jComboBox1.getSelectedItem().toString().endsWith("FUT") && !index.equals("SENSEX")) {
            index = index + " FUT";
        }

        String indexToken = (String) ((Map) this.api.indexToken.get(index)).get("token");
        double indexAvg = (Double) this.api.ws.extractedData.get(indexToken);
        if (!pnlData.containsKey(strike + product)) {
            String cpType = this.getCpType(strike);
            if (sl) {
                if (tranType.equals("B")) {
                    liveSl = avgPrice - slValue;
                } else if (tranType.equals("S")) {
                    liveSl = avgPrice + slValue;
                }

                slPoints = slValue;
            }

            if (target) {
                if (tranType.equals("B")) {
                    liveTarget = avgPrice + targetValue;
                } else if (tranType.equals("S")) {
                    liveTarget = avgPrice - targetValue;
                }
            }

            if (indexSl) {
                liveSl = indexSlValue;
                indexSlPoints = Math.abs(indexAvg - indexSlValue);
            }

            if (indexTarget) {
                liveTarget = indexTargetValue;
            }

            Map<String, Object> fields = new HashMap();
            boolean active = false;
            fields.put("strike", strike);
            fields.put("exch", exch);
            fields.put("product", product);
            fields.put("token", token);
            fields.put("avgQty", avgQty);
            fields.put("pendingQty", pendingQty);
            fields.put("avgPrice", (double) Math.round(avgPrice * 100.0) / 100.0);
            fields.put("tranType", tranType);
            fields.put("sl", sl);
            fields.put("target", target);
            fields.put("indexSl", indexSl);
            fields.put("indexTarget", indexTarget);
            fields.put("liveSl", liveSl);
            fields.put("liveTarget", liveTarget);
            fields.put("slTrail", slTrail);
            fields.put("trgtTrail", trgtTrail);
            fields.put("trailValue", trailValue);
            fields.put("trailStarted", false);
            fields.put("index", index);
            fields.put("cpType", cpType);
            fields.put("slPoints", slPoints);
            fields.put("indexSlPoints", indexSlPoints);
            fields.put("indexAvg", indexAvg);
            fields.put("indexToken", indexToken);
            fields.put("bep", false);
            fields.put("candleTrail", false);
            fields.put("tf", 1);
            fields.put("profit", 0.0);
            fields.put("locked", false);
            if (fields.get("initQty") == null && avgQty > 0) {
                fields.put("initQty", avgQty);
                fields.put("avgedOrders", 0);
            }

            if (avgQty > 0) {
                active = true;
            }

            fields.put("active", active);
            pnlData.put(strike + product, fields);
        } else {
            Map<String, Object> fields = (Map) pnlData.get(strike + product);
            fields.put("avgQty", avgQty);
            fields.put("avgPrice", avgPrice);
            fields.put("pendingQty", pendingQty);
            if ((Boolean) fields.get("sl")) {
                if (tranType.equals("B")) {
                    liveSl = avgPrice - slValue;
                } else if (tranType.equals("S")) {
                    liveSl = avgPrice + slValue;
                }

                slPoints = slValue;
            }

            if ((Boolean) fields.get("target")) {
                if (tranType.equals("B")) {
                    liveTarget = avgPrice + targetValue;
                } else if (tranType.equals("S")) {
                    liveTarget = avgPrice - targetValue;
                }
            }

            if ((Boolean) fields.get("indexSl")) {
                liveSl = (Double) fields.get("liveSl");
                indexSlPoints = Math.abs(indexAvg - liveSl);
            }

            if ((Boolean) fields.get("indexTarget")) {
                liveTarget = (Double) fields.get("liveTarget");
            }

            fields.put("liveSl", liveSl);
            fields.put("liveTarget", liveTarget);
            fields.put("slPoints", slPoints);
            fields.put("indexSlPoints", indexSlPoints);
            if ((Integer) fields.get("avgQty") > 0) {
                fields.put("active", true);
            }

            pnlData.put(strike + product, fields);
        }

        this.updatePnlTable();
    }

    public String getCpType(String strike) {
        for (int i = strike.length() - 1; i >= 0; --i) {
            char currentChar = strike.charAt(i);
            if (currentChar == 'P' || currentChar == 'C') {
                return "" + currentChar + "E";
            }
        }

        return null;
    }

    public Map<String, Object> defaultFields(String strike) {
        Map<String, Object> fields = new HashMap();
        fields.put("strike", strike);
        fields.put("sl", false);
        fields.put("slValue", 0.0);
        fields.put("target", false);
        fields.put("targetValue", 0.0);
        fields.put("indexSl", false);
        fields.put("indexSlValue", 0.0);
        fields.put("indexTarget", false);
        fields.put("indexTargetValue", 0.0);
        fields.put("slTrail", false);
        fields.put("trgtTrail", false);
        fields.put("trailValue", 0.0);
        return fields;
    }

    public void updatePnlTable() {
        Map<String, String> pnlCache = this.api.ws.pnlCache;
        pnlCache.clear();
        Map<String, Map<String, Object>> pnlData = this.api.ws.pnlData;
        controlPanel var10000 = this.api.myUi;
        DefaultTableModel tempModel = (DefaultTableModel) controlPanel.pnlTable.getModel();

        while (tempModel.getRowCount() > 0) {
            tempModel.removeRow(0);
        }

        DefaultTableModel miniModel = (DefaultTableModel) this.api.myUi.mini.jTable1.getModel();

        while (miniModel.getRowCount() > 0) {
            miniModel.removeRow(0);
        }

        int i = 0;

        for (Iterator var6 = pnlData.entrySet().iterator(); var6.hasNext(); ++i) {
            Map.Entry<String, Map<String, Object>> entry = (Map.Entry) var6.next();
            String key = (String) entry.getKey();
            Map<String, Object> values = (Map) entry.getValue();
            tempModel.addRow(new Object[]{key, values.get("tranType"), "0", "0", "EXIT", values.get("avgQty"), "-", values.get("liveSl"), "+", values.get("avgPrice"), "-", values.get("liveTarget"), "+", "H/L"});
            miniModel.addRow(new Object[]{key, "EXIT", "-", values.get("liveSl"), "+", "0", "-", values.get("liveTarget"), "+", "H/L"});
            String token = values.get("token").toString();
            if (!pnlCache.containsKey(token)) {
                String var10002 = values.get("strike").toString();
                pnlCache.put(token, var10002 + "_" + i);
            } else {
                String var12 = (String) pnlCache.get(token);
                String str = var12 + "_" + i;
                pnlCache.put(token, str);
            }
        }

    }

    public void addOpenOrders() {
        if (this.orders != null) {
            controlPanel var10000 = this.api.myUi;
            DefaultTableModel tempModel = (DefaultTableModel) controlPanel.orderTable.getModel();

            while (tempModel.getRowCount() > 0) {
                tempModel.removeRow(0);
            }

            for (int i = this.orders.length() - 1; i >= 0; --i) {
                JSONObject myPos = this.orders.getJSONObject(i);
                String status = myPos.getString("status");
                String strike = myPos.getString("tsym");
                String order_id = myPos.getString("norenordno");
                String avgPrice = myPos.getString("prc");
                String qty = myPos.getString("qty");
                if (this.searchStrike(strike) != null && status.equals("OPEN")) {
                    tempModel.addRow(new Object[]{strike, order_id, qty, avgPrice, "MARKET", "CANCEL"});
                }
            }
        }

    }

    public void cancelAll() {
        for (int i = this.orders.length() - 1; i >= 0; --i) {
            JSONObject myPos = this.orders.getJSONObject(i);
            String status = myPos.getString("status");
            String strike = myPos.getString("tsym");
            String order_id = myPos.getString("norenordno");
            if (this.searchStrike(strike) != null && status.equals("OPEN")) {
                this.cancelOrder(order_id);
            }
        }

    }

    public void marketAll() {
        for (int i = this.orders.length() - 1; i >= 0; --i) {
            JSONObject myPos = this.orders.getJSONObject(i);
            String status = myPos.getString("status");
            String strike = myPos.getString("tsym");
            String order_id = myPos.getString("norenordno");
            String qty = myPos.getString("qty");
            String exch = "NFO";
            if (strike.contains("SENSEX")) {
                exch = "BFO";
            }

            if (this.searchStrike(strike) != null && status.equals("OPEN")) {
                this.marketOrder(order_id, exch, strike, qty);
            }
        }

    }

    public void updateTable() {
        Map<String, String> posCache = this.api.ws.posCache;
        posCache.clear();
        this.allPosModel = (DefaultTableModel) this.api.myUi.allPosTable.getModel();

        while (this.allPosModel.getRowCount() > 0) {
            this.allPosModel.removeRow(0);
        }

        double[] pnl = new double[]{0.0};
        ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> allPositions = this.api.allPositions;
        AtomicInteger i = new AtomicInteger(0);
        allPositions.forEach((key, value) -> {
            String name = (String) value.get("strike");
            String token = (String) value.get("token");
            this.allPosModel.addRow(new Object[]{value.get("strike"), value.get("product"), value.get("avgQty"), value.get("avgPrice"), value.get("pnl"), "ADD TOOL"});
            double profit = Double.parseDouble(value.get("pnl").toString());
            pnl[0] += profit;
            if (!posCache.containsKey(token)) {
                posCache.put(token, name + "_" + String.valueOf(i));
            } else {
                String var10000 = (String) posCache.get(token);
                String str = var10000 + "_" + String.valueOf(i);
                posCache.put(token, str);
            }

            i.incrementAndGet();
        });
        if (this.allPosModel.getRowCount() > 0) {
            this.allPosModel.addRow(new Object[]{"", "", "", ""});
            this.allPosModel.setValueAt(pnl[0], this.allPosModel.getRowCount() - 1, 4);
        }

    }

    public void cancelOrder(String order_no) {
        String url = "https://api.shoonya.com/NorenWClientTP/CancelOrder";
        if (this.api.broker.equals("FLATTRADE")) {
            url = "https://piconnect.flattrade.in/PiConnectTP/CancelOrder";
        }

        JSONObject values = new JSONObject();
        values.put("uid", this.userName);
        values.put("norenordno", order_no);
        this.processPayload(values, url);
    }

    public void marketOrder(String order_no, String exch, String strike, String qty) {
        String url = "https://api.shoonya.com/NorenWClientTP/ModifyOrder";
        if (this.api.broker.equals("FLATTRADE")) {
            url = "https://piconnect.flattrade.in/PiConnectTP/ModifyOrder";
        }

        JSONObject values = new JSONObject();
        values.put("uid", this.userName);
        values.put("actid", this.userName);
        values.put("norenordno", order_no);
        values.put("exch", exch);
        values.put("tsym", strike);
        values.put("qty", qty);
        values.put("prctyp", "MKT");
        values.put("prc", "0");
        this.processPayload(values, url);
    }

    public void modifyLimit(String order_no, String exch, String strike, String qty, double price) {
        String url = "https://api.shoonya.com/NorenWClientTP/ModifyOrder";
        if (this.api.broker.equals("FLATTRADE")) {
            url = "https://piconnect.flattrade.in/PiConnectTP/ModifyOrder";
        }

        JSONObject values = new JSONObject();
        values.put("uid", this.userName);
        values.put("actid", this.userName);
        values.put("norenordno", order_no);
        values.put("exch", exch);
        values.put("tsym", strike);
        values.put("qty", qty);
        values.put("prctyp", "LMT");
        values.put("prc", price);
        this.processPayload(values, url);
    }

    public void processPayload(JSONObject values, String urlString) {
        String var10000 = values.toString();
        String payload = "jData=" + var10000 + "&jKey=" + this.suserToken;
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), payload);
        Request request = (new Request.Builder()).url(urlString).post(body).header("Content-Type", "application/x-www-form-urlencoded").build();

        try {
            Response response = this.client.newCall(request).execute();

            try {
                if (response.isSuccessful()) {
                    String var7 = response.body().string();
                } else {
                    System.out.println("Request failed. Response Code: " + response.code());
                    System.out.println("Response Body: " + response.body().string());
                }
            } catch (Throwable var10) {
                if (response != null) {
                    try {
                        response.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }
                }

                throw var10;
            }

            if (response != null) {
                response.close();
            }
        } catch (IOException var11) {
            Logger.getLogger(orderHandler.class.getName()).log(Level.SEVERE, (String) null, var11);
        }

    }
}
