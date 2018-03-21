package com.marshmallow.android.interfaces;

import android.content.Context;
import android.view.View;

/**
 * Abstract interface that gaurentees an object can be dynamically added to the program without
 * having to be defined in the a layout pre-emptively. This Is a custom view that may or may
 * not exist
 *
 * Created by Caleb on 3/21/2018.
 */

public interface MarshmallowCustomView {

    public View getBasicView(Context context );

    public View getDetailedView( Context context );

    // TODO Add more?
}
