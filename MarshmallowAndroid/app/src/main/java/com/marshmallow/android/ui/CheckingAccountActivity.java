package com.marshmallow.android.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.marshmallow.android.R;
import com.marshmallow.android.models.account.AccountManager;
import com.marshmallow.android.models.account.CheckingAccount;

public class CheckingAccountActivity extends AppCompatActivity {

    // GUI handles
    TextView accountNameTextView;
    TextView accountBasicValueTextView;
    TextView dateOpenedTextView;
    TextView interestRateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_account);

        // GUI handles instantiation
        accountNameTextView = findViewById(R.id.account_name);
        accountBasicValueTextView = findViewById(R.id.account_basic_value);
        dateOpenedTextView = findViewById(R.id.date_opened_text);
        interestRateTextView = findViewById(R.id.interest_rate_text);

        // Get the account associated with the launching of this view and fill in the fields
        String accountUniqueId = getIntent().getStringExtra("accountUniqueId");
        CheckingAccount checkingAccount = (CheckingAccount) AccountManager.getInstance().getAccount(accountUniqueId);
        accountNameTextView.setText(checkingAccount.getAccountName());
        String accountBasicValueString = "$" + Integer.toString(checkingAccount.getAccountValue());
        accountBasicValueTextView.setText(accountBasicValueString);
        dateOpenedTextView.setText(checkingAccount.getAccountOpenedDate());
        String accountInterestRateString = Integer.toString(checkingAccount.getInterestRate()) + "%";
        interestRateTextView.setText(accountInterestRateString);

        // TODO handle transactions
    }
}
