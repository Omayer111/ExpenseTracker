package com.example.expensetracker.views.activities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void testGetName() {
        user.setName("polok");
        assertEquals("polok", user.getName());
    }

    @Test
    public void testSetName() {
        user.setName("polok");
        assertEquals("polok", user.getName());
    }

    @Test
    public void testGetEmail() {
        user.setEmail("polok@gmail.com");
        assertEquals("polok@gmail.com", user.getEmail());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("polok@gmail.com");
        assertEquals("polok@gmail.com", user.getEmail());
    }

    @Test
    public void testGetDob() {
        user.setDob("01/01/1990");
        assertEquals("01/01/1990", user.getDob());
    }

    @Test
    public void testSetDob() {
        user.setDob("02/02/1992");
        assertEquals("02/02/1992", user.getDob());
    }

    @Test
    public void testGetMobile() {
        user.setMobile("017234567890");
        assertEquals("017234567890", user.getMobile());
    }

    @Test
    public void testSetMobile() {
        user.setMobile("01987654321");
        assertEquals("01987654321", user.getMobile());
    }

    @Test
    public void testGetPassword() {
        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newpassword123");
        assertEquals("newpassword123", user.getPassword());
    }
}
