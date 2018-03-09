package marshmallowlearning.marshmallowandroid;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

public abstract class MarshmallowIntentService extends IntentService {
    public static final String actionRetrieveData = "RETRIEVE";
    public static final String actionSendData = "SEND";
    public static final String actionDataRetrieved = "DATA_RETRIEVED";

    public MarshmallowIntentService() {
        super("MarshmallowIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
    }

    protected abstract void retrieveData();

    protected abstract void sendData();

    protected abstract void storeDataLocally();
}
