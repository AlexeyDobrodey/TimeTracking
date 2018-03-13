package com.example.alexey.timetracking;

import android.support.annotation.NonNull;

import com.example.alexey.timetracking.database.Task;

import java.util.List;

public class TaskScreenPresenter extends TaskScreenContract.Presenter {
    private TaskScreenContract.Model model;
    private Task task;

    TaskScreenPresenter(TaskScreenContract.Model model) {
        this.model = model;
    }

    @Override
    public void onAttach(TaskScreenContract.View view) {
        super.onAttach(view);
        getView().showTasks(model.getAllTasks());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        model.onDestroy();
    }

    @Override
    public void onCreateTaskClicked() {
        if (isViewAttached())
            getView().runTaskCreationDialog();
    }

    @Override
    public void onTaskCreated(@NonNull Task task) {
        this.task = task;
        if (isViewAttached()) {
            getView().closeTaskCreationDialog();
            getView().runTimerDialog();
        }
    }

    @Override
    void onShareClicked() {
        List<Task> tasks = model.getAllTasks();
        String convert = convertToString(tasks);
        getView().shareTasks(convert);
    }

    private String convertToString(@NonNull List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Name | Description | Time;\n");
        for (Task task : tasks) {
            sb.append(task).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void onFinishTime(String spentTime) {
        task.setTime(spentTime);
        model.addTask(task);
        if (isViewAttached()) {
            getView().showTasks(model.getAllTasks());
            getView().closeTimerDialog();
        }
    }
}
