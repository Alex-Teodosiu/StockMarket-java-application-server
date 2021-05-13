package com.sep3.javaapplicationserver.yahooapi.service;

import com.sep3.javaapplicationserver.yahooapi.model.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;



@SpringBootTest
class StockServiceTests {
    @Autowired
    private StockService stockService;

    @Test
    void getStock() {
        final StockWrapper stock = stockService.getStock("AAPL");
        System.out.println("Stock info: " + stock.getStock());
    }

    @Test
    void getStocks() {
    }

    @Test
    void findPrice() throws IOException {
        final StockWrapper stock = stockService.getStock("AAPL");
        final BigDecimal price = stockService.findPrice(stock);
        System.out.println("Stock price: " + price);
    }

    @Test
    void findLastChangePercent() throws IOException {
        final StockWrapper stock = stockService.getStock("UU.L");
        System.out.println("Changes in percentage: " + stockService.findLastChangePercent(stock));
    }

    @Test
    void findChangeFrom200MeanPercentage() throws IOException {
        final StockWrapper stock = stockService.getStock("UU.L");
        System.out.println("Changes in percentage from 200 average: " + stockService.findChangeFrom200MeanPercentage(stock));
    }
}