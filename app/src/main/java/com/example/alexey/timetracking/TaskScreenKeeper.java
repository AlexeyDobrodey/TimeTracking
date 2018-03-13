package com.example.alexey.timetracking;

public class TaskScreenKeeper {
    private TaskScreenContract.Presenter presenter;

    private static TaskScreenKeeper instance;

    private TaskScreenKeeper() {
    }

    static TaskScreenKeeper getInstance() {
        if (instance == null)
            instance = new TaskScreenKeeper();
        return instance;
    }


    TaskScreenContract.Presenter provideModule(TaskScreenContract.Model model) {
        if (presenter == null)
            presenter = new TaskScreenPresenter(model);
        return presenter;
    }

    public static void destroyProvider() {
        instance = null;
    }
}
