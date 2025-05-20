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
@RequestMapping("/customers/{customerId}/accounts")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping("create")
    public ResponseEntity<AccountOutputModel> createAccount(@RequestBody AccountInputModel accountInputModel, @PathVariable Long customerId) throws UserNotFoundException {
        LOGGER.info("Creating account: {}", accountInputModel.toString());
        AccountOutputModel createdAccount = accountService.create(customerId, accountInputModel);
        LOGGER.info("Account created successfully");
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @PutMapping("update/{accountId}")
    public ResponseEntity<AccountOutputModel> updateAccount(@RequestBody AccountInputModel accountInputModel, @PathVariable Long customerId, @PathVariable Long accountId) throws UserNotFoundException, FIAccountNotFoundException {
        LOGGER.info("Updating Account: {}", accountInputModel);
        AccountOutputModel updatedAccount = accountService.update(customerId,accountId,accountInputModel);
        LOGGER.info("Account updated successfully");
        return new ResponseEntity<>(updatedAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long customerId, @PathVariable Long accountId) throws UserNotFoundException, FIAccountNotFoundException {
        LOGGER.info("Deleting Account... ");
        accountService.delete(customerId,accountId);
        LOGGER.info("Account with ID {} deleted successfully", accountId);
        return ResponseEntity.ok("Account with id " + accountId + " deleted successfully");
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AccountOutputModel>> getAll(@PathVariable Long customerId) throws UserNotFoundException {
        LOGGER.info("Getting all Account with customer id: {}", customerId);
        List<AccountOutputModel> accountOutputModels = accountService.getAllAccounts(customerId);
        LOGGER.info("Accounts returned successfully");
        return new ResponseEntity<>(accountOutputModels, HttpStatus.OK);
    }

    @GetMapping("/getaccount/{accountId}")
    public ResponseEntity<AccountOutputModel> getAccount(@PathVariable Long customerId, @PathVariable Long accountId) throws UserNotFoundException, FIAccountNotFoundException {
        LOGGER.info("Getting Account by id: {}", accountId);
        AccountOutputModel returnedAccount = accountService.getAccountById(customerId,accountId);
        LOGGER.info("Account returned successfully");
        return new ResponseEntity<>(returnedAccount, HttpStatus.CREATED);
    }
}
