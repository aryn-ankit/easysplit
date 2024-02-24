package org.splitwise.dto;

public class BalanceInfo {
    String userName;
    Double amount;
    public BalanceInfo(String userName, Double amount) {
        this.userName = userName;
        this.amount = amount;
    }

    public BalanceInfo() {
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
