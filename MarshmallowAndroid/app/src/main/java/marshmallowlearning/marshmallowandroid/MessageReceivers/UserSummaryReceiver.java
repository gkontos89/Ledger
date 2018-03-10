package marshmallowlearning.marshmallowandroid.MessageReceivers;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import marshmallowlearning.marshmallowandroid.Messaging.MarshmallowMessage;
import marshmallowlearning.marshmallowandroid.Messaging.MessageManager;
import marshmallowlearning.marshmallowandroid.Messaging.MessageReceiver;
import marshmallowlearning.marshmallowandroid.ProtoJavaFiles.Heartbeat;
import marshmallowlearning.marshmallowandroid.TcpConnectionData;

/**
 * Created by George on 3/10/2018.
 */

public class UserSummaryReceiver extends IntentService implements MessageReceiver {
    public final static String userDataAvailable = "USER_DATA_AVAILABLE";
    public final static String actionRetrieveUserData = "RETRIEVE";

    public UserSummaryReceiver() {
        super("UserSummaryReceiver");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Client initiates data request from backend
        if (intent.getAction().equals(actionRetrieveUserData)) {
            // Issue request for UserSummary protobuf
            try {
                MarshmallowMessage marshmallowMessage = MessageManager.Instance().getMessage("UserSummaryRequest");
                MessageManager.Instance().publishMessage(marshmallowMessage);
            }
            catch (IOException e) {
            }
        }
    }


    public ArrayList<Comparable<?>> getHandledMessages() {
        ArrayList<Comparable<?>> ids = new ArrayList<Comparable<?>>();
        ids.add("UserSummary");
        ids.add("UserSummaryRequest");
        return ids;
    }

    public void handleMessage(MarshmallowMessage msg) {
        // UserSummary message that came from bytes from the backend
        if (msg.getMyIdDefaultValue().equals("UserSummary")) {
            Intent userSummaryBroadcast = new Intent();
            userSummaryBroadcast.setAction(userDataAvailable);

            // Pull out the UserSummary protobuf data into serializable data to send to the GUI
            Heartbeat.UserSummary userSummary = (Heartbeat.UserSummary) msg.getProtoMessage();
            userSummaryBroadcast.putExtra("Cash", userSummary.getCash());
            userSummaryBroadcast.putExtra("Networth", userSummary.getNetworth());
            userSummaryBroadcast.putExtra("AssetValue", userSummary.getAssetsValue());
            sendBroadcast(userSummaryBroadcast);
        } else if (msg.getMyIdDefaultValue().equals("UserSummaryRequest")) {
            // TODO: send out to wire
            try {
                Socket mainSocket = TcpConnectionData.Instance().getMainSocket();
                mainSocket.getOutputStream().write(msg.getAsByteArray());
            } catch (IOException e) {

            }
        }
    }
}


