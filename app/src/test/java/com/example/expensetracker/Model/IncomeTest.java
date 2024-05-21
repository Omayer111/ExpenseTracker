package com.example.expensetracker.Model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class IncomeTest {

    private Income income;

    @Before
    public void setUp() {
        // Initialize the Income object with test data
        income = new Income("Salary", "Bank Account", "Monthly salary", "2024-05-21", 5000.0, 1);
    }

    @Test
    public void testGetType() {
        assertEquals("Income", income.getType());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Salary", income.getCategory());
    }

    @Test
    public void testGetAccount() {
        assertEquals("Bank Account", income.getAccount());
    }

    @Test
    public void testGetNote() {
        assertEquals("Monthly salary", income.getNote());
    }

    @Test
    public void testGetDate() {
        assertEquals("2024-05-21", income.getDate());
    }

    @Test
    public void testGetAmount() {
        assertEquals(5000.0, income.getAmount(), 0.001);
    }

    @Test
    public void testGetId() {
        assertEquals(1, income.getId());
    }
}
