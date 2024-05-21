package com.example.expensetracker.utils;

import static org.junit.Assert.*;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperTest {

    @Test
    public void testFormatDate() {
        // Test case 1: Format date with day, month, and year
        Date date1 = new Date(122, 4, 18); // 18th May 2022 (Note: Month is 0-based index)
        String expectedFormattedDate1 = "18 May, 2022";
        String formattedDate1 = Helper.formatDate(date1);
        assertEquals(expectedFormattedDate1, formattedDate1);

        // Test case 2: Format date with different day, month, and year
        Date date2 = new Date(125, 10, 10); // 10th November 2025
        String expectedFormattedDate2 = "10 November, 2025";
        String formattedDate2 = Helper.formatDate(date2);
        assertEquals(expectedFormattedDate2, formattedDate2);
    }

    @Test
    public void testFormatDateByMonth() {
        // Test case 1: Format date with month and year
        Date date1 = new Date(122, 4, 18); // 18th May 2022 (Note: Month is 0-based index)
        String expectedFormattedDate1 = "May, 2022";
        String formattedDate1 = Helper.formatDateByMonth(date1);
        assertEquals(expectedFormattedDate1, formattedDate1);

        // Test case 2: Format date with different month and year
        Date date2 = new Date(125, 10, 10); // 10th November 2025
        String expectedFormattedDate2 = "November, 2025";
        String formattedDate2 = Helper.formatDateByMonth(date2);
        assertEquals(expectedFormattedDate2, formattedDate2);
    }
}
