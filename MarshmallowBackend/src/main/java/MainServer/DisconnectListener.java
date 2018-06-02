package MainServer;

import java.net.Socket;

public interface DisconnectListener 
{
	public void handleDisconnectEvent(Socket socket);
}
