package com.marshmallow.android.manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.marshmallow.android.model.career.Career;
import com.marshmallow.android.model.MarshmallowUser;


/**
 * Created by George on 11/29/2018.
 */
public class MarshmallowGameManager {
    private static MarshmallowGameManager instance = null;
    private MarshmallowUser marshmallowUser;
    private MarshmallowTime marshmallowTime;

    private MarshmallowGameManager() {
    }

    public static MarshmallowGameManager getInstance() {
        if (instance == null) {
            instance = new MarshmallowGameManager();
        }

        return instance;
    }

    public void createNewUser() {
        if (marshmallowUser != null) {
            marshmallowUser.clearUserData();
            marshmallowUser = null;
        }

        marshmallowUser = new MarshmallowUser();
    }

    public synchronized MarshmallowUser getMarshmallowUser() {
        return marshmallowUser;
    }

    public synchronized MarshmallowTime getMarshmallowTime() {
        return marshmallowTime;
    }

    public synchronized void storeMarshmallowTime(MarshmallowTime marshmallowTime) {
        this.marshmallowTime = marshmallowTime;
    }

    public Career generateRandomStartingCareer() {
        return null;
    }

    /**
     * Keys for storing time data in bundles and intents
     */
    private static final String marshmallowTimeDataKey = "marshmallowTimeData";
    public static final String marshmallowTimeIntentActionKey = "marshmallowTimeAction";

    /**
     * Used to store marshmallow time in a bundle for messaging
     * @param marshmallowTime - marshmallow time object
     * @return bundle with marshmallow time stored
     */
    public static Bundle getMarshmallowTimeBundle(MarshmallowTime marshmallowTime) {
        Bundle bundle = new Bundle();
        Gson gson = new Gson();
        String marshmallowTimeString = gson.toJson(marshmallowTime);
        bundle.putString(marshmallowTimeDataKey, marshmallowTimeString);
        return bundle;
    }

    /**
     * Used for retrieving a marshmallow time object from a bundle
     * @param bundle - bundle that contains time information
     * @return marshmallow time object
     */
    public static MarshmallowTime getMarshmallowTimeFromBundle(Bundle bundle) {
        Gson gson = new Gson();
        String marshmallowTimeString = bundle.getString(marshmallowTimeDataKey);
        return gson.fromJson(marshmallowTimeString, MarshmallowTime.class);
    }

    /**
     * Used for getting an Intent that contains marshmallow time object
     * @param marshmallowTime - time object to be stored in the intent
     * @return Intent with time data stored
     */
    public static Intent getMarshmallowTimeIntent(MarshmallowTime marshmallowTime) {
        Intent intent = new Intent();
        Gson gson = new Gson();
        String marshmallowTimeString = gson.toJson(marshmallowTime);
        intent.setAction(marshmallowTimeIntentActionKey);
        intent.putExtra(marshmallowTimeDataKey, marshmallowTimeString);
        return intent;
    }

    public static MarshmallowTime getMarshmallowTimeFromIntent(Intent intent) {
        Gson gson = new Gson();
        String marshmallowTimeString = intent.getStringExtra(marshmallowTimeDataKey);
        return gson.fromJson(marshmallowTimeString, MarshmallowTime.class);
    }

    public void loadUser(MarshmallowUser marshmallowUser) {
        this.marshmallowUser = marshmallowUser;
    }
}
