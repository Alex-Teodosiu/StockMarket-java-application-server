package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.exception.EntityInUseException;
import com.sep3.javaapplicationserver.exception.EntityNotFoundException;
import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AccountServiceImpl implements AccountService {

    public static final String ACCOUNT_NOT_FOUND = "Account Id %d not found";
    public static final String USERNAME_ALREADY_TAKEN = "Username %s already taken";

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account accountToEdit, Account accountCurrent) {
        BeanUtils.copyProperties(accountToEdit, accountCurrent, "id", "dateCreated");
        accountCurrent.setDateUpdated(LocalDateTime.now());

        return accountRepository.save(accountCurrent);
    }

    public Account findByIdOrFail(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(ACCOUNT_NOT_FOUND, accountId)));
    }

    public void findUniqueUsernameOrFail(String username) {
        accountRepository.findAccountByUsername(username)
                .ifPresent(a -> { throw  new EntityInUseException(
                        String.format(USERNAME_ALREADY_TAKEN, username)); } );
    }
}
