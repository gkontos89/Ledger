package Messaging;

import java.util.ArrayList;

/**
 * Interface that classes will implement if they are to react to a message being 
 * caught from a socket.
 * 
 * @author Caleb Hanselman
 */
public interface MessageReceiver 
{
	public ArrayList<Comparable<?>> getHandledMessages();
	
	public void handleMessage(MarshmallowMessage msg);
}
