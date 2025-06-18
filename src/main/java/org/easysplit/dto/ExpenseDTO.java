package org.easysplit.dto;

import java.util.List;

public class ExpenseDTO {
    private Long id;
    private Double amount;
    private String payeeUserName;
    private List<SplitDTO> splits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPayeeUserName() {
        return payeeUserName;
    }

    public void setPayeeUserName(String payeeUserName) {
        this.payeeUserName = payeeUserName;
    }

    public List<SplitDTO> getSplits() {
        return splits;
    }

    public void setSplits(List<SplitDTO> splits) {
        this.splits = splits;
    }
}
