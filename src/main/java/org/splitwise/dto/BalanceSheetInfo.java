package org.splitwise.dto;

import java.util.HashSet;
import java.util.Set;

public class BalanceSheetInfo {
    private String userName;
    private Set<BalanceInfo> balanceInfos;

    public BalanceSheetInfo(String userName) {
        this.userName = userName;
        this.balanceInfos = new HashSet<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<BalanceInfo> getBalanceInfos() {
        return balanceInfos;
    }

    public void setBalanceInfos(Set<BalanceInfo> balanceInfos) {
        this.balanceInfos = balanceInfos;
    }

}
