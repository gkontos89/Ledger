package com.marshmallow.android;

import com.marshmallow.android.models.account.AccountInformation;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by George on 6/3/2018.
 */
public abstract class AccountInformationBaseTest<T extends AccountInformation> {
    private T instance;

    protected abstract T createInstance();
    protected T getInstance() { return instance; }

    @Before
    public void setUp() {
        instance = createInstance();
    }

    @Test
    public void uniqueId() {
        String uniqueId = "gnh2h4239";
        instance.setUniqueId(uniqueId);
        assertEquals(instance.getUniqueId(), uniqueId);
    }

    @Test
    public void accountName() {
        String accountName = "myCheckingAccount";
        instance.setAccountName(accountName);
        assertEquals(instance.getAccountName(), accountName);
    }

    @Test
    public void accountOpenedDate() {
        String accountOpenedDate = "6/15/1989";
        instance.setAccountOpenedDate(accountOpenedDate);
        assertEquals(instance.getAccountOpenedDate(), accountOpenedDate);
    }

    @Test
    public void accountClosedDate() {
        String accountClosedDate = "7/18/2020";
        instance.setAccountClosedDate(accountClosedDate);
        assertEquals(instance.getAccountClosedDate(), accountClosedDate);
    }
}
