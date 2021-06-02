package com.sep3.javaapplicationserver.service.stock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sep3.javaapplicationserver.model.Stock;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;


public class StockServiceImpl implements StockService {

    private final String ApiKey = "8b66c2d14f4643e7b19076c0de11b861";
    private final String ApiKey1 = "ced0265304804736b7cd16bb6c5e4332";
    private final String ApiKey2 = "f04e879f0da545829306ede67e813e27";
    private final String ApiKey3 = "fe7fb827aab34438b8ce632f7f6ccacb";
    private final String ApiKey4 = "c523a60be5c54c4f952f933136b8b73f";
    private final String ApiKey5 = "e9fc5f0530944e17b6de5bd26380eea1";

    private String[] apiKeys;
    private Random generator;
    private String key;
    private Gson gson;

    public StockServiceImpl(){
        apiKeys = new String[] {ApiKey, ApiKey1, ApiKey2, ApiKey3, ApiKey4, ApiKey5};
        generator = new Random();
        gson = new GsonBuilder().create();
    }
    @Override
    public Stock getStock(String symbol) {
        GetAPIKey();
        var response = Unirest.get("https://api.twelvedata.com/quote?symbol="+symbol+
                "&interval=1day&apikey="+key).header("accept","application/json");
        try {
            Stock stock = gson.fromJson(String.valueOf(response.asJson().getBody()),Stock.class);
            stock.setPrice(getStockPrice(symbol));
            return stock;
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double getStockPrice(String symbol) {
        GetAPIKey();
        var response = Unirest.get("https://api.twelvedata.com/price?symbol="+symbol+
                "&apikey="+key);
        try {
            Price stock = gson.fromJson(String.valueOf(response.asJson().getBody()),Price.class);
            return stock.price;
        } catch (UnirestException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    @Override
    public Stock[] getStockPriceList(String symbol) {
        GetAPIKey();
        var response = Unirest.get("https://api.twelvedata.com/time_series?symbol="+symbol+
                "&interval=1month&apikey="+key);
        try {
            Stocks stocks = gson.fromJson(response.asJson().getBody().toString(),Stocks.class);
            System.out.println(stocks.values.length);
            return stocks.values;
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return new Stock[0];
    }

    private void GetAPIKey()
    {
        var num = generator.nextInt(1000) % 6;
        key = apiKeys[num];
    }

    private class Price{
        private double price;
    }

    private class Stocks{
        private Stock[] values;
    }

}
