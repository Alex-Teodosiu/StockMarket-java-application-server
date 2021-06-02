package com.sep3.javaapplicationserver.controller;

import com.sep3.javaapplicationserver.model.Stock;
import com.sep3.javaapplicationserver.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    public StockService stockService;

    @GetMapping("/{symbol}")
    public Stock getStock(@PathVariable String symbol){
        return stockService.getStock(symbol);
    }

    @GetMapping("/price/{symbol}")
    public double getStockPrice(@PathVariable String symbol){
        return stockService.getStockPrice(symbol);
    }

    @GetMapping("/list/{symbol}")
    public Stock[] getStockList(@PathVariable String symbol){
        return stockService.getStockPriceList(symbol);
    }
}
