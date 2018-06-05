package com.marshmallow.android.models.summaryEngine;

import java.util.HashMap;

/**
 * Created by George on 6/4/2018.
 */
public class CashSummary implements MarshmallowSummary {
    private int cashTotal;

    public CashSummary() {
        cashTotal = 0;
    }

    @Override
    public int getNumberOfCategories() { return 1; }

    @Override
    public int getValueByCategory(String category) {
        return cashTotal;
    }

    @Override
    public int getTotalValue() {
        return cashTotal;
    }

    @Override
    public int getCategoryPercentOfTotal(String category) {
        return 100;
    }

    @Override
    public void increaseCategoryTotal(String category, int amount) {
        cashTotal += amount;
    }

    @Override
    public void decreaseCategoryTotal(String category, int amount) {
        cashTotal -= amount;
    }
}
