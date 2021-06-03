package com.sep3.javaapplicationserver.model;

import lombok.Data;

@Data
public class OwnedStock {

    private String symbol;
    private double price;
    private String name;
    private double purchasePrice;
    private double totalCost;
    private int quantity;

    public double getTotalValue(){
        return quantity*price;
    }

    public double getGainLoss(){
        return getTotalValue() - totalCost;
    }
}
