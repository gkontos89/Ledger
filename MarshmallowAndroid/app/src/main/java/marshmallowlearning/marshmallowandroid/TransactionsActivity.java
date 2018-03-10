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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TransactionsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //UserBroadcastReceiver userBroadcastReceiver = new UserBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
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
        Intent userIntent = new Intent(getApplicationContext(), UserIntentService.class);
        userIntent.setAction(UserIntentService.actionRetrieveUserData);
        startService(userIntent);
    }

//    @Override
//    protected  void onResume() {
//        super.onResume();
//        registerReceiver(userBroadcastReceiver, new IntentFilter(UserIntentService.actionUserDataRetrievalComplete));
//    }
//
//    protected  void onPause() {
//        super.onPause();
//        unregisterReceiver(userBroadcastReceiver);
//    }

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
        } else if (id == R.id.nav_market) {
            Intent intent = new Intent(getApplicationContext(), MarketActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public class TransactionItemAdapter extends ArrayAdapter<UserBroadcastReceiver.TransactionItem> {
//        public TransactionItemAdapter(Context context, ArrayList<UserBroadcastReceiver.TransactionItem> transactionItems) {
//            super(context, 0, transactionItems);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // Get the data item for this position
//            // Get the data item for this position
//            UserBroadcastReceiver.TransactionItem transactionItem = getItem(position);
//            // Check if an existing view is being reused, otherwise inflate the view
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.transcation_item, parent, false);
//            }
//            // Lookup view for data population
//            TextView txName = (TextView) convertView.findViewById(R.id.name);
//            TextView txCost = (TextView) convertView.findViewById(R.id.cost);
//            //TextView marketItemRecurringCost = (TextView) convertView.findViewById(R.id.RecurringCost);
//
//            // Populate the data into the template view using the data object
//            txName.setText(transactionItem.name);
//            txCost.setText(transactionItem.dollarAmount);
//
//            // Return the completed view to render on screen
//            return convertView;
//        }
//    }
//
//    public void displayTransactionData(ArrayList<UserBroadcastReceiver.TransactionItem> transactionItems){
//        TransactionItemAdapter transactionItemAdapter = new TransactionItemAdapter(this, transactionItems);
//
//        // Attach data to market UI
//        ListView transactionsListView = (ListView) findViewById(R.id.TransactionListView);
//        transactionsListView.setAdapter(transactionItemAdapter);
//    }
}
