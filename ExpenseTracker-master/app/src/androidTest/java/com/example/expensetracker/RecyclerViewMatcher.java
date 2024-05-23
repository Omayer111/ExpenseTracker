package com.example.expensetracker;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class RecyclerViewMatcher {
    private final int recyclerViewId;

    public RecyclerViewMatcher(int recyclerViewId) {
        this.recyclerViewId = recyclerViewId;
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    public Matcher<View> atPosition(final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position: " + position);
            }

            @Override
            protected boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof RecyclerView)) {
                    return false;
                }
                RecyclerView recyclerView = (RecyclerView) view.getParent();
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                return viewHolder != null && viewHolder.itemView == view;
            }
        };
    }

}
