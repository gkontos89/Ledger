package marshmallowlearning.marshmallowandroid;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by George on 3/7/2018.
 */
public class MarshmallowGlobals extends Application{
    // Connection to backend database
    private InetAddress inet;
    private int port;
    private Socket backendSocket = null;

    // User Account variables
    private String username = null;

    // Debug variables
    private Boolean debugMode = false;

    public Socket getBackendSocket(){
        if (backendSocket != null) {
            return backendSocket;
        }
        else
        {
            try {
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                }
                else
                    connected = false;

                InetAddress inetAddress = InetAddress.getByName("192.168.1.1");
                InetAddress inetAddress2 = InetAddress.getByName("google.com");
                InetAddress[] possible = InetAddress.getAllByName(null);
                for(InetAddress n : possible)
                    System.out.println(n);
                Boolean result = inetAddress.isReachable(3000);
                Boolean result2 = inetAddress2.isReachable(3000);
                //Socket soc2 = new Socket(inetAddress2);
                //sock.connect(new InetSocketAddress(this.inet, this.port), 3000);
//                this.backendSocket = new Socket(this.inet, this.port);
                Socket sock = new Socket(inetAddress, this.port);
                sock.setSoTimeout(5000);
                backendSocket = sock;
            } catch (IOException e) {
                System.err.println("IOException " + e.getMessage());
            }
        }

        return backendSocket;
    }

    public void setBackendSocket(InetAddress inet, int port) {
        this.inet = inet;
        this.port = port;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getDebugMode() {return debugMode;}
    public void setDebugMode(Boolean debugMode) {this.debugMode = debugMode;}
}

