package com.debesh.smartspend.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Account {
    @Id
    private Long id;
    private String accName;
    private String accNumber;
    private double currentBalance;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "account", orphanRemoval = true)
    private List<Transaction> transactions;

    //	Current balance should start from 0.0
    @PrePersist
    public void setCurrentBalance() {
        this.currentBalance = 0.0;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


//cascadeType.Refresh - field will be refreshed each time a new transaction is added
//fetch type means when we fetch the account, all transaction should be fetched
//mapped by - mapped to account object of Transaction
//orphanRemoval - When account is deleted all transaction linked to account should also be deleted.