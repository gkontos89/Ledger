package Handlers;

import java.io.IOException;
import java.util.ArrayList;

import BackendModels.UserManager;
import MainServer.InitialTest;
import Messaging.MarshmallowMessage;
import Messaging.MessageReceiver;
import Utilities.LoggingUtilities;
import ProtoJavaFiles.Heartbeat.CreateAccountMessageResponse;

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
		
		CreateAccountMessageResponse response;
		if( !UserManager.Instance().registerUser(msg) )
		{
			LoggingUtilities.logBackend("Failed to register a user after receiving a create Account Message");
			response = CreateAccountMessageResponse.newBuilder().setId("CreateAccountMessageResponse").setSuccess(false).setInvalidUsername(true).build();
		}
		else
		{
			response = CreateAccountMessageResponse.newBuilder().setId("CreateAccountMessageResponse").setSuccess(true).build();
		}
		
		MarshmallowMessage responseMessage = new MarshmallowMessage(response);
		try {
			InitialTest.testingServer.sendMessage(responseMessage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
