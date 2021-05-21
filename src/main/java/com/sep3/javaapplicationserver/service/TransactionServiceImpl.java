package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.exception.EntityNotFoundException;
import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.model.Transaction;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import com.sep3.javaapplicationserver.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    public static final String TRANSACTION_NOT_FOUND = "Transaction with Id %d not found";

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public AccountService accountService;

    @Autowired
    public TransactionRepository transactionRepository;

    public Transaction save(Transaction transaction) {
        Long accountId = transaction.getAccount().getId();

        Account account = accountService.findByIdOrFail(accountId);

        transaction.setAccount(account);

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction findTransactionByIdOrFail(Long transactionId) {
        return transactionRepository
                .findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(TRANSACTION_NOT_FOUND, transactionId)));
    }

    @Transactional
    @Override
    public void deleteAllByAccountId(Long accountId) {
        transactionRepository.deleteTransactionByAccount_Id(accountId);
    }
}