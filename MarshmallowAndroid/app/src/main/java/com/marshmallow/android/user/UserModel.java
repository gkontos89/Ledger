package com.marshmallow.android.user;

import android.content.Intent;
import android.media.Image;

import com.marshmallow.android.asset.AssetModel;
import com.marshmallow.android.interfaces.MarshmallowModel;
import com.marshmallow.android.transactions.MarshmallowTransaction;

import java.util.Vector;

/**
 * A User Model represents any player in the game. We are making this a non-singleton object specifically
 * so that we can have multiple players in a game, interactions between players, view other players
 * etc.  Even if we dont want it now we dont want to code ourselfess into a corner... like basic bitches
 * Created by Caleb on 3/16/2018.
 */

public class UserModel implements MarshmallowModel {

    //TODO: We need to figure out what are all the assets. I dont like adding them in later
    // Maybe plan more here
    protected Vector<AssetModel> userAssets;
    protected Vector<MarshmallowTransaction> userTransactions;
    protected String userName;
    protected Image userImage; // do we want this? We can leave it blank for now, but it would be saved locally probably
    protected Integer userCash;
    protected Integer userNetWorth;


    // TODO move this too a better place holder maybe for the current instance of the application?
    public static UserModel mainUserModel;

    // 3/20/18 Specifically not adding in education yet as that should be its own entire other package and classes

    /**
     * Getters and Setters below
     */
    public Vector<AssetModel> getuserAssets() {
        return userAssets;
    }
    public void addAsset(AssetModel asset){
        userAssets.add(asset);
    }
    public Vector<MarshmallowTransaction> getuserTransactions() {
        return userTransactions;
    }
    public void addTransaction(MarshmallowTransaction transaction) {
        userTransactions.add(transaction);
    }
    public String getUserName() {
        return userName;
    }
    public Image getUserImage() {
        return userImage;
    }
    public void setUserName(String userName) { this.userName = userName; }
    public void setUserImage(Image userImage) { this.userImage = userImage; }
    public Integer getuserCash() { return userCash; }
    public Integer getUserNetWorth() { return userNetWorth; }
    public void setUserCash(int cash) { userCash = cash; }
    public void setUserNetWorth(int netWorth) { userNetWorth = netWorth; }

    public UserModel()
    {
        userAssets = new Vector<AssetModel>();
        userTransactions = new Vector<MarshmallowTransaction>();
    }

    @Override
    public void loadFromDate(Object input) {

    }

    @Override
    public Object saveState() {
        //TODO
        return null;
    }
}
