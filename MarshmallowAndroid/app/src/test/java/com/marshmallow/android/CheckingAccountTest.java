package com.marshmallow.android;

import com.marshmallow.android.models.account.CheckingAccount;

import static junit.framework.Assert.assertEquals;

/**
 * Created by George on 6/2/2018.
 */
public class CheckingAccountTest extends CheckingAccountBaseTest<CheckingAccount> {

    @Override
    protected CheckingAccount createInstance() {
        return new CheckingAccount();
    }
}