package com.marshmallow.android.Messaging;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Service that on start will try to connect to the server. If it is not connected to the server
 * the controller will update the view accordingly.
 * Created by Caleb on 3/29/2018.
 */

public class ServerConnectionService extends Service {

    private static InetAddress serverInet = null;
    private static int port = 8321;
    private static Socket mySocket = null;
    protected static ServerConnectionService instance = null;

    private static boolean hasConnected = false;
    private static BackendListenerThread myConnectionThread = null;

    protected static class BackendListenerThread extends Thread{
        protected boolean interrupted = false;
        protected boolean serverDropped = false;

        public void run()
        {
            while(!interrupted && mySocket != null)
            {
                byte[] inputBuffer = new byte[65535];
                int bytesRead = 0;
                try {
                    bytesRead = mySocket.getInputStream().read(inputBuffer);
                    if (bytesRead < 0) {
                        System.out.println("Connection has been dropped for Server ");
                        // TODO go back into a connection state and notify the application that the server has dropped connection.
                        // probably launch an activity
                        serverDropped = true;
                        break;
                    }

                    // the input buffer will only be filled up to the amount of bytes read, everything else is 0
                    byte[] dataBytes = new byte[bytesRead];
                    System.arraycopy(inputBuffer, 0, dataBytes, 0, bytesRead);
                    Serializable incomingMessage = MessageFactory.getMessage(dataBytes);
                    Intent newMessageIntent = new Intent();
                    newMessageIntent.putExtra(MarshmallowBroadcasts.getMarshmallowMessageKey(), incomingMessage);
                    ServerConnectionService.instance.sendBroadcast(newMessageIntent);
                    ServerConnectionService.instance.sendBroadcast(newMessageIntent);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void setInterrupted(boolean value)
        {
            interrupted = value;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        instance = this;
        while (mySocket == null)
        {
            try {
                serverInet = InetAddress.getByName("192.168.1.153");

                mySocket = new Socket(serverInet, port);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        hasConnected = true;

        myConnectionThread = new BackendListenerThread();
        myConnectionThread.start();
        return Service.START_STICKY;
    }

    /** Called when The service is no longer used and is being destroyed */
    @Override
    public void onDestroy() {
        myConnectionThread.setInterrupted(true);
        myConnectionThread.interrupt();
        try{ mySocket.close(); }
        catch(Exception e){ e.printStackTrace(); }
        mySocket = null;
        myConnectionThread.interrupt();
        myConnectionThread = null;
    }
}
