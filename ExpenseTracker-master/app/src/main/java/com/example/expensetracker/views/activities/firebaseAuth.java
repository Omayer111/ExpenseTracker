package com.example.expensetracker.views.activities;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expensetracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class firebaseAuth {

    private static FirebaseAuth single_instance = null;

    // Private constructor restricted to this class itself to prevent instantiation
    private firebaseAuth() {
        // Initialization if needed
    }

    // Static method to create instance of Singleton class
    public static FirebaseAuth getInstance() {
        if (single_instance == null) {
            single_instance = FirebaseAuth.getInstance();
        }
        return single_instance;
    }

}
