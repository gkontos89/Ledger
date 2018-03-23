package com.marshmallow.android.asset;

import android.widget.ImageView;

import com.marshmallow.android.interfaces.MarshmallowModel;
import com.marshmallow.android.utilities.GraphicsLookupUtility;

/**
 * This class is meant to be the hook we can hang asset classes on. Every asset will have some basic info on it so
 * im not going to make it into an interface.
 * Created by Caleb on 3/18/2018.
 */

public class AssetModel implements MarshmallowModel {

    protected String assetName;
    protected Integer assetMarketValue;
    protected String assetRecurringCost;
    protected ImageView assetImage;

    /**
     * Actual code
     */
    public AssetModel()
    {
        assetName = "";
        assetMarketValue = new Integer(0);
        assetRecurringCost = "";
        assetImage = GraphicsLookupUtility.Instance().getNoLoveImage();
    }

    /**
     * Auto gen'ed getters and settings to hang more code on in the future
     */

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
