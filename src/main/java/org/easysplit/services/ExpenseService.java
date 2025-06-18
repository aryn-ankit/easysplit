package org.easysplit.services;

import org.easysplit.dto.BalanceSheetInfo;
import org.easysplit.dto.SplitDTO; // Import SplitDTO

import java.util.List;
import java.util.Map;

public interface ExpenseService {

    void addExpense(Double amount, int payeeUserID, List<SplitDTO> splits) throws Exception; // Use SplitDTO

    BalanceSheetInfo getBalancesForUser(Integer userID) throws Exception;

    Map<Integer, BalanceSheetInfo> getAllBalances() throws Exception;
}
