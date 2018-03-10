package marshmallowlearning.marshmallowandroid.MessageReceivers;

import java.io.IOException;
import java.util.ArrayList;

import marshmallowlearning.marshmallowandroid.Messaging.MarshmallowMessage;
import marshmallowlearning.marshmallowandroid.Messaging.MessageReceiver;
import marshmallowlearning.marshmallowandroid.TcpConnectionData;


/**
 * Created by George on 3/10/2018.
 */

public class UserSummaryRequestReceiver implements MessageReceiver {

    public ArrayList<Comparable<?>> getHandledMessages() {
        ArrayList<Comparable<?>> ids = new ArrayList<Comparable<?>>();
        ids.add("UserSummaryRequest");
        return ids;
    }

    public void handleMessage(MarshmallowMessage msg) {
        if (msg.getMyIdDefaultValue().equals("UserSummaryRequest")) {
            try {
                byte[] bytes = new byte[65535];
                bytes = msg.getAsByteArray();
                TcpConnectionData.Instance().getMainSocket().getOutputStream().write(bytes);
            }
            catch (IOException e) {

            }
        }
    }
}
