package com.debesh.smartspend.controller;

import com.debesh.smartspend.entity.Transaction;
import com.debesh.smartspend.exceptions.FIAccountNotFoundException;
import com.debesh.smartspend.model.TransactionInputModel;
import com.debesh.smartspend.model.TransactionOutputModel;
import com.debesh.smartspend.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/{accountId}/transactions")
@CrossOrigin
public class TransactionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> create(@PathVariable Long accountId,
                                    @RequestBody TransactionInputModel transactionInputModel)
            throws FIAccountNotFoundException {
        LOGGER.info("Creating transaction for Account ID: {}", accountId);
        TransactionOutputModel transactionSaved = transactionService.create(accountId, transactionInputModel);
        LOGGER.info("Transaction created: {}", transactionSaved);
        return new ResponseEntity<>(transactionSaved, HttpStatus.CREATED);
    }
}
