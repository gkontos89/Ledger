package com.marshmallow.android.asset;

import android.app.IntentService;
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
import com.marshmallow.android.interfaces.ModelIntentService;
import com.marshmallow.android.utilities.Heartbeat.AssetModelData;

/**
 * Created by Caleb on 3/29/2018.
 */

public class AssetModelIntentService extends IntentService implements ModelIntentService {

    public AssetModelIntentService() { super("AssetModelIntentService"); }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(MarshmallowBroadcasts.getServerToAssetModelListenerBroadcast())) {
            if (intent.getSerializableExtra(MarshmallowBroadcasts.getMarshmallowMessageKey()) != null) {
                handleServerMessages(intent);
            }
        }
        else if (intent.getAction() != null && intent.getAction().equals(MarshmallowBroadcasts.getAssetManagerToListenerBroadcast())) {
            if (intent.getIntExtra(MarshmallowBroadcasts.getModelUniqueIdKey(), -1) != -1) {
                handleModelManagerMessages(intent);
            }
        }
    }

    @Override
    public void handleServerMessages(Intent intent) {
        MarshmallowMessage msg = (MarshmallowMessage) intent.getSerializableExtra(MarshmallowBroadcasts.getMarshmallowMessageKey());
        if (!msg.getClass().getSimpleName().equals("AssetMessage")) {
            System.out.println("Not handling the message in the AssetModelIntentService.");
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

    @Override
    public void handleModelManagerMessages(Intent intent) {
        // Grab the model from the manager and get a protobuf object
        Integer uniqueId = intent.getIntExtra(MarshmallowBroadcasts.getModelUniqueIdKey(), -1);
        if (AssetsModelManager.Instance().hasModel(uniqueId)) {
            AssetModel assetModel = (AssetModel) AssetsModelManager.Instance().getModel(uniqueId);
            AssetModelData assetModelData = (AssetModelData) assetModel.generateProtoDataFromModel();

            // Get a MarshmallowMessage from the protobuf and broadcast to the server
            try {
                MarshmallowMessage marshmallowMessage = MessageManager.Instance().getMessage(assetModelData.toByteArray());
                Intent serverIntent = new Intent();
                serverIntent.setAction(MarshmallowBroadcasts.getAssetModelListenerToServerBroadcast());
                serverIntent.putExtra(MarshmallowBroadcasts.getMarshmallowMessageKey(), marshmallowMessage);
                sendBroadcast(serverIntent);
            } catch (Exception e) {
                System.out.println("Error converting message to byte array");
            }
        }
    }
}
