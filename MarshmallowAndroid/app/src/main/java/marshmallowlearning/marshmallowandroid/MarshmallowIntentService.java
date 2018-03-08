package marshmallowlearning.marshmallowandroid;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

public class MarshmallowIntentService extends IntentService {

    public MarshmallowIntentService() {
        super("MarshmallowIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
    }
}
