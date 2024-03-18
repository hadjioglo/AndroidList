package com.example.myapplication.singleton;

import com.example.myapplication.dto.Workout;

import java.util.ArrayList;

public class Workouts {

    private static Workouts instance;
    private ArrayList<Workout> itemsList = new ArrayList<>();

    private Workouts() {}

    public static Workouts getInstance() {
        if (instance == null) {
            instance = new Workouts();
        }
        return instance;
    }

    public ArrayList<Workout> getItemsList() {
        return itemsList;
    }

    public void setItemsList(ArrayList<Workout> itemsList) {
        this.itemsList = itemsList;
    }
}
