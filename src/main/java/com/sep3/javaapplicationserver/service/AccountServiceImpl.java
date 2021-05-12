package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public void registerAccount(Account account) {
        Optional<Account> accountOptional = accountRepository
                .findAccountByUsername(account.getUsername());

        if (accountOptional.isPresent()) {
            throw new IllegalStateException("username already taken");
        }

        accountRepository.save(account);
    }
    @Override
    public void login(String username, String password) {
        Optional<Account> possibleUser = accountRepository.findAccountByUsername(username);

        if (!possibleUser.isPresent()) {
            throw new IllegalStateException("Account username does not exist");
        }

        if(!password.equals(possibleUser.get().getPassword())){
            throw new IllegalStateException("Invalid password");
        }
    }

    @Transactional //MAGIC!!!!!!!!!!!!!!!!
    @Override
    public void editAccount(Account account) {
        Account account1 = accountRepository.findById(account.getId()).orElseThrow(() -> new IllegalStateException("Account doesn't exist"));

        if(account.getUsername()!=null && account.getUsername().length()>0 && !Objects.equals(account1.getUsername(), account.getUsername())){
            account1.setUsername(account.getUsername());
        }

        if(account.getPassword()!=null && account.getPassword().length()>6 && !Objects.equals(account1.getPassword(), account.getPassword())){
            account1.setPassword(account.getPassword());
        }

    }

    @Override
    public Optional<Account> getAccount(String username) {
        Optional<Account> account1 = accountRepository.findAccountByUsername(username);
        return account1;
    }


}
