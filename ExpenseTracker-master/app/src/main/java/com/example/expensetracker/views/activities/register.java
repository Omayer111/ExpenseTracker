package com.example.expensetracker.views.activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expensetracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
/**
 * Activity for user registration.
 */
public class register extends AppCompatActivity {

    private EditText editTextRegisterName, editTextRegisterEmail, editTextRegisterDob, editTextRegisterMobile, editTextRegisterPwd, editTextRegisterConfirmPwd;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonGenderSelected;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_register);

        editTextRegisterName = findViewById(R.id.editText_register_full_name);
        editTextRegisterEmail = findViewById(R.id.editText_register_email);
        editTextRegisterDob = findViewById(R.id.editText_register_dob);
        editTextRegisterMobile = findViewById(R.id.editText_register_mobile);
        editTextRegisterPwd = findViewById(R.id.editText_register_password);
        radioGroupGender = findViewById(R.id.radio_group_register_gender);
        radioGroupGender.clearCheck();

        // Set up DatePickerDialog for DOB
        editTextRegisterDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        Button buttonRegister = findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextRegisterName.getText().toString().trim();
                String email = editTextRegisterEmail.getText().toString().trim();
                String dob = editTextRegisterDob.getText().toString().trim();
                String mobile = editTextRegisterMobile.getText().toString().trim();
                String password = editTextRegisterPwd.getText().toString().trim();

                int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
                radioButtonGenderSelected = findViewById(selectedGenderId);

                if (TextUtils.isEmpty(name)) {
                    editTextRegisterName.setError("Name is required");
                    return;
                } else if (TextUtils.isEmpty(email)) {
                    editTextRegisterEmail.setError("Email is required");
                    return;
                } else if (TextUtils.isEmpty(dob)) {
                    editTextRegisterDob.setError("Date of Birth is required");
                    return;
                } else if (TextUtils.isEmpty(mobile)) {
                    editTextRegisterMobile.setError("Mobile is required");
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    editTextRegisterPwd.setError("Password is required");
                    return;
                } else if (selectedGenderId == -1) {
                    Toast.makeText(register.this, "Please Select Your Gender", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String gender = radioButtonGenderSelected.getText().toString();
                    checkIfEmailExistsAndRegister(name, email, dob, mobile, password, gender);
                }
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                register.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                        editTextRegisterDob.setText(format.format(calendar.getTime()));
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void checkIfEmailExistsAndRegister(String name, String email, String dob, String mobile, String password, String gender) {
        FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                if (task.isSuccessful()) {
                    SignInMethodQueryResult result = task.getResult();
                    Log.d(TAG, "Sign-in methods: " + result.getSignInMethods());
                    if (result != null && result.getSignInMethods() != null && !result.getSignInMethods().isEmpty()) {
                        // Email is already registered
                        Toast.makeText(register.this, "Email already exists. Please enter a new email.", Toast.LENGTH_SHORT).show();
                    } else {
                        // Email is not registered, proceed with registration
                        registerUser(name, email, dob, mobile, password, gender);
                    }
                } else {
                    // Error checking email existence
                    Log.e(TAG, "Error checking email existence", task.getException());
                    Toast.makeText(register.this, "Error checking email existence", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


    private void registerUser(String name, String email, String dob, String mobile, String password, String gender) {
        Toast.makeText(register.this, "Registering User", Toast.LENGTH_LONG).show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    ReadWriteUserDetails readWriteUserDetails = new ReadWriteUserDetails(name, email, dob, mobile, password);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                    databaseReference.child(user.getUid()).setValue(readWriteUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(register.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(register.this, login.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(register.this, "User Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
