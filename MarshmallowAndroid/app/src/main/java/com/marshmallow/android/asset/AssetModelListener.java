package com.marshmallow.android.asset;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.marshmallow.android.messaging.MarshmallowBroadcasts;
import com.marshmallow.android.messaging.MarshmallowMessage;
import com.marshmallow.android.messaging.MessageManager;
import com.marshmallow.android.interfaces.ModelListener;
import com.marshmallow.android.utilities.Heartbeat.AssetModelData;

/**
 * Created by Caleb on 3/29/2018.
 */

public class AssetModelListener extends Service implements ModelListener{

    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;

    @Override
    public void onCreate() {
        super.onCreate();
        configureBroadcastReceiver();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        //TODO figure out what this even does, do we need to fill this out?
        return null;
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        ListenerThread listenerThread = new ListenerThread();
//        listenerThread.start();
//        return Service.START_STICKY;
//    }


    /** Called when The service is no longer used and is being destroyed */
    @Override
    public void onDestroy() {
        this.unregisterReceiver(broadcastReceiver);
    }


    public void configureBroadcastReceiver() {
        intentFilter = new IntentFilter();
        intentFilter.addAction(MarshmallowBroadcasts.getServerToAssetModelListenerBroadcast());
        intentFilter.addAction(MarshmallowBroadcasts.getAssetManagerToListenerBroadcast());

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Handle messages from server
                if (intent.getAction() != null && intent.getAction().equals(MarshmallowBroadcasts.getServerToAssetModelListenerBroadcast())) {
                    if (intent.getSerializableExtra(MarshmallowBroadcasts.getMarshmallowMessageKey()) != null) {
                        MarshmallowMessage msg = (MarshmallowMessage) intent.getSerializableExtra(MarshmallowBroadcasts.getMarshmallowMessageKey());
                        if (!msg.getClass().getSimpleName().equals("AssetMessage")) {
                            System.out.println("Not handling the message in the AssetModelListener.");
                            return;
                        } else {
                            // We have a valid MarshmallowMessage.  Convert that to an AssetModel and pass to the AssetModelManager
                            AssetModelData assetModelData = (AssetModelData) msg.getProtoMessage();
                            AssetModel assetModel = new AssetModel();
                            assetModel.mapProtoDataToModel(assetModelData);
                            if (AssetsModelManager.Instance().hasModel(assetModelData.getUniqueId())) {
                                AssetsModelManager.Instance().updateModel(assetModel, assetModel.getUniqueId());
                            } else {
                                AssetsModelManager.Instance().addModel(assetModel, assetModel.getUniqueId());
                            }
                        }
                    }

                }
                // Handle messages from asset manager
                else if (intent.getAction() != null && intent.getAction().equals(MarshmallowBroadcasts.getAssetManagerToListenerBroadcast())) {
                    if (intent.getIntExtra(MarshmallowBroadcasts.getModelUniqueIdKey(), -1) != -1){
                        // Grab the model from the manager and get a protobuf object
                        Integer uniqueId = intent.getIntExtra(MarshmallowBroadcasts.getModelUniqueIdKey(), -1);
                        AssetModel assetModel = (AssetModel) AssetsModelManager.Instance().getModel(uniqueId);
                        AssetModelData assetModelData = (AssetModelData) assetModel.generateProtoDataFromModel();

                        // Get a MarshmallowMessage from the protobuf and broadcast to the server
                        try {
                            MarshmallowMessage marshmallowMessage = MessageManager.Instance().getMessage(assetModelData.toByteArray());
                            Intent serverIntent = new Intent();
                            serverIntent.setAction(MarshmallowBroadcasts.getAssetModelListenerToServerBroadcast());
                            serverIntent.putExtra(MarshmallowBroadcasts.getMarshmallowMessageKey(), marshmallowMessage);
                        } catch (Exception e) {
                            System.out.println("Error converting message to byte array");
                        }
                    }
                }
            }
        };

        registerReceiver(broadcastReceiver, intentFilter);
    }

    protected static class ListenerThread extends Thread {
        public void run()
        {
            while (true) {}
        }
    }
}
