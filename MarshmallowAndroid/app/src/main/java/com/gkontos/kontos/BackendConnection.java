package com.gkontos.kontos;

import android.app.Application;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by George on 3/3/2018.
 */
public class BackendConnection extends Application{
    // Connection to backend database
    private InetAddress inet;
    private int port;
    private Socket backendSocket = null;

    public Socket getBackendSocket(){
        if (backendSocket != null) {
            return backendSocket;
        }
        else
        {
            try {
                this.backendSocket = new Socket(this.inet, this.port);
                this.backendSocket.setSoTimeout(5000);
                //this.backendSocket.connect(new InetSocketAddress(this.inet, this.port), 5000);
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
}
