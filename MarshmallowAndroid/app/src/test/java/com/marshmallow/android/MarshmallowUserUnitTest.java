package com.marshmallow.android;

import com.marshmallow.android.model.MarshmallowUser;
import com.marshmallow.android.model.asset.Asset;
import com.marshmallow.android.model.asset.AssetInterface;
import com.marshmallow.android.model.career.Career;
import com.marshmallow.android.model.education.Education;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by George on 1/18/2019.
 */
public class MarshmallowUserUnitTest {
    MarshmallowUser mu;

    @Before
    public void setup() {
        mu = new MarshmallowUser();
    }

    @Test
    public void nameTest() {
        mu.setName("GK");
        assertEquals("GK", mu.getName());
    }

    @Test
    public void assetTest() {
        Vector<AssetInterface> assets = new Vector<>();
        Asset testAsset = new Asset("test", 500, 300);
        assets.add(testAsset);
        mu.setAssets(assets);
        assertTrue(mu.getAssets().contains(testAsset));
    }

    @Test
    public void educationTest() {
        Education education = new Education("testDegree", "Hardknocks", 100);
        mu.addEducation(education);
        assertTrue(mu.getEducation().containsValue(education));
    }

    @Test
    public void careerTest() {
        Career career = new Career("testCareer", 50000);
        mu.setCareer(career);
        assertEquals(career, mu.getCareer());
    }

    @Test
    public void savingsTest() {
        mu.setSavings(50);
        assertEquals(50, mu.getSavings());
    }

    @Test
    public void clearUserDataTest() {
        mu.setSavings(500);
        mu.setCareer(new Career("test", 5000));
        mu.addEducation(new Education("test", "hardknocks", 1000));
        mu.getAssets().add(new Asset("test", 500, 500));
        mu.clearUserData();
        assertNull(mu.getName());
        assertNull(mu.getEducation());
        assertNull(mu.getCareer());
        assertEquals(0, mu.getSavings());
        assertTrue(mu.getAssets().isEmpty());
    }

    @Test
    public void addAssetTest() {
        mu.setSavings(1000);
        mu.addAsset(new Asset("test", 500, 300));
        assertEquals(500, mu.getSavings());
    }

    @Test
    public void applyMonthlyUpdatesTest() {
        mu.setSavings(10000);
        assertEquals(10000, mu.getSavings());
        mu.setCareer(new Career("test", 12000));
        mu.addAsset(new Asset("testA", 500, 500));
        mu.addAsset(new Asset("testB", 500, 750));
        assertEquals(9000, mu.getSavings());

        mu.applyMonthlyUpdates();
        assertEquals(9000 + 1000 - 500 - 750, mu.getSavings());
    }
}
