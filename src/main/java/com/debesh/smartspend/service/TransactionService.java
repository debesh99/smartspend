package com.debesh.smartspend.service;

import com.debesh.smartspend.entity.Transaction;
import com.debesh.smartspend.exceptions.FIAccountNotFoundException;
import com.debesh.smartspend.exceptions.TransactionNotFoundException;
import com.debesh.smartspend.model.TransactionInputModel;
import com.debesh.smartspend.model.TransactionOutputModel;

import java.util.List;

public interface TransactionService {
    //    CRUD OPERAION
    Transaction create(Long accountId, TransactionInputModel transactionInputModel) throws FIAccountNotFoundException;
    List<TransactionOutputModel> getAll(Long accountId) throws FIAccountNotFoundException;
    TransactionOutputModel getById(Long accountId, Long id) throws FIAccountNotFoundException,TransactionNotFoundException;
    TransactionOutputModel update(Long accountId, Long id, TransactionInputModel transactionInputModel) throws FIAccountNotFoundException,TransactionNotFoundException;
    boolean delete(Long accountId, Long id) throws FIAccountNotFoundException, TransactionNotFoundException;
}
