package com.marshmallow.android.service;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;

/**
 * Created by George on 1/15/2019.
 */
public class MarshmallowTimeManager {

    private static MarshmallowTimeManager instance = null;
    private MarshmallowTime currentMarshmallowTime = null;

    private MarshmallowTimeManager() {
    }

    public static MarshmallowTimeManager getInstance() {
        if (instance == null) {
            instance = new MarshmallowTimeManager();
        }

        return instance;
    }

    public synchronized MarshmallowTime getCurrentMarshmallowTime() {
        return currentMarshmallowTime;
    }

    public synchronized void storeMarshmallowTime(MarshmallowTime marshmallowTime) {
        this.currentMarshmallowTime = marshmallowTime;
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
}
