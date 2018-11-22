package com.marshmallow.android;

import com.marshmallow.android.models.account.AccountManager;
import com.marshmallow.android.models.account.CheckingAccount;
import com.marshmallow.android.models.transaction.Transaction;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by George on 6/4/2018.
 */
public class AccountManagerTest {

    private static CheckingAccount checkingAccount;
    private static String checkingAccountUniqueId;

    @BeforeClass
    public static void setUp() {
        AccountManager.getInstance().destroyMe();
        checkingAccountUniqueId = "2340hnfg";
        checkingAccount = new CheckingAccount();
        checkingAccount.setUniqueId(checkingAccountUniqueId);
        AccountManager.getInstance().addAccount(checkingAccount);
    }

    @Test
    public void accountManagerIsSingleton() {
        AccountManager accountManager1 = AccountManager.getInstance();
        AccountManager accountManager2 = AccountManager.getInstance();
        assertEquals(accountManager1, accountManager2);
    }

    @Test
    public void getAccountFromAccountManger() {
        assertEquals(AccountManager.getInstance().getAccount(checkingAccountUniqueId), checkingAccount);
    }

    @Test
    public void getAccountTotals() {
        Transaction transaction = new Transaction();
        transaction.setAccountingEntry(Transaction.EntryType.CREDIT);
        transaction.setValue(570);

        CheckingAccount checkingAccount2 = new CheckingAccount();
        checkingAccount2.setUniqueId("2390873fhas");
        AccountManager.getInstance().addAccount(checkingAccount2);
        checkingAccount2.handleTransaction(transaction);

        checkingAccount.handleTransaction(transaction);

        assertEquals(AccountManager.getInstance().getAccountTotals(), transaction.getValue() * 2);
    }
}
