package org.easysplit.dto;

public class BalanceInfo {
    Integer userId;
    Double amount;
    public BalanceInfo(Integer userId, Double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public BalanceInfo() {
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
