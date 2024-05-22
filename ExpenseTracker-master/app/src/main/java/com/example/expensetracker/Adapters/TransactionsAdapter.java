package com.example.expensetracker.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.Model.Category;
import com.example.expensetracker.Model.Transaction;
import com.example.expensetracker.Model.TransactionFactory;
import com.example.expensetracker.R;
import com.example.expensetracker.databinding.RowTransactionsBinding;
import com.example.expensetracker.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * TransactionsAdapter is a RecyclerView.Adapter that displays a list of Transaction objects.
 */
public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder> {

    @NonNull
    Context context;
    ArrayList<Transaction> transactions;

    /**
     * Constructs a new TransactionsAdapter.
     *
     * @param context The context in which the adapter is running.
     * @param transactions The list of Transaction objects to be displayed.
     */
    public TransactionsAdapter(Context context, ArrayList<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    /**
     * Called when RecyclerView needs a new {@link TransactionViewHolder} of the given type to represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new TransactionViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public TransactionsAdapter.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.row_transactions, parent, false));
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull TransactionsAdapter.TransactionViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.binding.amount.setText(String.valueOf(transaction.getAmount()));
        holder.binding.accountLable.setText(transaction.getAccount());
        holder.binding.transactionDate.setText(transaction.getNote());
        holder.binding.transactionCategory.setText(transaction.getCategory());
        Category transactionCategory = Constants.getCategoryDetails(transaction.getCategory());
        holder.binding.transactionIcon.setImageResource(transactionCategory.getCategoryImage());
        holder.binding.transactionIcon.setBackgroundTintList(context.getColorStateList(transactionCategory.getCategoryColor()));
        holder.binding.accountLable.setBackgroundTintList(context.getColorStateList(Constants.getAccountsColor(transaction.getAccount())));

        if (transaction.getType().equals(Constants.INCOME)) {
            holder.binding.amount.setTextColor(R.color.greenColor);
        } else if (transaction.getType().equals(Constants.EXPENSE)) {
            holder.binding.amount.setTextColor(R.color.redColor);
        }
        // Bind other data accordingly
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return transactions.size();
    }

    /**
     * Removes an item from the list at the specified position.
     *
     * @param position The position of the item to be removed.
     */
    public void removeItem(int position) {
        transactions.remove(position); // Remove item from the list
        // Notify the adapter that item has been removed
        notifyItemRemoved(position);
    }

    /**
     * Removes a transaction from Firebase.
     *
     * @param transaction The transaction to be removed.
     */
    public void removeFromFirebase(Transaction transaction) {
        long idd = transaction.getId();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            // User is not authenticated, handle accordingly (e.g., show login screen)
            return;
        }

        String userID = firebaseUser.getUid();
        DatabaseReference transactionsRef = FirebaseDatabase.getInstance().getReference("Users")
                .child(userID)
                .child("transactions");

        transactionsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot transactionSnapshot : snapshot.getChildren()) {
                    String type = transactionSnapshot.child("type").getValue(String.class);
                    String category = transactionSnapshot.child("category").getValue(String.class);
                    String account = transactionSnapshot.child("account").getValue(String.class);
                    String note = transactionSnapshot.child("note").getValue(String.class);
                    String dateString = transactionSnapshot.child("date").getValue(String.class);
                    double amount = transactionSnapshot.child("amount").getValue(Double.class);
                    long id = transactionSnapshot.child("id").getValue(Long.class);

                    // Create Transaction object
                    TransactionFactory transactionFactory = new TransactionFactory();
                    String date = dateString; // Assuming dateString is a valid date string
                    Transaction transaction = transactionFactory.getTransaction(type, category, account, note, date, amount, id);
                    if (id == idd) {
                        transactionSnapshot.getRef().removeValue().addOnSuccessListener(aVoid -> {
                            // Success handling
                        }).addOnFailureListener(e -> {
                            // Failure handling
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle data retrieval cancellation or error
                Log.e("MainActivity", "Error fetching transactions: " + error.getMessage());
            }
        });
    }

    /**
     * ViewHolder class for Transaction items.
     */
    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        RowTransactionsBinding binding;

        /**
         * Constructs a new TransactionViewHolder.
         *
         * @param itemView The view of the item.
         */
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RowTransactionsBinding.bind(itemView);
            itemView.setOnLongClickListener(v -> {
                // Handle long click here
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Transaction clickedTransaction = transactions.get(position);
                    removeFromFirebase(clickedTransaction);
                    // Example: Perform an action on the clicked transaction
                    Toast.makeText(itemView.getContext(), "Deleted Transaction: " + clickedTransaction.getType(), Toast.LENGTH_SHORT).show();
                    removeItem(position);
                }
                return true; // Consume the long click event
            });
        }
    }
}
