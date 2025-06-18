package org.easysplit.controller;

import org.easysplit.dto.AddExpenseRequest;
import org.easysplit.dto.BalanceSheetInfo;
import org.easysplit.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<String> addExpense(@RequestBody AddExpenseRequest request) {
        try {
            expenseService.addExpense(request.getAmount(), request.getPayeeUserID(), request.getSplits());
            return ResponseEntity.ok("Expense added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding expense: " + e.getMessage());
        }
    }

    @GetMapping("/balance/all")
    public ResponseEntity<Map<Integer, BalanceSheetInfo>> getAllBalances() {
        try {
            Map<Integer, BalanceSheetInfo> balances = expenseService.getAllBalances();
            return ResponseEntity.ok(balances);
        } catch (Exception e) {
            // In a real application, you might want more specific error handling and response
            e.printStackTrace(); // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/balance/{userID}")
    public ResponseEntity<BalanceSheetInfo> getBalancesForUser(@PathVariable Integer userID) {
        try {
            BalanceSheetInfo balanceSheet = expenseService.getBalancesForUser(userID);
            return ResponseEntity.ok(balanceSheet);
        } catch (Exception e) {
             // In a real application, you might want more specific error handling and response
            e.printStackTrace(); // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
