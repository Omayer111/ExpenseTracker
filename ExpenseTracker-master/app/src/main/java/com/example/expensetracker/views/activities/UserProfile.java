package com.example.expensetracker.views.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expensetracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    private TextView name, email, dob, mobile, password;
    private String dname,demail,ddob,dmobile,dpassword;
    private ImageView imageView;

    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private ValueEventListener userListener;
    private User currentUser; // Declare a global variable to store the current user data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.textView_show_full_name);
        email = findViewById(R.id.textView_show_email);
        dob = findViewById(R.id.textView_show_dob);
        mobile = findViewById(R.id.textView_show_mobile);
        password = findViewById(R.id.textView_show_password);

        // Get reference to "Users" node in the database


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            // Get the UID of the current user


            // Get reference to the node for the specific user using their UID
            String userID = firebaseUser.getUid();
            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Users").child(userID);
            referenceProfile.addListenerForSingleValueEvent(new ValueEventListener()  {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        ReadWriteUserDetails readWriteUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                        if (readWriteUserDetails != null) {
                            dname = readWriteUserDetails.name;
                            demail = readWriteUserDetails.email;
                            ddob = readWriteUserDetails.dob;
                            dmobile = readWriteUserDetails.mobile;
                            dpassword = readWriteUserDetails.password;

                            name.setText(dname);
                            email.setText(demail);
                            dob.setText(ddob);
                            mobile.setText(dmobile);
                            password.setText(dpassword);
                        } else {
                            Toast.makeText(UserProfile.this, "User data is null", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(UserProfile.this, "No data found for this user", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(UserProfile.this, "sorry didnt' work", Toast.LENGTH_SHORT).show();
                }
            });
        }}}