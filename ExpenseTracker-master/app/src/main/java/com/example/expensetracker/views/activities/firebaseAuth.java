package com.example.expensetracker.views.activities;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * The firebaseAuth class provides a singleton instance of FirebaseAuth for authentication purposes.
 */
public class firebaseAuth {

    /** Singleton instance of FirebaseAuth */
    private static FirebaseAuth single_instance = null;

    // Private constructor restricted to this class itself to prevent instantiation
    private firebaseAuth() {
        // Initialization if needed
    }

    /**
     * Static method to create or retrieve the singleton instance of FirebaseAuth.
     *
     * @return The singleton instance of FirebaseAuth.
     */
    public static FirebaseAuth getInstance() {
        if (single_instance == null) {
            single_instance = FirebaseAuth.getInstance();
        }
        return single_instance;
    }

}
