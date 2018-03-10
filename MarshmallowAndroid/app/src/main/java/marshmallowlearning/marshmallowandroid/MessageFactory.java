package marshmallowlearning.marshmallowandroid;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The Message Factory is exactly what you think it is stoopid. What do you think, it was some magic stuff?
 * No its just a class that consumes data and spits out messages. 
 * 
 * @author Caleb Hanselman
 */
public class MessageFactory 
{
	
	public static MarshmallowMessage getMessage(byte[] input) throws IOException
	{
		MarshmallowMessage msg = MessageManager.Instance().getMessage(input);
		msg.fillFromByteArray(input);
		
		// Reduce the input so that if we got multiple messages in a packet we can continue to parse
		int msgSize = msg.getDataSize();
		byte[] remainingBytes = new byte[input.length-msgSize];
		for(int i = 0; i<remainingBytes.length; msgSize++)
			remainingBytes[i] = input[msgSize + i];
		// Update via reference
		input = remainingBytes;
		
		return msg;
	}
	
	/**
	 * A Utility function that will set the ID field of the message to the name of the class of the message
	 * @param msg The message which you wish the set the ID on 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static MarshmallowMessage setMessageId(MarshmallowMessage msg) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Method setter = msg.getClass().getMethod("setId", String.class);
		String msgId = msg.getClass().getSimpleName();
		Method buildMethod = msg.getClass().getMethod("build", null);
		if(setter == null) {
			return null;
		}
		if(buildMethod == null) {
			return null;
		}
		
		Object setMsg = setter.invoke(msg, msgId);
		return (MarshmallowMessage)buildMethod.invoke(setMsg, null);
	}
}
