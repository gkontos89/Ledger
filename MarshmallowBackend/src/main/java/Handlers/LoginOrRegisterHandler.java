package Handlers;

import java.io.IOException;
import java.util.ArrayList;

import BackendModels.UserManager;
import MainServer.InitialTest;
import Messaging.MarshmallowMessage;
import Messaging.MessageReceiver;
import ProtoJavaFiles.Heartbeat.CreateAccountMessageResponse;
import ProtoJavaFiles.Heartbeat.LoginApproved;
import ProtoJavaFiles.Heartbeat.LoginRequest;
import Utilities.LoggingUtilities;
import Utilities.RandomUtilities;

public class LoginOrRegisterHandler implements MessageReceiver {

	@Override
	public ArrayList<Comparable<?>> getHandledMessages() {
		ArrayList<Comparable<?>> ids = new ArrayList<Comparable<?>>();
		ids.add("LoginRequest");
		ids.add("CreateAccountMessage");
		return ids;
	}

	@Override
	public void handleMessage(MarshmallowMessage msg) 
	{
		if(msg.getMyIdDefaultValue().equals("LoginRequest"))
		{
				
			LoginApproved newMsg;
			String username = ((LoginRequest)msg.getProtoMessage()).getUsername();
			String password = ((LoginRequest)msg.getProtoMessage()).getPassword();
			if( !UserManager.Instance().isUserRegistered(username) )
			{
				LoggingUtilities.logBackend("User tried to login but has not been registered");
				newMsg = LoginApproved.newBuilder().setId("LoginApproved").setSuccess(false).setInvalidUsername(true).build();
			}
			else if( !UserManager.Instance().loginUser(username, password))
			{
				LoggingUtilities.logBackend("User tried to login but has not been registered");
				newMsg = LoginApproved.newBuilder().setId("LoginApproved").setSuccess(false).setInvalidPassword(true).build();
			}
			else
			{
				newMsg = LoginApproved.newBuilder().setId("LoginApproved").setSuccess(true).build();
			}
			MarshmallowMessage response = new MarshmallowMessage(newMsg);
			try {
				InitialTest.testingServer.sendMessage(response, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( msg.getMyIdDefaultValue().equals("CreateAccountMessage") )
		{
		
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
				InitialTest.testingServer.sendMessage(responseMessage, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
