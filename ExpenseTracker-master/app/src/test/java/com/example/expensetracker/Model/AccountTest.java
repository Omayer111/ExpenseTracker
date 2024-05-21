package com.example.expensetracker.Model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class AccountTest extends TestCase {

    private Account account;

    @Before
    public void setUp() {
        // Initialize the Account object with test data
        account = new Account(1500.0, "Savings Account");
    }

    @Test
    public void testGetAccountAmount() {
        assertEquals(1500.0, account.getAccountAmount(), 0.001);
    }

    @Test
    public void testSetAccountAmount() {
        account.setAccountAmount(2000.0);
        assertEquals(2000.0, account.getAccountAmount(), 0.001);
    }

    @Test
    public void testGetAccountName() {
        assertEquals("Savings Account", account.getAccountName());
    }

    @Test
    public void testSetAccountName() {
        account.setAccountName("Checking Account");
        assertEquals("Checking Account", account.getAccountName());
    }
}