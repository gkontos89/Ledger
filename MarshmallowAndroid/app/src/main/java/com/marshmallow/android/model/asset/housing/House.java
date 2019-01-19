package com.marshmallow.android.model.asset.housing;

import com.marshmallow.android.model.asset.Asset;
import com.marshmallow.android.model.asset.SpeedBump;

/**
 * Created by George on 11/29/2018.
 */
public class House extends Asset {
    public House() {
        super("House", 250000, 2200);
        potentialSpeedBumps.add(new SpeedBump("New Roof Replacement",
                12000,
                3650,
                "Roofs need replaced typically every 10 years.  Yours is due!"));
        potentialSpeedBumps.add(new SpeedBump( "Hot Water Heater Replacement",
                700,
                200,
                "Hot water heater has went out and needs to be replaced"));
    }
}
