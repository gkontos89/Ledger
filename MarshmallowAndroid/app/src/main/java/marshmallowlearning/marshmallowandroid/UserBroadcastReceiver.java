package marshmallowlearning.marshmallowandroid;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by George on 3/9/2018.
 */

public class UserBroadcastReceiver extends BroadcastReceiver {
    // TODO: Create an object to a protobuf for that holds user data including financials and assets
    public Object userData = null;
    private static UserBroadcastReceiver instance = null;

    UserBroadcastReceiver() {
    }
    
    public static UserBroadcastReceiver getInstance() {
        if (instance == null) {
            instance = new UserBroadcastReceiver();
        }

        return instance;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(UserIntentService.actionUserDataRetrievalComplete)) {
            if (intent.getBooleanExtra(UserIntentService.extraNewData, false)) {
                // TODO: store new data locally or in class
            }


            // TODO: Update the GUI based on the context passed in
            if (context instanceof TransactionsActivity) {
                TransactionsActivity transactionsActivity = (TransactionsActivity) context;
                ArrayList<TransactionItem> transactionItems = new ArrayList<>();

                transactionItems.add(new TransactionItem("income", "$5000", "none"));
                transactionItems.add(new TransactionItem("clothes", "$700", "none"));
                transactionItems.add(new TransactionItem("food", "$50", "none"));
                transactionItems.add(new TransactionItem("ballgame", "300", "none"));
                transactionItems.add(new TransactionItem("income", "$5000", "none"));
                transactionsActivity.displayTransactionData(transactionItems);
            }

        }
    }

    // TODO: will be protobuf
    public class TransactionItem {
        public String name;
        public String dollarAmount;
        public String metaData;

        TransactionItem(String name, String dollarAmount, String metaData){
            this.name = name;
            this.dollarAmount = dollarAmount;
            this.metaData = metaData;
        }
    }
}
