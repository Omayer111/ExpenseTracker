package com.example.expensetracker.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Helper class provides utility methods for formatting dates.
 */
public class Helper {

    /**
     * Formats the given date into a string representation with the format "dd MMMM, yyyy".
     *
     * @param date The date to be formatted.
     * @return A string representation of the formatted date.
     */
    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
        return dateFormat.format(date);
    }

    /**
     * Formats the given date into a string representation with the format "MMMM, yyyy".
     *
     * @param date The date to be formatted.
     * @return A string representation of the formatted date.
     */
    public static String formatDateByMonth(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM, yyyy");
        return dateFormat.format(date);
    }
}