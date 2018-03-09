package marshmallowlearning.marshmallowandroid;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

public class MainIntentService extends MarshmallowIntentService {

    private int testCounter;

    public MainIntentService() {
        super();
        testCounter = 0;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent.getAction().equals(actionRetrieveData)) {
            retrieveData();
        }
        else if (intent.getAction().equals(actionSendData)) {
            sendData();
        }
    }

    @Override
    protected void retrieveData(){
        testCounter += 1;
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(actionDataRetrieved);
        broadcastIntent.putExtra("data", testCounter);
        sendBroadcast(broadcastIntent);
    }

    @Override
    // As of now the Main activity will not send any data
    protected void sendData(){}

    @Override
    protected void storeDataLocally(){

    }
}
