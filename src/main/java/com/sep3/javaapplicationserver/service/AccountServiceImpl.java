package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
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
    public Account addNewAccount(Account account) {
        checkUsername(account);

        return accountRepository.save(account);
    }

    @Override
    public Account editAccount(Account account) {
        Account accountToEdit = accountRepository
                .findById(account.getId())
                .orElseThrow(() -> new EntityNotFoundException("Account doesn't exist"));

        checkUsername(account);

        if (account.getPassword() != null){
            accountToEdit.setPassword(account.getPassword());
        }
        if (account.getUsername() != null) {
            accountToEdit.setUsername(account.getUsername());
        }
        accountToEdit.setDateUpdated(LocalDateTime.now());

        return accountRepository.save(accountToEdit);
    }

    private void checkUsername(Account account){
        Optional<Account> accountOptional = accountRepository
                .findAccountByUsername(account.getUsername());

        if (accountOptional.isPresent() &&
                !account.getId().equals(accountOptional.get().getId())) {
            throw new DataIntegrityViolationException("Username already taken");
        }
    }

//    @Transactional //MAGIC!!!!!!!!!!!!!!!!
//    @Override
//    public void editAccount(Account account) {
//        Account account1 = accountRepository.findById(account.getId()).orElseThrow(() -> new IllegalStateException("Account doesn't exist"));
//
//        if(account.getUsername()!=null && account.getUsername().length()>0 && !Objects.equals(account1.getUsername(), account.getUsername())){
//            account1.setUsername(account.getUsername());
//        }
//
//        if(account.getPassword()!=null && account.getPassword().length()>6 && !Objects.equals(account1.getPassword(), account.getPassword())){
//            account1.setPassword(account.getPassword());
//        }
//
//    }
//
//    @Override
//    public Optional<Account> getAccount(String username) {
//        Optional<Account> account1 = accountRepository.findAccountByUsername(username);
//        return account1;
//    }
}
