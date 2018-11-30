package com.marshmallow.android;

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

    }

    public void loadUser() {

    }

    public void saveUser() {

    }

    public MarshmallowUser getMarshmallowUser() {
        return marshmallowUser;
    }

    public void loadGameSession(Context context) {

    }

    public void saveGameSession() {
    }

    public void currentGameSessionExists() {

    }

    public void startNewGame() {

    }

    public Career generateRandomStartingCareer() {
        return null;
    }
}
