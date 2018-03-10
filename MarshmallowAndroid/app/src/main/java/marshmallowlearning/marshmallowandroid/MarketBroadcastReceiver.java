package marshmallowlearning.marshmallowandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
            // TODO: Update the GUI based on the context passed in
            MarketItem mi0 = new MarketItem("house", "MarketValue: $250,000", "Recurring Cost: $1200/mo");
            MarketItem mi1 = new MarketItem("car", "MarketValue: $5000", "Recurring Cost: $400/mo");
            MarketItem mi2 = new MarketItem("video game", "MarketValue: $60", "Recurring Cost: $0/mo");

            ArrayList<MarketItem> marketItems = new ArrayList<>();
            marketItems.add(mi0);
            marketItems.add(mi1);
            marketItems.add(mi2);

            MarketActivity marketActivity = (MarketActivity) context;
            marketActivity.displayMarketData(marketItems);
        }
    }

    // TODO: this will be a protobuf
    public class MarketItem {
        public String name;
        public String marketValue;
        public String recurringCost;

        MarketItem(String name, String marketValue, String recurringCost){
            this.name = name;
            this.marketValue = marketValue;
            this.recurringCost = recurringCost;
        }
    }



}
