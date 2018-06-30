package com.marshmallow.android.models.summaryEngine;

import com.marshmallow.android.models.account.AccountManager;

import java.util.HashMap;

/**
 * Created by George on 6/4/2018.
 */
public class SummaryEngine {
    private static SummaryEngine instance = null;

    public enum SummaryType {
        CASH,
        DEBT,
        INVESTMENTS,
        ASSETS
    }

    private SummaryEngine(){
    }

    public static SummaryEngine getInstance() {
        if (instance == null) {
            instance = new SummaryEngine();
        }

        return instance;
    }

    public void destroyMe() {
        instance = null;
    }


    public int getCashSummaryTotalValue() {
        HashMap<SummaryType, Integer> summaries = AccountManager.getInstance().getSummaries();
        return summaries.get(SummaryType.CASH);
    }

    public int getNetWorth() {
        HashMap<SummaryType, Integer> summaries = AccountManager.getInstance().getSummaries();
        return summaries.get(SummaryType.CASH);
    }
}
