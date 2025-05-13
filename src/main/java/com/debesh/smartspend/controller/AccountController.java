package com.debesh.smartspend.controller;

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

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<AccountOutputModel> registerCustomer(@RequestBody AccountInputModel accountInputModel, @RequestBody Long customerId) throws UserNotFoundException {
        LOGGER.info("Creating wallet: {}", accountInputModel);
        AccountOutputModel accountCreated = accountService.create(customerId, accountInputModel);
        LOGGER.info("Wallet created successfully");
        return new ResponseEntity<>(accountCreated, HttpStatus.CREATED);
    }
}
