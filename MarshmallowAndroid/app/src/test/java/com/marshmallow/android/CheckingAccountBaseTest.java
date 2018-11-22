package com.marshmallow.android;

import com.marshmallow.android.models.account.AccountInformation;
import com.marshmallow.android.models.account.CheckingAccount;
import com.marshmallow.android.models.transaction.Transaction;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by George on 6/2/2018.
 */
public abstract class CheckingAccountBaseTest<T extends CheckingAccount>  {
    private T instance;

    private String creditTransactionId = "f293u4ht2";
    private Transaction creditTransaction = new Transaction();

    private String debitTransactionId = "f293uasdfasdfa";
    private Transaction debitTransaction = new Transaction();

    protected abstract  T createInstance();
    protected T getInstance() { return instance;}

    @Before
    public void setUp() {
        instance = createInstance();

        creditTransaction.setUniqueId(creditTransactionId);
        creditTransaction.setAccountingEntry(Transaction.EntryType.CREDIT);
        creditTransaction.setValue(3800);

        debitTransaction.setUniqueId(debitTransactionId);
        debitTransaction.setAccountingEntry(Transaction.EntryType.DEBIT);
        debitTransaction.setValue(1500);
    }

    @Test
    public void uniqueId() {
        String uniqueId = "gjqh3408";
        instance.setUniqueId(uniqueId);
        assertEquals(instance.getUniqueId(), uniqueId);
    }

    @Test
    public void getAccountInformation() {
        AccountInformation accountInformation = new AccountInformation();
        instance.setAccountInformation(accountInformation);
        assertEquals(instance.getAccountInformation(), accountInformation);
    }

    @Test
    public void handleCreditTransactions() {
        int startingValue = instance.getAccountValue();
        instance.handleTransaction(creditTransaction);
        assertEquals(instance.getAccountValue(), startingValue + creditTransaction.getValue());
    }

    @Test
    public void handleDebitTransaction() {
        int startingValue = instance.getAccountValue();
        instance.handleTransaction(debitTransaction);
        assertEquals(instance.getAccountValue(), startingValue - debitTransaction.getValue());
    }

    @Test
    public void getTransaction() {
        instance.handleTransaction(debitTransaction);
        assertEquals(instance.getTransaction(debitTransactionId), debitTransaction);
    }
}
