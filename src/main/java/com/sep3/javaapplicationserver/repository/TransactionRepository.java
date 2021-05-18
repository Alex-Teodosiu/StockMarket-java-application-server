package com.sep3.javaapplicationserver.repository;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

//    @Query("SELECT a FROM Transaction a WHERE a.accountID = ?1")
//    Optional<Transaction> findTransactionByAccountID(long id);
//    Optional<Transaction> findTransactionByAccountID(long id);
    List<Transaction> account_id(Long id);
}
