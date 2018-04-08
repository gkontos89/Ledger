package com.marshmallow.android.interfaces;

import android.content.Intent;

/**
 * This is the interface for ModelIntentServices designed to handle the following
 *  - Handle launches from Messaging layer that contain new marshmallow messages
 *  - creates a model from that message and passses it to the respective ModelManager
 *
 *  - Handles launches for model updates from ModelManager
 *  - retrieves the model using the unique id in the message
 *  - build protobuffer from model
 *  - creates a serializable MarshmallowMessage from the proto
 *  - broadcasts to message layer
 *
 *  Created by George on 4/7/2018.
 */

public interface ModelIntentService {

    /**
     * This function will must configure the broadcastReceiver to filter on appropriate events
     * from the ServerConnectionService and ModelManager and handle those events accordingly
     */
    void handleServerMessages(Intent intent);

    void handleModelManagerMessages(Intent intent);
}
