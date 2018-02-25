package com.bank.honest.model.service;

import com.bank.honest.model.dao.TransactionRepository;
import com.bank.honest.model.dto.TransactionDTO;
import com.bank.honest.model.entity.Currency;
import com.bank.honest.model.entity.Transaction;
import com.bank.honest.model.entity.TransactionStatus;
import com.bank.honest.model.entity.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 2/21/2018.
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Transactional
    public boolean addTransaction(String number, TransactionStatus status, TransactionType type, Long amount, Currency currency) {
        if (transactionRepository.existsByNumber(number))
            return false;

        Transaction transaction = Transaction.builder()
                .date(new Date())
                .number(number)
                .status(status)
                .type(type)
                .amount(amount)
                .currency(currency)
                .build();
        transactionRepository.save(transaction);

        return true;
    }

    @Transactional
    public List<TransactionDTO> findAll(Pageable pageable) {
        List<Transaction> transactions = transactionRepository.findAll(pageable).getContent();
        List<TransactionDTO> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            result.add(transaction.toDTO());
        }
        return result;
    }

    @Transactional(readOnly = true)
    public TransactionDTO findTransaction(Long transaction_id) {
        Transaction transaction = transactionRepository.findOne(transaction_id);
        TransactionDTO result = transaction.toDTO();
        return result;
    }

    @Transactional
    public List<TransactionDTO> findTransaction(Date date) {
        List<TransactionDTO> result = new ArrayList<>();
        List<Transaction> transactions = transactionRepository.findByDate(date);
        for (Transaction transaction : transactions)
            result.add(transaction.toDTO());
        return result;
    }

    @Transactional
    public void deleteTransaction(Long id) {
        transactionRepository.delete(id);
    }

    @Transactional
    public void deleteTransaction(Long[] toDelete) {
        for (long id : toDelete)
            transactionRepository.delete(id);
    }
}