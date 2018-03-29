package Messaging;

import java.io.IOException;

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
}
