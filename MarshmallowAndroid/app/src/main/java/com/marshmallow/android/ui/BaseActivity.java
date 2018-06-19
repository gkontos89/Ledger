package com.marshmallow.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.marshmallow.android.R;

/**
 * Created by George on 6/18/2018.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.summary_button:
                        Intent summaryIntent = new Intent(getApplicationContext(), SummaryActivity.class);
                        startActivity(summaryIntent);
                        break;

                    case R.id.accounts_button:
                        Intent accountsIntent = new Intent(getApplicationContext(), AccountsHomeActivity.class);
                        startActivity(accountsIntent);
                        break;
                }

                return true;
            }
        });
    }
}
