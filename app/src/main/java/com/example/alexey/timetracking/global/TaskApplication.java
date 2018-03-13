package com.example.alexey.timetracking.global;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.alexey.timetracking.database.AppDatabase;

public class TaskApplication extends Application {

    private static TaskApplication instance;

    public static TaskApplication getInstance() {
        return instance;
    }

    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public AppDatabase getDatabase() {
        return appDatabase;
    }
}
