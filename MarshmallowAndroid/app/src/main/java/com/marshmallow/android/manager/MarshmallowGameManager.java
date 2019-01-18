package com.marshmallow.android.manager;

import android.content.Context;

import com.marshmallow.android.model.career.Career;
import com.marshmallow.android.model.MarshmallowUser;

/**
 * Created by George on 11/29/2018.
 */
public class MarshmallowGameManager {
    private static MarshmallowGameManager instance = null;
    private MarshmallowUser marshmallowUser;

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

    public void loadUser(MarshmallowUser marshmallowUser) {
        createNewUser();
        this.marshmallowUser = marshmallowUser;
    }



    public synchronized MarshmallowUser getMarshmallowUser() {
        return marshmallowUser;
    }

    public Career generateRandomStartingCareer() {
        return null;
    }
}
