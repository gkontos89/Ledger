package Handlers;

import java.io.IOException;
import java.util.ArrayList;

import MainServer.InitialTest;
import Messaging.MarshmallowMessage;
import Messaging.MessageReceiver;
import ProtoJavaFiles.Heartbeat.LoginApproved;
import ProtoJavaFiles.Heartbeat.LoginRequest;
import SpecificMessages.LoginApprovedWrappedMsg;
import SpecificMessages.LoginRequestWrappedMsg;

public class LogginHandler implements MessageReceiver {

	@Override
	public ArrayList<Comparable<?>> getHandledMessages() {
		ArrayList<Comparable<?>> ids = new ArrayList<Comparable<?>>();
		ids.add("LoginRequest");
		return ids;
	}

	@Override
	public void handleMessage(MarshmallowMessage msg) 
	{
		if(msg instanceof LoginRequestWrappedMsg)
		{
			System.out.println("We living boyyys");
		}
		
		LoginApproved newMsg = LoginApproved.newBuilder().setId("LoginApproved").setSuccess(true).build();
		LoginApprovedWrappedMsg response = new LoginApprovedWrappedMsg(newMsg);
		try {
			InitialTest.testingServer.sendMessage(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
