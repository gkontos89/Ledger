package marshmallowlearning.marshmallowandroid;

import java.io.IOException;
import java.util.ArrayList;


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
            byte[] bytes = new byte[65535];
            try {
                bytes = msg.getAsByteArray();
                TcpConnectionData.Instance().getMainSocket().getOutputStream().write(bytes);
            }
            catch (IOException e) {

            }
        }
    }
}
