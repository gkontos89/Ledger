package com.marshmallow.android;

import com.marshmallow.android.models.account.CheckingAccount;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by George on 6/2/2018.
 */
public abstract class CheckingAccountBaseTest<T extends CheckingAccount>  {
    private T instance;

    protected abstract  T createInstance();
    protected T getInstance() { return instance;}

    @Before
    public void setUp() {
        instance = createInstance();
    }

    @Test
    public void testName() {
        //assertEquals(instance.name, "account");
    }

    @Test
    public void addTransaction() {

    }

    @Test
    public void removeTransaction() {

    }

    @Test
    public void retrieveTransaction() {

    }

    @Test
    public void storeAccountInformation() {

    }

    @Test
    public void retrieveAccountInformation() {

    }

    @Test
    public void updateFinancialInstrument() {

    }

    @Test
    public void getFinancialInstrument() {

    }
}
