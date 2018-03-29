package com.marshmallow.android.asset;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marshmallow.android.R;
import com.marshmallow.android.interfaces.MarshmallowModel;
import com.marshmallow.android.utilities.ResourceLookupUtility;

/**
 * This class is meant to be the hook we can hang asset classes on. Every asset will have some basic info on it so
 * im not going to make it into an interface.
 * Created by Caleb on 3/18/2018.
 *
 * Added to this class to capture asset information after it has been owned and sold off by a user - George
 */

public class AssetModel implements MarshmallowModel{

    // Marketplace information
    protected String assetName;
    protected Integer assetMarketValue;
    protected Integer assetRecurringCost;
    protected ImageView assetImage;

    // Information for an asset currently owned
    protected Integer assetPurchasePrice;
    protected Integer assetTotalCosts;
    protected Long assetDatePurchased;

    // Information for an asset that has been owned, then sold
    protected Integer assetSoldPrice;
    protected Long assetSoldDate;
    protected Integer assetReturnOnInvestment;

    /**
     * Actual code
     */
    public AssetModel()
    {
        // Marketplace asset data
        assetName = "";
        assetMarketValue = new Integer(0);
        assetRecurringCost = new Integer(0);
        assetImage = ResourceLookupUtility.Instance().getNoLoveImage();

        // Extended purchased and owned asset data
        assetPurchasePrice = new Integer(0);
        assetTotalCosts = new Integer(0);
        assetDatePurchased = new Long(-1);

        // Extended purchased, owned and sold asset data
        assetSoldPrice = new Integer(0);
        assetSoldDate = new Long(-1);
        assetReturnOnInvestment = new Integer(0);
    }

    // Getters and setters
    public String getAssetName() {
        return assetName;
    }
    public Integer getAssetMarketValue() {
        return assetMarketValue;
    }
    public Integer getAssetRecurringCost() { return assetRecurringCost; }
    public ImageView getAssetImage() {
        return assetImage;
    }

    public Integer getAssetPurchasePrice() { return assetPurchasePrice; }
    public Integer getAssetTotalCosts() { return assetTotalCosts; }
    public Long getAssetDatePurchased() { return assetDatePurchased; }

    public Integer getAssetSoldPrice() { return assetSoldPrice;}
    public Long getAssetSoldDate() { return assetSoldDate; }
    public Integer getAssetReturnOnInvestment() { return assetReturnOnInvestment; }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
        try {
            //assetImage = ResourceLookupUtility.Instance().lookupImage(assetName);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void setAssetMarketValue(Integer assetMarketValue) {
        this.assetMarketValue = assetMarketValue;
    }
    public void setAssetRecurringCost(Integer assetRecurringCost) {
        this.assetRecurringCost = assetRecurringCost;
    }
    public void setAssetImage() {}; //TODO: how to implement?

    public void setAssetPurchasePrice(Integer assetPurchasePrice) { this.assetPurchasePrice = assetPurchasePrice; }
    public void setAssetTotalCosts(Integer assetTotalCosts) { this.assetTotalCosts = assetTotalCosts; }
    public void setAssetDatePurchased(Long assetDatePurchased) { this.assetDatePurchased = assetDatePurchased; }

    public void setAssetSoldPrice(Integer assetSoldPrice) { this.assetSoldPrice = assetSoldPrice;}
    public void setAssetSoldDate(Long assetSoldDate) { this.assetSoldDate = assetSoldDate; }
    public void setAssetReturnOnInvestment(Integer assetReturnOnInvestment) { this.assetReturnOnInvestment = assetReturnOnInvestment; }

    @Override
    public void loadFromDate(Object input) {
        // TODO This will probably be protobuff data
    }

    @Override
    public Object saveState() {
        // TODO this will probably change return a protobuff object
        return null;
    }
}
