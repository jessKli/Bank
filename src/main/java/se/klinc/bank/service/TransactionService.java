package se.klinc.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.klinc.bank.dao.Transaction;
import se.klinc.bank.dao.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactionsForAccount(String accountId){
        return         transactionRepository.findByAccountId(accountId);

    }
}
