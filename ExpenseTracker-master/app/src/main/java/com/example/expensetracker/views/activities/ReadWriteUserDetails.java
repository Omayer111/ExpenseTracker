package com.example.expensetracker.views.activities;

/**
 * Class representing user details for reading and writing operations.
 */
public class ReadWriteUserDetails {
    public String name, email, dob, mobile, password;

    /**
     * Default constructor.
     */
    public ReadWriteUserDetails() {
    }

    /**
     * Parameterized constructor to initialize user details.
     *
     * @param name     The name of the user.
     * @param email    The email of the user.
     * @param dob      The date of birth of the user.
     * @param mobile   The mobile number of the user.
     * @param password The password of the user.
     */
    public ReadWriteUserDetails(String name, String email, String dob, String mobile, String password) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.mobile = mobile;
        this.password = password;
    }
}
