package com.sep3.javaapplicationserver.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Transaction {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String stockSymbol;

    @OneToOne
    @JoinColumn(name = "account.id",nullable = false)
    private Account account;
    private int quantity;
    private Timestamp dateTime;
    private BigDecimal price;
    private boolean isBuy;

}
