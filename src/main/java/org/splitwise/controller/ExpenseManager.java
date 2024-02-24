package org.splitwise.controller;

import org.splitwise.entitites.Expense;
import org.splitwise.entitites.Split;
import org.splitwise.entitites.User;
import org.splitwise.services.ExpenseUtilityService;

import java.util.*;

public class ExpenseManager {
    private final Map<String, User> usersByUserName;
    private final Map<String, Map<String, Double>> balanceSheetByUserName;
    private final List<Expense> expenses;

    public ExpenseManager() {
        this.usersByUserName = new HashMap<>();
        this.balanceSheetByUserName = new HashMap<>();
        this.expenses = new ArrayList<>();
    }

    public void addUser(User user) {
        usersByUserName.put(user.getUserName(), user);
        balanceSheetByUserName.put(user.getUserName(), new HashMap<>());
    }

    //TODO add various expense types
    public void addExpense(Double amount, String payeeUserName, List<Split> splits) {
        Expense expense = ExpenseUtilityService.createExpense(usersByUserName.get(payeeUserName), amount, splits);
        expenses.add(expense);
        Map<String, Double> balancesByUsersForPayee = balanceSheetByUserName.get(payeeUserName);

        splits.forEach(split -> {
            String userName = split.getUser().getUserName();

            //first increment the balance for this split-user in payee's balance sheet
            if (!balancesByUsersForPayee.containsKey(userName)) {
                balancesByUsersForPayee.put(userName, 0.0);
            }

            double currentAmountforPayeeForUser = balancesByUsersForPayee.get(userName);
            balancesByUsersForPayee.put(userName, currentAmountforPayeeForUser + split.getAmount());

            //now let's decrement the payee's balance in user's balance sheet;
            Map<String, Double> balancesForSplitUser = balanceSheetByUserName.get(userName);

            if (!balancesForSplitUser.containsKey(payeeUserName)) {
                balancesForSplitUser.put(payeeUserName, 0.0);
            }

            double currentPayeeAmountForUser = balancesForSplitUser.get(payeeUserName);
            balancesForSplitUser.put(payeeUserName, currentPayeeAmountForUser - split.getAmount());
        });
    }

    public void showAllBalances() {
        usersByUserName.keySet().forEach(this::showBalanceForUser);
    }

    public void showBalanceForUser(String userName) {
        Map<String, Double> balancesForUser = balanceSheetByUserName.get(userName);
        System.out.println("Balances for user : " + userName);
        balancesForUser.forEach((key, value) -> {
            if (!userName.equals(key))
                System.out.println(key + " " + value);
        });
    }
    public User getUser(String username) {
        return usersByUserName.get(username);
    }

}
