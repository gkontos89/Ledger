package com.marshmallow.android.models.account;

import com.marshmallow.android.models.financialInstrument.CashInstrument;
import com.marshmallow.android.models.transaction.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Created by George on 6/2/2018.
 */
public class CheckingAccount implements MarshmallowAccountInterface {
    private String uniqueId;
    private AccountInformation accountInformation;
    private HashMap<String, Transaction> transactions;
    private CashInstrument cashInstrument;

    public CheckingAccount() {
        transactions = new HashMap<>();
        cashInstrument = new CashInstrument();
    }

    public String getUniqueId() { return uniqueId; }
    public void setUniqueId(String uniqueId) { this.uniqueId = uniqueId; }
    public AccountInformation getAccountInformation() { return accountInformation; }
    public void setAccountInformation(AccountInformation accountInformation) { this.accountInformation = accountInformation; }
    public Transaction getTransaction(String uniqueId) { return transactions.get(uniqueId); }

    @Override
    public int getAccountValue() {
        return cashInstrument.getValue();
    }

    @Override
    public void handleTransaction(Transaction transaction) {
        if (transaction.getAccountingEntry() == Transaction.EntryType.CREDIT) {
            cashInstrument.increaseValue(transaction.getValue());
        } else {
            cashInstrument.decreaseValue(transaction.getValue());
        }

        transactions.put(transaction.getUniqueId(), transaction);
    }
}



