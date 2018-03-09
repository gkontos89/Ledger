package marshmallowlearning.marshmallowandroid;

import android.app.IntentService;
import android.content.Intent;

public class MarketIntentService extends IntentService {
    public static final String actionRetrieveMarketData = "RETRIEVE";
    public static final String actionMarketDataRetrievalComplete = "RETRIEVE_COMPLETE";
    public static final String actionSendMarketData = "SEND";



    public MarketIntentService() {
        super("MarketIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent.getAction().equals(actionRetrieveMarketData)) {
            retrieveMarketData();
        }
        else if (intent.getAction().equals(actionSendMarketData)) {
            sendMarketData(intent);
        }
    }

    protected void retrieveMarketData() {
        // TODO: retrieve user data via protobufs

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(actionMarketDataRetrievalComplete);

        // TODO: Add data to the intent

        sendBroadcast(broadcastIntent);
    }

    protected void sendMarketData(Intent intent) {
        // TODO: protobuf up a market transactions and send

        // TODO: receive "ack" from backend

        // Kick off User Intent to receive new user data
        Intent userIntent = new Intent(getApplicationContext(), UserIntentService.class);
        userIntent.setAction(UserIntentService.actionRetrieveUserData);
        userIntent.putExtra(UserIntentService.extraRefresh, true);
        startActivity(userIntent);

    }
}
