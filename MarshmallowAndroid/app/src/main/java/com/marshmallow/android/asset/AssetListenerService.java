package com.marshmallow.android.asset;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.marshmallow.android.Messaging.MarshmallowMessage;
import com.marshmallow.android.Messaging.ServerConnectionService;
import com.marshmallow.android.utilities.Heartbeat.AssetModelData;

import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Caleb on 3/29/2018.
 */

public class AssetListenerService extends Service{

    BroadcastReceiver br = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getSerializableExtra("MarshmallowMessage") != null) {
                MarshmallowMessage msg = (MarshmallowMessage) intent.getSerializableExtra("MarshmallowMessage");
                if (!msg.getClass().getSimpleName().equals("AssetMessage")) {
                    System.out.println("Not handling the message in the AssetListenerService.");
                    return;
                } else {
                    //TODO Cast the object to the message and get the Key for the asset then do the update, remove etc.
                    AssetModelData assetModelData = (AssetModelData) msg.getProtoMessage();
                    if (AssetsManager.Instance().hasAssetModel(assetModelData.getUniqueId()) {
                        
                    }
                }
            }
            else
            {
                System.out.println("Was not given a MarshmallowMessage to the AssetListenerService");
                return;
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        //TODO figure out what this even does, do we need to fill this out?
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(br, filter);

        return Service.START_STICKY;
    }


    /** Called when The service is no longer used and is being destroyed */
    @Override
    public void onDestroy() {
        this.unregisterReceiver(br);
    }
}
