package com.example.myapplication.singleton;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDataSource {

    private static final WorkoutDataSource instance = new WorkoutDataSource();
    private List<String> workouts = new ArrayList<>();

    private WorkoutDataSource() {}

    public static WorkoutDataSource getInstance() {
        return instance;
    }

    public List<String> getWorkouts() {
        return workouts;
    }

    public void addWorkout(String workout) {
        workouts.add(workout);
    }
}
