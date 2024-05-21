package com.example.expensetracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.expensetracker.Model.Transaction;
import com.example.expensetracker.Model.Income;
import com.example.expensetracker.Model.Expense;
import com.example.expensetracker.views.activities.MainActivity;
import com.example.expensetracker.Adapters.TransactionsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Mock
    FirebaseAuth mockFirebaseAuth;

    @Mock
    FirebaseUser mockFirebaseUser;

    @Mock
    FirebaseDatabase mockFirebaseDatabase;

    @Mock
    DatabaseReference mockDatabaseReference;

    @Mock
    DataSnapshot mockDataSnapshot;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockFirebaseAuth.getCurrentUser()).thenReturn(mockFirebaseUser);
        when(mockFirebaseUser.getUid()).thenReturn("testUserID");
        when(mockFirebaseDatabase.getReference("Users")).thenReturn(mockDatabaseReference);
    }

    @Test
    public void testFetchTransactionsFromFirebase() {
        MainActivity mainActivity = activityRule.getActivity();

        // Create mock data for transactions
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Income("Salary", "Bank", "Monthly salary", "2023-05-21", 5000.0, 1L));
        transactions.add(new Expense("Groceries", "Cash", "Weekly groceries", "2023-05-21", 200.0, 2L));

        // Manually set the transaction data
        ArrayList<Transaction> fetchedTransactions = new ArrayList<>(transactions);

        mainActivity.runOnUiThread(() -> {
            // Update UI components
            double income = 0.0;
            double expense = 0.0;

            for (Transaction transaction : fetchedTransactions) {
                if (transaction.getType().equals("INCOME")) {
                    income += transaction.getAmount();
                } else if (transaction.getType().equals("EXPENSE")) {
                    expense += transaction.getAmount();
                }
            }
            double total = income - expense;

            mainActivity.IncomePrint.setText(String.valueOf(income));
            mainActivity.ExpensePrint.setText(String.valueOf(expense));
            mainActivity.TotalPrint.setText(String.valueOf(total));

            // Update RecyclerView
            RecyclerView recyclerView = mainActivity.binding.trasactionList;
            recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
            TransactionsAdapter adapter = new TransactionsAdapter(mainActivity, fetchedTransactions);
            recyclerView.setAdapter(adapter);

            // Verify RecyclerView
            assertNotNull(recyclerView.getAdapter());
            assertEquals(2, recyclerView.getAdapter().getItemCount());
        });
    }
    @Test
    public void testFetchTransactionsWithNoUser() {
        MainActivity mainActivity = activityRule.getActivity();
        when(mockFirebaseAuth.getCurrentUser()).thenReturn(null);

        mainActivity.fetchTransactionsFromFirebase("2023-05-21");

        // Verify that the method returns early if the user is not authenticated
        verify(mockDatabaseReference, never()).addListenerForSingleValueEvent(any(ValueEventListener.class));
    }
}
