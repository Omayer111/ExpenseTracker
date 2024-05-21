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

    private CountingIdlingResource countingIdlingResource = new CountingIdlingResource("Global");

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(countingIdlingResource);
        disableAnimations();
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(countingIdlingResource);
    }

    private void disableAnimations() {
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(
                "settings put global transition_animation_scale 0");
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(
                "settings put global window_animation_scale 0");
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(
                "settings put global animator_duration_scale 0");
    }

    @Test
    public void testAccountSelection() {
        // Launch the main activity
        ActivityScenario.launch(MainActivity.class);

        // Open the Add_Transaction_Fragment
        onView(withId(R.id.floatingActionButton)).perform(click());

        // Interact with views
        onView(withId(R.id.income)).perform(click());
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(equalTo(DatePicker.class.getName()))).perform(setDate(2024, 5, 21));
        onView(withId(android.R.id.button1)).perform(click());

        // Type amount
        onView(withId(R.id.amount)).perform(typeText("500"), closeSoftKeyboard());

        // Open category dialog and select first item
        onView(withId(R.id.category)).perform(click());
        onView(withRecyclerView(R.id.recyclerView).atPosition(0)).perform(click());
        onView(withId(R.id.category)).check(matches(withText("Salary")));

        // Open account dialog and select first item
        onView(withId(R.id.account)).perform(click());
        onView(withRecyclerView(R.id.recyclerView).atPosition(0)).perform(click());
        onView(withId(R.id.account)).check(matches(withText("Cash")));

        // Type note and save transaction
        onView(withId(R.id.note)).perform(typeText("xyz"), closeSoftKeyboard());
        onView(withId(R.id.saveTransactionBtn)).perform(scrollTo(), click());

        onView(isRoot()).perform(waitFor(1000)); // Wait for RecyclerView to update

        // Log the view hierarchy for debugging
        logViewHierarchy();

        // Check if the first item has the expected values
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToPosition(0));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.transactionCategory))
                .check(matches(withText("Bussiness")));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.accountLable))
                .check(matches(withText("Cash")));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.transactionDate))
                .check(matches(withText("21 May,2024")));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.amount))
                .check(matches(withText("1000")));
    }


    @Rule
    public ActivityScenarioRule<auth_first_page> activityScenarioRule =
            new ActivityScenarioRule<>(auth_first_page.class);

    @Test
    public void testRegisterButtonShowsRegisterActivity() {
        // Perform click on the register button
        onView(withId(R.id.button_register)).perform(click());

        // Verify that the register activity layout is displayed
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }


    private void logViewHierarchy() {
        try {
            ViewInteraction interaction = onView(withId(android.R.id.content));
            interaction.perform(new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return isRoot();
                }

                @Override
                public String getDescription() {
                    return "Logging view hierarchy";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        Log.d("ViewHierarchy", child.toString());
                    }
                }
            });
        } catch (NoMatchingViewException e) {
            Log.e("ViewHierarchy", "No matching view found", e);
        }
    }

    public static ViewAction waitFor(final long delay) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for " + delay + " milliseconds";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(delay);
            }
        };
    }
}
