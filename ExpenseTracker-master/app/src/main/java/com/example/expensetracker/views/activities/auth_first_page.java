package com.example.expensetracker.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensetracker.R;

/**
 * The auth_first_page activity serves as the entry point for users who are not logged in.
 * It provides options for logging in or registering a new account.
 */
public class auth_first_page extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_first_page);

        // Set up click listener for the "Login" button
        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the login activity when the "Login" button is clicked
                Intent intent = new Intent(auth_first_page.this, login.class);
                startActivity(intent);
            }
        });

        // Set up click listener for the "Register" button
        Button buttonRegister = findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the register activity when the "Register" button is clicked
                Intent intent = new Intent(auth_first_page.this, register.class);
                startActivity(intent);
            }
        });
    }
}
