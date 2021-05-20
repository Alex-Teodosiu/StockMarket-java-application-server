package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.model.Transaction;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import com.sep3.javaapplicationserver.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TransactionService {

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public TransactionRepository transactionRepository;

    public Transaction save(Transaction transaction) {
        Long accountId = transaction.getAccount().getId();

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new EntityNotFoundException(
                String.format("Account with Id %d not found", accountId)));

        transaction.setAccount(account);

        return transactionRepository.save(transaction);
    }
}
