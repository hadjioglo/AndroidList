package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
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
 *
 * **/
public class MainActivity extends AppCompatActivity {

    private ImageButton addNewWorkoutButton;
    public ListView workoutsList;


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

        addNewWorkoutButton = findViewById(R.id.addWorkoutButton);

//        this method points to the another page when user clicks on Add workout button
        addNewWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewWorkout.class);
                startActivity(intent);
                finish();
            }
        });

        workoutsList = findViewById(R.id.wokroutsList);

//        when you create this ArrayAdapter and set it to a ListView or Spinner,
//        it will display each item from the workouts array using the simple layout provided by android.R.layout.simple_list_item_1.
//        Each item will be represented by a single line of text in the UI.

        
//        deleting  workouts
        setUpWorkoutsListViewListener();
    }

    private void setUpWorkoutsListViewListener() {
//        we will use long press action. when user long press the item will be deleted from the list
        workoutsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Workout Removed", Toast.LENGTH_LONG).show();

////                removes position from the list
//                workouts.remove(position);
////                refreshes the Adapter because workout was removed
//                workoutsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
