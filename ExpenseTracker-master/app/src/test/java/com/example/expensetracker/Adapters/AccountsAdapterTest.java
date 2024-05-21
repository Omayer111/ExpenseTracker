package com.example.expensetracker.Adapters;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.test.core.app.ApplicationProvider;

import com.example.expensetracker.Model.Account;
import com.example.expensetracker.databinding.RowAccountBinding;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28}) // Use appropriate SDK level
public class AccountsAdapterTest {

    private Context context;
    private AccountsAdapter adapter;
    private ArrayList<Account> accounts;
    private AccountsAdapter.AccountsClickListener mockListener;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        accounts = new ArrayList<>();
        accounts.add(new Account(0, "Cash"));
        accounts.add(new Account(0, "Bank"));
        mockListener = mock(AccountsAdapter.AccountsClickListener.class);
        adapter = new AccountsAdapter(context, accounts, mockListener);
    }

    @Test
    public void testGetItemCount() {
        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void testOnBindViewHolder() {
        // Create a ViewGroup to act as the parent
        ViewGroup parent = Robolectric.buildActivity(TestActivity.class).get().findViewById(android.R.id.content);
        LayoutInflater inflater = LayoutInflater.from(context);
        RowAccountBinding binding = RowAccountBinding.inflate(inflater, parent, false);

        // Create a view holder
        AccountsAdapter.AccountsViewHolder viewHolder = adapter.new AccountsViewHolder(binding.getRoot());

        // Bind the view holder
        adapter.onBindViewHolder(viewHolder, 0);

        // Verify the account name is correctly set
        assertEquals("Cash", viewHolder.binding.accountName.getText().toString());
    }

    @Test
    public void testOnAccountSelected() {
        // Create a ViewGroup to act as the parent
        ViewGroup parent = Robolectric.buildActivity(TestActivity.class).get().findViewById(android.R.id.content);
        LayoutInflater inflater = LayoutInflater.from(context);
        RowAccountBinding binding = RowAccountBinding.inflate(inflater, parent, false);

        // Create a view holder
        AccountsAdapter.AccountsViewHolder viewHolder = adapter.new AccountsViewHolder(binding.getRoot());

        // Bind the view holder
        adapter.onBindViewHolder(viewHolder, 0);

        // Perform click
        viewHolder.itemView.performClick();

        // Capture the argument passed to the listener
        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        verify(mockListener).onAccountSelected(captor.capture());

        // Verify the correct account was passed
        assertEquals("Cash", captor.getValue().getAccountName());
    }

    // Dummy activity for parent ViewGroup
    public static class TestActivity extends androidx.appcompat.app.AppCompatActivity {}
}
