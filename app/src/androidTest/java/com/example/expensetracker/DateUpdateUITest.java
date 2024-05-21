package com.example.expensetracker;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.expensetracker.views.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@RunWith(AndroidJUnit4.class)
public class DateUpdateUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        // Set initial date to April 2, 2024
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 2);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
        String initialDate = dateFormat.format(calendar.getTime());

        // Set the initial date on the TextView
        ActivityScenario<MainActivity> scenario = activityScenarioRule.getScenario();
        scenario.onActivity(activity -> {
            TextView currentDate = activity.findViewById(R.id.currentDate);
            currentDate.setText(initialDate);
        });
    }

    @Test
    public void testDateUpdate() {
        // Simulate click on the next date button
        onView(withId(R.id.nextDateBtn)).perform(click());

        // Expected date after adding one day
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 3);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
        String expectedDate = dateFormat.format(calendar.getTime());

        // Check if the TextView displaying the date is updated
        onView(withId(R.id.currentDate)).check(matches(withText(expectedDate)));
    }
}
