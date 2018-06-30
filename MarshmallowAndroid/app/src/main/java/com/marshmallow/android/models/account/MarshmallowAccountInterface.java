package com.marshmallow.android.models.account;

import com.marshmallow.android.models.summaryEngine.SummaryEngine;
import com.marshmallow.android.models.transaction.Transaction;

import java.util.HashMap;

/**
 * Created by George on 6/2/2018.
 */
public interface MarshmallowAccountInterface {

    int getAccountValue();

    void setAccountValue(int value);

    void handleTransaction(Transaction transaction);

    String getAccountName();

    String getUniqueId();

    String getAccountOpenedDate();

    // TODO getTransactions...but only a certain amount...

    HashMap<SummaryEngine.SummaryType, Integer> getSummaries();
}
