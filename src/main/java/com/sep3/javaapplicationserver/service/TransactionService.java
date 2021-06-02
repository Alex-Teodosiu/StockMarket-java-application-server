package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.OwnedStock;
import com.sep3.javaapplicationserver.model.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction save(Transaction transaction);

    Transaction findTransactionByIdOrFail(Long transactionId);

    void deleteAllByAccountId(Long accountId);

    List<OwnedStock> getOwnedStock(Long accountId);
}