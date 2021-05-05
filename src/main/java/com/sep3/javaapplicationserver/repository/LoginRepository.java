package com.sep3.javaapplicationserver.repository;

import com.sep3.javaapplicationserver.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.username = ?1")
    Optional<Account> getAccountBy(String username);
}
