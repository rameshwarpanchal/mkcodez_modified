//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ram.code;

import com.ram.code.NorenApi;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.json.JSONArray;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpressionEvaluator {
    NorenApi api;
    Map<String, String> allTokens;
    Map<String, Double> extractedData;
    Map<String, String> tokenCache;
    Map<String, String> indexShorts = new HashMap();
    Map<String, String> ohlc = new HashMap();
    boolean error = false;
    ScriptEngine engine;

    public ExpressionEvaluator(NorenApi api) {
        this.api = api;
        this.allTokens = this.api.allTokens;
        this.extractedData = this.api.ws.extractedData;
        this.tokenCache = new HashMap();
        this.indexShorts.put("N", "NIFTY");
        this.indexShorts.put("B", "BANKNIFTY");
        this.indexShorts.put("F", "FINNIFTY");
        this.indexShorts.put("M", "MIDCPNIFTY");
        this.indexShorts.put("S", "SENSEX");
        this.indexShorts.put("NF", "NIFTY FUT");
        this.indexShorts.put("BF", "BANKNIFTY FUT");
        this.indexShorts.put("FF", "FINNIFTY FUT");
        this.indexShorts.put("MF", "MIDCPNIFTY FUT");
        this.ohlc.put("O", "into");
        this.ohlc.put("H", "inth");
        this.ohlc.put("L", "intl");
        this.ohlc.put("C", "intc");
        this.createEngine();

    }


    public void createEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        this.engine = manager.getEngineByName("JavaScript");

        this.engine.put("rsi", (QuadFunction<String, Integer, Integer, Integer, Double>) this::rsi);
        this.engine.put("ema", (PentaFunction<String, Integer, Integer, String, Integer, Double>) this::ema);
        this.engine.put("sma", (PentaFunction<String, Integer, Integer, String, Integer, Double>) this::sma);
        this.engine.put("wma", (PentaFunction<String, Integer, Integer, String, Integer, Double>) this::wma);
        this.engine.put("supertrend", (PentaFunction<String, Integer, Integer, Integer, Integer, Double>) this::supertrend);


        this.engine.put("ltp", (Function<String, Double>) this::ltp);
        this.engine.put("pdLow", (Function<String, Double>) this::pdLow);
        this.engine.put("pdHigh", (Function<String, Double>) this::pdHigh);
        this.engine.put("macd", (TriFunction<String, Integer, Integer, Double>) this::macd);
        this.engine.put("signal",(TriFunction<String, Integer, Integer, Double>) this::signal);
        this.engine.put("dip", (TriFunction<String, Integer, Integer, Double>) this::dip);
        this.engine.put("dim", (TriFunction<String, Integer, Integer, Double>) this::dim);
        this.engine.put("adx", (TriFunction<String, Integer, Integer, Double>) this::adx);
        this.engine.put("uBol", (TriFunction<String, Integer, Integer, Double>) this::uBol);
        this.engine.put("dBol", (TriFunction<String, Integer, Integer, Double>) this::dBol);
        this.engine.put("mBol", (TriFunction<String, Integer, Integer, Double>) this::mBol);
        this.engine.put("stoch", (QuadFunction<String, Integer, String, Integer, Double>) this::stoch);

        this.engine.put("close", (TriFunction<String, Integer, Integer, Double>) this::close);
        this.engine.put("high", (TriFunction<String, Integer, Integer, Double>) this::high);
        this.engine.put("low", (TriFunction<String, Integer, Integer, Double>) this::low);

        this.engine.put("openn", (TriFunction<String, Integer, Integer, Double>) this::openn);


        this.engine.put("nClose", (Function<Void, Double>) v -> this.nClose());
        this.engine.put("bnClose", (Function<Void, Double>) v -> this.bnClose());
        this.engine.put("fClose", (Function<Void, Double>) v -> this.fClose());
        this.engine.put("mClose", (Function<Void, Double>) v -> this.mClose());
        this.engine.put("sClose", (Function<Void, Double>) v -> this.sClose());
        this.engine.put("nfClose", (Function<Void, Double>) v -> this.nfClose());
        this.engine.put("bnfClose", (Function<Void, Double>) v -> this.bnfClose());
        this.engine.put("ffClose", (Function<Void, Double>) v -> this.ffClose());
        this.engine.put("mfClose", (Function<Void, Double>) v -> this.mfClose());
    }
    public Object evaluateExpression(String expression) {
        try {
            this.error = false;
            Object result = this.engine.eval(expression);
            return this.error ? null : result;
        } catch (ScriptException var3) {
            return null;
        }
    }

    public double nClose() {
        try {
            // Check if the token for "NIFTY" is already cached
            if (this.tokenCache.containsKey("NIFTY")) {
                // Return the value from extractedData using the cached token
                return (Double) this.extractedData.get(this.tokenCache.get("NIFTY"));
            }

            // If the token is not cached, fetch it from the API indexToken map
            String token = (String) ((Map) this.api.indexToken.get("NIFTY")).get("token");

            // Cache the token for future reference to avoid repeated API calls
            this.tokenCache.put("NIFTY", token);

            // Return the value from extractedData using the newly cached token
            return (Double) this.extractedData.get(token);

        } catch (Exception e) {
            // Log any exceptions that occur (e.g., NullPointerException, API access issues)
            Logger.getLogger(ExpressionEvaluator.class.getName()).log(Level.SEVERE, null, e);
            return 0.0;  // Return 0.0 as a fallback in case of an error
        }
    }

    public double bnClose() {
        try {
            // Check if the token for "BANKNIFTY" is already cached
            if (this.tokenCache.containsKey("BANKNIFTY")) {
                // Return the value from extractedData using the cached token
                return (Double) this.extractedData.get(this.tokenCache.get("BANKNIFTY"));
            }

            // If the token is not cached, fetch it from the API indexToken map
            String token = (String) ((Map) this.api.indexToken.get("BANKNIFTY")).get("token");

            // Cache the token for future reference to avoid repeated API calls
            this.tokenCache.put("BANKNIFTY", token);

            // Return the value from extractedData using the newly cached token
            return (Double) this.extractedData.get(token);

        } catch (Exception e) {
            // Log any exceptions that occur (e.g., NullPointerException, API access issues)
            Logger.getLogger(ExpressionEvaluator.class.getName()).log(Level.SEVERE, null, e);
            return 0.0;  // Return 0.0 as a fallback in case of an error
        }
    }

    public double fClose() {
        try {
            // Check if the token for "FINNIFTY" is already cached
            if (this.tokenCache.containsKey("FINNIFTY")) {
                // Return the value from extractedData using the cached token
                return (Double) this.extractedData.get(this.tokenCache.get("FINNIFTY"));
            }

            // If the token is not cached, fetch it from the API indexToken map
            String token = (String) ((Map) this.api.indexToken.get("FINNIFTY")).get("token");

            // Cache the token for future reference to avoid repeated API calls
            this.tokenCache.put("FINNIFTY", token);

            // Return the value from extractedData using the newly cached token
            return (Double) this.extractedData.get(token);

        } catch (Exception e) {
            // Log any exceptions that occur, e.g., NullPointerException or API access issues
            Logger.getLogger(ExpressionEvaluator.class.getName()).log(Level.SEVERE, null, e);
            return 0.0;  // Return 0.0 as a fallback in case of an error
        }
    }

    public double sClose() {
        try {
            // Check if the tokenCache already contains the token for "SENSEX"
            if (this.tokenCache.containsKey("SENSEX")) {
                // If found in tokenCache, return the value from extractedData using the cached token
                return (Double) this.extractedData.get(this.tokenCache.get("SENSEX"));
            }

            // If token is not cached, fetch the token from the API indexToken map
            String token = (String) ((Map) this.api.indexToken.get("SENSEX")).get("token");

            // Cache the fetched token for future reference to avoid repeated API calls
            this.tokenCache.put("SENSEX", token);

            // Return the value from extractedData using the newly cached token
            return (Double) this.extractedData.get(token);

        } catch (Exception e) {
            // Handle any exceptions (e.g., null pointer exceptions, API errors)
            Logger.getLogger(ExpressionEvaluator.class.getName()).log(Level.SEVERE, null, e);
            return 0.0;  // Return 0.0 as a fallback in case of an error
        }
    }

    public double mClose() {
        try {
            // Check if the tokenCache already contains the token for "MIDCPNIFTY"
            if (this.tokenCache.containsKey("MIDCPNIFTY")) {
                // If found in tokenCache, return the value from extractedData using the cached token
                return (Double) this.extractedData.get(this.tokenCache.get("MIDCPNIFTY"));
            }

            // If token is not cached, fetch the token from the API indexToken map
            String token = (String) ((Map) this.api.indexToken.get("MIDCPNIFTY")).get("token");

            // Cache the fetched token for future reference to avoid repeated API calls
            this.tokenCache.put("MIDCPNIFTY", token);

            // Return the value from extractedData using the newly cached token
            return (Double) this.extractedData.get(token);

        } catch (Exception e) {
            // Handle any exceptions (e.g., null pointer exceptions, API errors)
            Logger.getLogger(ExpressionEvaluator.class.getName()).log(Level.SEVERE, null, e);
            return 0.0;  // Return 0.0 as a fallback in case of an error
        }
    }

    public double nfClose() {
        try {
            // Check if the tokenCache already contains the token for "NIFTY FUT"
            if (this.tokenCache.containsKey("NIFTY FUT")) {
                // If found in tokenCache, directly return the value from extractedData using the cached token
                return (Double) this.extractedData.get(this.tokenCache.get("NIFTY FUT"));
            }

            // If token is not cached, fetch the token from the API indexToken map
            String token = (String) ((Map) this.api.indexToken.get("NIFTY FUT")).get("token");

            // Cache the fetched token for future reference to avoid repeated API calls
            this.tokenCache.put("NIFTY FUT", token);

            // Return the value from extractedData using the newly cached token
            return (Double) this.extractedData.get(token);

        } catch (Exception e) {
            // Handle any exceptions (e.g., null pointer exceptions, API errors)
            Logger.getLogger(ExpressionEvaluator.class.getName()).log(Level.SEVERE, null, e);
            return 0.0;  // Return 0.0 as a fallback in case of an error
        }
    }

    public double bnfClose() {
        try {
            // Check if the tokenCache already contains the token for "BANKNIFTY FUT"
            if (this.tokenCache.containsKey("BANKNIFTY FUT")) {
                // If found in tokenCache, directly return the value from extractedData using the cached token
                return (Double) this.extractedData.get(this.tokenCache.get("BANKNIFTY FUT"));
            }

            // If token is not cached, fetch the token from the API indexToken map
            String token = (String) ((Map) this.api.indexToken.get("BANKNIFTY FUT")).get("token");

            // Cache the fetched token for future reference to avoid repeated API calls
            this.tokenCache.put("BANKNIFTY FUT", token);

            // Return the value from extractedData using the newly cached token
            return (Double) this.extractedData.get(token);

        } catch (Exception e) {
            // Handle any exceptions (e.g., null pointer exceptions, API errors)
            Logger.getLogger(ExpressionEvaluator.class.getName()).log(Level.SEVERE, null, e);
            return 0.0;  // Return 0.0 as a fallback in case of an error
        }
    }

    public double ffClose() {
        try {
            // Check if the tokenCache already contains the token for "FINNIFTY FUT"
            if (this.tokenCache.containsKey("FINNIFTY FUT")) {
                // If found in tokenCache, directly return the value from extractedData using the cached token
                return (Double) this.extractedData.get(this.tokenCache.get("FINNIFTY FUT"));
            }

            // If token is not cached, fetch the token from the API indexToken map
            String token = (String) ((Map) this.api.indexToken.get("FINNIFTY FUT")).get("token");

            // Cache the fetched token for future reference to avoid repeated API calls
            this.tokenCache.put("FINNIFTY FUT", token);

            // Return the value from extractedData using the newly cached token
            return (Double) this.extractedData.get(token);

        } catch (Exception e) {
            // Handle any exceptions (e.g., null pointer exceptions, API errors)
            Logger.getLogger(ExpressionEvaluator.class.getName()).log(Level.SEVERE, null, e);
            return 0.0;  // Return 0.0 as a fallback in case of an error
        }
    }

    public double mfClose() {
        try {
            // Check if the tokenCache already contains the token for "MIDCPNIFTY FUT"
            if (this.tokenCache.containsKey("MIDCPNIFTY FUT")) {
                // If found in tokenCache, directly return the value from extractedData using the cached token
                return (Double) this.extractedData.get(this.tokenCache.get("MIDCPNIFTY FUT"));
            }

            // If token is not cached, fetch the token from the API indexToken map
            String token = (String) ((Map) this.api.indexToken.get("MIDCPNIFTY FUT")).get("token");

            // Cache the fetched token for future reference to avoid repeated API calls
            this.tokenCache.put("MIDCPNIFTY FUT", token);

            // Return the value from extractedData using the newly cached token
            return (Double) this.extractedData.get(token);

        } catch (Exception e) {
            // Handle any exceptions (e.g., null pointer exceptions, API errors)
            Logger.getLogger(ExpressionEvaluator.class.getName()).log(Level.SEVERE, null, e);
            return 0.0;  // Return 0.0 as a fallback in case of an error
        }
    }

    public double ltp(String symbol) {
        try {
            // Check if the tokenCache already contains the symbol
            if (this.tokenCache.containsKey(symbol)) {
                // If found in tokenCache, return the value from extractedData using the cached token
                return (Double) this.extractedData.get(this.tokenCache.get(symbol));
            }

            // Check if symbol exists in allTokens
            if (this.allTokens.containsKey(symbol)) {
                // If the token exists in allTokens, cache the token and fetch the LTP from extractedData
                String token = this.allTokens.get(symbol);
                if (this.extractedData.containsKey(token)) {
                    this.tokenCache.put(symbol, token);  // Cache the token for future reference
                    return (Double) this.extractedData.get(token);  // Return the value from extractedData
                }
            }

            // If not found in tokenCache or allTokens, determine the exchange and fetch LTP via API
            String exch = "NFO";  // Default exchange
            if (symbol.contains("SENSEX")) {
                exch = "BFO";  // Change exchange to BFO if the symbol contains "SENSEX"
            }

            // Fetch LTP from the API and return the value
            return this.api.getLtp(symbol, exch);
        } catch (IOException e) {
            // Log any I/O exceptions and return 0.0 to indicate failure
            Logger.getLogger(ExpressionEvaluator.class.getName()).log(Level.SEVERE, null, e);
            return 0.0;
        }
    }

    public double close(String sym, int tf, int loc) {
        List<Map<String, Object>> jsonArray = this.parseData(sym, tf);
        Map<String, Object> record = (Map) jsonArray.get(jsonArray.size() - 1 + loc);
        return Double.parseDouble(record.get("intc").toString());
    }

    public double high(String sym, int tf, int loc) {
        List<Map<String, Object>> jsonArray = this.parseData(sym, tf);
        Map<String, Object> record = (Map) jsonArray.get(jsonArray.size() - 1 + loc);
        return Double.parseDouble(record.get("inth").toString());
    }

    public double low(String sym, int tf, int loc) {
        List<Map<String, Object>> jsonArray = this.parseData(sym, tf);
        Map<String, Object> record = (Map) jsonArray.get(jsonArray.size() - 1 + loc);
        return Double.parseDouble(record.get("intl").toString());
    }

    public double openn(String sym, int tf, int loc) {
        List<Map<String, Object>> jsonArray = this.parseData(sym, tf);
        Map<String, Object> record = (Map) jsonArray.get(jsonArray.size() - 1 + loc);
        return Double.parseDouble(record.get("into").toString());
    }

    public double pdLow(String sym) {
        try {
            // Parse the financial data for the symbol "sym" with a fixed timeframe of 5
            List<Map<String, Object>> finData = this.parseData(sym, 5);

            // Ensure there is enough data to process
            if (finData.size() < 2) {
                throw new IllegalArgumentException("Insufficient data to calculate pdLow.");
            }

            // Initialize variables for tracking the lowest value of the day
            int i = finData.size() - 2;  // Start from the second-to-last entry
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((Date) finData.get(finData.size() - 1).get("time"));  // Get the time of the latest data point
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);  // Extract the current day from the calendar

            double res = Double.MAX_VALUE;  // Initialize the result to the maximum possible value (we're looking for the minimum)
            Map<String, Object> out;
            Date time;
            int day;

            // Step 1: Find the first low price of the previous day
            while (i >= 0) {
                out = finData.get(i);
                time = (Date) out.get("time");
                calendar.setTime(time);
                day = calendar.get(Calendar.DAY_OF_MONTH);  // Get the day of the month for the current entry

                // If the day changes, update the `res` value to the lowest of the previous day
                if (day != currentDay) {
                    currentDay = day;
                    res = Double.parseDouble(out.get("intl").toString());  // Initialize res with the current low value
                    i--;  // Move to the previous data point
                    break;
                }
                i--;
            }

            // Step 2: Iterate over the remaining data to find the lowest price for the current day
            while (i >= 0) {
                out = finData.get(i);
                time = (Date) out.get("time");
                calendar.setTime(time);
                day = calendar.get(Calendar.DAY_OF_MONTH);  // Check if the day is still the same

                // If the day has changed, we've reached the end of the current day, so return the result
                if (day != currentDay) {
                    System.out.println(res);  // Log the result for debugging
                    return res;
                }

                // Compare the current low value with `res` and update `res` if needed
                double intlValue = Double.parseDouble(out.get("intl").toString());
                if (intlValue < res) {
                    res = intlValue;  // Update res to the lowest value
                }

                i--;  // Move to the previous data point
            }

            // Return the final result and print the value for debugging
            System.out.println(res);  // Log the result for debugging
            return res;
        } catch (Exception e) {
            // Log any exceptions that occur and set the error flag
            e.printStackTrace();
            this.error = true;
            return 0.0;  // Return 0.0 in case of error
        }
    }

    public double pdHigh(String sym) {
        try {
            // Parse the financial data for the symbol "sym" with a fixed timeframe of 5.
            List<Map<String, Object>> finData = this.parseData(sym, 5);

            // Ensure there is sufficient data to proceed
            if (finData.size() < 2) {
                throw new IllegalArgumentException("Insufficient data to calculate pdHigh.");
            }

            // Set up the calendar object to extract the day of the "current" trading day
            int i = finData.size() - 2;  // Start from the second-to-last entry
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((Date) finData.get(finData.size() - 1).get("time"));  // Get the time of the latest data point
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);  // Extract the current day from the calendar

            double res = 0.0;  // Variable to hold the highest value
            Map<String, Object> out;
            Date time;
            int day;

            // Step 1: Find the first price of the day (highest for previous day)
            while (i >= 0) {
                out = finData.get(i);
                time = (Date) out.get("time");
                calendar.setTime(time);
                day = calendar.get(Calendar.DAY_OF_MONTH);  // Get the day of the month for the current entry

                // If the day changes, update the `res` value to the highest of the previous day
                if (day != currentDay) {
                    currentDay = day;
                    res = Double.parseDouble(out.get("inth").toString());  // Initialize res with the current high value
                    i--;  // Move to the previous data point
                    break;
                }
                i--;
            }

            // Step 2: Iterate over the remaining data to find the highest price for the current day
            while (i >= 0) {
                out = finData.get(i);
                time = (Date) out.get("time");
                calendar.setTime(time);
                day = calendar.get(Calendar.DAY_OF_MONTH);  // Check if the day is still the same

                // If the day has changed, we've reached the end of the current day, so return the result
                if (day != currentDay) {
                    System.out.println(res);
                    return res;
                }

                // Compare the current high value with `res` and update `res` if needed
                double intlValue = Double.parseDouble(out.get("inth").toString());
                if (intlValue > res) {
                    res = intlValue;  // Update res to the highest value
                }

                i--;  // Move to the previous data point
            }

            // Return the final result and print the value for debugging
            System.out.println(res);
            return res;
        } catch (Exception e) {
            // Log any exceptions that occur and set the error flag
            e.printStackTrace();
            this.error = true;
            return 0.0;
        }
    }

    public double wma(String sym, int tf, int period, String kType, int loc) {
        // Ensure kType corresponds to a valid OHLC key (e.g., "close", "open", "high", etc.)
        kType = (String) this.ohlc.get(kType);

        try {
            // Parse financial data for the given symbol and timeframe
            List<Map<String, Object>> finData = this.parseData(sym, tf);

            // Ensure there is enough data for the given period
            if (finData.size() < period) {
                throw new IllegalArgumentException("Insufficient data to calculate WMA.");
            }

            // Step 1: Initialize the variables for weighted sum and weight sum
            double weightedSum = 0.0;
            double weightSum = 0.0;

            // Step 2: Loop through the 'period' data points to calculate the weighted sum
            for (int i = 0; i < period; ++i) {
                double value = getPrice((Map) finData.get(finData.size() - period + loc + i), kType);

                // Apply the weight to the value (weight decreases as we move back in time)
                weightedSum += value * (period - i);
                weightSum += (period - i);
            }

            // Step 3: Calculate the final WMA by dividing the weighted sum by the total weight
            double wma = weightedSum / weightSum;

            // Log and return the final WMA value
            System.out.println("Calculated WMA: " + wma);
            return wma;

        } catch (Exception e) {
            // Handle any exceptions that occur during data parsing or calculation
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error calculating WMA", e);
            this.error = true;
            return 0.0;
        }
    }

    public double ema(String sym, int tf, int period, String kType, int loc) {
        // Ensure kType corresponds to a valid OHLC key (e.g., "close", "open", "high", etc.)
        kType = (String) this.ohlc.get(kType);

        try {
            // Parse financial data for the given symbol and timeframe
            List<Map<String, Object>> jsonArray = this.parseData(sym, tf);

            // Check if the data is valid and contains enough data points for the given period
            if (jsonArray.size() < period) {
                throw new IllegalArgumentException("Insufficient data to calculate EMA.");
            }

            // Step 1: Calculate the Simple Moving Average (SMA) for the initial 'period' values
            double sma = 0.0;
            for (int i = 0; i < period; ++i) {
                Map<String, Object> record = jsonArray.get(i);
                double intc = Double.parseDouble(record.get(kType).toString());
                sma += intc;
            }
            sma /= period;

            // Step 2: Apply the EMA smoothing formula: EMA = (Current Price * k) + (Previous EMA * (1 - k))
            double k = 2.0 / (period + 1);  // Smoothing factor
            double ema = sma;  // Start EMA as the SMA for the first period

            // Step 3: Loop through the remaining data points and apply the EMA formula
            for (int i = period; i < jsonArray.size() + loc; ++i) {
                Map<String, Object> record = jsonArray.get(i);
                double intc = Double.parseDouble(record.get(kType).toString());

                // Update EMA for the current data point
                ema = (intc * k) + (ema * (1.0 - k));
            }

            // Log and return the final EMA value
            System.out.println("Calculated EMA: " + ema);
            return ema;

        } catch (Exception e) {
            // Handle any exceptions that occur during data parsing or calculation
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error calculating EMA", e);
            this.error = true;
            return 0.0;
        }
    }

    public double rsi(String sym, int tf, int period, int loc) {
        // Set up logger for debugging and error tracking
        Logger logger = Logger.getLogger(this.getClass().getName());

        try {
            // Block 1: Parse financial data and initialize variables
            List<Map<String, Object>> finData = this.parseData(sym, tf);  // Parse financial data for symbol and timeframe
            int length = finData.size();  // Get the length of the data list
            double avgGain = 0.0;  // Initialize average gain accumulator
            double avgLoss = 0.0;  // Initialize average loss accumulator

            // Block 2: Calculate initial average gain and loss over the first period
            for (int i = 0; i < period; ++i) {
                Map currentRecord = (Map) finData.get(i);
                Map nextRecord = (Map) finData.get(i + 1);
                double currentPrice = getPrice(currentRecord, "intc");  // Get current price (close)
                double nextPrice = getPrice(nextRecord, "intc");  // Get next price (close)

                // Accumulate gains and losses based on price change
                if (nextPrice > currentPrice) {
                    avgGain += nextPrice - currentPrice;  // Gain
                } else if (nextPrice < currentPrice) {
                    avgLoss += currentPrice - nextPrice;  // Loss
                }
            }

            // Block 3: Calculate the smoothed average gain and loss using the sliding window approach
            avgGain /= (double) period;  // Average gain over period
            avgLoss /= (double) period;  // Average loss over period

            // Block 4: Continue calculating average gain/loss for the rest of the data using a sliding window
            for (int i = period; i < length - 1 + loc; ++i) {
                Map currentRecord = (Map) finData.get(i);
                Map nextRecord = (Map) finData.get(i + 1);
                double currentPrice = getPrice(currentRecord, "intc");  // Current close price
                double nextPrice = getPrice(nextRecord, "intc");  // Next close price

                // Sliding window calculation of smoothed average gain/loss
                if (nextPrice > currentPrice) {
                    avgGain = (avgGain * (double) (period - 1) + (nextPrice - currentPrice)) / (double) period;
                    avgLoss = avgLoss * (double) (period - 1) / (double) period;
                } else if (nextPrice < currentPrice) {
                    avgLoss = (avgLoss * (double) (period - 1) + (currentPrice - nextPrice)) / (double) period;
                    avgGain = avgGain * (double) (period - 1) / (double) period;
                }

                // Log the current smoothed values for debugging (optional)
                if (i % 10 == 0) {  // Log every 10th iteration (can be adjusted)
                    logger.info(String.format("Iteration %d: avgGain = %f, avgLoss = %f", i, avgGain, avgLoss));
                }
            }

            // Block 5: Calculate the RSI value
            if (avgLoss == 0.0) {
                return 100.0;  // If there is no loss, RSI is 100
            } else {
                double rsi = 100.0 - 100.0 / (1.0 + avgGain / avgLoss);  // RSI formula
                logger.info("RSI value: " + rsi);  // Log the RSI value for debugging
                return rsi;  // Return the calculated RSI
            }
        } catch (Exception ex) {
            // Block 6: Error handling
            logger.severe("Error occurred during RSI calculation: " + ex.getMessage());
            this.error = true;  // Set error flag to true
            return 0.0;  // Return default value 0.0 in case of error
        }
    }

    public double sma(String sym, int tf, int period, String kType, int loc) {
        try {
            // Block 1: Validate and fetch kType from the ohlc map
            kType = (String) this.ohlc.get(kType);  // Fetch the key (OHLC) from the 'ohlc' map using 'kType'

            // Block 2: Parse financial data and initialize variables
            List<Map<String, Object>> jsonArray = this.parseData(sym, tf);  // Get financial data based on symbol and time frame
            double sma = 0.0;  // Initialize the sum for Simple Moving Average (SMA)
            int length = jsonArray.size() - 1;  // Calculate the length of the financial data list

            // Block 3: Sum the selected period data for SMA
            for (int i = 0; i < period; ++i) {
                // Fetch the data at index 'length - i + loc' for the given 'kType'
                Map<String, Object> record = (Map) jsonArray.get(length - i + loc);

                // Parse the specific data field (e.g., closing price or any other OHLC value)
                double intc = Double.parseDouble(record.get(kType).toString());

                sma += intc;  // Accumulate the values for the period
            }

            // Block 4: Calculate the final SMA and return the result
            double result = sma / (double) period;  // Calculate SMA by averaging over the period
            System.out.println(result);  // Print the result (optional for debugging)
            return result;  // Return the calculated SMA value

        } catch (Exception var14) {
            // Block 5: Error handling
            this.error = true;  // Set error flag
            return 0.0;  // Return 0 in case of error
        }
    }

    public double supertrend(String sym, int tf, int period, int multiplier, int offset) {
        try {
            // Step 1: Parse financial data
            List<Map<String, Object>> finData = this.parseData(sym, tf);
            int length = finData.size() + offset;

            // Ensure we don't go out of bounds
            if (length > finData.size()) {
                length = finData.size();
            }

            // Initialize variables
            double atr = 0.0;  // Average True Range (ATR)
            double bub = 0.0;  // Upper Band
            double blb = 0.0;  // Lower Band
            double fub = 0.0;  // Final Upper Band
            double flb = 0.0;  // Final Lower Band
            double pfub = 0.0; // Previous Final Upper Band
            double pflb = 0.0; // Previous Final Lower Band
            double pstrend = 0.0; // Previous Supertrend

            // Step 2: Calculate ATR for the first 'period' number of data points
            for (int i = 1; i < period; ++i) {
                // Get high, low, and previous close prices for ATR calculation
                double highPrice = getPrice((Map) finData.get(i), "inth");
                double lowPrice = getPrice((Map) finData.get(i), "intl");
                double previousClose = getPrice((Map) finData.get(i - 1), "intc");

                // ATR formula: max(High-Low, High-PrevClose, PrevClose-Low)
                atr += Math.max(Math.max(highPrice - lowPrice, highPrice - previousClose), previousClose - lowPrice);
            }

            // Average ATR over the 'period' window
            atr /= (double) period;

            // Step 3: Initialize the first bands (upper and lower bands)
            double previousHigh = getPrice((Map) finData.get(period - 1), "inth");
            double previousLow = getPrice((Map) finData.get(period - 1), "intl");

            bub = (previousHigh + previousLow) / 2.0 + (double) multiplier * atr;  // Upper Band
            blb = (previousHigh + previousLow) / 2.0 - (double) multiplier * atr;  // Lower Band
            pfub = bub;  // Previous Upper Band
            pflb = blb;  // Previous Lower Band
            pstrend = bub;  // Supertrend starts at the Upper Band

            // Step 4: Loop over remaining data to calculate bands and supertrend
            for (int i = period; i < length; ++i) {
                double highPrice = getPrice((Map) finData.get(i), "inth");
                double lowPrice = getPrice((Map) finData.get(i), "intl");
                double previousClose = getPrice((Map) finData.get(i - 1), "intc");

                // Recalculate ATR
                atr = (atr * (double) (period - 1) + Math.max(Math.max(highPrice - lowPrice, highPrice - previousClose), previousClose - lowPrice)) / (double) period;

                // Recalculate Upper and Lower Bands
                bub = (highPrice + lowPrice) / 2.0 + (double) multiplier * atr;
                blb = (highPrice + lowPrice) / 2.0 - (double) multiplier * atr;

                // Final Upper Band (FUB) and Final Lower Band (FLB) adjustment based on price action
                fub = (bub < pfub && previousClose > pfub) ? bub : pfub;  // If price closes above previous FUB, use current FUB
                flb = (blb > pflb && previousClose < pflb) ? blb : pflb;  // If price closes below previous FLB, use current FLB

                // Get the current close price
                double currentClosePrice = getPrice((Map) finData.get(i), "intc");

                // Step 5: Determine the Supertrend direction based on closing price and previous trend
                if (pstrend == pfub && currentClosePrice <= fub) {
                    pstrend = fub;  // Trend switches to upper band
                } else if (pstrend == pfub && currentClosePrice >= fub) {
                    pstrend = flb;  // Trend switches to lower band
                } else if (pstrend == pflb && currentClosePrice >= flb) {
                    pstrend = flb;  // Trend stays at the lower band
                } else if (pstrend == pflb && currentClosePrice <= flb) {
                    pstrend = fub;  // Trend switches to upper band
                }

                // Update previous bands for the next iteration
                pfub = fub;
                pflb = flb;
            }

            // Return the final supertrend value
            System.out.println(pstrend);
            return pstrend;
        } catch (Exception ex) {
            // Catch any exception and set the error flag
            this.error = true;
            return 0.0;
        }
    }

    public double macd(String sym, int tf, int offset) {
        try {
            List<Map<String, Object>> finData = this.parseData(sym, tf);
            int length = finData.size() - offset;
            if (length < 26) throw new IllegalArgumentException("Offset too large.");

            final double EMA12_MULTIPLIER = 2.0 / (12 + 1);
            final double EMA26_MULTIPLIER = 2.0 / (26 + 1);

            double ema12 = 0.0, ema26 = 0.0, macd = 0.0;

            // Initialize EMA values with first 12 and 26 prices
            for (int i = 0; i < 12; i++) ema12 += getPrice(finData.get(i), "intc");
            ema12 /= 12.0;

            for (int i = 0; i < 26; i++) ema26 += getPrice(finData.get(i), "intc");
            ema26 /= 26.0;

            // Compute MACD using EMA formula
            for (int k = 26; k < length; k++) {
                double price = getPrice(finData.get(k), "intc");
                ema12 += EMA12_MULTIPLIER * (price - ema12);
                ema26 += EMA26_MULTIPLIER * (price - ema26);
                macd = ema12 - ema26;
            }

            System.out.println(macd);
            return macd;
        } catch (Exception ex) {
            this.error = true;
            return 0.0;
        }
    }


    public double signal(String sym, int tf, int offset) {
        try {
            // Parsing financial data
            List<Map<String, Object>> finData = this.parseData(sym, tf);
            int length = finData.size() - offset;

            // Validate offset value
            if (length < 35) {
                throw new IllegalArgumentException("Offset too large.");
            }

            // Initialize variables for calculations
            double ema12 = 0.0;
            double ema26 = 0.0;

            // Calculate EMA12
            for (int i = 0; i < 12; ++i) {
                ema12 += getPrice((Map) finData.get(i), "intc");
            }
            ema12 /= 12.0;

            // Calculate EMA26
            for (int i = 0; i < 26; ++i) {
                ema26 += getPrice((Map) finData.get(i), "intc");
            }
            ema26 /= 26.0;

            // Calculate MACD values
            List<Double> macdArr = new ArrayList<>();
            for (int i = 26; i < length; ++i) {
                ema12 += 0.15384615384615385 * (getPrice((Map) finData.get(i), "intc") - ema12);
                ema26 += 0.07407407407407407 * (getPrice((Map) finData.get(i), "intc") - ema26);
                double macd = ema12 - ema26;
                macdArr.add(macd);
            }

            // Calculate signal value
            double signal = 0.0;
            for (int i = 0; i < 9; ++i) {
                signal += macdArr.get(i);
            }
            signal /= 9.0;

            // Apply smoothing to signal
            for (int i = 9; i < macdArr.size(); ++i) {
                signal += 0.2 * (macdArr.get(i) - signal);
            }

            // Print and return the signal value
            System.out.println(signal);
            return signal;

        } catch (IllegalArgumentException e) {
            // Handle specific exceptions and log meaningful messages
            this.error = true;
            System.err.println("IllegalArgumentException: " + e.getMessage());
            return 0.0;
        } catch (Exception e) {
            // General error handling, including logging
            this.error = true;
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace(); // Optionally log stack trace for debugging
            return 0.0;
        }
    }

    public double dip(String sym, int tf, int offset) {
        // Parse financial data
        List<Map<String, Object>> finData = this.parseData(sym, tf);
        int length = finData.size() - offset;

        // Validate offset length
        if (length < 28) {
            throw new IllegalArgumentException("Offset too large.");
        }

        // Initialize variables
        double dmPlus = 0.0, dmMinus = 0.0, avgDp = 0.0, avgDm = 0.0, atr = 0.0, adxValue = 0.0, avgAdx = 0.0;
        double prevHigh, prevLow, prevClose, currentDmPlus, currentDmMinus, high, low;

        // Calculate initial values for the first 14 periods
        for (int i = 1; i < 14; ++i) {
            high = getPrice((Map) finData.get(i), "inth");
            low = getPrice((Map) finData.get(i), "intl");
            prevHigh = getPrice((Map) finData.get(i - 1), "inth");
            prevLow = getPrice((Map) finData.get(i - 1), "intl");
            prevClose = getPrice((Map) finData.get(i - 1), "intc");

            // Calculate directional movement values
            currentDmPlus = high - prevHigh;
            currentDmMinus = prevLow - low;

            // Ensure non-negative values for DMPlus and DMMinus
            if (currentDmPlus < 0 && currentDmMinus < 0) {
                currentDmPlus = currentDmMinus = 0;
            } else if (currentDmPlus > currentDmMinus) {
                currentDmMinus = 0;
            } else {
                currentDmPlus = 0;
            }

            // Accumulate values
            dmPlus += currentDmPlus;
            dmMinus += currentDmMinus;
            atr += Math.max(Math.max(high - low, Math.abs(high - prevHigh)), Math.abs(prevHigh - low));
        }

        // Calculate averages for DM and ATR
        avgDp = dmPlus / 14.0;
        avgDm = dmMinus / 14.0;
        atr /= 14.0;

        // Calculate ADX value
        adxValue = (avgDp + avgDm == 0.0) ? 0.0 : Math.abs((avgDp - avgDm) / (avgDp + avgDm));

        // Update avgAdx for smoothing (initially, avgAdx is set to ADX value for period 14)
        avgAdx = adxValue;

        // Iterate over remaining data to calculate values for periods 15 to end
        for (int i = 14; i < length; ++i) {
            high = getPrice((Map) finData.get(i), "inth");
            low = getPrice((Map) finData.get(i), "intl");
            prevHigh = getPrice((Map) finData.get(i - 1), "inth");
            prevLow = getPrice((Map) finData.get(i - 1), "intl");
            prevClose = getPrice((Map) finData.get(i - 1), "intc");

            // Update directional movements
            currentDmPlus = high - prevHigh;
            currentDmMinus = prevLow - low;

            // Ensure non-negative values
            if (currentDmPlus < 0 && currentDmMinus < 0) {
                currentDmPlus = currentDmMinus = 0;
            } else if (currentDmPlus > currentDmMinus) {
                currentDmMinus = 0;
            } else {
                currentDmPlus = 0;
            }

            // Update averages and ATR
            avgDp = (avgDp * 13 + currentDmPlus) / 14;
            avgDm = (avgDm * 13 + currentDmMinus) / 14;
            atr = (atr * 13 + Math.max(Math.max(high - low, Math.abs(high - prevClose)), Math.abs(prevClose - low))) / 14;

            // Update ADX value
            adxValue = (avgDp + avgDm == 0.0) ? 0.0 : Math.abs((avgDp - avgDm) / (avgDp + avgDm));

            // Update avgAdx for smoothing
            avgAdx = (avgAdx * 13 + adxValue) / 14;
        }

        // Return the final calculation of DM+/ATR * 100
        double dipValue = (avgDp / atr) * 100.0;
        System.out.println(dipValue);
        return dipValue;
    }


    public double dim(String sym, int tf, int offset) {
        // Parse financial data
        List<Map<String, Object>> finData = this.parseData(sym, tf);
        int length = finData.size() - offset;

        // Validate offset length
        if (length < 28) {
            throw new IllegalArgumentException("Offset too large.");
        }

        // Initialize variables
        double dmPlus = 0.0, dmMinus = 0.0, avgDp = 0.0, avgDm = 0.0, atr = 0.0, adxValue = 0.0, avgAdx = 0.0;
        double prevHigh, prevLow, prevClose, currentDmPlus, currentDmMinus, high, low;

        // Calculate initial values for the first 14 periods
        for (int i = 1; i < 14; ++i) {
            high = getPrice((Map) finData.get(i), "inth");
            low = getPrice((Map) finData.get(i), "intl");
            prevHigh = getPrice((Map) finData.get(i - 1), "inth");
            prevLow = getPrice((Map) finData.get(i - 1), "intl");
            prevClose = getPrice((Map) finData.get(i - 1), "intc");

            // Calculate directional movement values
            currentDmPlus = high - prevHigh;
            currentDmMinus = prevLow - low;

            // Ensure non-negative values for DMPlus and DMMinus
            if (currentDmPlus < 0 && currentDmMinus < 0) {
                currentDmPlus = currentDmMinus = 0;
            } else if (currentDmPlus > currentDmMinus) {
                currentDmMinus = 0;
            } else {
                currentDmPlus = 0;
            }

            // Accumulate values
            dmPlus += currentDmPlus;
            dmMinus += currentDmMinus;
            atr += Math.max(Math.max(high - low, Math.abs(high - prevHigh)), Math.abs(prevHigh - low));
        }

        // Calculate averages for DM and ATR
        avgDp = dmPlus / 14.0;
        avgDm = dmMinus / 14.0;
        atr /= 14.0;

        // Calculate ADX value
        adxValue = (avgDp + avgDm == 0.0) ? 0.0 : Math.abs((avgDp - avgDm) / (avgDp + avgDm));

        // Set initial avgAdx as the ADX value for period 14
        avgAdx = adxValue;

        // Iterate over remaining data to calculate values for periods 15 to end
        for (int i = 14; i < length; ++i) {
            high = getPrice((Map) finData.get(i), "inth");
            low = getPrice((Map) finData.get(i), "intl");
            prevHigh = getPrice((Map) finData.get(i - 1), "inth");
            prevLow = getPrice((Map) finData.get(i - 1), "intl");
            prevClose = getPrice((Map) finData.get(i - 1), "intc");

            // Update directional movements
            currentDmPlus = high - prevHigh;
            currentDmMinus = prevLow - low;

            // Ensure non-negative values for DMPlus and DMMinus
            if (currentDmPlus < 0 && currentDmMinus < 0) {
                currentDmPlus = currentDmMinus = 0;
            } else if (currentDmPlus > currentDmMinus) {
                currentDmMinus = 0;
            } else {
                currentDmPlus = 0;
            }

            // Update averages and ATR
            avgDp = (avgDp * 13 + currentDmPlus) / 14;
            avgDm = (avgDm * 13 + currentDmMinus) / 14;
            atr = (atr * 13 + Math.max(Math.max(high - low, Math.abs(high - prevClose)), Math.abs(prevClose - low))) / 14;

            // Update ADX value
            adxValue = (avgDp + avgDm == 0.0) ? 0.0 : Math.abs((avgDp - avgDm) / (avgDp + avgDm));

            // Update avgAdx for smoothing
            avgAdx = (avgAdx * 13 + adxValue) / 14;
        }

        // Return the final calculation of DM-/ATR * 100
        double dimValue = (avgDm / atr) * 100.0;
        System.out.println(dimValue);
        return dimValue;
    }

    public double adx(String sym, int tf, int offset) {
        // Parse financial data
        List<Map<String, Object>> finData = this.parseData(sym, tf);
        int length = finData.size() - offset;

        // Validate offset length
        if (length < 28) {
            throw new IllegalArgumentException("Offset too large.");
        }

        // Initialize variables
        double dmPlus = 0.0, dmMinus = 0.0, avgDp = 0.0, avgDm = 0.0, atr = 0.0, adxValue = 0.0, avgAdx = 0.0;
        double prevHigh, prevLow, prevClose, currentDmPlus, currentDmMinus, high, low;

        // Calculate initial values for the first 14 periods
        for (int i = 1; i < 14; ++i) {
            high = getPrice((Map) finData.get(i), "inth");
            low = getPrice((Map) finData.get(i), "intl");
            prevHigh = getPrice((Map) finData.get(i - 1), "inth");
            prevLow = getPrice((Map) finData.get(i - 1), "intl");
            prevClose = getPrice((Map) finData.get(i - 1), "intc");

            // Calculate directional movement values
            currentDmPlus = high - prevHigh;
            currentDmMinus = prevLow - low;

            // Ensure non-negative values for DMPlus and DMMinus
            if (currentDmPlus < 0 && currentDmMinus < 0) {
                currentDmPlus = currentDmMinus = 0;
            } else if (currentDmPlus > currentDmMinus) {
                currentDmMinus = 0;
            } else {
                currentDmPlus = 0;
            }

            // Accumulate values
            dmPlus += currentDmPlus;
            dmMinus += currentDmMinus;
            atr += Math.max(Math.max(high - low, Math.abs(high - prevHigh)), Math.abs(prevHigh - low));
        }

        // Calculate averages for DM and ATR
        avgDp = dmPlus / 14.0;
        avgDm = dmMinus / 14.0;
        atr /= 14.0;

        // Calculate ADX value
        adxValue = (avgDp + avgDm == 0.0) ? 0.0 : Math.abs((avgDp - avgDm) / (avgDp + avgDm));

        // Update avgAdx for smoothing (initially, avgAdx is set to ADX value for period 14)
        avgAdx = adxValue;

        // Iterate over remaining data to calculate values for periods 15 to end
        for (int i = 14; i < length; ++i) {
            high = getPrice((Map) finData.get(i), "inth");
            low = getPrice((Map) finData.get(i), "intl");
            prevHigh = getPrice((Map) finData.get(i - 1), "inth");
            prevLow = getPrice((Map) finData.get(i - 1), "intl");
            prevClose = getPrice((Map) finData.get(i - 1), "intc");

            // Update directional movements
            currentDmPlus = high - prevHigh;
            currentDmMinus = prevLow - low;

            // Ensure non-negative values
            if (currentDmPlus < 0 && currentDmMinus < 0) {
                currentDmPlus = currentDmMinus = 0;
            } else if (currentDmPlus > currentDmMinus) {
                currentDmMinus = 0;
            } else {
                currentDmPlus = 0;
            }

            // Update averages and ATR
            avgDp = (avgDp * 13 + currentDmPlus) / 14;
            avgDm = (avgDm * 13 + currentDmMinus) / 14;
            atr = (atr * 13 + Math.max(Math.max(high - low, Math.abs(high - prevClose)), Math.abs(prevClose - low))) / 14;

            // Update ADX value
            adxValue = (avgDp + avgDm == 0.0) ? 0.0 : Math.abs((avgDp - avgDm) / (avgDp + avgDm));

            // Update avgAdx for smoothing
            avgAdx = (avgAdx * 13 + adxValue) / 14;
        }

        // Return the final ADX value (scaled by 100)
        double adxResult = avgAdx * 100.0;
        System.out.println(adxResult);
        return adxResult;
    }


    public double uBol(String sym, int tf, int offset) {
        List<Map<String, Object>> finData = this.parseData(sym, tf);

        // Ensure offset is within valid bounds
        if (offset > finData.size() - 1) {
            throw new IllegalArgumentException("Offset too large.");
        }

        // Calculate the 20-period moving average (simple average)
        double movingAverage = 0.0;
        int dataSize = finData.size();
        for (int i = 0; i < 20; i++) {
            movingAverage += getPrice((Map) finData.get(dataSize - i - offset - 1), "intc");
        }
        movingAverage /= 20.0;

        // Calculate the standard deviation
        double squaredDifferencesSum = 0.0;
        for (int i = 0; i < 20; i++) {
            double price = getPrice((Map) finData.get(dataSize - i - offset - 1), "intc");
            squaredDifferencesSum += Math.pow(price - movingAverage, 2.0);
        }

        // Standard deviation
        double standardDeviation = Math.sqrt(squaredDifferencesSum / 20.0);

        // Calculate and return the Bollinger Band upper bound
        double upperBand = movingAverage + 2.0 * standardDeviation;
        System.out.println(upperBand);
        return upperBand;
    }


    public double dBol(String sym, int tf, int offset) {
        List<Map<String, Object>> finData = this.parseData(sym, tf);

        // Ensure offset is within valid bounds
        if (offset > finData.size() - 1) {
            throw new IllegalArgumentException("Offset too large.");
        }

        // Calculate the 20-period moving average (simple average)
        double movingAverage = 0.0;
        int dataSize = finData.size();
        for (int i = 0; i < 20; i++) {
            movingAverage += getPrice((Map) finData.get(dataSize - i - offset - 1), "intc");
        }
        movingAverage /= 20.0;

        // Calculate the standard deviation
        double squaredDifferencesSum = 0.0;
        for (int i = 0; i < 20; i++) {
            double price = getPrice((Map) finData.get(dataSize - i - offset - 1), "intc");
            squaredDifferencesSum += Math.pow(price - movingAverage, 2.0);
        }

        // Standard deviation
        double standardDeviation = Math.sqrt(squaredDifferencesSum / 20.0);

        // Calculate and return the Bollinger Band lower bound
        double lowerBand = movingAverage - 2.0 * standardDeviation;
        System.out.println(lowerBand);
        return lowerBand;
    }


    public double mBol(String sym, int tf, int offset) {
        return this.sma(sym, tf, 20, "C", offset);
    }

    public double stoch(String sym, int tf, String rType, int loc) {
        // Parse financial data
        List<Map<String, Object>> finData = this.parseData(sym, tf);
        List<Double> kValues = new ArrayList<>(finData.size());

        int kPeriod = 14;
        int smoothingPeriod = 1;
        int dPeriod = 3;

        // Calculate %K values for each data point
        for (int i = kPeriod - 1; i < finData.size() + loc; ++i) {
            double highestHigh = Double.NEGATIVE_INFINITY;
            double lowestLow = Double.POSITIVE_INFINITY;

            // Find the highest high and lowest low over the last kPeriod periods
            for (int j = i - kPeriod + 1; j <= i; ++j) {
                double high = getPrice((Map) finData.get(j), "inth");
                double low = getPrice((Map) finData.get(j), "intl");
                highestHigh = Math.max(highestHigh, high);
                lowestLow = Math.min(lowestLow, low);
            }

            // Calculate %K value
            double currentClose = getPrice((Map) finData.get(i), "intc");
            double k = (highestHigh - lowestLow == 0.0) ? 0.0 : (currentClose - lowestLow) / (highestHigh - lowestLow) * 100.0;
            kValues.add(k);
        }

        // Smoothing of %K values
        List<Double> smoothedKValues = new ArrayList<>(kValues.size());
        for (int i = 0; i < kValues.size(); ++i) {
            double sum = 0.0;
            int count = 0;

            // Calculate the average of the last smoothingPeriod values
            for (int j = Math.max(0, i - smoothingPeriod + 1); j <= i; ++j) {
                sum += kValues.get(j);
                count++;
            }

            smoothedKValues.add(sum / count);
        }

        // If rType is "K", return the most recent smoothed %K value
        if (rType.equals("K")) {
            double result = smoothedKValues.get(smoothedKValues.size() - 1);
            System.out.println(result);
            return result;
        } else { // Calculate %D value (3-period SMA of smoothed %K)
            double result = 0.0;
            for (int i = dPeriod - 1; i < smoothedKValues.size(); ++i) {
                double sum = 0.0;

                // Calculate the average of the last dPeriod smoothed %K values
                for (int j = i - dPeriod + 1; j <= i; ++j) {
                    sum += smoothedKValues.get(j);
                }

                result = sum / dPeriod;
            }

            System.out.println(result);
            return result;
        }
    }

    public List<Map<String, Object>> parseData(String sym, int tf) {
        String token = null;
        List<Map<String, Object>> aggregateData = null;

        // Check if sym exists in indexShorts or allTokens, otherwise default to sym for token
        if (this.indexShorts.containsKey(sym)) {
            token = (String) ((Map) this.api.indexToken.get(this.indexShorts.get(sym))).get("token");
        } else if (this.api.allTokens.containsKey(sym)) {
            token = (String) this.api.allTokens.get(sym);
        } else if (this.api.hisData.containsKey(sym)) {
            token = sym;
        }

        // If token is determined, proceed to retrieve data
        if (token != null && this.api.hisData.containsKey(token)) {
            try {
                // Get data as JSONArray
                JSONArray data = (JSONArray) this.api.hisData.get(token);
                if (data != null) {
                    // Aggregate the data based on the given time frame (tf)
                    aggregateData = this.api.myUi.aggregateData(data, tf);
                } else {
                    // Log a warning if no data is found for the token
                    Logger.getLogger(ExpressionEvaluator.class.getName())
                            .log(Level.WARNING, "No data found for token: " + token);
                }
            } catch (ClassCastException e) {
                // Log error if there is a class casting issue
                Logger.getLogger(ExpressionEvaluator.class.getName())
                        .log(Level.SEVERE, "Error while parsing data for token: " + token, e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Log a warning if the token is missing or historical data is unavailable
            Logger.getLogger(ExpressionEvaluator.class.getName())
                    .log(Level.WARNING, "Token not found or historical data is missing for symbol: " + sym);
        }

        // Return empty list if no data found, ensuring the caller gets consistent results
        return (aggregateData != null) ? aggregateData : new ArrayList<>();
    }



    private static double getPrice(Map<String, Object> data, String key) {
        if (data == null || !data.containsKey(key)) {
            throw new IllegalArgumentException("Invalid data or missing key: " + key);
        }

        Object value = data.get(key);

        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        if (value instanceof String) {
            try {
                return Double.parseDouble((String) value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid price format: " + value, e);
            }
        }

        throw new IllegalArgumentException("Unsupported price type: " + value.getClass());
    }


    @FunctionalInterface
    public interface QuadFunction<T, U, V, W, R> {
        R apply(T var1, U var2, V var3, W var4);
    }

    @FunctionalInterface
    public interface PentaFunction<T, U, V, W, X, R> {
        R apply(T var1, U var2, V var3, W var4, X var5);
    }

    @FunctionalInterface
    public interface TriFunction<T, U, V, R> {
        R apply(T var1, U var2, V var3);
    }
}
