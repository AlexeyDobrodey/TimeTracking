package com.example.alexey.timetracking.dialogs.taskCreationDialog;

import com.example.alexey.timetracking.TaskScreenContract;

public class TaskPresenterKeeper {
    private TaskCreationContract.Presenter presenter;

    private static TaskPresenterKeeper instance;

    private TaskPresenterKeeper() {
    }

    static TaskPresenterKeeper getInstance() {
        if (instance == null)
            instance = new TaskPresenterKeeper();
        return instance;
    }


    TaskCreationContract.Presenter provideModule(TaskScreenContract.ICreateTaskListener createTaskListener) {
        if (presenter == null)
            presenter = new TaskCreationPresenter(createTaskListener);
        return presenter;
    }

    public static void destroyProvider() {
        instance = null;
    }
}
