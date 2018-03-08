package marshmallowlearning.marshmallowandroid;

import android.app.Application;

import java.io.IOException;
import java.net.InetAddress;
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
                this.backendSocket = new Socket(this.inet, this.port);
                this.backendSocket.setSoTimeout(5000);
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

