package com.example.expensetracker;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.expensetracker.views.activities.UserProfile;
import com.example.expensetracker.views.activities.auth_first_page;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class UserProfileTest {

    @Rule
    public ActivityScenarioRule<UserProfile> activityScenarioRule =
            new ActivityScenarioRule<>(UserProfile.class);

    @Test
    public void userProfileIsDisplayedCorrectly() {
        // Check if the user profile data is displayed correctly
        onView(withId(R.id.textView_show_full_name)).check(matches(withText("polok")));
        onView(withId(R.id.textView_show_email)).check(matches(withText("polok@example.com")));
        onView(withId(R.id.textView_show_dob)).check(matches(withText("01/01/1990")));
        onView(withId(R.id.textView_show_mobile)).check(matches(withText("1234567890")));
        onView(withId(R.id.textView_show_password)).check(matches(withText("password123")));
    }

    @Test
    public void testRefresh() {
        // Test swipe to refresh functionality if applicable
        onView(withId(R.id.swipeContainer)).perform(swipeDown());
        // Add additional checks to verify the refreshed data
    }
}
