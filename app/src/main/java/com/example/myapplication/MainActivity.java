package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

/**
 * From Youtube
 * https://www.youtube.com/watch?v=i9mkAoZ8FNk
 **/
public class MainActivity extends AppCompatActivity {

    private ImageButton addNewWorkoutButton;
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

        addNewWorkoutButton = findViewById(R.id.addWorkoutButton); //display on UI button
        workoutsList = findViewById(R.id.workoutsList); //display on UI list of existing workouts

//        this method executes another method displayAddedWorkout when user clicks on Add workout button
        addNewWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAddedWorkout(view);
            }
        });

        

//        when you create this ArrayAdapter and set it to a ListView or Spinner,
//        it will display each item from the workouts array using the simple layout provided by android.R.layout.simple_list_item_1.
//        Each item will be represented by a single line of text in the UI.
        workouts = new ArrayList<>();
        workoutsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workouts);//This is the array containing the data (in this case, String data) that the ArrayAdapter will adapt and display within the ListView. Each item in the array will correspond to a single item in the ListView.
        workoutsList.setAdapter(workoutsAdapter);//This means that the workoutsAdapter will be responsible for providing the data from the workouts array to be displayed in the workoutsList.


//        deleting  workouts
        setUpWorkoutsListViewListener();
    }

    private void displayAddedWorkout(View view) {
        TextView addWorkoutInput = findViewById(R.id.addNewWorkout);
        String addWorkoutText = addWorkoutInput.getText().toString();

        if (!(addWorkoutText.equals(""))) {
            workoutsAdapter.add(String.valueOf(addWorkoutText));
            addWorkoutInput.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "Add new prep, Bro", Toast.LENGTH_LONG).show();
        }

    }

    private void setUpWorkoutsListViewListener() {
//        we will use long press action. when user long press the item will be deleted from the list
        workoutsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Workout Removed", Toast.LENGTH_LONG).show();

//                removes position from the list
                workouts.remove(position);
//                refreshes the Adapter because workout was removed
                workoutsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
