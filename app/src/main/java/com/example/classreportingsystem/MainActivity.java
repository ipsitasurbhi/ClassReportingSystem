package com.example.classreportingsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent; // Import this to use Intent
import android.os.Bundle;
import android.view.View; // Import this to use View
import android.widget.Button; // Import this to use Button
//import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        //FirebaseApp.initializeApp(this);

        // Find the "Get Started" button by its ID from the XML
        Button getStartedButton = findViewById(R.id.button);

        // Set an OnClickListener on the button
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to LoginActivity (for activity_login.xml)
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}



