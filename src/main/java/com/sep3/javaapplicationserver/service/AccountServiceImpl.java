package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addNewAccount(Account account) {
        checkUsername(account);

        accountRepository.save(account);
    }

    @Override
    public void editAccount(Account account) {
        checkUsername(account);

        Account a = accountRepository.getOne(account.getId());
        a.setUsername(account.getUsername());
        a.setPassword(account.getPassword());
        accountRepository.save(a);
    }

    private void checkUsername(Account account){
        Optional<Account> accountOptional = accountRepository
                .findAccountByUsername(account.getUsername());

        if (accountOptional.isPresent() && account.getId() != accountOptional.get().getId()) {
            throw new IllegalStateException("username already taken");
        }
    }
}
