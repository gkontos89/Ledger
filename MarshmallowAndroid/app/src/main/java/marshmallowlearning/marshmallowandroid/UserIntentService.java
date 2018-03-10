package marshmallowlearning.marshmallowandroid;

import android.app.IntentService;
import android.content.Intent;

import java.io.IOException;


public class UserIntentService extends IntentService {
    public static final String actionRetrieveUserData = "RETRIEVE";

    public UserIntentService() {
        super("UserIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Client initiates data request from backend
        if (intent.getAction().equals(actionRetrieveUserData)) {
            // Issue request for UserSummary protobuf
            try {
                MarshmallowMessage marshmallowMessage = MessageManager.Instance().getMessage("UserSummaryReport");
                MessageManager.Instance().publishMessage(marshmallowMessage);
            }
            catch (IOException e) {
            }
        }
    }
}
