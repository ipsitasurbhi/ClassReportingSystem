package com.example.classreportingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import androidx.annotation.NonNull;
//import android.util.Log;
//import com.google.firebase.FirebaseApp;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private TextView forgotPasswordText;
    //private FirebaseAuth mAuth;
    //private DatabaseReference mDatabase;
    private static final String TAG = "LoginActivity";
    private EditText loginId;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase
        //FirebaseApp.initializeApp(this);

        //mAuth = FirebaseAuth.getInstance();
        //mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize the fields
        loginId = findViewById(R.id.loginId);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);

        // Set onClickListener for Login Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For now, we will just navigate to the User Profile page
                Toast.makeText(LoginActivity.this, "Logging in", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, UserProfile.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for Forgot Password Text
        forgotPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        //testDatabaseQuery();
    }

    /*
    private void loginUser() {
        final String loginId = loginIdField.getText().toString().trim();
        final String password = passwordField.getText().toString().trim();

        // Reference to the users in the database
        //DatabaseReference usersRef = mDatabase.child("Users");

        // Look up the email associated with the loginId
        // usersRef.orderByChild("loginId").equalTo(Integer.parseInt(loginId)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get the email from the database
                    // String email = dataSnapshot.getChildren().iterator().next().child("email").getValue(String.class);

                    // Now use the email to sign in
                    // mAuth.signInWithEmailAndPassword(email, password)
                    //     .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
                        
                        ...
                } else {
                
                ...
            }
            
            ...
        });
        
        ...
    }
    */

    /*
    private void testDatabaseQuery() {
        DatabaseReference usersRef = mDatabase.child("Users");

        // Directly query the database for the loginId
        usersRef.orderByChild("loginId").equalTo(2039).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.d("TestDatabaseQuery", "Login ID found!");
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String email = userSnapshot.child("email").getValue(String.class);
                        Log.d("TestDatabaseQuery", "Found email: " + email);
                    }
                } else {
                    Log.d("TestDatabaseQuery", "Login ID not found.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("TestDatabaseQuery", "Database error: " + databaseError.getMessage());
            }
        });
    }
    */

    //private void createUser(String email, String password) {
        // mAuth.createUserWithEmailAndPassword(email, password)
        //     .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        //         @Override
        //         public void onComplete(@NonNull Task<AuthResult> task) {
        //             if (task.isSuccessful()) {
        //                 // Sign in success, update UI with the signed-in user's information
        //                 Log.d(TAG, "createUserWithEmail:success");
        //                 FirebaseUser user = mAuth.getCurrentUser();
        //                 updateUI(user);
        //             } else {
        //                 // If sign in fails, display a message to the user.
        //                 Log.w(TAG, "createUserWithEmail:failure", task.getException());
        //                 Toast.makeText(LoginActivity.this, "Authentication failed.",
        //                         Toast.LENGTH_SHORT).show();
        //                 updateUI(null);
        //             }
        //         }
        //     });
    }

    //private void updateUI(FirebaseUser user) {
        // Implement your UI update logic here
    //}
//}
