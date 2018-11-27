package com.marshmallow.android.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.marshmallow.android.models.MarshmallowUser;

public class MarshmallowEngineService extends Service {
    private Messenger incomingMessenger = null;
    static final int MSG_INIT_TIMER = 0;
    static final int MSG_START_TIMER = 1;
    static final int MSG_PAUSE_TIMER = 2;
    static final int MSG_RESUME_TIMER = 3;
    static final int MSG_STOP_TIMER = 4;
    static final int MSG_SET_DAY_RATE = 5;
    static final int MSG_GET_TIME = 6;
    static final int MSG_SAVE_SESSION = 7;

    @Override
    public void onCreate() {
        super.onCreate();
        incomingMessenger = new Messenger(new MarshmallowServiceHandler());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return incomingMessenger.getBinder();
    }

    /**
     * This class handles any incoming messages to this service which control the
     * time keeping of the game
     */
    class MarshmallowServiceHandler extends Handler {
        private MarshmallowTimer marshmallowTimer = new MarshmallowTimer();

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_INIT_TIMER:
                    if (marshmallowTimer != null) {
                        marshmallowTimer.stopTimer();
                    }

                    MarshmallowUser.getInstance().clearUserData();
                    Bundle timerInitData = msg.getData();
                    int dayRate  = timerInitData.getInt("dayRate");
                    int day = timerInitData.getInt("day");
                    int month = timerInitData.getInt("month");
                    int year = timerInitData.getInt("year");
                    marshmallowTimer = new MarshmallowTimer();
                    marshmallowTimer.initTimer(dayRate, day, month, year);
                    break;

                case MSG_SET_DAY_RATE:
                    Bundle timerDayRateData = msg.getData();
                    int timerDayRate  = timerDayRateData.getInt("dayRate");
                    marshmallowTimer.setDayRate(timerDayRate);

                case MSG_START_TIMER:
                    if (marshmallowTimer != null) {
                        marshmallowTimer.start();
                    }
                    break;

                case MSG_PAUSE_TIMER:
                    marshmallowTimer.pauseTimer();
                    break;

                case MSG_RESUME_TIMER:
                    marshmallowTimer.unpauseTimer();
                    break;

                case MSG_STOP_TIMER:
                    marshmallowTimer.stopTimer();
                    break;

                case MSG_GET_TIME:
                    Intent intent = new Intent();
                    intent.setAction("TIME_DATA");
                    intent.putExtra("day", marshmallowTimer.getDay());
                    intent.putExtra("month", marshmallowTimer.getMonth());
                    intent.putExtra("year", marshmallowTimer.getYear());
                    sendBroadcast(intent);
                    break;

                case MSG_SAVE_SESSION:
                    // TODO implement
                    break;

                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    /**
     * This class keep the time of the game, updates the user data as necessary and notifies
     * the views that data has been updated
     */
    private class MarshmallowTimer extends Thread {
        private int dayRate = 0;
        private int month = 1;
        private int day = 0;
        private int year = 0;
        private boolean pause = false;
        private boolean stop = false;
        private boolean initialized = false;

        private MarshmallowTimer() {
        }

        public synchronized void initTimer(int dayRate, int day, int month, int year) {
            this.dayRate = dayRate;
            this.day = day;
            this.month = month;
            this.year = year;
            initialized = true;
        }

        public synchronized void setDayRate(int timerDayRate) {
            dayRate = timerDayRate;
        }

        public synchronized void pauseTimer() {
            pause = true;
        }

        public synchronized void unpauseTimer() {
            pause = false;
        }

        public synchronized void stopTimer() {
            stop = true;
            initialized = false;
        }

        public synchronized int getMonth() {
            return month;
        }

        public synchronized int getDay() {
            return day;
        }

        public synchronized int getYear() {
            return year;
        }

        public void run() {
            while (!stop) {
                if (!pause) {
                    try {
                        // TODO handle calendar better
                        Thread.sleep(dayRate);
                        day++;
                        MarshmallowUser.getInstance().applySpeedBumps();
                        // TODO return value indicates speed bump occurred, submit a broadcast
                        if (day > 30) {
                            day = 0;
                            month++;
                            MarshmallowUser.getInstance().applyMonthlyUpdates();
                            // TODO submit broadcast to update GUI of new updates

                            if (month > 12) {
                                month = 0;
                                year++;
                            }
                        }
                    } catch (InterruptedException e) {
                        // TODO handle this better
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
