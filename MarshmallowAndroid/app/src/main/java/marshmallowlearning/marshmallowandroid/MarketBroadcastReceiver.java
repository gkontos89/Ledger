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

            ArrayList<MarketItem> marketItems = new ArrayList<MarketItem>();
            marketItems.add(mi0);
            marketItems.add(mi1);
            marketItems.add(mi2);

            MarketActivity marketActivity = (MarketActivity) context;
            MarketItemAdapter marketItemAdapter = new MarketItemAdapter(marketActivity, marketItems);

            // Attach data to market UI
            ListView marketListView = (ListView) marketActivity.findViewById(R.id.MarketListView);
            marketListView.setAdapter(marketItemAdapter);
        }
    }

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


    public class MarketItemAdapter extends ArrayAdapter<MarketItem> {
        public MarketItemAdapter(Context context, ArrayList<MarketItem> marketItems) {
            super(context, 0, marketItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            // Get the data item for this position
            MarketItem marketItem = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.market_item, parent, false);
            }
            // Lookup view for data population
            TextView marketItemName = (TextView) convertView.findViewById(R.id.Name);
            TextView marketItemValue = (TextView) convertView.findViewById(R.id.MarketValue);
            TextView marketItemRecurringCost = (TextView) convertView.findViewById(R.id.RecurringCost);

            // Populate the data into the template view using the data object
            marketItemValue.setText(marketItem.marketValue);
            marketItemName.setText(marketItem.name);
            marketItemRecurringCost.setText(marketItem.recurringCost);

            // Return the completed view to render on screen
            return convertView;
        }
    }
}
