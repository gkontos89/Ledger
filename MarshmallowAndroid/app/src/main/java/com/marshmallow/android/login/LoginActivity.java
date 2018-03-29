package com.marshmallow.android.login;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import com.marshmallow.android.R;
import com.marshmallow.android.mainOverview.UserSummaryActivity;
import com.marshmallow.android.user.UserModel;
import com.marshmallow.android.utilities.Heartbeat;
import com.marshmallow.android.utilities.MarshmallowGlobals;
import com.marshmallow.android.utilities.RandomUtilities;
import com.marshmallow.android.utilities.ResourceLookupUtility;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    private enum LoginState{Login, Register, ForgotPassword};

    // UI references.
    private EditText mUserNameView;
    private EditText mPasswordView;
    private EditText mPasswordConfirmView;
    private EditText mEmailView;
    private Button mSignInButton;
    private Button mRegisterButton;
    private Button mForgotPasswordButton;

    // State variables
    private LoginState myState;
    private LoginModel myModel;

    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        ResourceLookupUtility.initialializeApp(this);

        initializeComponents();
        connectControllers();
    }

    protected void initializeComponents()
    {
        mUserNameView = findViewById(R.id.userNameInputPrompt);
        mPasswordView = findViewById(R.id.passwordInputPrompt);
        mPasswordConfirmView = findViewById(R.id.confimPasswordInputPrompt);
        mEmailView = findViewById(R.id.emailInputPrompt);
        mSignInButton = findViewById(R.id.sign_in_button);
        mRegisterButton = findViewById(R.id.registerAccountButton);
        mForgotPasswordButton = findViewById(R.id.forgotPasswordButton);
        myModel = new LoginModel();
        changeState(LoginState.Login);
    }

    protected void connectControllers()
    {
        mUserNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myModel.setUserName(mUserNameView.getText().toString());
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        mPasswordView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myModel.setPassword(mPasswordView.getText().toString());
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        mEmailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myModel.setEmail(mEmailView.getText().toString());
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myState.equals(LoginState.Login)){
                    changeState(LoginState.Register);
                }
                else if(myState.equals(LoginState.Register)){
                    if(!registerNewAccount())
                        return;
                    changeState(LoginState.Login);
                }
                else{
                    System.out.println("We got to an illegal state but still pressed register");
                }
            }
        });
        mForgotPasswordButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myState.equals(LoginState.Login)){
                   changeState(LoginState.ForgotPassword);
                }
                else if(myState.equals(LoginState.ForgotPassword)){
                    if(!resetPassword())
                        return;
                    changeState(LoginState.Login);
                }
                else{
                    System.out.println("We got to an illegal state but still pressed register");
                }
            }
        });
    }

    /**
     * Helper method that changes the view based on what state the user goes into
     * @param state the state you are going into
     */
    protected void changeState(LoginState state)
    {
        myState = state;
        if(myState.equals(LoginState.Login)){
            mUserNameView.setVisibility(View.VISIBLE);
            mPasswordView.setVisibility(View.VISIBLE);
            mPasswordConfirmView.setVisibility(View.GONE);
            mEmailView.setVisibility(View.GONE);

            mSignInButton.setVisibility(View.VISIBLE);
            mRegisterButton.setVisibility(View.VISIBLE);
            mForgotPasswordButton.setVisibility(View.VISIBLE);
            mForgotPasswordButton.setText("Forgot Password");
        }
        else if(myState.equals(LoginState.ForgotPassword)){
            mUserNameView.setVisibility(View.VISIBLE);
            mPasswordView.setVisibility(View.GONE);
            mPasswordConfirmView.setVisibility(View.GONE);
            mEmailView.setVisibility(View.VISIBLE);

            mSignInButton.setVisibility(View.GONE);
            mRegisterButton.setVisibility(View.GONE);
            mForgotPasswordButton.setVisibility(View.VISIBLE);
            mForgotPasswordButton.setText("Reset Password");
        }
        else if(myState.equals(LoginState.Register)){
            mUserNameView.setVisibility(View.VISIBLE);
            mPasswordView.setVisibility(View.VISIBLE);
            mPasswordConfirmView.setVisibility(View.VISIBLE);
            mEmailView.setVisibility(View.VISIBLE);

            mSignInButton.setVisibility(View.GONE);
            mRegisterButton.setVisibility(View.VISIBLE);
            mForgotPasswordButton.setVisibility(View.GONE);
            mForgotPasswordButton.setText("Reset Password");
        }
    }

    /**
     * Will send up to the server and try to register a unique Username and Password to an account.
     * Will wait for 3 seconds and display an error if there was one otherwise will pop up a "Registered"
     * and go back to the login screen
     * @return if the account was registered
     */
    protected boolean registerNewAccount()
    {
        return true;
    }

    /**
     * Will send to the server a request to have an email sent out the email address if the username matches
     * the email.
     * @return if the username and email matched
     */
    protected boolean resetPassword()
    {
        return true;
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mUserNameView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid strings.xmlemail, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        //TODO REMOVE THIS SHIT ITS JUST HERE TO TEST YA DINGUS
        UserModel mainUser = RandomUtilities.getRandomUserModel();
        UserModel.mainUserModel = mainUser;

        Intent mainOverViewIntent = new Intent(this, UserSummaryActivity.class);
        this.startActivity(mainOverViewIntent);
        this.finish();

        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUserNameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mUserNameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        //return password.length() > 4;
        return true;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        // Connection statuses
        private boolean connectionSucceeded = false;
        private boolean loginSucceeded = false;

        // Established connection to pass to the rest of the programs

        // Protobuf datatypes
        private Heartbeat.LoginRequest.Builder loginRequest;
        private Heartbeat.Header protoBufHeader;
        private Heartbeat.LoginApproved loginApproved;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return true;
                /*
            try {

                // TODO: attempt authentication against a network service.
                InetAddress inet = InetAddress.getByName("192.168.1.153");
                int port = 8321;
                int len = -1;
                MarshmallowGlobals marshmallowGlobals = (MarshmallowGlobals) getApplication();
                marshmallowGlobals.setBackendSocket(inet, port);
                Socket backendSocket = marshmallowGlobals.getBackendSocket();
                if (backendSocket == null) {
                    return false;
                }

                connectionSucceeded = true;

                // Submit LoginRequest
                loginRequest = Heartbeat.LoginRequest.newBuilder()
                        .setId("LoginRequest")
                        .setUsername(mEmail)
                        .setPassword(mPassword);
                byte[] byteArray = loginRequest.build().toByteArray();
                backendSocket.getOutputStream().write(byteArray);

                // Check for LoginApproved response
                byte[] buffer = new byte[65535];
                len = backendSocket.getInputStream().read();
                if (len != -1) {
                    byte[] dataBytes = new byte[len];
                    System.arraycopy(buffer, 0, dataBytes, 0, len);

                    protoBufHeader = Heartbeat.Header.parseFrom(dataBytes);
                    if (protoBufHeader.getId().equalsIgnoreCase("LoginApproved")) {
                        loginApproved = Heartbeat.LoginApproved.parseFrom(dataBytes);
                        if (loginApproved.getSuccess()) {
                            marshmallowGlobals.setUsername(mEmail);
                            loginSucceeded = true;
                            return true;
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("IOException " + e.getMessage());
                // TODO: print issue logging in to server
                return false;

            }
    */
//            try {
//                // Simulate network access.
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                return false;
//            }

//            for (String credential : DUMMY_CREDENTIALS) {
//                String[] pieces = credential.split(":");
//                if (pieces[0].equals(mEmail)) {
//                    // Account exists, return true if the password matches.
//                    return pieces[1].equals(mPassword);
//                }
//            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);
    /*
            if (success) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            } else {

                if (!connectionSucceeded) {
                    mPasswordView.setError(getString(R.string.error_connection_failure));
                }
                else if (!loginSucceeded) {
                    mPasswordView.setError(getString(R.string.error_incorrect_password));
                }

                mPasswordView.requestFocus();
            }
            */
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

