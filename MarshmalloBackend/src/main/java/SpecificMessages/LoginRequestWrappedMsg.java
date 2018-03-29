package SpecificMessages;

import java.io.IOException;

import Messaging.MarshmallowMessage;
import ProtoJavaFiles.Heartbeat.LoginRequest;;

public class LoginRequestWrappedMsg implements MarshmallowMessage {

	protected LoginRequest myMsg;
	
	public LoginRequestWrappedMsg(LoginRequest msg)
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
		return "LoginRequest";
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
		return new LoginRequestWrappedMsg(myMsg.toBuilder().build());
	}

}
