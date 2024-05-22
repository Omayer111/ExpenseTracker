package com.example.expensetracker;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.expensetracker.views.activities.auth_first_page;

import org.junit.Rule;
import org.junit.Test;

public class UiTestProfile {
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 820a71f6f288af9870cc926d62deed53b6563931
