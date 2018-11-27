package com.marshmallow.android.models;

/**
 * Created by George on 11/22/2018.
 */
public class MarshmallowUser {
    private static MarshmallowUser instance = null;

    private MarshmallowUser() {
    }

    public static MarshmallowUser getInstance() {
        if (instance == null) {
            instance = new MarshmallowUser();
        }

        return instance;
    }
}
