package com.sep3.javaapplicationserver.service;

import com.sep3.javaapplicationserver.exception.EntityNotFoundException;
import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.model.OwnedStock;
import com.sep3.javaapplicationserver.model.Transaction;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import com.sep3.javaapplicationserver.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    public static final String TRANSACTION_NOT_FOUND = "Transaction with Id %d not found";
    private List<OwnedStock> ownedStocks;

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public AccountService accountService;

    @Autowired
    public TransactionRepository transactionRepository;

    public Transaction save(Transaction transaction) {
        Long accountId = transaction.getAccount().getId();

        Account account = accountService.findByIdOrFail(accountId);

        accountService.update(transaction.getAccount(), account);
        transaction.setAccount(account);

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction findTransactionByIdOrFail(Long transactionId) {
        return transactionRepository
                .findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(TRANSACTION_NOT_FOUND, transactionId)));
    }

    @Transactional
    @Override
    public void deleteAllByAccountId(Long accountId) {
        transactionRepository.deleteTransactionByAccount_Id(accountId);
    }

    @Override
    public List<OwnedStock> getOwnedStock(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccount_id(accountId);
        return SortTransactions(transactions);
    }

    private List<OwnedStock> SortTransactions(List<Transaction> transactions){
        ownedStocks = new ArrayList<>();

        for (Transaction t: transactions) {
            boolean unique = true;
            for (OwnedStock o: ownedStocks){
                if (o.getSymbol().equals(t.getStockSymbol())){
                    unique = false;
                    break;
                }
            }
            if (unique){
                OwnedStock stock = new OwnedStock();
                stock.setSymbol(t.getStockSymbol());
                stock.setQuantity(t.getQuantity());
                stock.setPurchasePrice(t.getPrice().doubleValue());
                stock.setTotalCost(t.getQuantity() * t.getPrice().doubleValue());
                ownedStocks.add(stock);
            }else
            {
                OwnedStock stock = getOwnedStock(t.getStockSymbol());
                if (t.getIsBuy()){
                    stock.setQuantity(stock.getQuantity() + t.getQuantity());
                    stock.setTotalCost(stock.getTotalCost() + (t.getQuantity() * t.getPrice().doubleValue()));
                }
                else {
                    stock.setQuantity(stock.getQuantity() - t.getQuantity());
                    stock.setTotalCost(stock.getTotalCost() - (t.getQuantity() * t.getPrice().doubleValue()));
                }
            }
        }
        return ownedStocks;
    }
    private OwnedStock getOwnedStock(String symbol){
        for (OwnedStock o: ownedStocks){
            if (o.getSymbol().equals(symbol)){
                return o;
            }
        }
        return null;
    }
}