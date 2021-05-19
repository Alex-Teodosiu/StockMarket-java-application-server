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
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    public AccountService accountService;

    @Autowired
    public AccountRepository accountRepository;

    @PostMapping("")
    public ResponseEntity<?> addNewAccount(@RequestBody Account account) {
        try {
            account = accountService.addNewAccount(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(account);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)//409 Conflict
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editAccount(@PathVariable Long id, @RequestBody Account account) {
        Optional<Account> accountExists = accountRepository.findById(id);

        if (accountExists.isPresent()) {
            try {
                accountService.editAccount(account);

                BeanUtils.copyProperties(account, accountExists.get(), "id", "dateCreated");
                accountExists.get().setDateUpdated(LocalDateTime.now());

                accountRepository.save(accountExists.get());
                return ResponseEntity.ok(accountExists.get());

            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account id does not exists");
    }
}
