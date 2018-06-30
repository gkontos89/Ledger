package com.marshmallow.android;

import com.marshmallow.android.models.account.AccountManager;
import com.marshmallow.android.models.account.CheckingAccount;
import com.marshmallow.android.models.summaryEngine.SummaryEngine;
import com.marshmallow.android.models.transaction.Transaction;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by George on 6/4/2018.
 */
public class SummaryEngineTest {

    private static int creditTransactionAmount = 563;

    @BeforeClass
    public static void setUp() {
        AccountManager.getInstance().destroyMe();
        SummaryEngine.getInstance().destroyMe();
        Transaction transaction = new Transaction();
        transaction.setValue(creditTransactionAmount);
        transaction.setAccountingEntry(Transaction.EntryType.CREDIT);

        CheckingAccount checkingAccount = new CheckingAccount();
        AccountManager.getInstance().addAccount(checkingAccount);
        checkingAccount.handleTransaction(transaction);
    }

    @Test
    public void summaryEngineIsSingleton() {
        SummaryEngine summaryEngine1 = SummaryEngine.getInstance();
        SummaryEngine summaryEngine2 = SummaryEngine.getInstance();
        assertEquals(summaryEngine1, summaryEngine2);
    }
}
