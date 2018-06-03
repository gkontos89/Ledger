package com.marshmallow.android.models.financialInstrument;

/**
 * Created by George on 6/3/2018.
 */
public class CashInstrument implements FinancialInstrumentInterface {
    private int cashValue = 0;
    private String category = "cash"; // TODO create enum class for instrument categories

    public CashInstrument() {
    }

    @Override
    public int getValue() { return cashValue; }
    public void setValue(int value) { cashValue = value; }

    @Override
    public String getCategory() { return category; }

    public void increaseValue(int amount) {
        cashValue += amount;
    }

    public void decreaseValue(int amount) {
        // TODO how to handle negative?
        cashValue -= amount;
    }
}
