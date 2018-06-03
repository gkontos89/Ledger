package com.marshmallow.android;

import com.marshmallow.android.models.account.AccountInformation;

/**
 * Created by George on 6/3/2018.
 */
public class AccountInformationTest extends AccountInformationBaseTest<AccountInformation> {
    @Override
    protected AccountInformation createInstance() {
        return new AccountInformation();
    }
}
