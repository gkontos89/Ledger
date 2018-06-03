package com.marshmallow.android.models.account;

import com.marshmallow.android.models.financialInstrument.CashInstrument;
import com.marshmallow.android.models.transaction.Transaction;

import java.util.List;
import java.util.Vector;

/**
 * Created by George on 6/2/2018.
 */
public class CheckingAccount implements MarshmallowAccountInterface {
    private String uniqueId;
    private AccountInformation accountInformation;
    private List<Transaction> transactions;
    private CashInstrument cashInstrument;

    public CheckingAccount() {
        transactions = new Vector<>();
        cashInstrument = new CashInstrument();
    }

    public String getUniqueId() { return uniqueId; }
    public void setUniqueId(String uniqueId) { this.uniqueId = uniqueId; }
    public AccountInformation getAccountInformation() { return accountInformation; }
    public void setAccountInformation(AccountInformation accountInformation) { this.accountInformation = accountInformation; }

    @Override
    public int getAccountValue() {

    }

}



