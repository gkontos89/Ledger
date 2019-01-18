package com.marshmallow.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.marshmallow.android.manager.MarshmallowGameManager;

public class MarshmallowEngineService extends Service {
    private Messenger incomingMessenger = null;
    public static final int MSG_INIT_TIMER = 0;
    public static final int MSG_START_TIMER = 1;
    public static final int MSG_PAUSE_TIMER = 2;
    public static final int MSG_RESUME_TIMER = 3;
    public static final int MSG_STOP_TIMER = 4;
    public static final int MSG_SET_DAY_RATE = 5;
    public static final int MSG_GET_TIME = 6;
    public static final int MSG_SAVE_SESSION = 7;
    public static final int MSG_INIT = 8;
    public static final int MSG_INIT_RSP = 9;
    public static final int MSG_GET_TIME_RSP = 10;

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
                case MSG_INIT:
                    Messenger clientMessenger = msg.replyTo;
                    try {
                        clientMessenger.send(Message.obtain(null, MSG_INIT_RSP));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;

                case MSG_INIT_TIMER:
                    if (marshmallowTimer != null && marshmallowTimer.isAlive()) {
                        marshmallowTimer.stopTimer();
                        try {
                            marshmallowTimer.join();
                            marshmallowTimer = null;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

//                    MarshmallowGameManager.getInstance().getMarshmallowUser().clearUserData();
                    MarshmallowTime marshmallowInitTime = MarshmallowTimeManager.getMarshmallowTimeFromBundle(msg.getData());
                    marshmallowTimer = new MarshmallowTimer();
                    marshmallowTimer.initTimer(marshmallowInitTime);
                    break;

                case MSG_SET_DAY_RATE:
                    MarshmallowTime marshmallowTime = MarshmallowTimeManager.getMarshmallowTimeFromBundle(msg.getData());
                    marshmallowTimer.setDayRate(marshmallowTime.dayRate);
                    break;

                case MSG_START_TIMER:
                    if (marshmallowTimer != null && !marshmallowTimer.isAlive()) {
                        marshmallowTimer.start();
                    }
                    break;

                case MSG_PAUSE_TIMER:
                    marshmallowTimer.pauseTimer();
                    break;

                case MSG_RESUME_TIMER:
                    marshmallowTimer.unPauseTimer();
                    break;

                case MSG_STOP_TIMER:
                    if (marshmallowTimer != null && marshmallowTimer.isAlive()) {
                        marshmallowTimer.stopTimer();
                        try {
                            marshmallowTimer.join();
                            marshmallowTimer = null;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case MSG_GET_TIME:
                    Messenger timeRequestClient = msg.replyTo;
                    Bundle marshmallowTimeBundle = MarshmallowTimeManager.getMarshmallowTimeBundle(marshmallowTimer.getCurrentTime());
                    Message message = Message.obtain(null, MSG_GET_TIME_RSP);
                    message.setData(marshmallowTimeBundle);
                    try {
                        timeRequestClient.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
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

        public synchronized void initTimer(MarshmallowTime marshmallowTime) {
            this.dayRate = marshmallowTime.dayRate;
            this.day = marshmallowTime.day;
            this.month = marshmallowTime.month;
            this.year = marshmallowTime.year;
            initialized = true;
        }

        public synchronized void setDayRate(int timerDayRate) {
            dayRate = timerDayRate;
        }

        public synchronized void pauseTimer() {
            pause = true;
        }

        public synchronized void unPauseTimer() {
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

        public synchronized MarshmallowTime getCurrentTime() {
            return new MarshmallowTime(dayRate, day, month, year);
        }

        public void run() {
            while (!stop) {
                if (!pause) {
                    try {
                        // TODO handle calendar better
                        Thread.sleep(dayRate);
                        MarshmallowTime marshmallowTime = new MarshmallowTime();
                        day++;
//                        boolean speedBumpApplied = MarshmallowGameManager.getInstance().getMarshmallowUser().applySpeedBumps();
                        boolean speedBumpApplied = false;
                        if (speedBumpApplied) {
                            marshmallowTime.speedBumpApplied = true;
                        }

                        if (day > 30) {
                            day = 0;
                            month++;
//                            MarshmallowGameManager.getInstance().getMarshmallowUser().applyMonthlyUpdates();
                            marshmallowTime.monthlyUpdatesOccurred = true;

                            if (month > 12) {
                                month = 0;
                                year++;
                                marshmallowTime.yearHasPassed = true;
                            }
                        }

                        marshmallowTime.day = day;
                        marshmallowTime.month = month;
                        marshmallowTime.year = year;
                        MarshmallowTimeManager.getInstance().storeMarshmallowTime(marshmallowTime);
                        sendBroadcast(MarshmallowTimeManager.getMarshmallowTimeIntent(marshmallowTime));
                    } catch (InterruptedException e) {
                        // TODO handle this better
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
