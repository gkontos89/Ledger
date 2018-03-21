package com.marshmallow.android.transactions;

import android.media.Image;
import android.widget.ImageView;

import com.marshmallow.android.interfaces.MarshmallowModel;
import com.marshmallow.android.gameAsset.MarshmallowAsset;
import com.marshmallow.android.utilities.GraphicsLookupUtility;

/**
 * Just like a MarshmallowAsset, there can be many type of transactions but they will all have
 * some basic properties and abilities.
 *
 * Created by Caleb on 3/20/2018.
 */

public class MarshmallowTransaction implements MarshmallowModel {

    protected String sellerName;
    protected String buyerName;
    protected MarshmallowAsset itemSold;
    protected long timeSold; // Time is in ms
    protected ImageView transactionIcon;



    /**
     * Getters and setters below
     */
    public String getSellerName() {
        return sellerName;
    }
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public void setItemSold(MarshmallowAsset itemSold) {
        this.itemSold = itemSold;
    }
    public void setTimeSold(long timeSold) {
        this.timeSold = timeSold;
    }
    public void setTransactionIcon(ImageView transactionIcon) {
        this.transactionIcon = transactionIcon;
    }
    public String getBuyerName() {
        return buyerName;
    }
    public MarshmallowAsset getItemSold() {
        return itemSold;
    }
    public long getTimeSold() {
        return timeSold;
    }
    public ImageView getTransactionIcon() {
        return transactionIcon;
    }

    public MarshmallowTransaction()
    {
        sellerName = "";
        buyerName = "";
        itemSold = null;
        transactionIcon = GraphicsLookupUtility.Instance().getNoLoveImage();
        timeSold = System.currentTimeMillis();
    }


    @Override
    public void loadFromDate(Object input) {
        //TODO probably load from a protobuff AFTER we load all assets???
    }

    @Override
    public Object saveState() {
        //TODO probably save to a protobuff
        return null;
    }
}
