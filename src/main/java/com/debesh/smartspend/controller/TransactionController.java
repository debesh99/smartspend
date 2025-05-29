package com.debesh.smartspend.controller;

import com.debesh.smartspend.entity.Transaction;
import com.debesh.smartspend.exceptions.FIAccountNotFoundException;
import com.debesh.smartspend.exceptions.TransactionNotFoundException;
import com.debesh.smartspend.model.TransactionInputModel;
import com.debesh.smartspend.model.TransactionOutputModel;
import com.debesh.smartspend.service.TransactionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts/{accountId}/transactions")
@CrossOrigin
public class TransactionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @PostMapping("create")
    public ResponseEntity<?> create(@PathVariable Long accountId,
                                    @RequestBody TransactionInputModel transactionInputModel)
            throws FIAccountNotFoundException {
        LOGGER.info("Creating transaction for Account ID: {}", accountId);
        TransactionOutputModel transactionSaved = transactionService.create(accountId, transactionInputModel);
        LOGGER.info("Transaction created: {}", transactionSaved);
        return new ResponseEntity<>(transactionSaved, HttpStatus.CREATED);
    }

    @PutMapping("/update/{txnId}")
    public ResponseEntity<?> update(@PathVariable Long accountId, @PathVariable Long txnId,
                                    @Valid @RequestBody TransactionInputModel transactionInputModel, BindingResult result)
            throws FIAccountNotFoundException, TransactionNotFoundException {
        LOGGER.info("Updating transaction with ID: {} for account ID: {}", txnId, accountId);
        TransactionOutputModel transactionOutputModel = transactionService.update(accountId, txnId, transactionInputModel);
        return new ResponseEntity<>(transactionOutputModel, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{txnId}")
    public ResponseEntity<?> delete(@PathVariable Long accountId, @PathVariable Long txnId)
            throws FIAccountNotFoundException, TransactionNotFoundException {
        LOGGER.info("Deleting transaction with ID: {} for account",txnId);
        transactionService.delete(accountId, txnId);
        return ResponseEntity.ok("Transaction with id " + txnId + " successfully deleted");
    }

    @GetMapping("/gettransactionbyid/{txnId}")
    public ResponseEntity<?> getById(@PathVariable Long accountId, @PathVariable Long txnId)
            throws FIAccountNotFoundException, TransactionNotFoundException {
        LOGGER.info("Retrieving transaction with ID: {} for wallet ID: {}", txnId, accountId);
        return new ResponseEntity<>(transactionService.getById(accountId, txnId), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@PathVariable Long accountId) throws FIAccountNotFoundException{
        List<TransactionOutputModel> transactionOutputModels = transactionService.getAll(accountId);
        return new ResponseEntity<>(transactionOutputModels, HttpStatus.OK);
    }
}
