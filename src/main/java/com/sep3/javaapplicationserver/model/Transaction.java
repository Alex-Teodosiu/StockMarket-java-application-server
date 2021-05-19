package com.sep3.javaapplicationserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private int quantity;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp(0)")
    private LocalDateTime dateCreated;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean isBuy;

    @ManyToOne
    @JoinColumn(name = "account_id",nullable = false)
    private Account account;
}
