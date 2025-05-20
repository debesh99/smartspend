package com.debesh.smartspend.controller;

import com.debesh.smartspend.exceptions.InvalidCutomerCredentialException;
import com.debesh.smartspend.exceptions.UserNotFoundException;
import com.debesh.smartspend.model.CustomerInputModel;
import com.debesh.smartspend.model.CustomerOutputModel;
import com.debesh.smartspend.service.CustomerService;
import com.debesh.smartspend.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailService emailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("/register")
    public ResponseEntity<CustomerOutputModel> registerCustomer(@RequestBody CustomerInputModel customerInputModel) throws InvalidCutomerCredentialException {
        LOGGER.info("Registering a new customer: {}", customerInputModel.toString());
        CustomerOutputModel registeredCustomer = customerService.registerCustomer(customerInputModel);
        LOGGER.info("customer registered successfully !!!");
        return new ResponseEntity<>(registeredCustomer, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/get/{customerId}")
    public ResponseEntity<CustomerOutputModel> getCustomer(@PathVariable Long customerId) {
        try {
            CustomerOutputModel customerOutputModel = customerService.getCustomer(customerId);
            return ResponseEntity.ok(customerOutputModel);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build(); // Return 404 if customer not found
        }
    }
}
