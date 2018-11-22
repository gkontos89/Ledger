package com.marshmallow.android;

import com.marshmallow.android.models.transaction.Transaction;

/**
 * Created by George on 6/2/2018.
 */
public class TransactionTest extends TransactionBaseTest<Transaction> {
    @Override
    protected Transaction createInstance() {
        return new Transaction();
    }
}
