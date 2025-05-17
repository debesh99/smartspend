package com.debesh.smartspend.controller;

import com.debesh.smartspend.exceptions.FIAccountNotFoundException;
import com.debesh.smartspend.exceptions.UserNotFoundException;
import com.debesh.smartspend.model.AccountInputModel;
import com.debesh.smartspend.model.AccountOutputModel;
import com.debesh.smartspend.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<AccountOutputModel> createAccount(@RequestBody AccountInputModel accountInputModel, @RequestBody Long customerId) throws UserNotFoundException {
        LOGGER.info("Creating account: {}", accountInputModel);
        AccountOutputModel createdAccount = accountService.create(customerId, accountInputModel);
        LOGGER.info("Account created successfully");
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AccountOutputModel> updateAccount(@RequestBody AccountInputModel accountInputModel, @RequestBody Long customerId, @RequestBody Long accountId) throws UserNotFoundException, FIAccountNotFoundException {
        LOGGER.info("Updating Account: {}", accountInputModel);
        AccountOutputModel updatedAccount = accountService.update(customerId,accountId,accountInputModel);
        LOGGER.info("Account updated successfully");
        return new ResponseEntity<>(updatedAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestBody Long customerId, @RequestBody Long accountId) throws UserNotFoundException, FIAccountNotFoundException {
        LOGGER.info("Deleting Account... ");
        accountService.delete(customerId,accountId);
        LOGGER.info("Account with ID {} deleted successfully", accountId);
        return ResponseEntity.ok("Account with id " + accountId + " deleted successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AccountOutputModel>> getAll(@RequestBody Long accountId) throws UserNotFoundException {
        LOGGER.info("Getitng all Account with account id: {}", accountId);
        List<AccountOutputModel> accountOutputModels = accountService.getAllAccounts(accountId);
        LOGGER.info("Account returned successfully");
        return new ResponseEntity<>(accountOutputModels, HttpStatus.OK);
    }

    @GetMapping("/getAccount")
    public ResponseEntity<AccountOutputModel> getAccount(@RequestBody Long customerId, @RequestBody Long accountId) throws UserNotFoundException, FIAccountNotFoundException {
        LOGGER.info("Getting Account by id: {}", accountId);
        AccountOutputModel returnedAccount = accountService.getAccountById(customerId,accountId);
        LOGGER.info("Account returned successfully");
        return new ResponseEntity<>(returnedAccount, HttpStatus.CREATED);
    }
}
