package com.example.expensetracker.Model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest extends TestCase {
    private Category category;

    @Before
    public void setUp() {
        // Initialize the Category object with test data
        category = new Category("Groceries", 123, 456789);
    }

    @Test
    public void testGetCategoryName() {
        assertEquals("Groceries", category.getCategoryName());
    }

    @Test
    public void testSetCategoryName() {
        category.setCategoryName("Utilities");
        assertEquals("Utilities", category.getCategoryName());
    }

    @Test
    public void testGetCategoryImage() {
        assertEquals(123, category.getCategoryImage());
    }

    @Test
    public void testSetCategoryImage() {
        category.setCategoryImage(456);
        assertEquals(456, category.getCategoryImage());
    }

    @Test
    public void testGetCategoryColor() {
        assertEquals(456789, category.getCategoryColor());
    }

    @Test
    public void testSetCategoryColor() {
        category.setCategoryColor(987654);
        assertEquals(987654, category.getCategoryColor());
    }
}