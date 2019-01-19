package com.marshmallow.android.manager;

/**
 * Created by George on 1/19/2019.
 */
public class MarshmallowTime {

    public int dayRate;
    public int day;
    public int month;
    public int year;
    public boolean speedBumpApplied;
    public boolean monthlyUpdatesOccurred;
    public boolean yearHasPassed;

    public MarshmallowTime() {
    }

    public MarshmallowTime(int dayRate, int day, int month, int year) {
        this.dayRate = dayRate;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public MarshmallowTime(int dayRate, int day, int month, int year, boolean speedBumpApplied,
                           boolean monthlyUpdatesOccurred, boolean yearHasPassed) {
        this.dayRate = dayRate;
        this.day = day;
        this.month = month;
        this.year = year;
        this.speedBumpApplied = speedBumpApplied;
        this.monthlyUpdatesOccurred = monthlyUpdatesOccurred;
        this.yearHasPassed = yearHasPassed;
    }
}
