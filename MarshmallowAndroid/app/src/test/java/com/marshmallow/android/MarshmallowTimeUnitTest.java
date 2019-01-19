package com.marshmallow.android;

import com.marshmallow.android.manager.MarshmallowTime;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by George on 1/18/2019.
 */
public class MarshmallowTimeUnitTest {

    @Test
    public void basicConstructorTest() {
        MarshmallowTime mt = new MarshmallowTime();
        assertEquals(0, mt.dayRate);
        assertEquals(0, mt.day);
        assertEquals(0, mt.month);
        assertEquals(0, mt.year);
        assertFalse(mt.speedBumpApplied);
        assertFalse(mt.monthlyUpdatesOccurred);
        assertFalse(mt.yearHasPassed);
    }

    @Test
    public void partialConstructorTest() {
        int dayRate = 5;
        int day = 8;
        int month = 10;
        int year = 2999;
        MarshmallowTime mt = new MarshmallowTime(dayRate, day, month, year);
        assertEquals(dayRate, mt.dayRate);
        assertEquals(day, mt.day);
        assertEquals(month, mt.month);
        assertEquals(year, mt.year);
        assertFalse(mt.speedBumpApplied);
        assertFalse(mt.monthlyUpdatesOccurred);
        assertFalse(mt.yearHasPassed);
    }

    @Test
    public void fullConstructorTest() {
        MarshmallowTime mt = new MarshmallowTime(0, 0, 0, 0, true, true, true);
        assertTrue(mt.speedBumpApplied);
        assertTrue(mt.monthlyUpdatesOccurred);
        assertTrue(mt.yearHasPassed);
    }
}
