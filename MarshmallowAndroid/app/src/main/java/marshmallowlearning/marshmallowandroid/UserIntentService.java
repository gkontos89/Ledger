package marshmallowlearning.marshmallowandroid;

import android.app.IntentService;
import android.content.Intent;

public class UserIntentService extends IntentService {
    private boolean firstSyncComplete = false;
    public static final String actionRetrieveUserData = "RETRIEVE";
    public static final String actionUserDataRetrievalComplete = "RETRIEVE_COMPLETE";
    public static final String extraRefresh = "REFRESH";
    public static final String extraNewData = "NEW_DATA";

    public UserIntentService() {
        super("UserIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent.getAction().equals(actionRetrieveUserData)) {
            retrieveUserData(intent.getBooleanExtra(extraRefresh, false));
        }
    }

    protected void retrieveUserData(Boolean refresh) {
        Boolean newData = false;
        if (!firstSyncComplete || refresh) {
            // TODO: retrieve user data via protobufs
            firstSyncComplete = true;
            newData = true;
        }

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(actionUserDataRetrievalComplete);
        broadcastIntent.putExtra(extraNewData, newData);
        // TODO: Add data to the intent
        sendBroadcast(broadcastIntent);
    }
}
