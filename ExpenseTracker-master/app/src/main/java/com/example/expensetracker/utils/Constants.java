package com.example.expensetracker.utils;

import com.example.expensetracker.Model.Category;
import com.example.expensetracker.R;

import java.util.ArrayList;

/**
 * The Constants class holds static constants and methods used throughout the application.
 */
public class Constants {

    /** Transaction types */
    public static String INCOME = "Income";
    public static String EXPENSE = "Expense";

    /** List of predefined categories */
    public static ArrayList<Category> categories;

    /** Tab indices */
    public static int DAILY = 0;
    public static int MONTHLY = 1;
    public static int CALENDAR = 2;
    public static int SUMMARY = 3;
    public static int NOTES = 4;

    /** Currently selected tab index */
    public static int SELECTED_TAB = 0;

    /**
     * Initializes the list of predefined categories.
     */
    public static void setCategories() {
        categories = new ArrayList<>();
        categories.add(new Category("Salary", R.drawable.baseline_business_center_24, R.color.category1));
        categories.add(new Category("Bank", R.drawable.baseline_analytics_24, R.color.category2));
        categories.add(new Category("Loan", R.drawable.baseline_attach_money_24, R.color.category3));
        categories.add(new Category("Investment", R.drawable.baseline_checkroom_24, R.color.category4));
        categories.add(new Category("Rent", R.drawable.baseline_chair_24, R.color.category5));
        categories.add(new Category("Other", R.drawable.baseline_bar_chart_24, R.color.category6));
    }

    /**
     * Retrieves the details of a category based on its name.
     *
     * @param categoryName The name of the category.
     * @return The Category object corresponding to the given name, or null if not found.
     */
    public static Category getCategoryDetails(String categoryName) {
        for (Category cat : categories) {
            if (cat.getCategoryName().equals(categoryName)) {
                return cat;
            }
        }
        return null;
    }

    /**
     * Retrieves the color associated with a specific account.
     *
     * @param accountName The name of the account.
     * @return The color resource ID associated with the account.
     */
    public static int getAccountsColor(String accountName) {
        switch (accountName) {
            case "Bank":
                return R.color.greenColor;
            case "Cash":
                return R.color.black;
            case "Card":
                return R.color.orange;
            default:
                return R.color.gray;
        }
    }


}
