package com.marshmallow.android.model.asset;

/**
 * Created by George on 11/29/2018.
 */
public class MidSizedHome extends Asset {
    public MidSizedHome() {
        super("Mid-Sized Home", 250000, 2200);
        potentialSpeedBumps.add(new SpeedBump("New Roof Replacement",
                12000,
                3650,
                10,
                "Roofs need replaced typically every 10 years.  Yours is due!"));
        potentialSpeedBumps.add(new SpeedBump( "Hot Water Heater Replacement",
                700,
                200,
                10,
                "Hot water heater has went out and needs to be replaced"));
    }
}
