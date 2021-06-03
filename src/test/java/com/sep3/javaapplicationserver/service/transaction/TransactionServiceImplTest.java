package com.sep3.javaapplicationserver.service.transaction;

import com.sep3.javaapplicationserver.model.OwnedStock;
import com.sep3.javaapplicationserver.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
class TransactionServiceImplTest {

    private TransactionServiceImpl transactionService;

    @Test
    public void SortTransactionsTest(){
        //ARRANGE
        transactionService = new TransactionServiceImpl();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = new Transaction();
        transaction.setIsBuy(true);
        transaction.setStockSymbol("TSLA");
        transaction.setQuantity(3);
        transaction.setPrice(BigDecimal.valueOf(600));
        transactions.add(transaction);

        Transaction transaction1 = new Transaction();
        transaction1.setIsBuy(true);
        transaction1.setStockSymbol("TSLA");
        transaction1.setQuantity(2);
        transaction1.setPrice(BigDecimal.valueOf(600));
        transactions.add(transaction1);

        Transaction transaction2 = new Transaction();
        transaction2.setIsBuy(false);
        transaction2.setStockSymbol("TSLA");
        transaction2.setQuantity(4);
        transaction2.setPrice(BigDecimal.valueOf(650));
        transactions.add(transaction2);

        Transaction transaction3 = new Transaction();
        transaction3.setIsBuy(true);
        transaction3.setStockSymbol("FB");
        transaction3.setQuantity(3);
        transaction3.setPrice(BigDecimal.valueOf(200));
        transactions.add(transaction3);

        Transaction transaction4 = new Transaction();
        transaction4.setIsBuy(true);
        transaction4.setStockSymbol("FB");
        transaction4.setQuantity(2);
        transaction4.setPrice(BigDecimal.valueOf(150));
        transactions.add(transaction4);

        Transaction transaction5 = new Transaction();
        transaction5.setIsBuy(false);
        transaction5.setStockSymbol("FB");
        transaction5.setQuantity(1);
        transaction5.setPrice(BigDecimal.valueOf(300));
        transactions.add(transaction5);

        //ACT
        List<OwnedStock> ownedStocks = transactionService.SortTransactions(transactions);

        //ASSERT
        //Test grouping of transactions
        Assertions.assertEquals(2,ownedStocks.size());

        var ownedStock = transactionService.getOwnedStock("TSLA");

        //Test quantity
        Assertions.assertEquals(1, ownedStock.getQuantity());
        Assertions.assertNotEquals(10, ownedStock.getQuantity());
        //Test TotalCost
        Assertions.assertEquals(400,ownedStock.getTotalCost());
        //Test GainLoss
        Assertions.assertEquals(-400,ownedStock.getGainLoss());
        var ownedStock1 = transactionService.getOwnedStock("FB");
        //Test quantity
        Assertions.assertEquals(4, ownedStock1.getQuantity());
        Assertions.assertNotEquals(10, ownedStock.getQuantity());
        //Test TotalCost
        Assertions.assertEquals(600,ownedStock1.getTotalCost());
        //Test GainLoss
        Assertions.assertEquals(-600,ownedStock1.getGainLoss());

    }
}