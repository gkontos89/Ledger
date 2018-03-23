package com.marshmallow.android.asset;

import java.util.Date;
/**
 * This model represents an asset that has been purchased by a user and currently owns it.
 *
 * Created by George on 3/23/2018.
 */

public class AssetOwnedModel extends AssetModel {

    protected Integer assetPurchasePrice;
    protected Integer assetTotalCosts;
    protected Date assetDatePurchased;

    AssetOwnedModel() {
        assetPurchasePrice = new Integer(0);
        assetTotalCosts = new Integer(0);
        assetDatePurchased = new Date();
    }

    // Getters and Setters
    public Integer getAssetPurchasePrice() { return assetPurchasePrice; }
    public Integer getAssetTotalCosts() { return assetTotalCosts; }
    public Date getAssetDatePurchased() { return assetDatePurchased; }

    public void setAssetPurchasePrice(Integer assetPurchasePrice) { this.assetPurchasePrice = assetPurchasePrice; }
    public void setAssetTotalCosts(Integer assetTotalCosts) { this.assetTotalCosts = assetTotalCosts; }
    public void setAssetDatePurchased(Date assetDatePurchased) { this.assetDatePurchased = assetDatePurchased; }

}
