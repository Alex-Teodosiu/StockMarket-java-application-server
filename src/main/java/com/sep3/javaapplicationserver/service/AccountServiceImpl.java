package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Account addNewAccount(Account account) {
        Optional<Account> accountOptional = accountRepository
                .findAccountByUsername(account.getUsername());

        if (accountOptional.isPresent()) {
            throw new DataIntegrityViolationException("Username already taken");
        }
        return accountRepository.save(account);
    }

    @Override
    public void editAccount(Account account) {
        Optional<Account> accountOptional = accountRepository
                .findAccountByUsername(account.getUsername());

        if(accountOptional.isPresent() && !account.getId().equals(accountOptional.get().getId())) {
            throw new DataIntegrityViolationException("Username already taken");
        }
    }

}
