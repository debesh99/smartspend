package com.debesh.smartspend.model;

import java.time.LocalDateTime;

public class TransactionOutputModel {
    private Double amount;
    private String description;
    private int type; // 1. Income 2.Expense
    private LocalDateTime transactionDate;

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public int getType() {
        return type;
    }
}
