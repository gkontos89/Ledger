package com.marshmallow.android.user;

import android.media.Image;

import com.marshmallow.android.asset.AssetModel;
import com.marshmallow.android.career.CareerModel;
import com.marshmallow.android.education.EducationModel;
import com.marshmallow.android.interfaces.MarshmallowModel;
import com.marshmallow.android.transactions.TransactionModel;

import java.util.Vector;

/**
 * A User Model represents any player in the game. We are making this a non-singleton object specifically
 * so that we can have multiple players in a game, interactions between players, view other players
 * etc.  Even if we dont want it now we dont want to code ourselfess into a corner... like basic bitches
 * Created by Caleb on 3/16/2018.
 */

public class UserModel implements MarshmallowModel {

    //TODO: We need to figure out what are all the assets. I dont like adding them in later
    protected String userName;
    protected Image userImage; // do we want this? We can leave it blank for now, but it would be saved locally probably
    protected Integer userCash;
    protected Integer userNetWorth;
    protected Vector<AssetModel> userAssets;
    protected Vector<TransactionModel> userTransactions;
    protected Vector<EducationModel> userEducationLevels;
    protected Vector<CareerModel> userCareerData;

    // TODO move this too a better place holder maybe for the current instance of the application?
    public static UserModel mainUserModel;

    public UserModel()
    {
        userName = "";
        // TODO userImage initialization?
        userCash = new Integer(0);
        userNetWorth = new Integer(0);
        userAssets = new Vector<AssetModel>();
        userTransactions = new Vector<TransactionModel>();
        userEducationLevels = new Vector<EducationModel>();
        userCareerData = new Vector<CareerModel>();
    }

    /**
     * Getters and Setters below
     */
    public String getUserName() {
        return userName;
    }
    public Image getUserImage() {
        return userImage;
    }
    public Integer getuserCash() { return userCash; }
    public Integer getUserNetWorth() { return userNetWorth; }
    public Vector<AssetModel> getUserAssets() {
        return userAssets;
    }
    public Vector<TransactionModel> getuserTransactions() {
        return userTransactions;
    }
    public Vector<EducationModel> getUserEducationLevels() { return userEducationLevels; }
    public Vector<CareerModel> getUserCareerData() { return  userCareerData; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setUserImage(Image userImage) { this.userImage = userImage; }
    public void setUserCash(int cash) { userCash = cash; }
    public void setUserNetWorth(int netWorth) { userNetWorth = netWorth; }
    public void addAsset(AssetModel asset){
        userAssets.add(asset);
    }
    public void addTransaction(TransactionModel transaction) {
        userTransactions.add(transaction);
    }
    public void addEducation(EducationModel educationModel) { userEducationLevels.add(educationModel); }
    public void addCareer(CareerModel careerModel) { userCareerData.add(careerModel); }

    @Override
    public void loadFromDate(Object input) {

    }

    @Override
    public Object saveState() {
        //TODO
        return null;
    }
}
