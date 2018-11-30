package com.marshmallow.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marshmallow.android.R;
import com.marshmallow.android.service.MarshmallowEngineService;
import com.marshmallow.android.service.MarshmallowServiceConnection;

public class MainActivity extends AppCompatActivity {
    private MarshmallowServiceConnection marshmallowEngineServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO determine if this is the best spot for this
        bindToMarshmallowEngineService();
    }

    private void bindToMarshmallowEngineService() {
        marshmallowEngineServiceConnection = new MarshmallowServiceConnection(new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        });

        bindService(new Intent(getApplicationContext(), MarshmallowEngineService.class),
               marshmallowEngineServiceConnection,
                Context.BIND_AUTO_CREATE);
    }
}
