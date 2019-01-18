package com.marshmallow.android.model.asset.transportation;

import com.marshmallow.android.model.asset.Asset;
import com.marshmallow.android.model.asset.SpeedBump;

/**
 * Created by George on 1/9/2019.
 */
public class NewCar extends Asset {
    public NewCar() {
        super("New Car", 30000, 600);
        potentialSpeedBumps.add(new SpeedBump("Flat Tire",
                300,
                10,
                1,
                "You drove over a nail and you tire went flat!"));
        potentialSpeedBumps.add(new SpeedBump("Oil Change",
                34,
                180,
                1,
                "Oil needs changed every 6 months and you are due!"));
        potentialSpeedBumps.add(new SpeedBump("Auto Accident",
                1200,
                50,
                1,
                "Someone hit your new car! You have to pay to get it fixed"));
    }
}
