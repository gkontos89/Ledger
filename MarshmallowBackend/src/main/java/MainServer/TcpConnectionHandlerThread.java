package MainServer;

import java.io.IOException;
import java.net.Socket;

import BackendModels.User;
import BackendModels.UserManager;
import Messaging.MarshmallowMessage;
import Messaging.MessageFactory;
import Messaging.MessageManager;
import ProtoJavaFiles.Heartbeat.LoginApproved;
import ProtoJavaFiles.Heartbeat.LoginRequest;
import Utilities.LoggingUtilities;

/**
 * 
 * 
 * @author chbla
 */
public class TcpConnectionHandlerThread extends Thread 
{
	protected Socket mySocket;
	protected boolean shouldClose;
	protected boolean manuallyClose;
	protected long lastRecieveTime;
	protected long connectionTime;
	protected boolean currentlyRunning;
	
	public TcpConnectionHandlerThread(Socket socket)
	{
		mySocket = socket;
		shouldClose = false;
		manuallyClose = false;
		
		connectionTime = lastRecieveTime = System.currentTimeMillis();
		currentlyRunning = false;
	}
	
	/**
	 * Method that will send the data out over the socket and return if there was any error
	 * This will be done asynchronously of any other method
	 * @param data
	 * @return
	 */
	public boolean sendData(byte[] data)
	{
		try {
			mySocket.getOutputStream().write(data);
		} catch (IOException e) {
			if(!e.getMessage().contains("reset by peer"))
			{
				LoggingUtilities.logBackend("Failed to send data to client with id: "+getMyId());
				e.printStackTrace();
			}
			shouldClose = true;
			return false;
		}
		return true;
	}
	
	public synchronized void manuallyClose()
	{
		shouldClose = true;
		manuallyClose = true;
	}
	
	/**
	 * This Method uniquely identifies the connection for each user.
	 * 
	 * returns the uniqueId for this connection
	 */
	public String getMyId()
	{
		//TODO think of a better ID
		return "Connection port:"+mySocket.getPort()+" IPAddress:"+mySocket.getInetAddress();
	}
	
	public Socket getSocket() { return mySocket; }
	public boolean getShouldClose() { return shouldClose; }
	public boolean manuallyClosed() { return manuallyClose; }
	public long getLastRecieveTime() { return lastRecieveTime; }
	public long getConnectionTime()	{ return connectionTime; }
	public boolean currentlyRunning() {	return currentlyRunning; }
	/**
	 * Will constantly sit on the Socket and try to pull data of maximum TCP packet Size 
	 * then pass it to be handled.
	 */
	@Override
	public void run() 
	{
		int bytesRead = 0;
		currentlyRunning = true;
		while(!shouldClose)
		{
			try {
				// Using the maximum size for a TCP payload just to be safe...
				byte[] inputBuffer = new byte[65535];
				bytesRead = mySocket.getInputStream().read(inputBuffer);
				if(bytesRead < 0 ) 
				{
					LoggingUtilities.logConnection("Connection has been dropped for socket: "+getMyId());
					System.out.println("Connection has been dropped for socket: "+getMyId());
					shouldClose = true;
					break;
				}
				LoggingUtilities.logFrontEnd("Recieved "+bytesRead+" bytes from the socket "+getMyId());
				
				// the input buffer will only be filled up to the amount of bytes read, everything else is 0
				byte[] dataBytes = new byte[bytesRead];
				System.arraycopy(inputBuffer, 0, dataBytes, 0, bytesRead);	
				
				// TCP can sometimes be rapid and package multiple messages together. 
				// So we will read until all of the databytes have been processed or we get an error
				while(dataBytes.length > 0)
				{
					try {
						MarshmallowMessage receivedMessage = MessageFactory.getMessage(dataBytes);
						MessageManager.Instance().publishMessage(receivedMessage);
						
						// We want each user to hold a pointer to its connection, if it the user isnt in yet then just
						// end our connection
						if(receivedMessage.getMyIdDefaultValue().equals("LoginRequest"))
						{
							String username = ((LoginRequest)receivedMessage.getProtoMessage()).getUsername();
							User user = UserManager.Instance().getUser(username); 
							if(user != null)
								user.setMyConnectionThread(this);
							else
								shouldClose = true;
						}								
						
						// Reduce the input so that if we got multiple messages in a packet we can continue to parse
						int msgSize = receivedMessage.getDataSize();
						byte[] remainingBytes = new byte[dataBytes.length-msgSize];
						for(int i = 0; i<remainingBytes.length; msgSize++)
							remainingBytes[i] = dataBytes[msgSize + i];
						// Update via reference
						dataBytes = remainingBytes;
					}catch(IOException e) {
						LoggingUtilities.logFrontEnd(e.getMessage());
						break;
					}
				}
			} 
			catch (IOException e) {
				System.out.println("Client has disconnected with exception: "+e.getMessage());
				shouldClose = true;
			}
		}
	}

}
