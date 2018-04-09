package com.marshmallow.android.messaging;

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
     * Broadcast messages for ServerConnectionService <-> AssetModelIntentService <-> AssetActivity
     */
    private static final String serverToAssetModelServiceBroadcast = "serverToAssetModelServiceBroadcast";
    private static final String assetModelServiceToAssetActivityBroadcast = "assetModelServiceToAssetActivityBroadcast";
    private static final String assetActivityToAssetModelServiceBroadcast = "assetActivityToAssetModelServiceBroadcast";
    private static final String assetModelServiceToServerBroadcast = "assetModelServiceToServerBroadcast";
    public static String getServerToAssetModelServiceBroadcast() { return serverToAssetModelServiceBroadcast; }
    public static String getAssetModelServiceToAssetActivityBroadcast() { return assetModelServiceToAssetActivityBroadcast; }
    public static String getAssetActivityToAssetModelIntentServiceBroadcast() { return assetActivityToAssetModelServiceBroadcast; }
    public static String getAssetModelServiceToServerBroadcast() { return assetModelServiceToServerBroadcast; }
}
