package marshmallowlearning.marshmallowandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by George on 3/9/2018.
 */

public class UserBroadcastReceiver extends BroadcastReceiver {
    // TODO: Create an object to a protobuf for that holds user data including financials and assets
    public Object userData = null;
    private static UserBroadcastReceiver instance = null;

    UserBroadcastReceiver() {
    }
    
    public static UserBroadcastReceiver getInstance() {
        if (instance == null) {
            instance = new UserBroadcastReceiver();
        }

        return instance;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(UserIntentService.actionUserDataRetrievalComplete)) {
            if (intent.getBooleanExtra(UserIntentService.extraNewData, false)) {
                // TODO: store new data locally or in class
            }


            // TODO: Update the GUI based on the context passed in

        }
    }
}
