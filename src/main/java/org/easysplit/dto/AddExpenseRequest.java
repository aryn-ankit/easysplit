package org.easysplit.dto;

import java.util.List;

public class AddExpenseRequest {
    private Double amount;
    private int payeeUserID;
    private List<SplitDTO> splits;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<SplitDTO> getSplits() {
        return splits;
    }

    public void setSplits(List<SplitDTO> splits) {
        this.splits = splits;
    }

    public int getPayeeUserID() {
        return payeeUserID;
    }

    public void setPayeeUserID(int payeeUserID) {
        this.payeeUserID = payeeUserID;
    }
}
