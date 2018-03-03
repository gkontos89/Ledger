package MainServer;

import java.net.Socket;

public interface ConnectionListener
{
	public void handleConnectionEvent(TcpConnectionHandlerThread connectingSocket);
}
