package com.marshmallow.android.models.financialInstrument;

import com.marshmallow.android.models.summaryEngine.SummaryEngine;

/**
 * Created by George on 6/3/2018.
 */
public interface FinancialInstrumentInterface {
    int getValue();

    SummaryEngine.SummaryType getSummaryType();

    void increaseValue(int amount);

    void decreaseValue(int amount);
}
