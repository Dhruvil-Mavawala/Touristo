package com.example.tourttavels;

import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tourttavels.Activities.login;
import com.example.tourttavels.Admin_fragment.Admin_homeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Admin extends AppCompatActivity {
    BottomNavigationView btm;
    FrameLayout frame;
    private static final int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 1; // Request code for device credentials
    private int failedAttempts = 0; // Counter for failed attempts
    private static final int MAX_FAILED_ATTEMPTS = 3; // Maximum allowed attempts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);

        // Prompt for device password
        showPasswordDialog();

        frame = findViewById(R.id.frame);
        btm = findViewById(R.id.botnav);

        // Load the default fragment
        addFragment(new Admin_homeFragment());

        btm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_admin) {
                    addFragment(new Admin_homeFragment());
                } else if (item.getItemId() == R.id.Logout) {
                    Intent intent = new Intent(getApplicationContext(), login.class);
                    startActivity(intent);
                    finish(); // Finish the current activity
                } else {
                    addFragment(new Admin_homeFragment());
                }
                return true;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showPasswordDialog() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        if (keyguardManager != null && keyguardManager.isKeyguardSecure()) {
            Intent intent = keyguardManager.createConfirmDeviceCredentialIntent("Authentication Required", "Please enter your device credentials.");
            if (intent != null) {
                startActivityForResult(intent, REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS);
            } else {
                Toast.makeText(this, "No security set on this device", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No security set on this device", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            if (resultCode == RESULT_OK) {
                // Password was entered correctly
                Toast.makeText(this, "Password Correct!", Toast.LENGTH_SHORT).show();
                // Proceed to the main activity or load main content
                // e.g., addFragment(new MainFragment());
            } else {
                // Password was not entered correctly
                failedAttempts++; // Increment the failed attempts counter
                if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
                    Toast.makeText(this, "Too many failed attempts. Exiting...", Toast.LENGTH_SHORT).show();
                    finish(); // Close the current activity
                } else {
                    Toast.makeText(this, "Authentication Failed. Attempts left: " + (MAX_FAILED_ATTEMPTS - failedAttempts), Toast.LENGTH_SHORT).show();
                    showPasswordDialog(); // Prompt for password again
                }
            }
        }
    }

    public void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, fragment, null);
        fragmentTransaction.commit();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, null);
        fragmentTransaction.commit();
    }
}
