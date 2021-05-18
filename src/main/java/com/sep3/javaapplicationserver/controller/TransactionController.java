package com.sep3.javaapplicationserver.controller;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.model.Transaction;
import com.sep3.javaapplicationserver.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionRepository repository;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getTransactions(@RequestParam Long id) {
        ResponseEntity<Object> responseEntity;

        try {
            List<Transaction> transactions = repository.account_id(id);
            responseEntity = new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
