package com.example.expensetracker.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TransactionFactoryTest {

    @Test
    public void testGetTransaction() {
        TransactionFactory factory = new TransactionFactory();

        // Test case 1: Income transaction
        String incomeType = "INCOME";
        String category = "Salary";
        String account = "Savings";
        String note = "Monthly salary";
        String date = "2024-05-18";
        double amount = 2000.0;
        long id = 12345;

        Transaction incomeTransaction = factory.getTransaction(incomeType, category, account, note, date, amount, id);
        assertTrue(incomeTransaction instanceof Income);
        assertEquals(category, incomeTransaction.getCategory());
        assertEquals(account, incomeTransaction.getAccount());
        assertEquals(note, incomeTransaction.getNote());
        assertEquals(date, incomeTransaction.getDate());
        assertEquals(amount, incomeTransaction.getAmount(), 0.0);
        assertEquals(id, incomeTransaction.getId());

        // Test case 2: Expense transaction
        String expenseType = "EXPENSE";
        category = "Food";
        account = "Checking";
        note = "Dinner";
        date = "2024-05-17";
        amount = 20.0;
        id = 54321;

        Transaction expenseTransaction = factory.getTransaction(expenseType, category, account, note, date, amount, id);
        assertTrue(expenseTransaction instanceof Expense);
        assertEquals(category, expenseTransaction.getCategory());
        assertEquals(account, expenseTransaction.getAccount());
        assertEquals(note, expenseTransaction.getNote());
        assertEquals(date, expenseTransaction.getDate());
        assertEquals(amount, expenseTransaction.getAmount(), 0.0);
        assertEquals(id, expenseTransaction.getId());

        // Test case 3: Null transaction type
        String nullType = null;
        Transaction nullTransaction = factory.getTransaction(nullType, category, account, note, date, amount, id);
        assertNull(nullTransaction);
    }
}
