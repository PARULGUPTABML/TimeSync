package com.example.time_sync1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ScheduleActivity extends AppCompatActivity {

    private Button addTaskButton;
    private TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        // Hide the status bar
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));

        // Initialize views
        initViews();
        
        // Setup today's date
        setupDate();
        
        // Setup tab layout
        setupTabLayout();
        
        // Setup bottom navigation
        setupBottomNavigation();
        
        // Setup add task button
        setupAddTaskButton();
    }

    private void initViews() {
        addTaskButton = findViewById(R.id.addTaskButton);
        dateText = findViewById(R.id.dateText);
    }
    
    private void setupDate() {
        // Format: 17 September
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM", Locale.getDefault());
        String formattedDate = dateFormat.format(Calendar.getInstance().getTime());
        dateText.setText(formattedDate);
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
                    // Intent intent = new Intent(ScheduleActivity.this, StatisticsActivity.class);
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
                // Navigate to home (current view)
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
    }
    
    private void setupAddTaskButton() {
        addTaskButton.setOnClickListener(v -> {
            navigateToAddTask();
        });
    }
    
    // Method to navigate to Add Task page
    private void navigateToAddTask() {
        Intent intent = new Intent(ScheduleActivity.this, AddTaskActivity.class);
        startActivity(intent);
    }
} 