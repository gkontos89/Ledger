package MainServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import Messaging.MarshmallowMessage;
import Utilities.LoggingUtilities;

/**
 * 
 * This purpose of this class is to be the entry point to the server. Server
 * will allow for the application to launch on a PC or server and accept clients 
 * connections. Each client connection will spawn a client handler class whichw will 
 * handle a specific connection.
 * 
 * This class will be spawned either by a seperate class or a main method for a single demo
 * 
 * @author Caleb Hanselman
 *
 */
public class TcpServer 
{
	protected int myPort;
	protected ServerSocket mySocket; 
	
	protected TcpConnectionThread myServerThread;
	
	protected Vector<TcpConnectionHandlerThread> myThreadHandlers; 
	
	protected Vector<ConnectionListener> myConnectionListeners;
	protected Vector<DisconnectListener> myDisconnectListeners;
	
	public TcpServer(int port)
	{
		myConnectionListeners = new Vector<ConnectionListener>();
		myDisconnectListeners = new Vector<DisconnectListener>();
		myServerThread = null;
		try {
			myPort = port;
			mySocket = new ServerSocket();
			mySocket.bind( new InetSocketAddress(port) );
			myThreadHandlers = new Vector<TcpConnectionHandlerThread>();
		}
		catch(Exception e)
		{
			LoggingUtilities.logConnection("Failed to create a new server socket on port "+port);
			e.printStackTrace();
		}
	}
	
	public synchronized ServerSocket getMySocket()
	{
		return mySocket;
	}
	
	public int getMyPort()
	{
		return myPort;
	}
	
	public void addClientHandler(TcpConnectionHandlerThread handler)
	{
		LoggingUtilities.logConnection("A new client has connected to the server");
		myThreadHandlers.add(handler);
		handler.start();
		for(ConnectionListener listener : myConnectionListeners)
		{
			listener.handleConnectionEvent(handler);
		}
	}
	
	public void startServer()
	{
		myServerThread = new TcpConnectionThread(this);
		//TODO Notify anyone who gives a fuck about this.
		myServerThread.start();
	}
	
	public void closeServer()
	{
		for(TcpConnectionHandlerThread client : myThreadHandlers)
		{
			client.manuallyClose();
			client.interrupt();
		}
		myThreadHandlers.clear();
		
		myServerThread.setInterrupted(true);
		myServerThread.interrupt();
	}
	
	public boolean sendMessage(MarshmallowMessage msg/*, MarshmallowUser user*/) throws IOException
	{
		boolean itWorked = true;
		for(TcpConnectionHandlerThread client : myThreadHandlers)
			if( !client.sendData(msg.getAsByteArray()) )
				itWorked = false;
		
		msg.fillFromByteArray(msg.getAsByteArray());
		
		return itWorked;
	}
	
	public void addConnectionListener(ConnectionListener listener){myConnectionListeners.add(listener); }
	public boolean removeConnectionListener(ConnectionListener listener){return myConnectionListeners.remove(listener); }
	
	public void addDisconnectListener(DisconnectListener listener){myDisconnectListeners.add(listener); }
	public boolean removeDisconnectListener(DisconnectListener listener){return myDisconnectListeners.remove(listener); }
	
	protected static class TcpConnectionThread extends Thread
	{
		private boolean isInterrupted;
		private TcpServer myServer;
		
		public synchronized void setInterrupted(boolean b) 
		{
			isInterrupted = b;
		}
		
		public TcpConnectionThread(TcpServer server)
		{
			myServer = server;
		}
		
		@Override
		public void run()
		{
			while(!isInterrupted)
			{
				try {
					Socket clientSocket = myServer.getMySocket().accept();
					System.out.println("We got a connection!");
					TcpConnectionHandlerThread newClientHandler = new TcpConnectionHandlerThread(clientSocket);
					myServer.addClientHandler(newClientHandler);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
		}
	}
}
