package com.debesh.smartspend.model;

public class TransactionInputModel {
    private Double amount;
    private String description;
    private int type; // 1. Income 2.Expense

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TransactionInputModel{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}
