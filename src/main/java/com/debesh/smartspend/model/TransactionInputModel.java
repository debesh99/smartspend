package com.debesh.smartspend.model;

public class TransactionInputModel {
    private Double amount;
    private String description;
    private int type; // 1. Income 2.Expense

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(int type) {
        this.type = type;
    }
}
