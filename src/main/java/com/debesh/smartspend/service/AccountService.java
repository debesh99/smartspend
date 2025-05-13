package com.debesh.smartspend.service;

import com.debesh.smartspend.exceptions.FIAccountNotFoundException;
import com.debesh.smartspend.exceptions.UserNotFoundException;
import com.debesh.smartspend.model.AccountInputModel;
import com.debesh.smartspend.model.AccountOutputModel;

import java.util.List;

public interface AccountService {
    //    CRUD Operations
    AccountOutputModel create(Long customerId, AccountInputModel accountInputModel)throws UserNotFoundException;

    AccountOutputModel update( Long customerId, Long accountId, AccountInputModel accountInputModel) throws  UserNotFoundException,FIAccountNotFoundException;

    boolean deleteWallet(Long customerId, Long accountId) throws  UserNotFoundException,FIAccountNotFoundException;

    AccountOutputModel getWalletById(Long customerId, Long accountId) throws  UserNotFoundException,FIAccountNotFoundException;

    List<AccountOutputModel> getAllWallets(Long customerId) throws  UserNotFoundException;

}
