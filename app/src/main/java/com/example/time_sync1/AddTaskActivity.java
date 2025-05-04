package com.example.time_sync1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {

    private TextView classButton, examButton, labButton, assignmentButton, presentationButton;
    private CardView subjectSelection, dateSelection, timeSelection;
    private Button addTaskButton;
    private TextView dateText, timeText;
    private Calendar selectedDate = Calendar.getInstance();
    private String selectedCategory = "Class"; // Default category
    private String selectedSubject = "Biology"; // Default subject

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
        
        // Setup date and time pickers
        setupDateTimePickers();
        
        // Setup add task button
        setupAddTaskButton();
    }

    private void initViews() {
        classButton = findViewById(R.id.classButton);
        examButton = findViewById(R.id.examButton);
        labButton = findViewById(R.id.labButton);
        assignmentButton = findViewById(R.id.assignmentButton);
        presentationButton = findViewById(R.id.presentationButton);
        
        subjectSelection = findViewById(R.id.subjectSelection);
        dateSelection = findViewById(R.id.dateSelection);
        timeSelection = findViewById(R.id.timeSelection);
        
        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);
        
        addTaskButton = findViewById(R.id.addTaskButton);
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
        
        // Setup subject selection
        subjectSelection.setOnClickListener(v -> {
            // This would typically open a subject selection dialog
            // For now, we'll just show a toast
            Toast.makeText(AddTaskActivity.this, "Subject selection clicked", Toast.LENGTH_SHORT).show();
        });
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
    
    private void setupDateTimePickers() {
        // Format and display the current date and time
        updateDateDisplay();
        updateTimeDisplay();
        
        // Setup date picker
        dateSelection.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddTaskActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        // Update selected date
                        selectedDate.set(Calendar.YEAR, year);
                        selectedDate.set(Calendar.MONTH, month);
                        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        
                        // Update the display
                        updateDateDisplay();
                    },
                    selectedDate.get(Calendar.YEAR),
                    selectedDate.get(Calendar.MONTH),
                    selectedDate.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });
        
        // Setup time picker
        timeSelection.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    AddTaskActivity.this,
                    (view, hourOfDay, minute) -> {
                        // Update selected time
                        selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDate.set(Calendar.MINUTE, minute);
                        
                        // Update the display
                        updateTimeDisplay();
                    },
                    selectedDate.get(Calendar.HOUR_OF_DAY),
                    selectedDate.get(Calendar.MINUTE),
                    false
            );
            timePickerDialog.show();
        });
    }
    
    private void updateDateDisplay() {
        // Format: Fri 25, September, 2020
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd, MMMM, yyyy", Locale.getDefault());
        dateText.setText(dateFormat.format(selectedDate.getTime()));
    }
    
    private void updateTimeDisplay() {
        // Format: 09:30 AM
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        timeText.setText(timeFormat.format(selectedDate.getTime()));
    }
    
    private void setupAddTaskButton() {
        addTaskButton.setOnClickListener(v -> {
            // Save the task to database or SharedPreferences
            // For now, just show a toast and go back to the main screen
            Toast.makeText(AddTaskActivity.this, 
                    "Task added: " + selectedCategory + " - " + selectedSubject, 
                    Toast.LENGTH_SHORT).show();
                    
            // Return to the main activity
            Intent intent = new Intent(AddTaskActivity.this, ActivitiesActivity.class);
            startActivity(intent);
            finish();
        });
    }
} 