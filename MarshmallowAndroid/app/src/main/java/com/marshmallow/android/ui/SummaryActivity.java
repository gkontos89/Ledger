package com.marshmallow.android.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import com.marshmallow.android.R;
import com.marshmallow.android.models.summaryEngine.SummaryEngine;

public class SummaryActivity extends BaseActivity {

    // GUI handles
    private CardView netWorthCard;
    private CardView cashCard;
    private TextView netWorthValue;
    private TextView cashValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // GUI Handle instantiation
        netWorthCard = findViewById(R.id.net_worth_card);
        cashCard = findViewById(R.id.cash_card);
        netWorthValue = findViewById(R.id.net_worth_value);
        cashValue = findViewById(R.id.cash_value);

        // TODO set interactive features for cards

        populateCardValues(netWorthValue, SummaryEngine.getInstance().getNetWorth());
        populateCardValues(cashValue, SummaryEngine.getInstance().getCashSummaryTotalValue());
    }

    private void populateCardValues(TextView valueText, int value) {
        valueText.setText(value);
        if (value < 0) {
            valueText.setTextColor(Color.parseColor("#D50000"));
        } else if (value > 0) {
            valueText.setTextColor(Color.parseColor("#00C853"));
        } else {
            valueText.setTextColor(Color.parseColor("#000000"));
        }
    }
}
