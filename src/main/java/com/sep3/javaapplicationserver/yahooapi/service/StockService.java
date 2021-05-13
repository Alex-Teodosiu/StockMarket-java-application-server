package com.sep3.javaapplicationserver.yahooapi.service;

import com.sep3.javaapplicationserver.yahooapi.model.StockWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockService {
    private final RefreshService refreshService;

    public StockWrapper getStock(final String ticker){
        try {
            return new StockWrapper(YahooFinance.get(ticker));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<StockWrapper> getStocks(final List<String> tickers){
        return tickers.stream().map(this::getStock).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public BigDecimal findPrice(final StockWrapper stock) throws IOException {
        return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getPrice();
    }

    public BigDecimal findLastChangePercent(final StockWrapper stock) throws IOException {
        return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getChangeInPercent();
    }

    public BigDecimal findChangeFrom200MeanPercentage(final StockWrapper stock) throws IOException {
        return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getChangeFromAvg200InPercent();
    }





}
