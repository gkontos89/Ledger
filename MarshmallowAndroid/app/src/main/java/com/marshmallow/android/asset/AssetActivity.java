package com.marshmallow.android.asset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.marshmallow.android.messaging.MarshmallowBroadcasts;
import com.marshmallow.android.messaging.ServerConnectionService;
import com.marshmallow.android.R;
import com.marshmallow.android.utilities.Heartbeat;
import com.marshmallow.android.utilities.ResourceLookupUtility;

import java.util.ArrayList;

public class AssetActivity extends AppCompatActivity {
    private AssetsModelManager assetsModelManager;
    public ListView assetListView;
    public ArrayAdapter<LinearLayout> assetArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);

        // TODO REMOVE THIS.  ONLY FOR TESTING
        ResourceLookupUtility.Instance().initialializeApp(this);

        /**
         * Get current assets from the manager, populate the ArrayAdapter and attach the adapter to
         * Assets scrollable list
         */

        // Start the ServerConnectionService
        // TODO: remove this from here once milestone 1 and 2 are complete.  The will sit in a higher up level
//        Intent intent = new Intent(this, ServerConnectionService.class);
//        startService(intent);
    }

}