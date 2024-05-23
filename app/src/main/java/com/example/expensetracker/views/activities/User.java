package com.example.expensetracker.views.activities;

/**
 * Represents a user entity with attributes such as name, email, date of birth, mobile number, and password.
 */
public class User {
    private String name;
    private String email;
    private String dob;
    private String mobile;
    private String password;

    /**
     * Default constructor required for Firebase.
     */
    public User() {
        // Default constructor required for Firebase
    }

    /**
     * Retrieves the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the date of birth of the user.
     *
     * @return The date of birth of the user.
     */
    public String getDob() {
        return dob;
    }

    /**
     * Sets the date of birth of the user.
     *
     * @param dob The date of birth of the user.
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * Retrieves the mobile number of the user.
     *
     * @return The mobile number of the user.
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the mobile number of the user.
     *
     * @param mobile The mobile number of the user.
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}