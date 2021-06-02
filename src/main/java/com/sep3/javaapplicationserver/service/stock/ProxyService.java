package com.sep3.javaapplicationserver.service.stock;

import com.sep3.javaapplicationserver.model.Stock;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class ProxyService implements StockService{

    private StockService stockService;

    private HashMap<String, StockDateTime> cachedStocks;
    private HashMap<String, PriceDateTime> cachedStockPrice;
    private HashMap<String, ListDateTime> cachedStockPriceList;

    public ProxyService(){
        stockService = new StockServiceImpl();

        cachedStocks = new HashMap<>();
        cachedStockPrice = new HashMap<>();
        cachedStockPriceList = new HashMap<>();
    }

    @Override
    public Stock getStock(String symbol) {
        if (!cachedStocks.containsKey(symbol))
        {
            Stock stock = stockService.getStock(symbol);
            StockDateTime stockDateTime = new StockDateTime();
            stockDateTime.setStock(stock);
            stockDateTime.setDateTime(System.currentTimeMillis());
            cachedStocks.put(symbol,stockDateTime);
            System.out.println("STOCK FROM API: "+stock);
            return stock;
        }
        if (System.currentTimeMillis() - cachedStocks.get(symbol).dateTime > 60000){
            Stock stock = stockService.getStock(symbol);
            cachedStocks.get(symbol).setStock(stock);
            cachedStocks.get(symbol).setDateTime(System.currentTimeMillis());
            System.out.println("CACHED STOCK UPDATED FROM API: "+stock);
            return stock;
        }
        Stock stock = cachedStocks.get(symbol).stock;
        System.out.println("STOCK FROM CACHE: "+stock);
        return stock;
    }

    @Override
    public double getStockPrice(String symbol) {
        if (!cachedStockPrice.containsKey(symbol))
        {
            double stock = stockService.getStockPrice(symbol);
            PriceDateTime priceDateTime = new PriceDateTime();
            priceDateTime.setPrice(stock);
            priceDateTime.setDateTime(System.currentTimeMillis());
            cachedStockPrice.put(symbol,priceDateTime);
            System.out.println("STOCK PRICE FROM API: "+stock);
            return stock;
        }
        if (System.currentTimeMillis() - cachedStockPrice.get(symbol).dateTime > 60000){
            double stock = stockService.getStockPrice(symbol);
            cachedStockPrice.get(symbol).setPrice(stock);
            cachedStockPrice.get(symbol).setDateTime(System.currentTimeMillis());
            System.out.println("CACHED STOCK PRICE UPDATED FROM API: "+stock);
            return stock;
        }
        double stock = cachedStockPrice.get(symbol).price;
        System.out.println("STOCK PRICE FROM CACHE: "+stock);
        return stock;
    }

    @Override
    public Stock[] getStockPriceList(String symbol) {
        if (!cachedStockPriceList.containsKey(symbol))
        {
            Stock[] stock = stockService.getStockPriceList(symbol);
            ListDateTime priceDateTime = new ListDateTime();
            priceDateTime.setStocks(stock);
            priceDateTime.setDateTime(System.currentTimeMillis());
            cachedStockPriceList.put(symbol,priceDateTime);
            System.out.println("STOCK LIST FROM API: "+stock);
            return stock;
        }
        if (System.currentTimeMillis() - cachedStockPriceList.get(symbol).dateTime > 60000){
            Stock[] stock = stockService.getStockPriceList(symbol);
            cachedStockPriceList.get(symbol).setStocks(stock);
            cachedStockPriceList.get(symbol).setDateTime(System.currentTimeMillis());
            System.out.println("CACHED STOCK LIST PRICE UPDATED FROM API: "+stock);
            return stock;
        }
        Stock[] stock = cachedStockPriceList.get(symbol).stocks;
        System.out.println("STOCK LIST FROM CACHE: "+stock);
        return stock;
    }

    private class StockDateTime
    {
        public Stock stock;
        public long dateTime;

        public Stock getStock() {
            return stock;
        }

        public void setStock(Stock stock) {
            this.stock = stock;
        }

        public long getDateTime() {
            return dateTime;
        }

        public void setDateTime(long dateTime) {
            this.dateTime = dateTime;
        }
    }

    private class PriceDateTime
    {
        public double price;
        public long dateTime;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public long getDateTime() {
            return dateTime;
        }

        public void setDateTime(long dateTime) {
            this.dateTime = dateTime;
        }
    }

    private class ListDateTime
    {
        public Stock[] stocks;
        public long dateTime;

        public Stock[] getStocks() {
            return stocks;
        }

        public void setStocks(Stock[] stocks) {
            this.stocks = stocks;
        }

        public long getDateTime() {
            return dateTime;
        }

        public void setDateTime(long dateTime) {
            this.dateTime = dateTime;
        }
    }
}
