package com.example.myapplication.singleton;

import com.example.myapplication.dto.Workout;

import java.util.ArrayList;

public class WorkoutsAdapter {

    private static WorkoutsAdapter instance;
    private ArrayList<Workout> itemsList = new ArrayList<>();

    private WorkoutsAdapter() {}

    public static WorkoutsAdapter getInstance() {
        if (instance == null) {
            instance = new WorkoutsAdapter();
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
