package com.marshmallow.android.interfaces;

/**
 * Any class that implements this can handle info from the server in some extent
 * Created by Caleb on 3/29/2018.
 */

public interface ServerListener {
    public void handleIncomingData(Object message);
}
