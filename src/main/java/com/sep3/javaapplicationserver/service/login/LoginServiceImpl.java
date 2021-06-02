package com.sep3.javaapplicationserver.service.login;

import com.sep3.javaapplicationserver.exception.EntityNotFoundException;
import com.sep3.javaapplicationserver.exception.BusinessException;
import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    public static final String USERNAME_NOT_FOUND = "Username %s not found";
    public static final String INVALID_PASSWORD = "Invalid password";

    private final AccountRepository accountRepository;

    @Autowired
    public LoginServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account login(String username, String password) {
        Account toLogin = findByUsernameOrFail(username);

        if (!password.equals(toLogin.getPassword())) {
            throw new BusinessException(INVALID_PASSWORD);
        }
        else return toLogin;
    }

    @Override
    public Account findByUsernameOrFail(String username) {
        return accountRepository
                .findAccountByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(USERNAME_NOT_FOUND, username)));
    }


}
