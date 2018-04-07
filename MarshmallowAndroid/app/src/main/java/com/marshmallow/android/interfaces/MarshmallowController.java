package com.marshmallow.android.interfaces;

import android.view.View;

/**
 *
 */
public interface MarshmallowController {
    void connectModelAndView();

    void updateView();

    void setModel(Object model);

    Object getModel();
}
