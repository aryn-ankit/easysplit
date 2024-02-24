package org.splitwise.entitites;

import java.util.List;

public class Expense {
    private List<Split> splits;
    private Double      amount;
    private User        paidByUser;

    public Expense(List<Split> splits, Double amount, User paidByUser) {
        this.splits = splits;
        this.amount = amount;
        this.paidByUser = paidByUser;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getPaidByUser() {
        return paidByUser;
    }

    public void setPaidByUser(User paidByUser) {
        this.paidByUser = paidByUser;
    }
}
