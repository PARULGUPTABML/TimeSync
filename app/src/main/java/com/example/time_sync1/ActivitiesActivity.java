package com.example.time_sync1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class ActivitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        // Hide the status bar
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));

        // Setup tab layout
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Switch between Time Table and Statistics
                if (tab.getPosition() == 0) {
                    // Time Table tab selected
                } else {
                    // Statistics tab selected
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Not needed for now
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Not needed for now
            }
        });

        // Setup bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            
            if (itemId == R.id.nav_home) {
                // Already on home
                return true;
            } else if (itemId == R.id.nav_calendar) {
                // Navigate to calendar
                return true;
            } else if (itemId == R.id.nav_add) {
                // Navigate to add task
                navigateToAddTask();
                return true;
            } else if (itemId == R.id.nav_messages) {
                // Navigate to messages
                return true;
            } else if (itemId == R.id.nav_profile) {
                // Navigate to profile
                return true;
            }
            
            return false;
        });
        
        // Add click listener to "Running Subjects" text to navigate to Categories
        TextView runningSubjectsTitle = findViewById(R.id.runningSubjectsTitle);
        if (runningSubjectsTitle != null) {
            runningSubjectsTitle.setOnClickListener(v -> {
                navigateToCategories();
            });
        }
    }
    
    // Method to navigate to Categories page
    private void navigateToCategories() {
        Intent intent = new Intent(ActivitiesActivity.this, CategoriesActivity.class);
        startActivity(intent);
    }
    
    // Method to navigate to Add Task page
    private void navigateToAddTask() {
        Intent intent = new Intent(ActivitiesActivity.this, AddTaskActivity.class);
        startActivity(intent);
    }
} 