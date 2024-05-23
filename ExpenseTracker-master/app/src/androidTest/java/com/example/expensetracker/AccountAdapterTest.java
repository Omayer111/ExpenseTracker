package com.example.expensetracker;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.example.expensetracker.RecyclerViewMatcher.withRecyclerView;
import static org.hamcrest.Matchers.equalTo;

import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.expensetracker.Adapters.TransactionsAdapter;
import com.example.expensetracker.Model.Income;
import com.example.expensetracker.Model.Transaction;
import com.example.expensetracker.R;
import com.example.expensetracker.databinding.ActivityMainBinding;
import com.example.expensetracker.views.activities.MainActivity;
import com.example.expensetracker.views.activities.auth_first_page;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class AccountAdapterTest {
    ArrayList<Transaction> transactions = new ArrayList<>();
    public ActivityMainBinding binding;
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);



    @Test
    public void testAccountSelection() {
        ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.floatingActionButton)).perform(click());

        onView(withId(R.id.income)).perform(click());
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(equalTo(DatePicker.class.getName()))).perform(setDate(2024, 5, 21));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.amount)).perform(typeText("500"), closeSoftKeyboard());

        onView(withId(R.id.category)).perform(click());
        onView(withRecyclerView(R.id.recyclerView).atPosition(0)).perform(click());
        onView(withId(R.id.category)).check(matches(withText("Salary")));

        onView(withId(R.id.account)).perform(click());
        onView(withRecyclerView(R.id.recyclerView).atPosition(0)).perform(click());
        onView(withId(R.id.account)).check(matches(withText("Cash")));

        onView(withId(R.id.note)).perform(typeText("xyz"), closeSoftKeyboard());
        onView(withId(R.id.saveTransactionBtn)).perform(scrollTo(), click());
    }







}
