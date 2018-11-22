package com.marshmallow.android.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshmallow.android.R;
import com.marshmallow.android.models.account.AccountManager;

import java.util.List;

/**
 * Created by George on 6/28/2018.
 */
public class AccountSelectionAdapter extends RecyclerView.Adapter<AccountSelectionAdapter.AccountSelectionHolder> {

    private Context context;
    private List<String> accountTypes;

    public AccountSelectionAdapter(Context context, List<String> accountTypes) {
        this.context = context;
        this.accountTypes = accountTypes;
    }

    @NonNull
    @Override
    public AccountSelectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_selection, parent, false);
        return new AccountSelectionHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AccountSelectionHolder holder, final int position) {
        final String accountType = accountTypes.get(position);
        holder.accountType.setText(accountType);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), AccountManager.getInstance().getAccountClassifiers().get(accountType));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return accountTypes.size(); }

    public class AccountSelectionHolder extends RecyclerView.ViewHolder {
        public TextView accountType;

        private AccountSelectionHolder(View v) {
            super(v);
            accountType = v.findViewById(R.id.account_type);
        }
    }
}
