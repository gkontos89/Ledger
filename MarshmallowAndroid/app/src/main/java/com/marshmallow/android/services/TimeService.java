package com.marshmallow.android.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/**
 * Created by George on 11/22/2018.
 */
public class TimeService extends Service {

    private Messenger incomingMessenger = null;
    static final int MSG_START_TIMER = 2;
    static final int MSG_PAUSE_TIMER = 3;
    static final int MSG_UNPAUSE_TIMER = 4;
    static final int MSG_SET_TIMER_DURATION = 5;
    static final int MSG_SET_TICK_RATE = 6;
    static final int MSG_TICK = 7;
    static final int MSG_MONTH_TICK = 8;

    @Override
    public void onCreate() {
        super.onCreate();
        incomingMessenger = new Messenger(new IncomingHandler());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return incomingMessenger.getBinder();
    }

    class IncomingHandler extends Handler {
        private TickerThread tickerThread = null;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_START_TIMER:
                    if (tickerThread != null) {
                        tickerThread.stopTicker();
                    }

                    Bundle startTimerData = msg.getData();
                    int tickRate = startTimerData.getInt("tickRate");
                    tickerThread = new TickerThread();
                    tickerThread.setClientMessenger(msg.replyTo);
                    tickerThread.setTickDayRate(tickRate);
                    tickerThread.start();
                    break;

                case MSG_SET_TICK_RATE:
                    Bundle timerTickRateData = msg.getData();
                    int newTickRate = timerTickRateData.getInt("tickRate");
                    tickerThread.setTickDayRate(newTickRate);
                    break;

                case MSG_PAUSE_TIMER:
                    tickerThread.pauseTicker();
                    break;

                case MSG_UNPAUSE_TIMER:
                    tickerThread.unPauseTicker();
                    break;

                default:
                    super.handleMessage(msg);
            }
        }
    }

    private class TickerThread extends Thread {
        private Messenger clientMessenger = null;
        private int tickDayRate = 0;
        private int tickMonth = 30;
        private int tickCount = 0;
        private boolean pause = false;
        private boolean stop = false;

        private TickerThread() {
        }

        public synchronized void setClientMessenger(Messenger clientMessenger) {
            this.clientMessenger = clientMessenger;
        }

        public synchronized void setTickDayRate(int tickDayRate) {
            this.tickDayRate = tickDayRate;
        }

        public synchronized void pauseTicker() {
            pause = true;
        }

        public synchronized void unPauseTicker() {
            pause = false;
        }

        public synchronized void stopTicker() {
            stop = true;
        }

        public void run() {
            while (!stop) {
                if (!pause) {
                    tickCount++;
                    int msgId = MSG_TICK;
                    if (tickCount > tickMonth) {
                        msgId = MSG_MONTH_TICK;
                        tickCount = 0;
                    }

                    try {
                        clientMessenger.send(Message.obtain(null, msgId));
                        Thread.sleep(tickDayRate);
                    } catch (RemoteException | InterruptedException e) {
                        stop = true;
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
