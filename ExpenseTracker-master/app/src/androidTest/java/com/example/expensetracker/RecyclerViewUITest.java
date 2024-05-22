package com.example.expensetracker;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.expensetracker.R;
import com.example.expensetracker.views.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testDisplayDummyIncome() {
        // Dummy income data
        String category = "Salary";
        String account = "Bank";
        String date = "2024-05-22"; // Set the date according to your requirement
        String amount = "2000";

        // Set the transaction category
        Espresso.onView(ViewMatchers.withId(R.id.transactionCategory))
                .perform(ViewActions.replaceText(category));

        // Set the account label
        Espresso.onView(ViewMatchers.withId(R.id.accountLable))
                .perform(ViewActions.replaceText(account));

        // Set the transaction date
        Espresso.onView(ViewMatchers.withId(R.id.transactionDate))
                .perform(ViewActions.replaceText(date));

        // Set the amount
        Espresso.onView(ViewMatchers.withId(R.id.amount))
                .perform(ViewActions.replaceText(amount));

        // Perform checks
        Espresso.onView(ViewMatchers.withId(R.id.transactionCategory))
                .check(ViewAssertions.matches(ViewMatchers.withText(category)));

        Espresso.onView(ViewMatchers.withId(R.id.accountLable))
                .check(ViewAssertions.matches(ViewMatchers.withText(account)));

        Espresso.onView(ViewMatchers.withId(R.id.transactionDate))
                .check(ViewAssertions.matches(ViewMatchers.withText(date)));

        Espresso.onView(ViewMatchers.withId(R.id.amount))
                .check(ViewAssertions.matches(ViewMatchers.withText(amount)));

    }
}
