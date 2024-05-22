package com.example.expensetracker;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.util.Log;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.expensetracker.utils.Helper;
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

//    @Before
//    public void setUp() {
//        // Set initial date to April 2, 2024
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2024, Calendar.APRIL, 2);
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
//        String initialDate = dateFormat.format(calendar.getTime());
//
//        // Set the initial date on the TextView
//        ActivityScenario<MainActivity> scenario = activityScenarioRule.getScenario();
//        scenario.onActivity(activity -> {
//            TextView currentDate = activity.findViewById(R.id.currentDate);
//            currentDate.setText(initialDate);
//        });
//    }

    @Test
    public void testDateUpdate() {
        // Simulate click on the next date button
        Espresso.onView(withId(R.id.nextDateBtn)).perform(ViewActions.click());
        try {
            Thread.sleep(1000); // 1 second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Expected date after adding one day
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.MAY, 23);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
        String expectedDate = dateFormat.format(calendar.getTime());

        // Check if the TextView displaying the date is updated
        Espresso.onView(withId(R.id.currentDate))
                .check(ViewAssertions.matches(withText(expectedDate)));
        try {
            Thread.sleep(1000); // 1 second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testNextDateButton() {
        // Set the initial date text
        //onView(withId(R.id.currentDate)).perform(replaceText("May 18, 2024"));
        //onView(withId(R.id.currentDate)).check(matches(withText("22 May, 2024")));

        // Click the next date button
        // Perform click on nextDateBtn
        try {
            Thread.sleep(1000); // 1 second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.nextDateBtn)).perform(click());
      //  testDateUpdate();

// Add a delay to ensure the date is updated
        try {
            Thread.sleep(1000); // 1 second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

// Verify the date is updated to the next day (May 19, 2024)
        onView(withId(R.id.currentDate)).check(matches(withText("22 May, 2024")));

// Add debug log to check if the button is clickable
        Log.d("Test", "Is nextDateBtn clickable: " + onView(withId(R.id.nextDateBtn)).check(matches(isClickable())));

    }

}
