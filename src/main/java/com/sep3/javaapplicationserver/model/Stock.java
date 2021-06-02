package com.sep3.javaapplicationserver.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
public class Stock {
    private String symbol;

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date datetime;

    private double price;

    private double open;

    private double high;

    private double low;

    private double close;

    private double percent_change;

    private long volume;

}
