package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.singleton.Workouts;

import java.util.ArrayList;

public class AddNewWorkout extends AppCompatActivity {

    private Button saveWorkoutButton;
    public ArrayAdapter<String> workoutsAdapter;
    private ArrayList<String> workouts;
    public ListView workoutsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_new_workout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        saveWorkoutButton = findViewById(R.id.buttonSaveNewWorkout);
        saveWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(v);
            }
        });

        workouts = new ArrayList<>();
        workoutsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workouts);
        workoutsList.setAdapter(workoutsAdapter);
    }

    private void addItem(View v) {
        EditText input = findViewById(R.id.nameYourPrep);
        String workoutNameText = input.getText().toString();

        if (!(workoutNameText.isEmpty())){
            workoutsAdapter.add(workoutNameText);
            input.setText("");
        }
        else {
            Toast.makeText(getApplicationContext(), "Add new prep, Bro", Toast.LENGTH_LONG).show();
        }
    }
}