package com.example.time_sync1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class AddTaskActivity extends AppCompatActivity {

    private TextView classButton, examButton, labButton, assignmentButton, presentationButton;
    private String selectedCategory = "Class"; // Default category

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Hide the status bar
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));

        // Initialize views
        initViews();
        
        // Setup tab layout
        setupTabLayout();
        
        // Setup bottom navigation
        setupBottomNavigation();
        
        // Setup category selection
        setupCategorySelection();
    }

    private void initViews() {
        classButton = findViewById(R.id.classButton);
        examButton = findViewById(R.id.examButton);
        labButton = findViewById(R.id.labButton);
        assignmentButton = findViewById(R.id.assignmentButton);
        presentationButton = findViewById(R.id.presentationButton);
    }

    private void setupTabLayout() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Switch between Time Table and Statistics
                if (tab.getPosition() == 0) {
                    // Time Table tab selected - current view
                } else {
                    // Statistics tab selected
                    // Intent intent = new Intent(AddTaskActivity.this, StatisticsActivity.class);
                    // startActivity(intent);
                    // finish();
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
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            
            if (itemId == R.id.nav_home) {
                // Navigate to home
                Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.nav_calendar) {
                // Navigate to calendar
                return true;
            } else if (itemId == R.id.nav_add) {
                // Already on add screen
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
    }

    private void setupCategorySelection() {
        // Set initial selection (Class is selected by default)
        updateCategorySelection(classButton);
        
        // Setup click listeners for category buttons
        classButton.setOnClickListener(v -> updateCategorySelection(classButton));
        examButton.setOnClickListener(v -> updateCategorySelection(examButton));
        labButton.setOnClickListener(v -> updateCategorySelection(labButton));
        assignmentButton.setOnClickListener(v -> updateCategorySelection(assignmentButton));
        presentationButton.setOnClickListener(v -> updateCategorySelection(presentationButton));
    }

    private void updateCategorySelection(TextView selected) {
        // Reset all button elevations
        classButton.setElevation(2f);
        examButton.setElevation(2f);
        labButton.setElevation(2f);
        assignmentButton.setElevation(2f);
        presentationButton.setElevation(2f);
        
        // Highlight the selected button
        selected.setElevation(6f);
        
        // Store the selected category
        selectedCategory = selected.getText().toString();
    }
} 