package com.marshmallow.android.model;

/**
 * Created by George on 11/28/2018.
 */
public interface AssetInterface {
    String getName();
    int getTotalCosts();
    int getInitialCost();
    void setInitialCost(int initialCost);
    int getAddedCosts();
    int getReturnOnInvestment();
    int getSellPrice();
    void sellAsset(int salePrice);
    int getCurrentValue();
    int getMonthlyCosts();
    boolean isOwned();
    void setOwned();
    SpeedBump checkForSpeedBump();
    int applyMonthlyCosts();
}
