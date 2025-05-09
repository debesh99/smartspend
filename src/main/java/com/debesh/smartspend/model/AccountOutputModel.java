package com.debesh.smartspend.model;

public class AccountOutputModel {
    private Long id;
    private String accountName;
    private String accountNumber;
    private double currentBalance;

    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public Long getId() {
        return id;
    }
}
