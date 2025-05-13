package com.debesh.smartspend.service;

import com.debesh.smartspend.exceptions.FIAccountNotFoundException;
import com.debesh.smartspend.exceptions.TransactionNotFoundException;
import com.debesh.smartspend.model.TransactionInputModel;
import com.debesh.smartspend.model.TransactionOutputModel;

import java.util.List;

public interface TransactionService {
    //    CRUD OPERAION
    TransactionOutputModel create(Long accountId, TransactionInputModel transactionInputModel) throws FIAccountNotFoundException;
    List<TransactionOutputModel> getAll(Long accountId) throws FIAccountNotFoundException;
    TransactionOutputModel getById(Long accountId, Long txnId) throws FIAccountNotFoundException,TransactionNotFoundException;
    TransactionOutputModel update(Long accountId, Long txnId, TransactionInputModel transactionInputModel) throws FIAccountNotFoundException,TransactionNotFoundException;
    boolean delete(Long accountId, Long txnId) throws FIAccountNotFoundException, TransactionNotFoundException;
}
