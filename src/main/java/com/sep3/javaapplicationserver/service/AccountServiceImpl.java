package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        if(accountOptional.isPresent()) {
            throw new DataIntegrityViolationException("Username already taken");
        }
//        BeanUtils.copyProperties(account, accountOptional.get(), "id", "dateCreated");
//        accountOptional.get().setDateUpdated(LocalDateTime.now());
//
//        return accountRepository.save(accountOptional.get());


//        Optional<Account> accountOptional = accountRepository
//                    .findAccountByUsername(account.getUsername());
//
//        if (accountOptional.isPresent()) {
//            throw new DataIntegrityViolationException("Username already taken");
//        }
//
//        if(account.getUsername()!= null && account.getUsername().length()>0
//                && !Objects.equals(accountOptional.get().getUsername(), account.getUsername())){
//            accountOptional.get().setUsername(account.getUsername());
//        }
//
//        if(account.getPassword()!=null && account.getPassword().length()>6
//                && !Objects.equals(accountOptional.get().getPassword(), account.getPassword())){
//            accountOptional.get().setPassword(account.getPassword());
//        }
//
//        return accountRepository.save(accountOptional.get());
    }

}
