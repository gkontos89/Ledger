package com.marshmallow.android.models.financialInstrument;

import com.marshmallow.android.models.summaryEngine.SummaryEngine;

/**
 * Created by George on 6/3/2018.
 */
public class CashInstrument implements FinancialInstrumentInterface {
    private String uniqueId;
    private int cashValue = 0;

    public CashInstrument() {
    }

    @Override
    public int getValue() { return cashValue; }
    public void setValue(int value) { cashValue = value; }

    @Override
    //TODO return an internal category enum
    public String getCategory() { return "cash"; }

    public void increaseValue(int amount) {
        cashValue += amount;
        SummaryEngine.getInstance().updateCashSummary("cash", amount);
    }

    public void decreaseValue(int amount) {
        // TODO how to handle negative?
        cashValue -= amount;
        SummaryEngine.getInstance().updateCashSummary("cash", amount);
    }

    public String getUniqueId() { return uniqueId; }
    public void setUniqueId(String uniqueId) { this.uniqueId = uniqueId; }
}
