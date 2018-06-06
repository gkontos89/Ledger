package com.marshmallow.android.models.financialInstrument;

/**
 * Created by George on 6/3/2018.
 */
public interface FinancialInstrumentInterface {
    int getValue();

    String getCategory();

    void increaseValue(int amount);

    void decreaseValue(int amount);
}
