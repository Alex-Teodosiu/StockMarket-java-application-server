package com.sep3.javaapplicationserver.repository;

import com.sep3.javaapplicationserver.model.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository {

    Optional<Account> findAccountByUsername(String username);
}
