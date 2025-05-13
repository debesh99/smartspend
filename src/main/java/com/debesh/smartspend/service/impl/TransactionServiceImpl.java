package com.debesh.smartspend.service.impl;

import com.debesh.smartspend.entity.Account;
import com.debesh.smartspend.entity.Transaction;
import com.debesh.smartspend.exceptions.FIAccountNotFoundException;
import com.debesh.smartspend.exceptions.TransactionNotFoundException;
import com.debesh.smartspend.model.TransactionInputModel;
import com.debesh.smartspend.model.TransactionOutputModel;
import com.debesh.smartspend.repository.AccountRepository;
import com.debesh.smartspend.repository.TransactionRepository;
import com.debesh.smartspend.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TransactionOutputModel create(Long accountId, TransactionInputModel transactionInputModel) throws FIAccountNotFoundException {
        Account account = getAccount(accountId);
        Transaction transaction = modelMapper.map(transactionInputModel, Transaction.class);
        transactionRepository.save(transaction);
        TransactionOutputModel transactionOutputModel = modelMapper.map(transaction, TransactionOutputModel.class);
        if(transactionOutputModel.getType()==1){
           account.setCurrentBalance(account.getCurrentBalance()+transactionOutputModel.getAmount());
        }else if(transactionOutputModel.getType()==2){
            account.setCurrentBalance(account.getCurrentBalance()-transactionOutputModel.getAmount());
        }
        return transactionOutputModel;
    }

    @Override
    public List<TransactionOutputModel> getAll(Long accountId) throws FIAccountNotFoundException {
        Account account = getAccount(accountId);
        List<Transaction> transactionList = transactionRepository.findByAccount(account);
        List<TransactionOutputModel> transactionOutputModels = new ArrayList<>();
        for(Transaction transaction: transactionList){
            TransactionOutputModel transactionOutputModel = modelMapper.map(transaction, TransactionOutputModel.class);
            transactionOutputModels.add(transactionOutputModel);
        }
        return transactionOutputModels;
    }

    @Override
    public TransactionOutputModel getById(Long accountId, Long txnId) throws FIAccountNotFoundException, TransactionNotFoundException {
        Account account = getAccount(accountId);
        Transaction transaction = getTransaction(txnId);
        TransactionOutputModel transactionOutputModel = modelMapper.map(transaction, TransactionOutputModel.class);
        return  transactionOutputModel;
    }

    @Override
    public TransactionOutputModel update(Long accountId, Long txnId, TransactionInputModel transactionInputModel) throws FIAccountNotFoundException, TransactionNotFoundException {
        Account account = getAccount(accountId);
        Transaction transaction = getTransaction(txnId);
        transaction = modelMapper.map(transactionInputModel, Transaction.class);
        transactionRepository.save(transaction);
        TransactionOutputModel transactionOutputModel = modelMapper.map(transaction, TransactionOutputModel.class);
        if(transactionOutputModel.getType()==1){
            account.setCurrentBalance(account.getCurrentBalance()+transactionOutputModel.getAmount());
        }else if(transactionOutputModel.getType()==2){
            account.setCurrentBalance(account.getCurrentBalance()-transactionOutputModel.getAmount());
        }
        return transactionOutputModel;
    }

    @Override
    public boolean delete(Long accountId, Long txnId) throws FIAccountNotFoundException, TransactionNotFoundException {
        Account account = getAccount(accountId);
        Transaction transaction = getTransaction(txnId);
        transactionRepository.delete(transaction);
        return true;
    }

    private Account getAccount(Long accountId) throws FIAccountNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new FIAccountNotFoundException("Account with " + accountId + " is not present"));
    }

    private Transaction getTransaction(Long txnId) throws TransactionNotFoundException {
        return transactionRepository.findById(txnId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with " + txnId + " is not present"));
    }
}
