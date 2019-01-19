package com.marshmallow.android.model.asset;

/**
 * Created by George on 11/28/2018.
 */
public interface AssetInterface {
    String getName();
    int getTotalCosts();
    int getInitialCost();
    int getAddedCosts();
    int getReturnOnInvestment();
    int getSellPrice();
    int getCurrentValue();
    void setCurrentValue(int currentValue);
    int getMonthlyCosts();
    void setSellPrice(int sellPrice);
    boolean isOwned();
    void setOwned(boolean owned);
    SpeedBump checkForSpeedBump();
    int applyMonthlyCosts();
}
