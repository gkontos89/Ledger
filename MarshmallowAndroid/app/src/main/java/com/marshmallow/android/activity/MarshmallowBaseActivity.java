package com.marshmallow.android.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.marshmallow.android.manager.MarshmallowGameManager;
import com.marshmallow.android.model.MarshmallowUser;
import com.marshmallow.android.service.MarshmallowEngineService;
import com.marshmallow.android.service.MarshmallowServiceConnection;
import com.marshmallow.android.service.MarshmallowTime;
import com.marshmallow.android.service.MarshmallowTimeManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by George on 1/12/2019.
 */
public class MarshmallowBaseActivity extends AppCompatActivity {

    private static final String gameSaveData = "gameSaveData.txt";
    private MarshmallowServiceConnection marshmallowEngineServiceConnection = null;
    protected boolean isConnectedToEngineService = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void bindToMarshmallowEngineService() {
        marshmallowEngineServiceConnection = new MarshmallowServiceConnection(new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MarshmallowEngineService.MSG_INIT_RSP:
                        isConnectedToEngineService = true;
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

    protected void sendMessageToMarshmallowEngine(int msgId, Bundle data) {
        if (marshmallowEngineServiceConnection != null) {
            Message message = Message.obtain(null, msgId);
            if (data != null) {
                message.setData(data);
            }

            marshmallowEngineServiceConnection.sendMessage(message);
        } else {
            showToastMessage("Connection to Marshmallow Engine is down!");
        }
    }

    protected void saveGameData() {
        // Save data into different file names to know which class are where
        try {
            MarshmallowUser marshmallowUser = MarshmallowGameManager.getInstance().getMarshmallowUser();
            MarshmallowTime marshmallowTime = MarshmallowTimeManager.getInstance().getCurrentMarshmallowTime();
            if (marshmallowUser != null && marshmallowTime != null) {
                FileWriter fileWriter = new FileWriter(new File(this.getFilesDir(), gameSaveData), false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                Gson gson = new Gson();
                String userString = gson.toJson(marshmallowUser);
                String timeString = gson.toJson(marshmallowTime);
                bufferedWriter.write(userString);
                bufferedWriter.write(timeString);
                bufferedWriter.close();
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            showToastMessage("Error finding file to save game data");
        } catch (IOException e) {
            showToastMessage("Error writing saved game data");
        }
    }

    protected void loadGameData() {
        try {
            FileReader fileReader = new FileReader(new File(this.getFilesDir(), gameSaveData));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Gson gson = new Gson();
            String userString = bufferedReader.readLine();
            String timeString = bufferedReader.readLine();
            MarshmallowGameManager.getInstance().loadUser(gson.fromJson(userString, MarshmallowUser.class));

            // Initialize the main marshmallow engine timer with loaded time data
            MarshmallowTime loadedMarshmallowTime = gson.fromJson(timeString, MarshmallowTime.class);
            MarshmallowTimeManager.getMarshmallowTimeBundle(loadedMarshmallowTime);
            MarshmallowTimeManager.getInstance().storeMarshmallowTime(loadedMarshmallowTime);
            sendMessageToMarshmallowEngine(MarshmallowEngineService.MSG_INIT_TIMER, MarshmallowTimeManager.getMarshmallowTimeBundle(loadedMarshmallowTime));
        } catch (FileNotFoundException e) {
            showToastMessage("Error unable to find saved game data");
        } catch (IOException e) {
            showToastMessage("Error unable to load saved game data");
        }
    }

    protected void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void displayGif(@RawRes @DrawableRes @Nullable Integer resourceId, ImageView imageView){
        Glide.with(this).load(resourceId).into(imageView);
    }
}
