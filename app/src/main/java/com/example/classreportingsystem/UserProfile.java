package com.example.classreportingsystem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import androidx.annotation.NonNull;
import android.util.Log;

public class UserProfile extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    private ImageView profileImage;
    private EditText nameField, emailField, designationField, phoneField, dobField;
    private Button saveButton, reportButton;
    private Uri imageUri;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        String userId = getIntent().getStringExtra("USER_ID");
        if (userId != null) {
            loadUserProfile(userId);
        } else {
            Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void loadUserProfile(String userId) {
        mDatabase.child("users").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    if (snapshot != null && snapshot.exists()) {
                        // Update UI with user data
                        updateUI(snapshot);
                    } else {
                        Toast.makeText(UserProfile.this, "User data not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserProfile.this, "Error loading user data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUI(DataSnapshot snapshot) {
        String name = snapshot.child("name").getValue(String.class);
        String email = snapshot.child("email").getValue(String.class);
        // Update your UI elements with the user data
        // For example:
        // nameField.setText(name);
        // emailField.setText(email);
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            profileImage.setImageURI(imageUri);
        }
    }

    private void saveUserData() {
        // TODO: Save user data to database
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String designation = designationField.getText().toString();
        String phone = phoneField.getText().toString();
        String dob = dobField.getText().toString();

        // Here you would typically save this data to your database
        // For now, we'll just show a toast message
        Toast.makeText(this, "User data saved successfully", Toast.LENGTH_SHORT).show();
    }
}
