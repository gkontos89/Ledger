package com.marshmallow.android.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshmallow.android.R;
import com.marshmallow.android.models.account.AccountManager;
import com.marshmallow.android.models.account.CheckingAccount;
import com.marshmallow.android.models.account.MarshmallowAccountInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * Created by George on 6/26/2018.
 */
public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountHolder> {

    private Context context;
    private List<String> accountUniqueIds;

    public AccountAdapter(Context context, Set<String> accountUniqueIds) {
        this.context = context;
        this.accountUniqueIds = new ArrayList<>(accountUniqueIds);
    }

    @NonNull
    @Override
    public AccountHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_basic, parent, false);
        return new AccountHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AccountHolder holder, final int position) {
        final Object account = AccountManager.getInstance().getAccount(accountUniqueIds.get(position));
        String accountName = ((MarshmallowAccountInterface) account).getAccountName();
        Integer accountValue = ((MarshmallowAccountInterface) account).getAccountValue();
        final String accountUniqueId = ((MarshmallowAccountInterface) account).getUniqueId();

        holder.getAccountName().setText(accountName);
        holder.getAccountValue().setText(String.format("$%d", accountValue));
        if (accountValue < 0) {
            holder.getAccountValue().setTextColor(Color.parseColor("#D50000"));
        } else if (accountValue > 0) {
            holder.getAccountValue().setTextColor(Color.parseColor("#00C853"));
        } else {
            holder.getAccountValue().setTextColor(Color.parseColor("#000000"));
        }

        // Launch correct activity based on account type
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (account instanceof CheckingAccount) {
                    Intent intent = new Intent(context.getApplicationContext(), CheckingAccountActivity.class);
                    intent.putExtra("accountUniqueId", accountUniqueId);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() { return accountUniqueIds.size(); }

    public class AccountHolder extends RecyclerView.ViewHolder {
        private TextView accountName;
        private TextView accountValue;

        private AccountHolder(View v) {
            super(v);
            accountName = v.findViewById(R.id.account_name);
            accountValue = v.findViewById(R.id.account_basic_value);
        }

        private TextView getAccountName() { return accountName; }
        private TextView getAccountValue() { return accountValue; }
    }
}
