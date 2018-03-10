package Handlers;

import java.util.ArrayList;

import BackendModels.UserManager;
import Messaging.MarshmallowMessage;
import Messaging.MessageReceiver;
import Utilities.LoggingUtilities;

public class CreateAccountMessageHandler implements MessageReceiver {

	@Override
	public ArrayList<Comparable<?>> getHandledMessages() {
		ArrayList<Comparable<?>> ids = new ArrayList<Comparable<?>>();
		ids.add("CreateAccountMessage");
		return ids;
	}

	@Override
	public void handleMessage(MarshmallowMessage msg) {
		if( msg.getMyIdDefaultValue().equals("CreateAccountMessage") )
			return;
		if( !UserManager.Instance().registerUser(msg) )
		{
			LoggingUtilities.logBackend("Failed to register a user after receiving a create Account Message");
		}
	}
}
