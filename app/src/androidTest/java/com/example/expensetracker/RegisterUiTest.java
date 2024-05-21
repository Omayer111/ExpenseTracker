package com.example.expensetracker;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.expensetracker.views.activities.register;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RegisterUiTest {

    @Rule
    public ActivityScenarioRule<register> activityScenarioRule =
            new ActivityScenarioRule<>(register.class);

    @Test
    public void testRegisterUser() {
        // Input name
        onView(withId(R.id.editText_register_full_name)).perform(replaceText("gor"));
        // Input email
        onView(withId(R.id.editText_register_email)).perform(replaceText("gor@gmail.com"));
        // Input date of birth
        onView(withId(R.id.editText_register_dob)).perform(replaceText("1/1/2000"));
        // Input mobile number
        onView(withId(R.id.editText_register_mobile)).perform(replaceText("3809283092483"));
        // Input password
        onView(withId(R.id.editText_register_password)).perform(replaceText("gorgorgor"));
        // Select gender (assuming the RadioButton for gender has id radio_male)
        onView(withId(R.id.radio_male)).perform(click());

        // Ensure the register button is visible and scroll to it if necessary
        onView(withId(R.id.button_register)).perform(scrollTo(), click());

        // Check if the login activity is displayed after successful registration
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }
}
