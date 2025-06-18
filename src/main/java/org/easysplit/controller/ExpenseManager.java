package org.easysplit.controller;

import org.easysplit.dto.ExpenseDTO;
import org.easysplit.dto.SplitDTO;
import org.easysplit.dto.UserInfo;
import org.easysplit.services.ExpenseUtilityService;

import java.util.*;

public class ExpenseManager {
    private final Map<Integer, UserInfo> usersById;
    private final Map<Integer, Map<Integer, Double>> balanceSheetByUserName;
    private final List<ExpenseDTO> expenses;

    public ExpenseManager() {
        this.usersById = new HashMap<>();
        this.balanceSheetByUserName = new HashMap<>();
        this.expenses = new ArrayList<>();
    }

    public void addUser(UserInfo user) {
        usersById.put(user.getId(), user);
        balanceSheetByUserName.put(user.getId(), new HashMap<>());
    }

    //TODO add various expense types
    public void addExpense(Double amount, Integer payeeUserId, List<SplitDTO> splits) {
        ExpenseDTO expense = ExpenseUtilityService.createExpense(usersById.get(payeeUserId), amount, splits);
        expenses.add(expense);
        Map<Integer, Double> balancesByUsersForPayee = balanceSheetByUserName.get(payeeUserId);

        splits.forEach(split -> {
            int userId = split.getUserId();

            //first increment the balance for this split-user in payee's balance sheet
            if (!balancesByUsersForPayee.containsKey(userId)) {
                balancesByUsersForPayee.put(userId, 0.0);
            }

            double currentAmountforPayeeForUser = balancesByUsersForPayee.get(userId);
            balancesByUsersForPayee.put(userId, currentAmountforPayeeForUser + split.getAmount());
        
            //now let's decrement the payee's balance in user's balance sheet;
            Map<Integer, Double> balancesForSplitUser = balanceSheetByUserName.get(userId);

            if (!balancesForSplitUser.containsKey(payeeUserId)) {
                balancesForSplitUser.put(payeeUserId, 0.0);
            }

            double currentPayeeAmountForUser = balancesForSplitUser.get(payeeUserId);
            balancesForSplitUser.put(payeeUserId, currentPayeeAmountForUser - split.getAmount());
        });
    }

    public void showAllBalances() {
        usersById.keySet().forEach(this::showBalanceForUser);
    }

    public void showBalanceForUser(Integer userID) {
        Map<Integer, Double> balancesForUser = balanceSheetByUserName.get(userID);
        System.out.println("Balances for user : " + userID);
        balancesForUser.forEach((key, value) -> {
            if (!userID.equals(key))
                System.out.println(key + " " + value);
        });
    }
    public UserInfo getUser(String username) {
        return usersById.get(username);
    }
//   root@localhost: nDgN.wENm1;d
}
