package com.marshmallow.android.model.asset;

import java.util.Random;

/**
 * Created by George on 11/28/2018.
 */
public class SpeedBump {
    private String name;
    private int cost;
    private String description;
    // represented as ie. 1:500, so the oddCap would be '500'
    private int oddCap;
    private Random pseudoSpeedBumpGenerator;

    public SpeedBump(String name, int cost, int oddCap, String description) {
        this.name = name;
        this.cost = cost;
        this.oddCap = oddCap;
        this.description = description;
        pseudoSpeedBumpGenerator = new Random();
    }

    public boolean materialize() {
        int value = pseudoSpeedBumpGenerator.nextInt(oddCap) + 1;
        return (value == 1);
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
