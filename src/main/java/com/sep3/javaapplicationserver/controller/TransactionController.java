package com.sep3.javaapplicationserver.controller;

import com.sep3.javaapplicationserver.exception.BusinessException;
import com.sep3.javaapplicationserver.exception.EntityNotFoundException;
import com.sep3.javaapplicationserver.model.OwnedStock;
import com.sep3.javaapplicationserver.model.Transaction;
import com.sep3.javaapplicationserver.repository.TransactionRepository;
import com.sep3.javaapplicationserver.service.AccountService;
import com.sep3.javaapplicationserver.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;


    @GetMapping
    public List<Transaction> listAll() {
        return transactionRepository.findAll();
    }

    @GetMapping("/{transactionId}")
    public Transaction getById(@PathVariable Long transactionId) {
        return transactionService.findTransactionByIdOrFail(transactionId);
    }

    @GetMapping("/all/{accountId}")
    public List<Transaction> getByAccountId(@PathVariable Long accountId) {
        accountService.findByIdOrFail(accountId);
        return transactionRepository.findByAccount_id(accountId);
    }

    @GetMapping("/ownedStocks/{accountId}")
    public List<OwnedStock> getOwnedStocks(@PathVariable Long accountId) {
        accountService.findByIdOrFail(accountId);
        return transactionService.getOwnedStock(accountId);
    }


    // TODO return/throw exception if not found parameters

    @GetMapping("/by-account-and-type")
    public List<Transaction> getByAccount(Long accountId, boolean isBuy) {
        return transactionRepository.getByAccountIdAndType(accountId, isBuy);
    }

    @GetMapping("/by-account-and-stockSymbol")
    public List<Transaction> getByAccountAndStockSymbol(Long accountId, String stockSymbol) {
        accountService.findByIdOrFail(accountId);

        return transactionRepository.getByAccountAndStockSymbol(accountId, stockSymbol);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction add(@RequestBody Transaction transaction) {
        try {
            return transactionService.save(transaction);

        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllByAccountId(@PathVariable Long accountId) {
        accountService.findByIdOrFail(accountId);
        transactionService.deleteAllByAccountId(accountId);
    }

}
