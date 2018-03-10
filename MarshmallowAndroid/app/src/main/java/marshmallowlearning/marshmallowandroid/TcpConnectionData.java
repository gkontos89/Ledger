package marshmallowlearning.marshmallowandroid;

import java.net.Socket;

/**
 * Created by George on 3/10/2018.
 */

public class TcpConnectionData {
    protected static TcpConnectionData instance = null;
    private Socket mainSocket = null;

    public static TcpConnectionData Instance()
    {
        if(instance == null)
            instance = new TcpConnectionData();
        return instance;
    }

    public Socket getMainSocket() {return mainSocket;}
    public void setMainSocket(Socket socket) {mainSocket = socket;}
}
