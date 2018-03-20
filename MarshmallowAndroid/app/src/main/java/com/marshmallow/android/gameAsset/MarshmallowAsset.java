package com.marshmallow.android.gameAsset;

import android.media.Image;
import com.marshmallow.android.MarshmallowModel;
import com.marshmallow.android.utilities.GraphicsLookupUtility;

/**
 * This class is meant to be the hook we can hang asset classes on. Every asset will have some basic info on it so
 * im not going to make it into an interface.
 * Created by Caleb on 3/18/2018.
 */

public class MarshmallowAsset implements MarshmallowModel
{
    protected String assetName;
    protected Integer assetDebt;
    protected Integer assetValue;
    protected Integer assetAppreciationRate;
    protected Image assetImage;

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
    public Image getAssetImage() {
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
}
