package SpecificMessages;

import java.io.IOException;

import Messaging.MarshmallowMessage;
import ProtoJavaFiles.Heartbeat.LoginApproved;

public class LoginApprovedWrappedMsg implements MarshmallowMessage {

	LoginApproved myMsg;
	public LoginApprovedWrappedMsg(LoginApproved msg)
	{
		myMsg = msg;
	}
	@Override
	public Comparable<?> getMyIdData() {
		return myMsg.getId();
	}

	@Override
	public int getDataSize() {
		return myMsg.getSerializedSize();
	}

	@Override
	public Comparable<?> getMyIdDefaultValue() {
		return "LoginApproved";
	}

	@Override
	public byte[] getAsByteArray() throws IOException {
		return myMsg.toByteArray();
	}

	@Override
	public void fillFromByteArray(byte[] input) throws IOException {
		myMsg = myMsg.parseFrom(input);
	}

	@Override
	public boolean canParse(byte[] input) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MarshmallowMessage getClone() {
		return new LoginApprovedWrappedMsg(myMsg.toBuilder().build());
	}

}
