package com.marshmallow.android;

import com.marshmallow.android.models.financialInstrument.CashInstrument;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by George on 6/3/2018.
 */
public abstract class CashInstrumentBaseTest<T extends CashInstrument> {
    private T instance;

    protected abstract T createInstance();
    protected T getInstance() { return instance; }

    @Before
    public void setUp() {
        instance = createInstance();
    }

    @Test
    public void getValue() {
        int value = 1500;
        instance.setValue(value);
        assertEquals(instance.getValue(), value);
    }

    @Test
    public void increaseValue() {
        int currentValue = instance.getValue();
        int amountToIncreaseBy = 150;
        instance.increaseValue(amountToIncreaseBy);
        assertEquals(instance.getValue(), currentValue + amountToIncreaseBy);
    }

    @Test
    public void decreaseValue() {
        int startingValue = 1500;
        instance.setValue(startingValue);
        assertEquals(instance.getValue(), startingValue);
        int amountToDecreaseBy = 1000;
        instance.decreaseValue(amountToDecreaseBy);
        assertEquals(instance.getValue(), startingValue - amountToDecreaseBy);
    }

    @Test
    public void category() {
        // todo use category enum
        assertEquals(instance.getCategory(), "cash");
    }
}
