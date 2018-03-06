package com.gkontos.kontos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import com.gkontos.kontos.Heartbeat.Header;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Spinner timePeriodSpinner;
    public ArrayList<String> timePeriods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // On first launch determine the time periods available for the spinner selection
        if (timePeriods.isEmpty())
        {
            timePeriods.add("Overall");
            timePeriods.add("2017");
            timePeriods.add("2018");
        }

        timePeriodSpinner = (Spinner) findViewById(R.id.timePeriod);
        timePeriodSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timePeriods));

        // Try to set the async
        HeartbeatHandler heartbeatHandler = new HeartbeatHandler();
        heartbeatHandler.execute();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            TextView roiTextView = (TextView) findViewById(R.id.roiText);
            roiTextView.setText(backendString[0]);
        }
    }
}
