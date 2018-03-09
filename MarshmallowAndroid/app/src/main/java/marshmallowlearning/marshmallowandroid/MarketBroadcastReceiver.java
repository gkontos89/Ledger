package marshmallowlearning.marshmallowandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by George on 3/9/2018.
 */

public class MarketBroadcastReceiver extends BroadcastReceiver {
    // TODO: Create an object to a protobuf for that holds user data including financials and assets
    public Object marketData = null;
    private static MarketBroadcastReceiver instance = null;

    MarketBroadcastReceiver() {
    }

    public static MarketBroadcastReceiver getInstance() {
        if (instance == null) {
            instance = new MarketBroadcastReceiver();
        }

        return instance;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(MarketIntentService.actionMarketDataRetrievalComplete)) {

            // TODO: store new data locally or in class
            if (context instanceof MarketActivity) {
                // TODO: Update the GUI based on the context passed in
                int i=0;
            }
        }
    }
}
