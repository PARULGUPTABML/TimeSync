package com.example.time_sync1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Hide the status bar
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));

        // Setup tab layout with navigation
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    // Time Table tab selected - current view
                } else {
                    // Statistics tab selected - navigate to statistics
                    // Intent intent = new Intent(CategoriesActivity.this, StatisticsActivity.class);
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

        // Setup bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            
            if (itemId == R.id.nav_home) {
                // Navigate to home
                Intent intent = new Intent(CategoriesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
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

        // Setup click listeners for subject cards
        setupSubjectCards();
    }

    private void setupSubjectCards() {
        // Find all subject cards
        CardView mathematicsCard = findViewById(R.id.mathematicsCard);
        CardView chemistryCard = findViewById(R.id.chemistryCard);
        CardView literatureCard = findViewById(R.id.literatureCard);
        CardView biologyCard = findViewById(R.id.biologyCard);
        CardView physicsCard = findViewById(R.id.physicsCard);

        // Set click listeners for each card
        mathematicsCard.setOnClickListener(v -> {
            // Open Mathematics details
            // Intent intent = new Intent(CategoriesActivity.this, SubjectDetailActivity.class);
            // intent.putExtra("subject", "Mathematics");
            // startActivity(intent);
        });

        chemistryCard.setOnClickListener(v -> {
            // Open Chemistry details
            // Intent intent = new Intent(CategoriesActivity.this, SubjectDetailActivity.class);
            // intent.putExtra("subject", "Chemistry");
            // startActivity(intent);
        });

        literatureCard.setOnClickListener(v -> {
            // Open Literature details
            // Intent intent = new Intent(CategoriesActivity.this, SubjectDetailActivity.class);
            // intent.putExtra("subject", "Literature");
            // startActivity(intent);
        });

        biologyCard.setOnClickListener(v -> {
            // Open Biology details
            // Intent intent = new Intent(CategoriesActivity.this, SubjectDetailActivity.class);
            // intent.putExtra("subject", "Biology");
            // startActivity(intent);
        });

        physicsCard.setOnClickListener(v -> {
            // Open Physics details
            // Intent intent = new Intent(CategoriesActivity.this, SubjectDetailActivity.class);
            // intent.putExtra("subject", "Physics");
            // startActivity(intent);
        });
    }
    
    // Method to navigate to Add Task page
    private void navigateToAddTask() {
        Intent intent = new Intent(CategoriesActivity.this, AddTaskActivity.class);
        startActivity(intent);
    }
} 