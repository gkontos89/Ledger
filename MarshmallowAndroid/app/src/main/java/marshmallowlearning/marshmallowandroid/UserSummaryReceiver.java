package marshmallowlearning.marshmallowandroid;

import java.util.ArrayList;

/**
 * Created by George on 3/10/2018.
 */

public class UserSummaryReceiver implements MessageReceiver {
    public ArrayList<Comparable<?>> getHandledMessages() {
        ArrayList<Comparable<?>> ids = new ArrayList<Comparable<?>>();
        ids.add("UserSummary");
        return ids;
    }

    public void handleMessage(MarshmallowMessage msg) {
        if (msg.getMyIdDefaultValue().equals("UserSummary")) {

        }
    }
}
