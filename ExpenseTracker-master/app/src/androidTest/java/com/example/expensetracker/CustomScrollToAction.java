package com.example.expensetracker;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import android.view.View;
import org.hamcrest.Matcher;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;

public class CustomScrollToAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return isAssignableFrom(View.class);
    }

    @Override
    public String getDescription() {
        return "Custom scroll to view";
    }

    @Override
    public void perform(UiController uiController, View view) {
        view.getParent().requestChildFocus(view, view);
        uiController.loopMainThreadUntilIdle();
    }
}
