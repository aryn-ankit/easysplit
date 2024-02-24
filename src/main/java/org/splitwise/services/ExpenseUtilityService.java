package org.splitwise.services;

import org.splitwise.entitites.Expense;
import org.splitwise.entitites.Split;
import org.splitwise.entitites.User;

import java.util.List;

public class ExpenseUtilityService {
    public static Expense createExpense(User paidByUser, Double amount, List<Split> splitList) {
        Expense expense = new Expense(splitList, amount, paidByUser);
        return expense;
    }
}
