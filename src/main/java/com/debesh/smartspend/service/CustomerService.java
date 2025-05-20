package com.debesh.smartspend.service;

import com.debesh.smartspend.exceptions.InvalidCutomerCredentialException;
import com.debesh.smartspend.exceptions.UserNotFoundException;
import com.debesh.smartspend.model.CustomerInputModel;
import com.debesh.smartspend.model.CustomerOutputModel;

public interface CustomerService {
    CustomerOutputModel registerCustomer(CustomerInputModel customerInputModel) throws InvalidCutomerCredentialException;
    CustomerOutputModel getCustomer(Long customerId) throws UserNotFoundException;
}
