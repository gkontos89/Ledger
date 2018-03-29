package com.marshmallow.android.asset;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.marshmallow.android.Messaging.ServerConnectionService;
import com.marshmallow.android.interfaces.ServerListener;

import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Caleb on 3/29/2018.
 */

public class AssetListenerService extends Service implements ServerListener {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

    }



    /** Called when The service is no longer used and is being destroyed */
    @Override
    public void onDestroy() {

    }

    @Override
    public void handleIncomingData(Object message) {

    }
}
