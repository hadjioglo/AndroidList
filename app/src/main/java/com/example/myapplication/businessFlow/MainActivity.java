package com.example.myapplication.businessFlow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.singleton.WorkoutDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * From Youtube
 * <a href="https://www.youtube.com/watch?v=i9mkAoZ8FNk">...</a>
 **/
public class MainActivity extends AppCompatActivity {

    private ArrayList<String> workouts; //holds the list of workouts
    private ArrayAdapter<String> workoutsAdapter; //this Adapter populates the ListView workoutList
    private ListView workoutsList;  // list that will be displayed in UI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        workoutsList = findViewById(R.id.workoutsList); //display on UI list of existing workouts
        ImageButton buttonAddWorkout = findViewById(R.id.addWorkoutButton);//display on UI button

//        when you create this ArrayAdapter and set it to a ListView or Spinner,
//        it will display each item from the workouts array using the simple layout provided by android.R.layout.simple_list_item_1.
//        Each item will be represented by a single line of text in the UI.
        workouts = new ArrayList<>();
        workoutsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workouts);//This is the array containing the data (in this case, String data) that the ArrayAdapter will adapt and display within the ListView. Each item in the array will correspond to a single item in the ListView.
        workoutsList.setAdapter(workoutsAdapter);//This means that the workoutsAdapter will be responsible for providing the data from the workouts array to be displayed in the workoutsList.

//        this method adds workout and refreshes the workout list, after hitting enter on keyboard
        addWorkoutDisplay();

//        Set onClickListener for adding workout
        buttonAddWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddWorkoutActivity.class);
                startActivity(intent);
            }
        });

//        deleting  workouts
        //implement method
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update list of workouts here
        List<String> workouts = WorkoutDataSource.getInstance().getWorkouts();
        // Update your adapter with the new list of workouts
        workoutsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workouts);//This is the array containing the data (in this case, String data) that the ArrayAdapter will adapt and display within the ListView. Each item in the array will correspond to a single item in the ListView.
        workoutsList.setAdapter(workoutsAdapter);//This means that the workoutsAdapter will be responsible for providing the data from the workouts array to be displayed in the workoutsList.
    }

    private void addWorkoutDisplay() {
        TextView addWorkoutInput = findViewById(R.id.addNewWorkout);
        String addWorkoutText = addWorkoutInput.getText().toString();

        if (!(addWorkoutText.isEmpty())) {
            workoutsAdapter.add(addWorkoutText);
            addWorkoutInput.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "Add new prep, Bro", Toast.LENGTH_LONG).show();
        }

        addWorkoutInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addWorkout();
                    return true;
                }
                return false;
            }
        });
    }

    private void addWorkout() {
        TextView addWorkoutInput = findViewById(R.id.addNewWorkout);
        String addWorkoutText = addWorkoutInput.getText().toString();

        if (!(addWorkoutText.isEmpty())) {
            workoutsAdapter.add(addWorkoutText);
            workoutsAdapter.notifyDataSetChanged();
            addWorkoutInput.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "Add new prep, Bro", Toast.LENGTH_LONG).show();
        }

        // Hide the keyboard after hit enter in add workout input field
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(addWorkoutInput.getWindowToken(), 0);
    }

}
