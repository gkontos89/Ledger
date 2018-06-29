package com.marshmallow.android.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.marshmallow.android.R;
import com.marshmallow.android.models.account.AccountManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AccountSelectionActivity extends AppCompatActivity {

    // GUI handles
    private RecyclerView accountSelectionRecyclerView;
    private RecyclerView.LayoutManager accountSelectionLayoutManager;
    private AccountSelectionAdapter accountSelectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_selection);

        // GUI handle instantiation
        accountSelectionRecyclerView = findViewById(R.id.account_selection_rv);
        accountSelectionLayoutManager = new LinearLayoutManager(this);
        accountSelectionRecyclerView.setLayoutManager(accountSelectionLayoutManager);
        Set<String> accountClassifierStrings = AccountManager.getInstance().getAccountClassifiers().keySet();
        accountSelectionAdapter = new AccountSelectionAdapter(this, new ArrayList<>(accountClassifierStrings));
        accountSelectionRecyclerView.setAdapter(accountSelectionAdapter);
    }
}
