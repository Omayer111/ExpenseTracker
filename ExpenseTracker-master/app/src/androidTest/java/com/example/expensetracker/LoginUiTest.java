package com.example.expensetracker;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.expensetracker.views.activities.auth_first_page;
import com.example.expensetracker.views.activities.login;
import com.example.expensetracker.views.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginUiTest {

    @Rule
    public ActivityScenarioRule<login> activityScenarioRule =
            new ActivityScenarioRule<>(login.class);

    @Test
    public void testLoginSuccess() {
        // Input email
        onView(withId(R.id.editText_login_email)).perform(replaceText("omayer@gmail.com"));
        // Input password
        onView(withId(R.id.editText_login_pwd)).perform(replaceText("omayer"));
        // Click login button
        onView(withId(R.id.button_login)).perform(click());

        // Check if MainActivity is displayed after login success
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }


}