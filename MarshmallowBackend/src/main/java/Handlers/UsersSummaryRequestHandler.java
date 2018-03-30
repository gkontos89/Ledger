package Handlers;

import java.io.IOException;
import java.util.ArrayList;

import BackendModels.Asset;
import BackendModels.User;
import BackendModels.UserManager;
import MainServer.InitialTest;
import Messaging.MarshmallowMessage;
import Messaging.MessageReceiver;
import ProtoJavaFiles.Heartbeat.UserSummaryRequest;
import ProtoJavaFiles.Heartbeat.UserSummary;
import Utilities.LoggingUtilities;

public class UsersSummaryRequestHandler implements MessageReceiver {

	@Override
	public ArrayList<Comparable<?>> getHandledMessages() {
		ArrayList<Comparable<?>> ids = new ArrayList<Comparable<?>>();
		ids.add("UserSummaryRequest");
		return ids;
	}

	@Override
	public void handleMessage(MarshmallowMessage msg) {
		if(!msg.getMyIdDefaultValue().equals("UserSummaryRequest"))
			return;
		
		User theUser = UserManager.Instance().getUser( ((UserSummaryRequest)msg.getProtoMessage()).getUsername());
		if(theUser == null)
		{
			LoggingUtilities.logBackend("Was asked to get the UserSummary for a user that is not active");
			return;
		}
		UserSummary summary = UserSummary.newBuilder().setId("UserSummary").setCash((int) theUser.getMoney()).setNetworth((int) theUser.getMoney()).build();
		int assetsValue = 0;
		for(Asset a : theUser.getAssets())
			assetsValue += a.getValue();
		summary = summary.newBuilder().setAssetsValue(assetsValue).build();
		
		MarshmallowMessage answer = new MarshmallowMessage(theUser);
		try {
			InitialTest.testingServer.sendMessage(answer, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
								
	}

}
