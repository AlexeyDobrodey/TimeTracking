package com.example.alexey.timetracking;

import com.example.alexey.timetracking.database.AppDatabase;
import com.example.alexey.timetracking.database.Task;
import com.example.alexey.timetracking.global.TaskApplication;

import java.util.List;

public class TaskScreenModel implements TaskScreenContract.Model {
    private AppDatabase appDatabase;

    TaskScreenModel() {
        appDatabase = TaskApplication.getInstance().getDatabase();
    }

    @Override
    public void addTask(Task task) {
        appDatabase.taskDao().insert(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return appDatabase.taskDao().getAllTasks();
    }

    @Override
    public void onDestroy() {
        appDatabase = null;
    }
}
