package com.marshmallow.android.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.marshmallow.android.R;
import com.marshmallow.android.models.account.AccountManager;
import com.marshmallow.android.models.account.CheckingAccount;

public class CheckingAccountActivity extends BaseActivity {

    // GUI handles
    TextView accountNameTextView;
    TextView accountBasicValueTextView;
    TextView dateOpenedTextView;
    TextView interestRateTextView;
    FloatingActionButton addTransactionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_account);

        // GUI handles instantiation
        accountNameTextView = findViewById(R.id.account_name);
        accountBasicValueTextView = findViewById(R.id.account_basic_value);
        dateOpenedTextView = findViewById(R.id.date_opened_text);
        interestRateTextView = findViewById(R.id.interest_rate_text);
        addTransactionButton = findViewById(R.id.add_transaction_button);

        // Get the account associated with the launching of this view and fill in the fields
        final String accountUniqueId = getIntent().getStringExtra("accountUniqueId");
        CheckingAccount checkingAccount = (CheckingAccount) AccountManager.getInstance().getAccount(accountUniqueId);
        accountNameTextView.setText(checkingAccount.getAccountName());
        String accountBasicValueString = "$" + Integer.toString(checkingAccount.getAccountValue());
        accountBasicValueTextView.setText(accountBasicValueString);
        dateOpenedTextView.setText(checkingAccount.getAccountOpenedDate());
        String accountInterestRateString = Integer.toString(checkingAccount.getInterestRate()) + "%";
        interestRateTextView.setText(accountInterestRateString);

        addTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TransactionActivity.class);
                intent.putExtra("accountUniqueId", accountUniqueId);
                startActivity(intent);
            }
        });

        // TODO handle displaying/selecting of transactions
    }
}
