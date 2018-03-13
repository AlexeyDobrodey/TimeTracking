package com.example.alexey.timetracking;

import android.support.annotation.NonNull;

import com.example.alexey.timetracking.database.Task;

import java.util.List;

public interface TaskScreenContract {
    interface View {
        void showTasks(@NonNull List<Task> tasks);

        void runTaskCreationDialog();

        void closeTaskCreationDialog();

        void runTimerDialog();

        void closeTimerDialog();

        void shareTasks(String tasksData);
    }

    abstract class Presenter extends BasePresenter<View> implements ICreateTaskListener, ITimerListener {
        abstract void onCreateTaskClicked();

        abstract void onShareClicked();
    }

    interface Model {
        void addTask(Task task);

        List<Task> getAllTasks();

        void onDestroy();
    }

    interface ICreateTaskListener {
        void onTaskCreated(Task task);
    }

    interface ITimerListener {
        void onFinishTime(String spentTime);
    }
}
