package com.marshmallow.android.models.financialInstrument;

/**
 * Created by George on 6/3/2018.
 */
public interface FinancialInstrumentInterface {
    public int getValue();

    public String getCategory();

    public void increaseValue(int amount);

    public void decreaseValue(int amount);
}
