package com.marshmallow.android.services;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/**
 * Created by George on 11/22/2018.
 */
public class MarshmallowServiceConnection implements ServiceConnection {
    private Messenger incomingMessenger = null;
    private Messenger outgoingMessenger = null;

    public MarshmallowServiceConnection(Handler handler) {
        incomingMessenger = new Messenger(handler);
        outgoingMessenger = null;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        outgoingMessenger = new Messenger(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        incomingMessenger = null;
        outgoingMessenger = null;
    }

    public void sendMessage(Message message) {
        try {
            message.replyTo = incomingMessenger;
            outgoingMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public IBinder getIncomingMessengerBinder() {
        return incomingMessenger.getBinder();
    }
}
