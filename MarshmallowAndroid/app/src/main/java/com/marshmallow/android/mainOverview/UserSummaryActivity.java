package com.marshmallow.android.mainOverview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.marshmallow.android.R;
import com.marshmallow.android.gameAsset.MarshmallowAsset;
import com.marshmallow.android.user.UserModel;

/**
 * Created by Caleb on 3/16/2018.
 */

public class UserSummaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_summary_layout);
        initializeComponents();
        connectControllers();
    }

    protected void initializeComponents() {
        ScrollView assetsView = findViewById(R.id.assetsPreviewScrollView);
        // EAT DAT ASS BOIIII
        for(MarshmallowAsset ass : UserModel.mainUserModel.getMyAssets())
            assetsView.addView(ass.getBasicView(this));
    }

    protected void connectControllers() {

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }
}
