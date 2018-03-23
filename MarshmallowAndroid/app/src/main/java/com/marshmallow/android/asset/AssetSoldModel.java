package com.marshmallow.android.asset;

import java.util.Date;
/**
 * This class represents the model for an asset that was sold by a user after owning it.
 *
 * Created by George on 3/23/2018.
 */

public class AssetSoldModel extends AssetOwnedModel {

    protected Integer assetSoldPrice;
    protected Date assetSoldDate;
    protected Integer assetReturnOnInvestment;

    AssetSoldModel() {
        assetSoldPrice = new Integer(0);
        assetSoldDate = new Date();
        assetReturnOnInvestment = new Integer(0);
    }

    // Getters and setters
    public Integer getAssetSoldPrice() { return assetSoldPrice;}
    public Date getAssetSoldDate() { return assetSoldDate; }
    public Integer getAssetReturnOnInvestment() { return assetReturnOnInvestment; }

    public void setAssetSoldPrice(Integer assetSoldPrice) { this.assetSoldPrice = assetSoldPrice;}
    public void setAssetSoldDate(Date assetSoldDate) { this.assetSoldDate = assetSoldDate; }
    public void setAssetReturnOnInvestment(Integer assetReturnOnInvestment) { this.assetReturnOnInvestment = assetReturnOnInvestment; }

}
