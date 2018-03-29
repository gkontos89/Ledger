package com.marshmallow.android.asset;

import android.content.Context;
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

    public AssetController(AssetModel model)
    {
        myModel = model;
        detailedPopup = null;
    }

    public AssetModel getMyModel() {
        return myModel;
    }

    @Override
    public View connectModelAndView() {
        final LinearLayout simpleLayout;
        try{
            simpleLayout =(LinearLayout) ResourceLookupUtility.Instance().getViewFromXmlLayout(R.layout.asset_basic_layout);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }

        TextView nameView = (TextView)(simpleLayout.findViewById(R.id.assetName));
        nameView.setText(myModel.getAssetName());
        TextView valueView = (TextView)(simpleLayout.findViewById(R.id.assetMarketValue));
        valueView.setText(myModel.getAssetMarketValue().toString());
        TextView rateView = (TextView)(simpleLayout.findViewById(R.id.assetRecurringCost));
        rateView.setText(myModel.getAssetRecurringCost().toString());

        // Now attach the controlling actions to the view...

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
            }
        });
        return simpleLayout;
    }
}
