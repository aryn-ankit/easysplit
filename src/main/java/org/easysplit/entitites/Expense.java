package org.easysplit.entitites;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Expense extends BaseEntity { // Assuming BaseEntity provides @Id and @GeneratedValue

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Split> splits;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "payee_id") // Assuming a column named payee_id in the expense table
    private User payee;

    // Default constructor for JPA
    public Expense() {
    }

    // Constructor for creating Expense objects (optional, depending on usage)
    public Expense(Double amount, User payee, List<Split> splits) {
        this.amount = amount;
        this.payee = payee;
        this.splits = splits;
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

    public User getPayee() {
        return payee;
    }

    public void setPayee(User payee) {
        this.payee = payee;
    }
}
