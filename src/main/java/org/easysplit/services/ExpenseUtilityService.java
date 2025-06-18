package org.easysplit.services;

import org.easysplit.dto.ExpenseDTO;
import org.easysplit.dto.SplitDTO;
import org.easysplit.dto.UserInfo;

import java.util.List;

public class ExpenseUtilityService {
    public static ExpenseDTO createExpense(UserInfo payee, Double amount, List<SplitDTO> splitList) {
        ExpenseDTO expense = new ExpenseDTO();
        expense.setAmount(amount);
        expense.setPayeeUserName(payee.getUserName());
        expense.setSplits(splitList);        
        return expense;
    }
}
