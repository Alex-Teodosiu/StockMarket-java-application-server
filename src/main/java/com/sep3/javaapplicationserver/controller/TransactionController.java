package com.sep3.javaapplicationserver.controller;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.model.Transaction;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import com.sep3.javaapplicationserver.repository.TransactionRepository;
import com.sep3.javaapplicationserver.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountRepository accountRepository;


    @GetMapping
    public List<Transaction> listAll() {
        return transactionRepository.findAll();
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getById(@PathVariable Long transactionId) {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);

        if (transaction.isPresent()) {
            return ResponseEntity.ok(transaction.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all/{accountId}")
    public ResponseEntity<List<Transaction>> getByAccountId(@PathVariable Long accountId) {

        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isPresent()) {
            List<Transaction> allTransactionsByAccount = transactionRepository.account_id(accountId);

            return ResponseEntity.ok(allTransactionsByAccount);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/by-account-and-type")
    public List<Transaction> getByAccount(Long accountId, boolean isBuy) {
        return transactionRepository.getByAccoundIdAndType(accountId, isBuy);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Transaction transaction) {
        try {
            transaction = transactionService.save(transaction);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(transaction);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
