package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public void addNewAccount(Account account) {
        Optional<Account> accountOptional = accountRepository
                .findAccountByUsername(account.getUsername());

        if (accountOptional.isPresent()) {
            throw new IllegalStateException("username already taken");
        }

        accountRepository.save(account);
    }

    @Override
    public void editAccount(Account account) {
        Account a = accountRepository.getOne(account.getId());
        a.setUsername(account.getUsername());
        a.setPassword(account.getPassword());
        accountRepository.save(a);
    }
}
