package com.marshmallow.android.model.asset;

import java.util.Vector;

/**
 * Created by George on 11/28/2018.
 */
public class Asset implements AssetInterface {
    protected String name;
    protected boolean owned;
    protected int initialCost;
    protected int totalAddedCosts;
    protected int sellPrice;
    protected int currentValue;
    protected int monthlyCost;
    public Vector<SpeedBump> potentialSpeedBumps;

    public Asset(String name, int initialCost, int monthlyCost) {
        this.name = name;
        owned = false;
        this.initialCost = initialCost;
        this.monthlyCost = monthlyCost;
        totalAddedCosts = 0;
        sellPrice = 0;

        // Other assets will have to create their own speed bumps here
        potentialSpeedBumps = new Vector<SpeedBump>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getTotalCosts() {
        return initialCost + totalAddedCosts;
    }

    @Override
    public int getInitialCost() {
        return initialCost;
    }

    @Override
    public int applyMonthlyCosts() {
        if (isOwned()) {
            totalAddedCosts += monthlyCost;
            return monthlyCost;
        } else {
            return 0;
        }
    }

    @Override
    public int getAddedCosts() {
        return totalAddedCosts;
    }

    @Override
    public int getMonthlyCosts() {
        return monthlyCost;
    }

    @Override
    public int getReturnOnInvestment() {
        int investment = initialCost + totalAddedCosts;
        return ((sellPrice - investment) / investment) * 100;
    }

    @Override
    public SpeedBump checkForSpeedBump() {
        for (SpeedBump speedBump : potentialSpeedBumps) {
            if (speedBump.materialize()) {
                return speedBump;
            }
        }

        return null;
    }

    @Override
    public boolean isOwned() {
        return owned;
    }

    @Override
    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    @Override
    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public int getSellPrice() {
        return sellPrice;
    }

    @Override
    public int getCurrentValue() {
        return currentValue;
    }

    @Override
    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
