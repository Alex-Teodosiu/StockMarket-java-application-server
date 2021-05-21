package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.model.Account;

public interface AccountService {
    Account save(Account account);
    Account update(Account accountToEdit, Account accountCurrent);

    Account findByIdOrFail(Long accountId);
    void findUniqueUsernameOrFail(String username);

    void delete(Long accountId);
}

