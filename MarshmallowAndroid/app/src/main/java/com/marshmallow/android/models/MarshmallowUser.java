package com.marshmallow.android.models;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created by George on 11/22/2018.
 */
public class MarshmallowUser {
    private static MarshmallowUser instance = null;
    private String name;
    private Vector<AssetInterface> assets;
    private HashMap<String, Education> education;
    private Career career;
    private Integer savings;


    private MarshmallowUser() {
        clearUserData();
    }

    public static MarshmallowUser getInstance() {
        if (instance == null) {
            instance = new MarshmallowUser();
        }

        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector<AssetInterface> getAssets() {
        return assets;
    }

    public void setAssets(Vector<AssetInterface> assets) {
        this.assets = assets;
    }

    public HashMap<String, Education> getEducation() {
        return education;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public int getSavings() {
        return savings;
    }

    // Returns boolean indicating a speed bump occurred
    public boolean applySpeedBumps() {
        boolean speedBumpApplied = false;
        for (AssetInterface asset : assets) {
            SpeedBump speedBump = asset.checkForSpeedBump();
            if (speedBump != null) {
                speedBumpApplied = true;
                savings -= speedBump.getCost();
            }
        }

        return speedBumpApplied;
    }

    public void applyMonthlyUpdates() {
        for (AssetInterface asset : assets) {
            savings -= asset.applyMonthlyCosts();
        }

        savings += career.applyPayCheck();
    }

    public void clearUserData() {
        assets = new Vector<AssetInterface>();
        education = null;
        career = null;
        savings = 0;
    }
}
