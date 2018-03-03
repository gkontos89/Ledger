package Messaging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Utilities.LoggingUtilities;

/**
 * This class will act as our Manager for all existing messages.
 * 
 * In the near future this should be made to dynamically load in all messages in the resources and 
 * then build the map dynamically. This can be done either off of a configuration file or off
 * of reflection of the classes packaged in the server
 * 
 * @author caleb Hanselman
 */
public class MessageManager 
{
	protected Map messageMap;
	protected Map eventMap;
	
	// The Header Message is a message that all will allow any Marshmallow messages to be decoded 
	// because evey marshmallow message must have the same id type and start with it.
	protected MarshmallowMessage headerMessage;
	protected static MessageManager instance = null;
	
	public static MessageManager Instance()
	{
		if(instance == null)
			instance = new MessageManager();
		return instance;
	}
	
	protected MessageManager()
	{
		messageMap = new HashMap<Comparable<?>, MarshmallowMessage>();
		eventMap = new HashMap<Comparable<?>, ArrayList<MessageReceiver>>();
		headerMessage = null;
		discoverMessages();
	}
	
	public void addMessageReceiver(MessageReceiver receiver)
	{
		//for(Comparable<?> id : receiver.getHandledMessages())
			//eventMap.put(key, value)
	}
	
	/**
	 * Method that will go populate the Message Map.
	 * Can be configured in the future to use reflection, be hard coded or use a configuration
	 */
	public void discoverMessages()
	{
		//TODO
	}
	
	/**
	 * Method that will use the header Message to find what type of message this byte 
	 * array holds and then return a empty clone of that message so that it can be loaded
	 * with further data or have whatever happen to it.
	 * @param input
	 * @return the message that this data holds or starts to hold
	 */
	public MarshmallowMessage getMessage(byte[] input) throws IOException
	{
		if(headerMessage == null)
			throw new IOException("The header message has not been defined yet. It is impossible to determine messages.");
		headerMessage.fillFromByteArray(input);
		Comparable<?> messageId = headerMessage.getMyIdData();
		if(!messageMap.containsKey(messageId))
		{
			LoggingUtilities.logConnection("Decoded a header:"+messageId+" that the Manager is unaware of.");
			throw new IOException("Unsupported message ID:"+messageId);
		}
		return ((MarshmallowMessage) messageMap.get(messageId)).getClone();
	}
	
	/**
	 * Method that takes a known ID and if the map contains the ID will return a blank clone 
	 * for the caller to do more operations on
	 * @param id
	 * @return a new clone of the object indexed by the ID object
	 * @throws IOException
	 */
	public MarshmallowMessage getMessage(Comparable<?> id) throws IOException
	{
		if(!messageMap.containsKey(id))
		{
			LoggingUtilities.logConnection("Decoded a header:"+id+" that the Manager is unaware of.");
			throw new IOException("Unsupported message ID:"+id);
		}
		return ((MarshmallowMessage) messageMap.get(id)).getClone();
	}
	
	/**
	 * Method that will take a message and publish it to all methods that have registered a 
	 * callback to the incoming event 
	 * @param msg
	 */
	public void publishMessage(MarshmallowMessage msg)
	{
		
	}
}
