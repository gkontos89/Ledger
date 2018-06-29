package com.marshmallow.android.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.marshmallow.android.R;
import com.marshmallow.android.models.account.AccountInformation;
import com.marshmallow.android.models.account.AccountManager;
import com.marshmallow.android.models.account.CheckingAccount;

public class CheckingAccountCreationActivity extends AppCompatActivity {

    private EditText checkingAccountName;
    private EditText checkingAccountDateOpened;
    private EditText checkingAccountValue;
    private EditText checkingAccountInterestRate;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_account_creation);

        // GUI handle instantiation
        checkingAccountName = findViewById(R.id.checking_account_name);
        checkingAccountDateOpened = findViewById(R.id.checking_account_date_opened);
        checkingAccountValue = findViewById(R.id.checking_account_value);
        checkingAccountInterestRate = findViewById(R.id.checking_account_interest_rate);
        saveButton = findViewById(R.id.checking_account_save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valuesAreValid()) {
                    AccountInformation accountInformation = new AccountInformation();
                    accountInformation.setAccountName(checkingAccountName.getText().toString());
                    accountInformation.setAccountOpenedDate(checkingAccountDateOpened.getText().toString());

                    CheckingAccount checkingAccount = new CheckingAccount();
                    checkingAccount.setAccountInformation(accountInformation);
                    checkingAccount.setAccountValue(Integer.getInteger(checkingAccountValue.getText().toString()));
                    checkingAccount.setInterestRate(Integer.getInteger(checkingAccountInterestRate.getText().toString()));

                    AccountManager.getInstance().addAccount(checkingAccount);

                    // TODO investigate "finishing" and moving to home screen
                    // TODO launch "saving" spinning wheel while info gets stored in backend
                    finish();
                    Intent intent = new Intent(getApplicationContext(), AccountsHomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private Boolean valuesAreValid() {
        return true;
    }
}

