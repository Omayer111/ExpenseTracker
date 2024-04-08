package com.example.expensetracker.views.activities;


public class ReadWriteUserDetails {
    public String name, email, dob,mobile,password;

    public ReadWriteUserDetails() {
    }
    public ReadWriteUserDetails(String name, String email, String dob, String mobile, String password) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.mobile = mobile;
        this.password = password;
    }
}
