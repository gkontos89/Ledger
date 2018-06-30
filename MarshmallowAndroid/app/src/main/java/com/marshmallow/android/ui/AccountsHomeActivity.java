package com.marshmallow.android.ui;

import android.content.Intent;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.marshmallow.android.R;
import com.marshmallow.android.models.account.AccountManager;
import com.marshmallow.android.models.account.MarshmallowAccountInterface;

import java.util.Vector;

public class AccountsHomeActivity extends BaseActivity {

    // GUI handles
    private FloatingActionButton addAccount;
    private RecyclerView accountsRecyclerView;
    private RecyclerView.LayoutManager accountsLayoutManager;
    private AccountAdapter accountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_home);

        // GUI handle instantiation
        addAccount = findViewById(R.id.add_account_button);
        accountsRecyclerView = findViewById(R.id.account_recycler_view);
        accountsLayoutManager = new LinearLayoutManager(this);
        accountsRecyclerView.setLayoutManager(accountsLayoutManager);
        accountAdapter = new AccountAdapter(this, AccountManager.getInstance().getAccountUniqueIds());
        accountsRecyclerView.setAdapter(accountAdapter);


        // TODO add action for selecting an account

        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AccountSelectionActivity.class);
                startActivity(intent);
            }
        });
    }
}
