package com.example.expensetracker;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.expensetracker.views.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TransactionRecyclerViewTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testRecyclerViewItemContents() {
        // Wait for data to be loaded by checking if RecyclerView is displayed
        onView(withId(R.id.trasactionList))
                .check(matches(isDisplayed()));

        // Wait until the RecyclerView is populated
        activityRule.getScenario().onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.trasactionList);
            while (recyclerView.getAdapter() == null || recyclerView.getAdapter().getItemCount() == 0) {
                // Adding a small sleep to avoid busy-waiting
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.d("TransactionRecyclerViewTest", "RecyclerView is populated with items");
        });

        // Verify the Income, Expense, and Total text fields
        onView(withId(R.id.IncomeLbl))
                .check(matches(withText("0.0")));
        onView(withId(R.id.ExpenseLbl))
                .check(matches(withText("0.0")));
        onView(withId(R.id.TotalLbl))
                .check(matches(withText("0.0")));

        // Scroll to the first item in the RecyclerView and check its contents
        onView(withId(R.id.trasactionList))
                .perform(scrollToPosition(0))
                .check(matches(hasDescendant(withText("Business"))))
                .check(matches(hasDescendant(withText("Cash"))))
                .check(matches(hasDescendant(withText("2 May, 2024"))))
                .check(matches(hasDescendant(withText("1000"))));

        // Scroll to the second item in the RecyclerView and check its contents
        onView(withId(R.id.trasactionList))
                .perform(scrollToPosition(1))
                .check(matches(hasDescendant(withText("Salary"))))
                .check(matches(hasDescendant(withText("Bank"))))
                .check(matches(hasDescendant(withText("3 May, 2024"))))
                .check(matches(hasDescendant(withText("2000"))));
    }

    @Test
    public void testDateNavigation() {
        // Verify the initial date
        onView(withId(R.id.currentDate))
                .check(matches(withText("May 18,2024")));

        // Click the next date button and verify the date is updated
        onView(withId(R.id.nextDateBtn)).perform(click());
        onView(withId(R.id.currentDate))
                .check(matches(withText("May 19,2024")));

        // Click the previous date button twice and verify the date is updated
        onView(withId(R.id.PreviousDateBtn)).perform(click()).perform(click());
        onView(withId(R.id.currentDate))
                .check(matches(withText("May 17,2024")));
    }
}
