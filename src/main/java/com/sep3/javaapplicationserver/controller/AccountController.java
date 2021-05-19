package com.sep3.javaapplicationserver.controller;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import com.sep3.javaapplicationserver.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    public AccountService accountService;

    @Autowired
    public AccountRepository accountRepository;

    @PostMapping("")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        try {
            account = accountService.addNewAccount(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(account);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)//409 Conflict
                    .body(e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity<?> editAccount(@RequestBody Account account){
        Optional<Account> findAccount = accountRepository.findById(account.getId());

        if (findAccount.isPresent()){
            try {
                accountService.editAccount(account);
                return ResponseEntity.ok(account);
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            } catch (EntityNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }
        return ResponseEntity.ok(account);
    }
}
