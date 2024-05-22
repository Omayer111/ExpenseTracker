package com.example.expensetracker.utils;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import com.example.expensetracker.Model.Category;
import com.example.expensetracker.R;

public class ConstantsTest {

    @BeforeClass
    public static void setUp() {
        Constants.setCategories();
    }

    @Test
    public void testGetCategoryDetails_ExistingCategory() {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Salary", R.drawable.baseline_business_center_24, R.color.category1));
        categories.add(new Category("Bank", R.drawable.baseline_analytics_24, R.color.category2));

        Constants.categories = categories;

        String categoryName = "Bank";
        Category expectedCategory = categories.get(1);

        Category actualCategory = Constants.getCategoryDetails(categoryName);

        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    public void testGetAccountsColor() {
        // Test case 1: Existing account name
        String accountName1 = "Bank";
        int expectedColor1 = R.color.greenColor;
        int actualColor1 = Constants.getAccountsColor(accountName1);
        assertEquals(expectedColor1, actualColor1);

        // Test case 2: Non-existent account name
        String accountName2 = "Non-existent Account";
        int expectedColor2 = R.color.gray;
        int actualColor2 = Constants.getAccountsColor(accountName2);
        assertEquals(expectedColor2, actualColor2);
    }
}
