package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final AccountRepository accountRepository;

    @Autowired
    public LoginServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account login(String username, String password) {
        Optional<Account> possibleUser = accountRepository.findAccountByUsername(username);

        if (!possibleUser.isPresent()) {
            throw new IllegalStateException("Account username does not exist");
        }

        if(!password.equals(possibleUser.get().getPassword())){
            throw new IllegalStateException("Invalid password");
        }

        return possibleUser.get();
    }

}
