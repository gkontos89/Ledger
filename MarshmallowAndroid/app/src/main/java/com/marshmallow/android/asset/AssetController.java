package com.marshmallow.android.asset;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.marshmallow.android.R;
import com.marshmallow.android.interfaces.MarshmallowController;
import com.marshmallow.android.interfaces.MarshmallowModel;
import com.marshmallow.android.utilities.ResourceLookupUtility;

public class AssetController implements MarshmallowController {

    protected AssetModel myModel;
    protected PopupWindow detailedPopup;
    protected LinearLayout simpleLayout;

    public AssetController()
    {
        myModel = null;
        detailedPopup = null;
        try{
            simpleLayout =(LinearLayout) ResourceLookupUtility.Instance().getViewFromXmlLayout(R.layout.asset_basic_layout);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public AssetModel getMyModel() {
        return myModel;
    }
    public void setModel(Object model) {
        this.myModel = (AssetModel) model;
        connectModelAndView();
    }

    public void updateView() {
        TextView nameView = (TextView)(simpleLayout.findViewById(R.id.assetName));
        nameView.setText(myModel.getAssetName());
        TextView valueView = (TextView)(simpleLayout.findViewById(R.id.assetMarketValue));
        valueView.setText(myModel.getAssetMarketValue().toString());
        TextView rateView = (TextView)(simpleLayout.findViewById(R.id.assetRecurringCost));
        rateView.setText(myModel.getAssetRecurringCost().toString());
    }

    public View connectModelAndView() {
        // Now attach the controlling actions to the view...
        updateView();
        simpleLayout.setClickable(true);
        simpleLayout.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                if( detailedPopup != null ){
                    // Do nothing because it means that somehow it still exists?
                    return;
                }
                detailedPopup = new PopupWindow();
                detailedPopup.showAtLocation(v, Gravity.BOTTOM, 10, 10);
                detailedPopup.update(50, 50, 320, 90);
                detailedPopup.setContentView(v);
                //TODO i dont think this works
            }
        });
        return simpleLayout;
    }
}
