package SpecificMessages;

import java.io.IOException;

import Messaging.MarshmallowMessage;
import ProtoJavaFiles.Heartbeat.Header;

public class HeaderWrappedMsg implements MarshmallowMessage{

	protected Header myMsg;
	public HeaderWrappedMsg(Header msg)
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
		return "Header";
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
		return true;
	}
	@Override
	public MarshmallowMessage getClone() {
		return new HeaderWrappedMsg(myMsg.toBuilder().build());
	}
	
	
}
