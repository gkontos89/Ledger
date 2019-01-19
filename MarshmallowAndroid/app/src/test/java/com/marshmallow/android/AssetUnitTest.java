package com.marshmallow.android;

import com.marshmallow.android.model.asset.Asset;
import com.marshmallow.android.model.asset.SpeedBump;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by George on 1/18/2019.
 */
public class AssetUnitTest {
    public Asset a;

    @Before
    public void setup() {
        a = new Asset("testA", 550, 300);
        a.setOwned(true);
    }

    @Test
    public void nameTest() {
        assertEquals("testA", a.getName());
    }

    @Test
    public void ownedTest() {
        assertTrue(a.isOwned());
    }

    @Test
    public void monthlyCostTest() {
        int mCost = a.applyMonthlyCosts();
        assertEquals(300, mCost);
        assertEquals(850, a.getTotalCosts());
        a.setOwned(false);
        mCost = a.applyMonthlyCosts();
        assertEquals(0, mCost);
    }

    @Test
    public void initialCostTest() {
        assertEquals(550, a.getInitialCost());
    }

    @Test
    public void roiTest() {
        a.setSellPrice(5000);
        assertEquals(5000, a.getSellPrice());
        a.applyMonthlyCosts();
        a.applyMonthlyCosts();
        int investment = 550 + (2*300);
        assertEquals(((5000-investment)/investment)*100, a.getReturnOnInvestment());
    }

    @Test
    public void addedCostsTest() {
        a.applyMonthlyCosts();
        assertEquals(300, a.getAddedCosts());
    }

    @Test
    public void getMonthlyCostTest() {
        assertEquals(300, a.getMonthlyCosts());
    }

    @Test
    public void currentValueTest() {
        a.setCurrentValue(5);
        assertEquals(5, a.getCurrentValue());
    }

    @Test
    public void emptySpeedBumpTest() {
        assertNull(a.checkForSpeedBump());
    }

    @Test
    public void generateSpeedBumpTest() {
        a.potentialSpeedBumps.add(new SpeedBump("test", 1, 1, "oops"));
        SpeedBump mockSpeedBump = mock(SpeedBump.class);
        when(mockSpeedBump.materialize()).thenReturn(true);
        assertNotNull(a.checkForSpeedBump());
    }
}
