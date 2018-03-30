package Messaging;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import ProtoJavaFiles.Heartbeat;
import Utilities.LoggingUtilities;

/**
 * The purpose of this interface is to provide a way for all of our factories to gaurentee
 * methods and actions without knowing the contents of the message or how it will behave
 * 
 * Futher, this will allow us to use AMQP, protos, JSON, Monglo, w/e we want
 * 
 * @author caleb hanselman
 */
public class MarshmallowMessage
{
	Object protoMessage;
	
	public MarshmallowMessage(Object protoMsg)
	{
		protoMessage = protoMsg;
	}
	
	public Object getProtoMessage()
	{
		return protoMessage;
	}
	
	/**
	 * This method gets the value of the ID field as represented by the data. IE it can not be filled
	 * in correctly
	 * 
	 * We are making it return a Comparable for more flexibility. At inception a string seems cool and 
	 * dandy but this will allow us to grow to use ints or even more complex classes that we create 
	 * ourselfs
	 */
	public Comparable<?> getMyIdData()
	{
		String myIdName = "";
		try {
			myIdName = (String)protoMessage.getClass().getMethod("getId", null).invoke(protoMessage, null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			LoggingUtilities.logBackend("Was unable to find or invoke get Id Method");
		}
		return myIdName;
	}
	
	/**
	 * Method to return the size of all the data that is currently in this message
	 * 
	 * @return the size of the messages data currently stored on the object
	 */
	public int getDataSize()
	{
		int size = -1;
		try {
			size = (int)protoMessage.getClass().getMethod("getSerializedSize", null).invoke(protoMessage, null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			LoggingUtilities.logBackend("Was unable to find or invoke get serializedSize Method");
		}
		return size;
	}
	
	/**
	 * This method gets the default value of the ID. If you do not have a default value you are 
	 * not allowed to exist on this server.
	 * @return
	 */
	public Comparable<?> getMyIdDefaultValue()
	{
		return protoMessage.getClass().getSimpleName();
	}
	
	/**
	 * This is essentialy the serialize method. I dont care how you do it but to be a message you 
	 * must be able to do a goddamn byte array transformation!
	 * @return
	 */
	public byte[] getAsByteArray() throws IOException
	{
		byte[] data = new byte[1];
		try {
			data = (byte[])protoMessage.getClass().getMethod("toByteArray", null).invoke(protoMessage, null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			LoggingUtilities.logBackend("Was unable to find or invoke get toByteArray Method");
		}
		return data;
	}
	
	/**
	 * This method is the reverse from the top.
	 * @param input
	 */
	public void fillFromByteArray(byte[] input) throws IOException
	{
		try {
			protoMessage = protoMessage.getClass().getMethod("parseFrom", byte[].class).invoke(protoMessage, input);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			LoggingUtilities.logBackend("Was unable to find or invoke get parseFrom Method");
		}
	}
	
	/**
	 * A public exposing method that allows for us to wrap a clone of the message if you want too
	 * 
	 * @return a new clone of the message with all data coppied as it should be
	 */
	public MarshmallowMessage getClone()
	{
		MarshmallowMessage clone = null;
		try {
			Object cloneProtoBuilder = (protoMessage.getClass().getMethod("toBuilder", null).invoke(protoMessage, null));
			Object cloneProto = (cloneProtoBuilder.getClass().getMethod("build", null).invoke(cloneProtoBuilder, null));
			clone = new MarshmallowMessage(cloneProto);	
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			LoggingUtilities.logBackend("Was unable to find or invoke get build Method");
		}
		return clone;
	}
}
