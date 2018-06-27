package com.marshmallow.android.models.account;

import java.util.HashMap;

/**
 * Created by George on 6/4/2018.
 */
public class AccountManager {
    private static AccountManager instance = null;
    private HashMap<String, Object> accounts;

    private AccountManager() {
        accounts = new HashMap<>();
    }

    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }

        return instance;
    }

    public void destroyMe() {
        instance = null;
    }

    public void addAccount(CheckingAccount checkingAccount) {
        accounts.put(checkingAccount.getUniqueId(), checkingAccount);
    }

    public HashMap<String, Object> getAccounts() { return accounts; }

    public Object getAccount(String checkingAccountUniqueId) {
        return accounts.get(checkingAccountUniqueId);
    }

    public int getAccountTotals() {
        int total = 0;
        for (Object account : accounts.values()) {
            total += ((MarshmallowAccountInterface) account).getAccountValue();
        }

        return total;
    }
}
