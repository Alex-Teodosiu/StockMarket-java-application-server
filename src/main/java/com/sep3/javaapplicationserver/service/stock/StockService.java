package com.sep3.javaapplicationserver.service.stock;

import com.sep3.javaapplicationserver.model.Stock;

public interface StockService {
    Stock getStock(String symbol);
    double getStockPrice(String symbol);
    Stock[] getStockPriceList(String symbol);
}
