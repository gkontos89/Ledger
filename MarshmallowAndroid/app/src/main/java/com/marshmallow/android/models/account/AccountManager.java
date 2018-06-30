package com.marshmallow.android.models.account;

import com.marshmallow.android.models.summaryEngine.SummaryEngine;
import com.marshmallow.android.ui.CheckingAccountActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by George on 6/4/2018.
 */
public class AccountManager {
    private static AccountManager instance = null;
    private HashMap<String, Object> accounts;
    private HashMap<String, Class> accountClassifiers;

    private AccountManager() {
        accounts = new HashMap<>();
        accountClassifiers = new HashMap<String, Class>() {
            {
                put("Checking Account", CheckingAccountActivity.class);
            }
        };
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

    public Set<String> getAccountUniqueIds() { return accounts.keySet(); }

    public int getAccountTotals() {
        int total = 0;
        for (Object account : accounts.values()) {
            total += ((MarshmallowAccountInterface) account).getAccountValue();
        }

        return total;
    }

    public HashMap<String, Class> getAccountClassifiers() { return accountClassifiers; }

    public HashMap<SummaryEngine.SummaryType, Integer> getSummaries() {
        HashMap<SummaryEngine.SummaryType, Integer> summaries = new HashMap<>();
        for (Object account : accounts.values()) {
            HashMap<SummaryEngine.SummaryType, Integer> accountSummaries;
            accountSummaries = ((MarshmallowAccountInterface) account).getSummaries();
            for (Map.Entry<SummaryEngine.SummaryType, Integer> entry : accountSummaries.entrySet()) {
                Integer currentSummaryValue = 0;
                if (summaries.containsKey(entry.getKey())) {
                    currentSummaryValue = summaries.get(entry.getKey());
                }

                summaries.put(entry.getKey(), currentSummaryValue + entry.getValue());
            }
        }

        return summaries;
    }
}
