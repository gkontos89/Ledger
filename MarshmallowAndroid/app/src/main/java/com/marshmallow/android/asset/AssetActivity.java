package com.marshmallow.android.asset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marshmallow.android.Messaging.ServerConnectionService;
import com.marshmallow.android.R;

public class AssetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);

        // Start the ServerConnectionService
        Intent intent = new Intent(this, ServerConnectionService.class);
        startService(intent);
    }
}
