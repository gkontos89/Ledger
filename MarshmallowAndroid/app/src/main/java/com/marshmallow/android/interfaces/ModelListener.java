package com.marshmallow.android.interfaces;

import com.marshmallow.android.Messaging.MarshmallowMessage;

/**
 * This is the interface for ModelListeners that listen do the following:
 *  - Listen for broadcasts from the Messaging layer that contain new marshmallow messages
 *  - creates a model from that message and passses it to the respective ModelManager
 *
 *  - Listens for model update broadcasts from ModelManager
 *  - retrieves the model using the unique id in the message
 *  - build protobuffer from model
 *  - creates a serializable MarshmallowMessage from the proto
 *  - broadcasts to message layer
 *
 *  Created by George on 4/7/2018.
 */

public interface ModelListener {

    /**
     * This function will must configure the broadcastReceiver to filter on appropriate events
     * from the ServerConnectionService and ModelManager and handle those events accordingly
     */
    void configureBroadcastReceiver();
}
