package com.marshmallow.android;

import com.marshmallow.android.manager.MarshmallowGameManager;
import com.marshmallow.android.manager.MarshmallowTime;
import com.marshmallow.android.model.MarshmallowUser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by George on 1/18/2019.
 */
public class MarshmallowGameManagerUnitTest {
    private MarshmallowTime mt;

    @Before
    public void setup(){
        mt = new MarshmallowTime();
        mt.day = 6;
        mt.dayRate = 5;
        mt.month = 9;
        mt.year = 2009;
        mt.speedBumpApplied = true;
        mt.monthlyUpdatesOccurred = true;
        mt.yearHasPassed = true;
    }

    @Test
    public void marshmallowGameManagerIsASingletonTest() {
        MarshmallowGameManager mgm1 = MarshmallowGameManager.getInstance();
        MarshmallowGameManager mgm2 = MarshmallowGameManager.getInstance();
        assertEquals(mgm1, mgm2);
    }

    @Test
    public void createNewUserTest() {
        MarshmallowUser mu = new MarshmallowUser();
        MarshmallowGameManager.getInstance().loadUser(mu);
        MarshmallowGameManager.getInstance().createNewUser();
        assertNotEquals(mu, MarshmallowGameManager.getInstance().getMarshmallowUser());
    }

    @Test
    public void storeAndGetTimeTest() {
        MarshmallowGameManager.getInstance().storeMarshmallowTime(mt);
        MarshmallowTime currentMarshmallowTime = MarshmallowGameManager.getInstance().getMarshmallowTime();
        assertEquals(mt.dayRate, currentMarshmallowTime.dayRate);
        assertEquals(mt.day, currentMarshmallowTime.day);
        assertEquals(mt.month, currentMarshmallowTime.month);
        assertEquals(mt.year, currentMarshmallowTime.year);
        assertTrue(currentMarshmallowTime.speedBumpApplied);
        assertTrue(currentMarshmallowTime.monthlyUpdatesOccurred);
        assertTrue(currentMarshmallowTime.yearHasPassed);
    }

    @Test
    public void timeIntentActionKeyTest() {
        assertEquals("marshmallowTimeAction", MarshmallowGameManager.getInstance().marshmallowTimeIntentActionKey);
    }

    @Test
    public void generateRandomStartingCareerTest() {
        assertNull(MarshmallowGameManager.getInstance().generateRandomStartingCareer());
    }
}
