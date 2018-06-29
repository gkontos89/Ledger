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
    private AccountInformation accountInformation;
    private HashMap<String, Transaction> transactions;
    private CashInstrument cashInstrument;
    private Integer interestRate;

    public CheckingAccount() {
        transactions = new HashMap<>();
        cashInstrument = new CashInstrument();
    }

    @Override
    public String getUniqueId() { return accountInformation.getUniqueId(); }
    public void setUniqueId(String uniqueId) { this.accountInformation.setUniqueId(uniqueId); }
    public AccountInformation getAccountInformation() { return accountInformation; }
    public void setAccountInformation(AccountInformation accountInformation) { this.accountInformation = accountInformation; }
    public Transaction getTransaction(String uniqueId) { return transactions.get(uniqueId); }
    public Integer getInterestRate() { return interestRate; }
    public void setInterestRate(Integer interestRate) { this.interestRate = interestRate; }

    @Override
    public String getAccountName() { return accountInformation.getAccountName(); }

    @Override
    public int getAccountValue() {
        return cashInstrument.getValue();
    }

    @Override
    public void setAccountValue(int value) { cashInstrument.setValue(value); }

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



