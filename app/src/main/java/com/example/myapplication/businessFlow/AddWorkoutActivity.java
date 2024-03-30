package com.example.myapplication.businessFlow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.singleton.WorkoutDataSource;

public class AddWorkoutActivity extends AppCompatActivity {

    EditText editTextNewWorkout;
    Button buttonAddWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_workout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextNewWorkout = findViewById(R.id.addWorkoutNewPage);
        buttonAddWorkout = findViewById(R.id.buttonAddWorkout);

        buttonAddWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWorkout();
            }
        });
    }

    private void addWorkout() {
        String newWorkoutName = editTextNewWorkout.getText().toString().trim();
        if (!newWorkoutName.isEmpty()) {
            WorkoutDataSource.getInstance().addWorkout(newWorkoutName);
            finish();
        } else {
            Toast.makeText(this, "Add new prep, Bro", Toast.LENGTH_SHORT).show();
        }
    }
}