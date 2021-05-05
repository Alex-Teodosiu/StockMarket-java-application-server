package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import com.sep3.javaapplicationserver.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public void login(String username, String password) {
        Optional<Account> possibleUser = loginRepository.getAccountBy(username);

        if (!possibleUser.isPresent()) {
            throw new IllegalStateException("Account username does not exist");
        }

        if(!password.equals(possibleUser.get().getPassword())){
            throw new IllegalStateException("Invalid password");
        }


        //return new ApiResponse(200, "Successful Login", null);
    }

}
