package com.marshmallow.android.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.marshmallow.android.R;
import com.marshmallow.android.models.account.AccountManager;
import com.marshmallow.android.models.account.MarshmallowAccountInterface;
import com.marshmallow.android.models.transaction.Transaction;

public class TransactionActivity extends AppCompatActivity {

    // GUI handles
    EditText merchantNameEditText;
    EditText transactionAmountEditText;
    EditText transactionDateEditText;
    EditText transactionCategoryEditText;
    Transaction.EntryType entryType;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        // Instantiate GUI objects
        merchantNameEditText = findViewById(R.id.merchant_name);
        transactionAmountEditText = findViewById(R.id.transaction_amount);
        transactionDateEditText = findViewById(R.id.transaction_date);
        transactionCategoryEditText = findViewById(R.id.transaction_category);
        saveButton = findViewById(R.id.transaction_save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valuesAreValid()) {
                    Transaction transaction = new Transaction();
                    transaction.setMerchant(merchantNameEditText.getText().toString());
                    transaction.setValue(Integer.parseInt(transactionAmountEditText.getText().toString()));
                    transaction.setDate(transactionDateEditText.getText().toString());
                    transaction.setCategory(transactionCategoryEditText.getText().toString());
                    transaction.setAccountingEntry(entryType);

                    // Store transaction to the account associated with this transaction entry
                    String accountUniqueId = getIntent().getStringExtra("accountUniqueId");
                    MarshmallowAccountInterface marshmallowAccount = (MarshmallowAccountInterface) AccountManager.getInstance().getAccount(accountUniqueId);
                    marshmallowAccount.handleTransaction(transaction);
                    finish();
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_debit:
                if (checked) { entryType = Transaction.EntryType.DEBIT; }
                break;

            case R.id.radio_credit:
                if (checked) { entryType = Transaction.EntryType.CREDIT; }
                break;
        }
    }

    private Boolean valuesAreValid() {
        // TODO handle this appropriately
        return true;
    }
}
