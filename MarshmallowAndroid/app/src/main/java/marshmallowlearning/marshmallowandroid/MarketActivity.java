package marshmallowlearning.marshmallowandroid;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MarketActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MarketBroadcastReceiver marketBroadcastReceiver = new MarketBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Launch the User Intent service
        // TODO: NOTIFY USER THAT THE DATA IS UPDATING
        Intent marketIntent = new Intent(getApplicationContext(), MarketIntentService.class);
        marketIntent.setAction(MarketIntentService.actionRetrieveMarketData);
        startService(marketIntent);
    }

    @Override
    protected  void onResume() {
        super.onResume();
        registerReceiver(marketBroadcastReceiver, new IntentFilter(MarketIntentService.actionMarketDataRetrievalComplete));
    }

    protected  void onPause() {
        super.onPause();
        unregisterReceiver(marketBroadcastReceiver);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
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

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_assets) {
            Intent intent = new Intent(getApplicationContext(), AssetsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_transactions) {
            Intent intent = new Intent(getApplicationContext(), TransactionsActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class MarketItemAdapter extends ArrayAdapter<MarketBroadcastReceiver.MarketItem> {
        public MarketItemAdapter(Context context, ArrayList<MarketBroadcastReceiver.MarketItem> marketItems) {
            super(context, 0, marketItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            // Get the data item for this position
            MarketBroadcastReceiver.MarketItem marketItem = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.market_item, parent, false);
            }
            // Lookup view for data population
            TextView marketItemName = (TextView) convertView.findViewById(R.id.Name);
            TextView marketItemValue = (TextView) convertView.findViewById(R.id.MarketValue);
            TextView marketItemRecurringCost = (TextView) convertView.findViewById(R.id.RecurringCost);

            // Populate the data into the template view using the data object
            marketItemValue.setText(marketItem.marketValue);
            marketItemName.setText(marketItem.name);
            marketItemRecurringCost.setText(marketItem.recurringCost);

            // Return the completed view to render on screen
            return convertView;
        }
    }

    public void displayMarketData(ArrayList<MarketBroadcastReceiver.MarketItem> marketItems) {
        MarketItemAdapter marketItemAdapter = new MarketItemAdapter(this, marketItems);

        // Attach data to market UI
        ListView marketListView = (ListView) findViewById(R.id.MarketListView);
        marketListView.setAdapter(marketItemAdapter);
    }
}
