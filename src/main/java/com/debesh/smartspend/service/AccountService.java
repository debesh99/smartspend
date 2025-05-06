package com.debesh.smartspend.service;

import com.debesh.smartspend.exceptions.FIAccountNotFoundException;
import com.debesh.smartspend.exceptions.UserNotFoundException;
import com.debesh.smartspend.model.AccountInputModel;
import com.debesh.smartspend.model.AccountOutputModel;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountService {
    //    CRUD Operations
    AccountOutputModel create(Long userId, AccountInputModel accountInputModel)throws UserNotFoundException, FIAccountNotFoundException;

    AccountOutputModel update( AccountInputModel accountInputModel, Long id) throws FIAccountNotFoundException;

    boolean deleteWallet(Long id) throws FIAccountNotFoundException;

    AccountOutputModel getWalletById(Long id) throws FIAccountNotFoundException;

    List<AccountOutputModel> getAllWallets(Long userId) throws FIAccountNotFoundException;

}
