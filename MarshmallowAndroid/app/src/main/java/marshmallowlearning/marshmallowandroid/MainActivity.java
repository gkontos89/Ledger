package marshmallowlearning.marshmallowandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import marshmallowlearning.marshmallowandroid.MessageReceivers.UserSummaryReceiver;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    UserBroadcastReceiver userBroadcastReceiver = new UserBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BackEndListenerTask backEndListenerTask = new BackEndListenerTask(TcpConnectionData.Instance().getMainSocket());
        backEndListenerTask.execute((Void) null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Launch the User Intent service
        Intent userIntent = new Intent(getApplicationContext(), UserSummaryReceiver.class);
        userIntent.setAction(UserSummaryReceiver.actionRetrieveUserData);
        startService(userIntent);
    }

    @Override
    protected  void onResume() {
        super.onResume();
        registerReceiver(userBroadcastReceiver, new IntentFilter(UserSummaryReceiver.userDataAvailable));
    }

    protected  void onPause() {
        super.onPause();
        unregisterReceiver(userBroadcastReceiver);
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

        if (id == R.id.nav_transactions) {
            // Handle the camera action
            Intent intent = new Intent(getApplicationContext(), TransactionsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_assets) {
            Intent intent = new Intent(getApplicationContext(), AssetsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_market) {
            Intent intent = new Intent(getApplicationContext(), MarketActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class UserBroadcastReceiver extends BroadcastReceiver {
        // TODO: Create an object to a protobuf for that holds user data including financials and assets
        protected UserBroadcastReceiver instance = null;

        protected  UserBroadcastReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            int cash = intent.getIntExtra("Cash", 0);
            int netWorth = intent.getIntExtra("Networth", 0);
            int assetValue = intent.getIntExtra("AssetValue", 0);

            // Update the GUI based on new values
            TextView tvCash = findViewById(R.id.Cash);
            tvCash.setText(String.format("Cash:  $%d", Integer.toString(cash)));

            TextView tvNetWorth = findViewById(R.id.NetWorth);
            tvCash.setText(String.format("Net Worth:  $%d", Integer.toString(netWorth)));

            TextView tvAssetValue = findViewById(R.id.AssetValue);
            tvCash.setText(String.format("Asset Value:  $%d", Integer.toString(assetValue)));
        }
    }
}
