package com.sep3.javaapplicationserver.repository;

import com.sep3.javaapplicationserver.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount_id(Long id);

    @Query("from Transaction t join fetch t.account")
    List<Transaction> findAll();

    @Query("from Transaction where account.id = :accountId and isBuy = :isBuy")
    List<Transaction> getByAccountIdAndType(Long accountId, boolean isBuy);

    @Query("from Transaction where account.id = :accountId and stockSymbol = :stockSymbol")
    List<Transaction> getByAccountAndStockSymbol(Long accountId, String stockSymbol);

    void deleteTransactionByAccount_Id(Long accountId);
}
