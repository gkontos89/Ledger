package com.marshmallow.android.gameAsset;

import android.app.ActionBar;
import android.content.Context;
import android.media.Image;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marshmallow.android.R;
import com.marshmallow.android.interfaces.MarshmallowCustomView;
import com.marshmallow.android.interfaces.MarshmallowModel;
import com.marshmallow.android.utilities.GraphicsLookupUtility;

import java.util.Random;

/**
 * This class is meant to be the hook we can hang asset classes on. Every asset will have some basic info on it so
 * im not going to make it into an interface.
 * Created by Caleb on 3/18/2018.
 */

public class MarshmallowAsset implements MarshmallowModel, MarshmallowCustomView
{
    protected String assetName;
    protected Integer assetDebt;
    protected Integer assetValue;
    protected Integer assetAppreciationRate;
    protected ImageView assetImage;

    /**
     * Auto gen'ed getters and settings to hang more code on in the futur
     */

    public String getAssetName() {
        return assetName;
    }
    public Integer getAssetDebt() {
        return assetDebt;
    }
    public Integer getAssetValue() {
        return assetValue;
    }
    public Integer getAssetAppreciationRate() {
        return assetAppreciationRate;
    }
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
    public void setAssetDebt(Integer assetDebt) {
        this.assetDebt = assetDebt;
    }
    public void setAssetValue(Integer assetValue) {
        this.assetValue = assetValue;
    }
    public void setAssetAppreciationRate(Integer assetAppreciationRate) {
        this.assetAppreciationRate = assetAppreciationRate;
    }

    /**
     * Actual code
     */
    public MarshmallowAsset()
    {
        assetName = "";
        assetDebt = new Integer(0);
        assetValue = new Integer(0);
        assetAppreciationRate = new Integer(0);

        assetImage = GraphicsLookupUtility.Instance().getNoLoveImage();
    }

    @Override
    public void loadFromDate(Object input) {
        // TODO This will probably be protobuff data
    }

    @Override
    public Object saveState() {
        // TODO this will probably change return a protobuff object
        return null;
    }

    @Override
    public View getBasicView(Context context ) {
        LinearLayout myView = new LinearLayout(context);
        myView.setLayoutParams( new LinearLayout.LayoutParams(30, ActionBar.LayoutParams.WRAP_CONTENT));
        myView.setOrientation( LinearLayout.HORIZONTAL);

        // TODO remove this next ine
        assetImage = (ImageView) myView.findViewById(R.id.defaultTestingImage);;
        myView.addView(assetImage);

        TextView nameView = new TextView(context);
        nameView.setText(assetName);
        nameView.setInputType(InputType.TYPE_NULL);
        myView.addView(nameView);

        //TODO Make a GUI field that listens to a property and uses reflection to get its value when it changes?
        TextView valueView = new TextView(context);
        valueView.setText(assetValue.toString());
        valueView.setInputType(InputType.TYPE_NULL);
        myView.addView(valueView);

        TextView appreciationRateView = new TextView(context);
        appreciationRateView.setText(assetAppreciationRate.toString());
        appreciationRateView.setInputType(InputType.TYPE_NULL);
        myView.addView(appreciationRateView);

        return myView;
    }

    @Override
    public View getDetailedView( Context context ) {

        //TODO
        return null;
    }
}
