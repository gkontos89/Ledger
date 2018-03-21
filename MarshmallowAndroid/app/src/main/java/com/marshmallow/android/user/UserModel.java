package com.marshmallow.android.user;

import android.media.Image;
import android.widget.LinearLayout;

import com.marshmallow.android.interfaces.MarshmallowModel;
import com.marshmallow.android.gameAsset.MarshmallowAsset;
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
    protected Vector<MarshmallowAsset> myAssets;
    protected Vector<MarshmallowTransaction> myTransactions;
    protected String userName;
    protected Image userImage; // do we want this? We can leave it blank for now, but it would be saved locally probably

    // 3/20/18 Specifically not adding in education yet as that should be its own entire other package and classes

    /**
     * Getters and Setters below
     */
    public Vector<MarshmallowAsset> getMyAssets() {
        return myAssets;
    }
    public void addAsset(MarshmallowAsset asset){
        myAssets.add(asset);
    }
    public Vector<MarshmallowTransaction> getMyTransactions() {
        return myTransactions;
    }
    public void addTransaction(MarshmallowTransaction transaction) {
        myTransactions.add(transaction);
    }
    public String getUserName() {
        return userName;
    }
    public Image getUserImage() {
        return userImage;
    }
    public void setUserName(String userName) { this.userName = userName; }
    public void setUserImage(Image userImage) { this.userImage = userImage; }

    // TODO move this too a better place holder maybe for the current instance of the application?
    public static UserModel mainUserModel;

    public UserModel()
    {
        myAssets = new Vector<MarshmallowAsset>();
        myTransactions = new Vector<MarshmallowTransaction>();
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
