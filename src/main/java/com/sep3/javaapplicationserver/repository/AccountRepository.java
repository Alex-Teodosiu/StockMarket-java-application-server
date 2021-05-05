package com.sep3.javaapplicationserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sep3.javaapplicationserver.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // SELECT * FROM student WHERE email = ?
    @Query("SELECT a FROM Account a WHERE a.username = ?1")
    Optional<Account> findAccountByUsername(String username);

}
