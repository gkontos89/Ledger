package com.marshmallow.android.models.summaryEngine;

import com.marshmallow.android.models.account.AccountManager;

/**
 * Created by George on 6/4/2018.
 */
public class SummaryEngine {
    private static SummaryEngine instance = null;
    private CashSummary cashSummary;

    public enum SummaryType {
        CASH,
        DEBT,
        INVESTMENTS,
        ASSETS
    }

    private SummaryEngine() {
        cashSummary = new CashSummary();
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

    // TODO summary engine should talk to account manager for cash summaries
    public int getCashSummaryTotalValue() {
        return cashSummary.getTotalValue();
    }

    public void updateCashSummary(String category, int amountToUpdate) {
        if (amountToUpdate < 0) {
            // todo use category enum
            cashSummary.decreaseCategoryTotal("cash", amountToUpdate);
        } else {
            cashSummary.increaseCategoryTotal("cash", amountToUpdate);
        }
    }

    public int getNetWorth() {
        return cashSummary.getTotalValue();
    }
}
