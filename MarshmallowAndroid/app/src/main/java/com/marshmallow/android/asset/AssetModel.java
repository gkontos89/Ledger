package com.marshmallow.android.asset;

import android.widget.ImageView;

import com.marshmallow.android.interfaces.MarshmallowModel;
import com.marshmallow.android.utilities.GraphicsLookupUtility;

import java.util.Date;

/**
 * This class is meant to be the hook we can hang asset classes on. Every asset will have some basic info on it so
 * im not going to make it into an interface.
 * Created by Caleb on 3/18/2018.
 *
 * Added to this class to capture asset information after it has been owned and sold off by a user - George
 */

public class AssetModel implements MarshmallowModel {

    // Marketplace information
    protected String assetName;
    protected Integer assetMarketValue;
    protected String assetRecurringCost;
    protected ImageView assetImage;

    // Information for an asset currently owned
    protected Integer assetPurchasePrice;
    protected Integer assetTotalCosts;
    protected Date assetDatePurchased;

    // Information for an asset that has been owned, then sold
    protected Integer assetSoldPrice;
    protected Date assetSoldDate;
    protected Integer assetReturnOnInvestment;

    /**
     * Actual code
     */
    public AssetModel()
    {
        // Marketplace asset data
        assetName = "";
        assetMarketValue = new Integer(0);
        assetRecurringCost = "";
        assetImage = GraphicsLookupUtility.Instance().getNoLoveImage();

        // Extended purchased and owned asset data
        assetPurchasePrice = new Integer(0);
        assetTotalCosts = new Integer(0);
        assetDatePurchased = new Date();

        // Extended purchased, owned and sold asset data
        assetSoldPrice = new Integer(0);
        assetSoldDate = new Date();
        assetReturnOnInvestment = new Integer(0);
    }

    // Getters and setters
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
    public Date getAssetDatePurchased() { return assetDatePurchased; }

    public Integer getAssetSoldPrice() { return assetSoldPrice;}
    public Date getAssetSoldDate() { return assetSoldDate; }
    public Integer getAssetReturnOnInvestment() { return assetReturnOnInvestment; }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
        try {
            assetImage = GraphicsLookupUtility.Instance().lookupImage(assetName);
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
    public void setAssetDatePurchased(Date assetDatePurchased) { this.assetDatePurchased = assetDatePurchased; }

    public void setAssetSoldPrice(Integer assetSoldPrice) { this.assetSoldPrice = assetSoldPrice;}
    public void setAssetSoldDate(Date assetSoldDate) { this.assetSoldDate = assetSoldDate; }
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

//    @Override
//    public View getBasicView(Context context ) {
//        LinearLayout myView = new LinearLayout(context);
//        myView.setLayoutParams( new LinearLayout.LayoutParams(30, ActionBar.LayoutParams.WRAP_CONTENT));
//        myView.setOrientation( LinearLayout.HORIZONTAL);
//
//        // TODO remove this next ine
//        assetImage = (ImageView) myView.findViewById(R.id.defaultTestingImage);;
//        myView.addView(assetImage);
//
//        TextView nameView = new TextView(context);
//        nameView.setText(assetName);
//        nameView.setInputType(InputType.TYPE_NULL);
//        myView.addView(nameView);
//
//        //TODO Make a GUI field that listens to a property and uses reflection to get its value when it changes?
//        TextView valueView = new TextView(context);
//        valueView.setText(assetValue.toString());
//        valueView.setInputType(InputType.TYPE_NULL);
//        myView.addView(valueView);
//
//        TextView appreciationRateView = new TextView(context);
//        appreciationRateView.setText(assetAppreciationRate.toString());
//        appreciationRateView.setInputType(InputType.TYPE_NULL);
//        myView.addView(appreciationRateView);
//
//        return myView;
//    }
//
//    @Override
//    public View getDetailedView( Context context ) {
//
//        //TODO
//        return null;
//    }
}
