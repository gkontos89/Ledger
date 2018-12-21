package com.marshmallow.android.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.marshmallow.android.R;
import com.marshmallow.android.service.MarshmallowEngineService;
import com.marshmallow.android.service.MarshmallowServiceConnection;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private MarshmallowServiceConnection marshmallowEngineServiceConnection = null;
    private Button startServiceButton = null;
    private TextView statusText = null;
    private Button initTimerButton = null;
    private Button startTimerButton = null;
    private Button pauseTimerButton = null;
    private Button resumeTimerButton = null;
    private Button stopTimerButton = null;
    private Button setDayRate05Button = null;
    private Button setDayRate1Button = null;
    private Button getTimeButton = null;
    private TextView recurringTimeDisplay = null;
    private TextView getTimeDisplay = null;

    // Broadcasts
    private BroadcastReceiver timeReceiver = null;
    private IntentFilter timeReceiverFilter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startServiceButton = findViewById(R.id.start_service);
        statusText = findViewById(R.id.status);
        initTimerButton = findViewById(R.id.init_timer);
        startTimerButton = findViewById(R.id.start_time);
        pauseTimerButton = findViewById(R.id.pause_timer);
        resumeTimerButton = findViewById(R.id.resume_timer);
        stopTimerButton = findViewById(R.id.stop_timer);
        setDayRate05Button = findViewById(R.id.set_day_rate_05);
        setDayRate1Button = findViewById(R.id.set_day_rate_1);
        getTimeButton = findViewById(R.id.get_time);
        getTimeDisplay = findViewById(R.id.get_time_display);
        recurringTimeDisplay = findViewById(R.id.recurring_time);

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindToMarshmallowEngineService();
            }
        });

        initTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle timerInitData = new Bundle();
                timerInitData.putInt("dayRate", 1000);
                timerInitData.putInt("day", 1);
                timerInitData.putInt("month", 1);
                timerInitData.putInt("year", 2019);
                sendMessageToMarshmallowEngine(MarshmallowEngineService.MSG_INIT_TIMER, timerInitData);
            }
        });

        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToMarshmallowEngine(MarshmallowEngineService.MSG_START_TIMER, null);
            }
        });

        pauseTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToMarshmallowEngine(MarshmallowEngineService.MSG_PAUSE_TIMER, null);
            }
        });

        resumeTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToMarshmallowEngine(MarshmallowEngineService.MSG_RESUME_TIMER, null);
            }
        });

        stopTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToMarshmallowEngine(MarshmallowEngineService.MSG_STOP_TIMER, null);
            }
        });

        setDayRate05Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle timerDayRate = new Bundle();
                timerDayRate.putInt("dayRate", 500);
                sendMessageToMarshmallowEngine(MarshmallowEngineService.MSG_SET_DAY_RATE, timerDayRate);
            }
        });

        setDayRate1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle timerDayRate = new Bundle();
                timerDayRate.putInt("dayRate", 1000);
                sendMessageToMarshmallowEngine(MarshmallowEngineService.MSG_SET_DAY_RATE, timerDayRate);
            }
        });

        getTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToMarshmallowEngine(MarshmallowEngineService.MSG_GET_TIME, null);
            }
        });

        timeReceiverFilter = new IntentFilter();
        timeReceiverFilter.addAction("TimeIntervalUpdate");
        timeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int day = intent.getIntExtra("day", 0);
                int month = intent.getIntExtra("month", 0);
                int year = intent.getIntExtra("year", 0);
                String date = String.format("%d/%d/%d", month, day, year);
                recurringTimeDisplay.setText(date);
            }
        };

        registerReceiver(timeReceiver, timeReceiverFilter);
    }

    private void bindToMarshmallowEngineService() {
        marshmallowEngineServiceConnection = new MarshmallowServiceConnection(new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MarshmallowEngineService.MSG_INIT_RSP:
                        statusText.setText("Connected!");
                        break;

                    case MarshmallowEngineService.MSG_GET_TIME_RSP:
                        Bundle timeData = msg.getData();
                        int day = timeData.getInt("day");
                        int month = timeData.getInt("month");
                        int year = timeData.getInt("year");
                        String date = String.format("%d/%d/%d", month, day, year);
                        getTimeDisplay.setText(date);
                        break;

                    default:
                        super.handleMessage(msg);
                }
            }
        });

        bindService(new Intent(getApplicationContext(), MarshmallowEngineService.class),
               marshmallowEngineServiceConnection,
                Context.BIND_AUTO_CREATE);
    }

    private void sendMessageToMarshmallowEngine(int msgId, Bundle data) {
        if (marshmallowEngineServiceConnection != null) {
            Message message = Message.obtain(null, msgId);
            if (data != null) {
                message.setData(data);
            }

            marshmallowEngineServiceConnection.sendMessage(message);
        } else {
            // TODO handle invalid pop up
        }
    }
}
