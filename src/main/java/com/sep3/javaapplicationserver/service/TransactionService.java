package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Transaction;

public interface TransactionService {

    Transaction save(Transaction transaction);

    Transaction findTransactionByIdOrFail(Long transactionId);
}