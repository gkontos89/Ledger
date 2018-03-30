package com.marshmallow.android.asset;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.marshmallow.android.Messaging.ServerConnectionService;

import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Caleb on 3/29/2018.
 */

public class AssetListenerService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }


    /** Called when The service is no longer used and is being destroyed */
    @Override
    public void onDestroy() {

    }

    public void handleIncomingData(Object message) {

    }
}
