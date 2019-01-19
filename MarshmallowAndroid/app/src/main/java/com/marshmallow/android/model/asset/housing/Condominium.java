package com.marshmallow.android.model.asset.housing;

import com.marshmallow.android.model.asset.Asset;
import com.marshmallow.android.model.asset.SpeedBump;

/**
 * Created by George on 1/9/2019.
 */
public class Condominium extends Asset {
    public Condominium() {
        super("Condominium", 130000, 800);
        potentialSpeedBumps.add(new SpeedBump("Pipe Burst",
                1300,
                220,
                "Pipes froze and burst flooding the home!"));
    }
}
