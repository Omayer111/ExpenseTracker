package com.example.expensetracker.Model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class ExpenseTest extends TestCase {
    private Expense expense;

    @Before
    public void setUp() {
        // Initialize the Income object with test data
        expense = new Expense("Salary", "Bank Account", "Monthly salary", "2024-05-21", 5000.0, 1);
    }

    @Test
    public void testGetType() {
        assertEquals("Expense", expense.getType());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Salary", expense.getCategory());
    }

    @Test
    public void testGetAccount() {
        assertEquals("Bank Account", expense.getAccount());
    }

    @Test
    public void testGetNote() {
        assertEquals("Monthly salary", expense.getNote());
    }

    @Test
    public void testGetDate() {
        assertEquals("2024-05-21", expense.getDate());
    }

    @Test
    public void testGetAmount() {
        assertEquals(5000.0, expense.getAmount(), 0.001);
    }

    @Test
    public void testGetId() {
        assertEquals(1, expense.getId());
    }
}