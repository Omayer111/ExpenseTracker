package com.example.expensetracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.Model.Account;
import com.example.expensetracker.R;
import com.example.expensetracker.databinding.RowAccountBinding;

import java.util.ArrayList;

/**
 * AccountsAdapter is a RecyclerView.Adapter that displays a list of Account objects.
 */
public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder> {

    Context context;
    ArrayList<Account> accountArrayList;

    /**
     * Interface definition for a callback to be invoked when an account is selected.
     */
    public interface AccountsClickListener {
        /**
         * Called when an account has been selected.
         *
         * @param account The selected account.
         */
        void onAccountSelected(Account account);
    }

    AccountsClickListener accountsClickListener;

    /**
     * Constructs a new AccountsAdapter.
     *
     * @param context The context in which the adapter is running.
     * @param accountArrayList The list of Account objects to be displayed.
     * @param accountsClickListener The listener for account selection events.
     */
    public AccountsAdapter(Context context, ArrayList<Account> accountArrayList, AccountsClickListener accountsClickListener) {
        this.context = context;
        this.accountArrayList = accountArrayList;
        this.accountsClickListener = accountsClickListener;
    }

    /**
     * Called when RecyclerView needs a new {@link AccountsViewHolder} of the given type to represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new AccountsViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public AccountsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AccountsViewHolder(LayoutInflater.from(context).inflate(R.layout.row_account, parent, false));
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull AccountsViewHolder holder, int position) {
        Account account = accountArrayList.get(position);
        holder.binding.accountName.setText(account.getAccountName());
        holder.itemView.setOnClickListener(c -> {
            accountsClickListener.onAccountSelected(account);
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return accountArrayList.size();
    }

    /**
     * ViewHolder class for Account items.
     */
    public class AccountsViewHolder extends RecyclerView.ViewHolder {

        RowAccountBinding binding;

        /**
         * Constructs a new AccountsViewHolder.
         *
         * @param itemView The view of the item.
         */
        public AccountsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RowAccountBinding.bind(itemView);
        }
    }
}