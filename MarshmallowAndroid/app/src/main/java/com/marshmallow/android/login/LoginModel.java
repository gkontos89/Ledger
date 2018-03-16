package com.marshmallow.android.login;

import com.marshmallow.android.MarshmallowModel;

/**
 * Created by Caleb on 3/16/2018.
 */

public class LoginModel implements MarshmallowModel {

    protected String userName;
    protected String password;
    protected String email;

    public LoginModel()
    {
        userName = null;
        password = null;
        email = null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void loadFromDate(Object input) {
        // TODO
    }

    @Override
    public void saveState() {
        // TODO
    }
}
