package com.debesh.smartspend.service.impl;

import com.debesh.smartspend.entity.Account;
import com.debesh.smartspend.entity.Customer;
import com.debesh.smartspend.exceptions.FIAccountNotFoundException;
import com.debesh.smartspend.exceptions.UserNotFoundException;
import com.debesh.smartspend.model.AccountInputModel;
import com.debesh.smartspend.model.AccountOutputModel;
import com.debesh.smartspend.repository.AccountRepository;
import com.debesh.smartspend.repository.CustomerRepository;
import com.debesh.smartspend.service.AccountService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    @Transactional
    public AccountOutputModel create(Long customerId, AccountInputModel accountInputModel) throws UserNotFoundException {
        Customer customer = getCustomer(customerId);
        Account account = modelMapper.map(accountInputModel, Account.class);
        account.setCustomer(customer);
        accountRepository.save(account);
        AccountOutputModel accountOutputModel = modelMapper.map(account, AccountOutputModel.class);
        LOGGER.info(accountOutputModel.toString());
        return accountOutputModel;
    }

    @Override
    public AccountOutputModel update(Long customerId, Long accountId, AccountInputModel accountInputModel) throws UserNotFoundException, FIAccountNotFoundException {
        LOGGER.info(accountInputModel.toString());
        Customer customer = getCustomer(customerId);
        Account account = getAccount(accountId);
        account = modelMapper.map(accountInputModel, Account.class);
        account.setCustomer(customer);
        accountRepository.save(account);
        AccountOutputModel accountOutputModel = modelMapper.map(account, AccountOutputModel.class);
        return accountOutputModel;
    }

    @Override
    public void delete(Long customerId, Long accountId) throws UserNotFoundException, FIAccountNotFoundException {
        Customer customer = getCustomer(customerId);
        Account account = getAccount(accountId);
        accountRepository.deleteById(accountId);
    }

    @Override
    public AccountOutputModel getAccountById(Long customerId, Long accountId) throws UserNotFoundException, FIAccountNotFoundException {
        Customer customer = getCustomer(customerId);
        Account account = getAccount(accountId);
        AccountOutputModel accountOutputModel = modelMapper.map(account, AccountOutputModel.class);
        return accountOutputModel;
    }

    @Override
    public List<AccountOutputModel> getAllAccounts(Long customerId) throws UserNotFoundException {
        Customer customer = getCustomer(customerId);
        List<AccountOutputModel> accountOutputModelList = new ArrayList<>();
        List<Account> accountsList = accountRepository.findAllByCustomerId(customerId);
        for(Account account : accountsList){
            AccountOutputModel accountOutputModel = modelMapper.map(account, AccountOutputModel.class);
            accountOutputModelList.add(accountOutputModel);
        }
        return accountOutputModelList;
    }

    private Customer getCustomer(Long customerId) throws UserNotFoundException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new UserNotFoundException("Customer not found"));
    }

    private Account getAccount(Long accountId) throws FIAccountNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new FIAccountNotFoundException("Account with " + accountId + " is not present"));
    }
}
