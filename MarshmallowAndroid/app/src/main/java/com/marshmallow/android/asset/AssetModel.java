package com.marshmallow.android.asset;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marshmallow.android.R;
import com.marshmallow.android.interfaces.MarshmallowModel;
import com.marshmallow.android.utilities.Heartbeat.AssetModelData;
import com.marshmallow.android.utilities.ResourceLookupUtility;

import java.util.Date;

/**
 * This class is meant to be the hook we can hang asset classes on. Every asset will have some basic info on it so
 * im not going to make it into an interface.
 * Created by Caleb on 3/18/2018.
 *
 * Added to this class to capture asset information after it has been owned and sold off by a user - George
 */

public class AssetModel implements MarshmallowModel{

    // Unique id for the asset
    protected Integer uniqueId;

    // Marketplace information
    protected String assetName;
    protected Integer assetMarketValue;
    protected String assetRecurringCost;
    protected ImageView assetImage;

    // Information for an asset currently owned
    protected Integer assetPurchasePrice;
    protected Integer assetTotalCosts;
    protected Long assetDatePurchased;

    // Information for an asset that has been owned, then sold
    protected Integer assetSoldPrice;
    protected Long assetSoldDate;
    protected Integer assetReturnOnInvestment;

    protected AssetController assetController;

    /**
     * Actual code
     */
    public AssetModel()
    {
        uniqueId = -1;

        // Marketplace asset data
        assetName = "";
        assetMarketValue = new Integer(0);
        assetRecurringCost = "";
        assetImage = ResourceLookupUtility.Instance().getNoLoveImage();

        // Extended purchased and owned asset data
        assetPurchasePrice = new Integer(0);
        assetTotalCosts = new Integer(0);
        assetDatePurchased = new Long(-1);

        // Extended purchased, owned and sold asset data
        assetSoldPrice = new Integer(0);
        assetSoldDate = new Long(-1);
        assetReturnOnInvestment = new Integer(0);

        assetController = null;
    }

    // Getters and setters
    public Integer getUniqueId() { return uniqueId; }
    public String getAssetName() {
        return assetName;
    }
    public Integer getAssetMarketValue() {
        return assetMarketValue;
    }
    public String getAssetRecurringCost() { return assetRecurringCost; }
    public ImageView getAssetImage() {
        return assetImage;
    }

    public Integer getAssetPurchasePrice() { return assetPurchasePrice; }
    public Integer getAssetTotalCosts() { return assetTotalCosts; }
    public Long getAssetDatePurchased() { return assetDatePurchased; }

    public Integer getAssetSoldPrice() { return assetSoldPrice;}
    public Long getAssetSoldDate() { return assetSoldDate; }
    public Integer getAssetReturnOnInvestment() { return assetReturnOnInvestment; }

    public void setUniqueId(Integer uniqueId) { this.uniqueId = uniqueId; }
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
    public void setAssetRecurringCost(String assetRecurringCost) {
        this.assetRecurringCost = assetRecurringCost;
    }
    public void setAssetImage() {}; //TODO: how to implement?

    public void setAssetPurchasePrice(Integer assetPurchasePrice) { this.assetPurchasePrice = assetPurchasePrice; }
    public void setAssetTotalCosts(Integer assetTotalCosts) { this.assetTotalCosts = assetTotalCosts; }
    public void setAssetDatePurchased(Long assetDatePurchased) { this.assetDatePurchased = assetDatePurchased; }

    public void setAssetSoldPrice(Integer assetSoldPrice) { this.assetSoldPrice = assetSoldPrice;}
    public void setAssetSoldDate(Long assetSoldDate) { this.assetSoldDate = assetSoldDate; }
    public void setAssetReturnOnInvestment(Integer assetReturnOnInvestment) { this.assetReturnOnInvestment = assetReturnOnInvestment; }

    public AssetController getController() { return assetController; }
    public void setAssetController(AssetController assetController) {this.assetController = assetController; }

    public void mapProtoDataToModel(Object obj) {
        AssetModelData assetModelData = (AssetModelData) obj;
        setAssetName(assetModelData.getAssetName());
        setAssetMarketValue(assetModelData.getAssetMarketValue());
        setAssetRecurringCost(assetModelData.getAssetRecurringCost());
        setAssetPurchasePrice(assetModelData.getAssetPurchasePrice());
        setAssetTotalCosts(assetModelData.getAssetTotalCosts());
        setAssetDatePurchased((long) assetModelData.getAssetDatePurchased());
        setAssetSoldPrice(assetModelData.getAssetSoldPrice());
        setAssetSoldDate((long) assetModelData.getAssetSoldDate());
        setAssetReturnOnInvestment(assetModelData.getAssetReturnOnInvestment());
    }

    @Override
    public void loadFromData(Object input) {
        // TODO This will probably be protobuff data
    }

    @Override
    public Object saveState() {
        // TODO this will probably change return a protobuff object
        return null;
    }
}
