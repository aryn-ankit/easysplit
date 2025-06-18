package org.easysplit.entitites;

import jakarta.persistence.*;

@Entity
public class Split extends BaseEntity { // Assuming BaseEntity provides @Id and @GeneratedValue

    @ManyToOne
    @JoinColumn(name = "user_id") // Assuming a column named user_id in the split table
    private User user;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "expense_id") // Assuming a column named expense_id in the split table
    private Expense expense; // Add reference back to Expense

    // Default constructor for JPA
    public Split() {
    }

    // Constructor for creating Split objects (optional, depending on usage)
    public Split(User user, Double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }
}
