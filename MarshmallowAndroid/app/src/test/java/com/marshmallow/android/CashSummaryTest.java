package com.marshmallow.android;

import com.marshmallow.android.models.summaryEngine.CashSummary;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by George on 6/4/2018.
 */
public class CashSummaryTest {

    private CashSummary cashSummary;

    @Before
    public void setUp() {
        cashSummary = new CashSummary();
    }

    @Test
    public void cashSummaryHasOneCategory() {
        assertEquals(cashSummary.getNumberOfCategories(), 1);
    }

    @Test
    public void updateCashTotal() {
        // todo use category enums
        int startingValue = cashSummary.getTotalValue();
        int amountToIncrease = 16800;
        int amountToDecrease = 7800;
        cashSummary.increaseCategoryTotal("cash", startingValue + amountToIncrease);
        assertEquals(cashSummary.getValueByCategory("cash"), startingValue + amountToIncrease);
        assertEquals(cashSummary.getTotalValue(), startingValue + amountToIncrease);
        assertEquals(cashSummary.getCategoryPercentOfTotal("cash"), 100);

        cashSummary.decreaseCategoryTotal("cash", amountToDecrease);
        assertEquals(cashSummary.getValueByCategory("cash"), startingValue + amountToIncrease - amountToDecrease);
        assertEquals(cashSummary.getTotalValue(), startingValue + amountToIncrease - amountToDecrease);
        assertEquals(cashSummary.getCategoryPercentOfTotal("cash"), 100);
    }
}
