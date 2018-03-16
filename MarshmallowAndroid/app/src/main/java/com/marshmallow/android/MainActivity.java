package com.marshmallow.android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Spinner;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import com.marshmallow.android.login.LoginActivity;
import com.marshmallow.android.utilities.Heartbeat;
import com.marshmallow.android.utilities.Heartbeat.Header;
import com.marshmallow.android.utilities.MarshmallowGlobals;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Spinner timePeriodSpinner;
    public ArrayList<String> timePeriods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToLoginState();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_assets) {

        } else if (id == R.id.nav_transactions) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void goToLoginState()
    {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        this.startActivity(loginIntent);
        System.out.println("We are here now");
    }

    public class HeartbeatHandler extends AsyncTask<Void, String, Void> {
        // Protobuf datatypes
        private Header protoBufHeader;
        private Heartbeat.HeartBeat protoBufHeartbeat;

        @Override
        protected  Void doInBackground(Void... params)
        {
            //Socket sock = LoginActivity.getBackendSocket();
//                    host = "localhost";
//                    InetAddress inet = InetAddress.getByName("192.168.1.153");
//                    InetAddress inethost = InetAddress.getLocalHost();
//                    port = 8321;
            int len = -1;
            MarshmallowGlobals marshmallowGlobals = (MarshmallowGlobals)getApplication();
            Socket backendSocket = marshmallowGlobals.getBackendSocket();

            int retries = 5000;
            while (retries > 0) {
                try {
                    byte[] buffer = new byte[65535];
                    String beat = "";
                    len = backendSocket.getInputStream().read(buffer);
                    if (len != -1) {
                        byte[] dataBytes = new byte[len];
                        System.arraycopy(buffer, 0, dataBytes, 0, len);

                        protoBufHeader = Header.parseFrom(dataBytes);
                        if (protoBufHeader.getId().equalsIgnoreCase("Heartbeat")) {
                            protoBufHeartbeat = Heartbeat.HeartBeat.parseFrom(dataBytes);
                            beat = protoBufHeartbeat.getBeat();
                            publishProgress(beat);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("IOException " + e.getMessage());
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... backendString)
        {
            //TextView roiTextView = (TextView) findViewById(R.id.roiText);
            //roiTextView.setText(backendString[0]);
        }
    }
}
