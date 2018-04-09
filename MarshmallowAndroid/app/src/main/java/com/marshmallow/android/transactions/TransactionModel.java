package com.marshmallow.android.transactions;

import android.widget.ImageView;

import com.marshmallow.android.interfaces.MarshmallowModel;
import com.marshmallow.android.asset.AssetModel;
import com.marshmallow.android.utilities.ResourceLookupUtility;

import java.util.Date;

/**
 * Just like a AssetModel, there can be many type of transactions but they will all have
 * some basic properties and abilities.
 *
 * Created by Caleb on 3/20/2018.
 */

public class TransactionModel implements MarshmallowModel {

    protected String sellerName;
    protected String buyerName;
    protected AssetModel itemSold;
    protected Date timestamp;
    protected ImageView transactionIcon;
    protected Integer cashValue;
    protected String description;
    protected String category; // Idea is users can categorize their purchases for spending analysis later on

    public TransactionModel()
    {
        sellerName = "";
        buyerName = "";
        itemSold = null;
        transactionIcon = ResourceLookupUtility.Instance().getNoLoveImage();
        timestamp = new Date();
        cashValue = new Integer(0);
        description = "";
        category = "";
    }

    /**
     * Getters and setters below
     */
    public String getSellerName() {
        return sellerName;
    }
    public String getBuyerName() {
        return buyerName;
    }
    public AssetModel getItemSold() {
        return itemSold;
    }
    public Date gettimestamp() {
        return timestamp;
    }
    public ImageView getTransactionIcon() {
        return transactionIcon;
    }
    public Integer getCashValue() { return cashValue; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public void setItemSold(AssetModel itemSold) {
        this.itemSold = itemSold;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public void setTransactionIcon(ImageView transactionIcon) {
        this.transactionIcon = transactionIcon;
    }
    public void setCashValue(Integer cashValue) { this.cashValue = cashValue; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public void loadFromData(Object input) {
        //TODO probably load from a protobuff AFTER we load all assets???
    }

    @Override
    public Object saveState() {
        //TODO probably save to a protobuff
        return null;
    }

    @Override
    public void mapProtoDataToModel(Object protoData) {

    }
    @Override
    public Object generateProtoDataFromModel() {
        return new Object();
    }
}
