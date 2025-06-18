package org.easysplit.services.impl;

import org.easysplit.dto.BalanceInfo;
import org.easysplit.dto.BalanceSheetInfo;
import org.easysplit.dto.SplitDTO;
import org.easysplit.entitites.Expense;
import org.easysplit.entitites.Split;
import org.easysplit.entitites.User;
import org.easysplit.repositories.ExpenseRepository;
import org.easysplit.repositories.SplitRepository;
import org.easysplit.repositories.UserRepository;
import org.easysplit.services.ExpenseService;
import org.easysplit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private SplitRepository splitRepository;

    @Autowired
    private UserRepository userRepository; // Inject UserRepository to get User entities by ID

    @Autowired
    private UserService userService; // Inject UserService to get UserInfo DTOs (if needed for DTO conversion)

    // Note: In a real application, the balance calculation logic might be complex
    // and could potentially be moved to a dedicated service or use database queries
    // for better performance on large datasets. For this example, we'll calculate
    // balances by fetching all relevant expenses and splits.

    @Override
    public void addExpense(Double amount, int payeeUserID, List<SplitDTO> splits) throws Exception {
        // Fetch payee User entity
        User payee = userRepository.findById(payeeUserID).orElseThrow(() -> new Exception("Payee user not found: " + payeeUserID));

        // Create Split entities from SplitDTOs
        List<Split> splitEntities = new ArrayList<>();
        for (SplitDTO splitDTO : splits) {
            User splitUser = userRepository.findById(splitDTO.getUserId())
                                         .orElseThrow(() -> new Exception("Split user not found with ID: " + splitDTO.getUserId()));
            Split split = new Split(); // Use default constructor
            split.setUser(splitUser);
            split.setAmount(splitDTO.getAmount());
            splitEntities.add(split);
        }

        // Create and save Expense entity
        Expense expense = new Expense(); // Use default constructor
        expense.setAmount(amount);
        expense.setPayee(payee);
        expense.setSplits(splitEntities); // Link splits to expense

        Expense savedExpense = expenseRepository.save(expense);

        // Save Split entities (they should be cascaded from Expense save, but explicitly saving for clarity)
        for (Split split : splitEntities) {
            split.setExpense(savedExpense); // Link expense to splits
            splitRepository.save(split);
        }

        // Note: Balance sheet is not updated in memory anymore.
        // Balances will be calculated on the fly from the database.
    }

    @Override
    public Map<Integer, BalanceSheetInfo> getAllBalances() throws Exception {
        // Fetch all users to build the balance sheet structure
        List<User> allUsers = userRepository.findAll();
        Map<Integer, BalanceSheetInfo> allBalances = new HashMap<>();
        for (User user : allUsers) {
            allBalances.put(user.getId(), new BalanceSheetInfo(user.getId()));
        }

        // Fetch all expenses and splits to calculate balances
        List<Expense> allExpenses = expenseRepository.findAll();

        for (Expense expense : allExpenses) {
            Integer payeeUserId = expense.getPayee().getId();
            Double amount = expense.getAmount();
            List<Split> splits = expense.getSplits();

            for (Split split : splits) {
                Integer splitUserId = split.getUser().getId();
                Double splitAmount = split.getAmount();

                // Update balance for the payee (owes to split user)
                BalanceSheetInfo payeeBalanceSheet = allBalances.get(payeeUserId);
                if (payeeBalanceSheet != null) {
                    updateBalance(payeeBalanceSheet, splitUserId, splitAmount);
                }

                // Update balance for the split user (is owed by payee)
                BalanceSheetInfo splitUserBalanceSheet = allBalances.get(splitUserId);
                if (splitUserBalanceSheet != null) {
                    updateBalance(splitUserBalanceSheet, payeeUserId, -splitAmount); // Negative amount means payee owes split user
                }
            }
        }

        // Clean up zero balances and format for response
        Map<Integer, BalanceSheetInfo> finalBalances = new HashMap<>();
        for (Map.Entry<Integer, BalanceSheetInfo> entry : allBalances.entrySet()) {
            BalanceSheetInfo balanceSheet = entry.getValue();
            // Filter out zero balances
            Set<BalanceInfo> nonZeroBalances = balanceSheet.getBalanceInfos().stream()
                    .filter(b -> b.getAmount() != 0.0)
                    .collect(Collectors.toSet());
            if (!nonZeroBalances.isEmpty()) {
                balanceSheet.setBalanceInfos(nonZeroBalances);
                finalBalances.put(entry.getKey(), balanceSheet);
            }
        }

        return finalBalances;
    }

    @Override
    public BalanceSheetInfo getBalancesForUser(Integer userID) throws Exception {
        User user = userRepository.findById(userID).orElseThrow(() -> new Exception("User not found: " + userID));
        if (user == null) {
            throw new Exception("User not found: " + userID);
        }

        BalanceSheetInfo balanceSheetInfo = new BalanceSheetInfo(userID);
        Map<Integer, Double> balances = new HashMap<>(); // Temp map to aggregate balances

        // Find all expenses where this user is the payee
        List<Expense> expensesPaidByUser = expenseRepository.findByPayee(user);
        for (Expense expense : expensesPaidByUser) {
            for (Split split : expense.getSplits()) {
                Integer splitUserId = split.getUser().getId();
                Double splitAmount = split.getAmount();
                balances.merge(splitUserId, splitAmount, Double::sum);
            }
        }

        // Find all splits where this user is a split participant
        List<Split> splitsForUser = splitRepository.findByUser(user);
        for (Split split : splitsForUser) {
            Integer payeeUserId = split.getExpense().getPayee().getId();
            Double splitAmount = split.getAmount();
            balances.merge(payeeUserId, -splitAmount, Double::sum); // Negative as payee owes this user
        }

        // Convert aggregated balances to BalanceInfo DTOs
        for (Map.Entry<Integer, Double> entry : balances.entrySet()) {
            Integer otherUserId = entry.getKey();
            Double amount = entry.getValue();

            if (!userID.equals(otherUserId) && amount != 0.0) {
                BalanceInfo balanceInfo = new BalanceInfo();
                balanceInfo.setUserId(otherUserId);
                balanceInfo.setAmount(amount);
                balanceSheetInfo.getBalanceInfos().add(balanceInfo);
            }
        }

        return balanceSheetInfo;
    }

    // Helper method to update balances in the BalanceSheetInfo map
    private void updateBalance(BalanceSheetInfo balanceSheet, Integer otherUserID, Double amount) {
        Optional<BalanceInfo> existingBalance = balanceSheet.getBalanceInfos().stream()
                .filter(b -> b.getUserId().equals(otherUserID))
                .findFirst();

        if (existingBalance.isPresent()) {
            existingBalance.get().setAmount(existingBalance.get().getAmount() + amount);
        } else {
            BalanceInfo newBalance = new BalanceInfo();
            newBalance.setUserId(otherUserID);
            newBalance.setAmount(amount);
            balanceSheet.getBalanceInfos().add(newBalance);
        }
    }
}
