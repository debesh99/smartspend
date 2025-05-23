package com.debesh.smartspend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountName;
    private String accountNumber;
    private double currentBalance;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "account", orphanRemoval = true)
    private List<Transaction> transactions;

    //	Current balance should start from 0.0
    @PrePersist
    public void setBalance() {
        this.currentBalance = 0.0;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accName='" + accountName + '\'' +
                ", id=" + id +
                ", accNumber='" + accountNumber + '\'' +
                ", currentBalance=" + currentBalance +
                ", customer=" + customer +
                ", transactions=" + transactions +
                '}';
    }
}


//cascadeType.Refresh - field will be refreshed each time a new transaction is added
//fetch type means when we fetch the account, all transaction should be fetched
//mapped by - mapped to account object of Transaction
//orphanRemoval - When account is deleted all transaction linked to account should also be deleted.