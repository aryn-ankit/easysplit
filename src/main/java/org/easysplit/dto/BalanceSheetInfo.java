package org.easysplit.dto;

import java.util.HashSet;
import java.util.Set;

public class BalanceSheetInfo {
    private int userID;
    private Set<BalanceInfo> balanceInfos;

    public BalanceSheetInfo(int userID) {
        this.userID = userID;
        this.balanceInfos = new HashSet<>();
    }

    public Set<BalanceInfo> getBalanceInfos() {
        return balanceInfos;
    }

    public void setBalanceInfos(Set<BalanceInfo> balanceInfos) {
        this.balanceInfos = balanceInfos;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
