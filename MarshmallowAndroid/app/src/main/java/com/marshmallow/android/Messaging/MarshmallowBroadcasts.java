package com.marshmallow.android.Messaging;

/**
 * This class contains static data structures for handling application broadcasts between Messaging and
 * Application layers and between Application components
 *
 *  Created by George on 4/7/2018.
 */

public final class MarshmallowBroadcasts {

    /**
     * Strings serving as keys for extra content in broadcasts
     */
    private static final String marshmallowMessageKey = "MarshmallowMessageKey";
    private static final String modelUniqueIdKey = "modelUniqueIdKey";
    public static final String getMarshmallowMessageKey() { return marshmallowMessageKey; }
    public static final String getModelUniqueIdKey() { return modelUniqueIdKey; }

    /**
     * Broadcast messages for ServerConnectionService <-> AssetModelListener <-> AssetModelManager
     */
    private static final String serverToAssetModelListenerBroadcast = "serverToAssetModelListenerBroadcast";
    private static final String assetManagerToListenerBroadcast = "assetManagerToListenerBroadcast";
    private static final String assetModelListenerToServerBroadcast = "assetModelListenerToServerBroadcast";
    public static String getServerToAssetModelListenerBroadcast() { return serverToAssetModelListenerBroadcast; }
    public static String getAssetManagerToListenerBroadcast() { return assetManagerToListenerBroadcast; }
    public static String getAssetModelListenerToServerBroadcast() { return assetModelListenerToServerBroadcast; }
}
