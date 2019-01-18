package com.marshmallow.android.model.asset.transportation;

import com.marshmallow.android.model.asset.Asset;
import com.marshmallow.android.model.asset.SpeedBump;

/**
 * Created by George on 1/9/2019.
 */
public class Bicycle extends Asset {
    public Bicycle() {
        super("Bicycle", 300, 5);
        potentialSpeedBumps.add(new SpeedBump("Flat Tire",
                30,
                10,
                1,
                "You drove over a nail and you tire went flat!"));
    }
}
