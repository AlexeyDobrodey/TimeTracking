package com.example.alexey.timetracking.dialogs.timerDialog;

import com.example.alexey.timetracking.TaskScreenContract;

public class TimerPresenterKeeper {
    private TimerContract.Presenter presenter;

    private static TimerPresenterKeeper instance;

    private TimerPresenterKeeper() {
    }

    static TimerPresenterKeeper getInstance() {
        if (instance == null)
            instance = new TimerPresenterKeeper();
        return instance;
    }


    TimerContract.Presenter provideModule(TaskScreenContract.ITimerListener timerListener) {
        if (presenter == null)
            presenter = new TimerPresenter(timerListener);
        return presenter;
    }

    public static void destroyProvider() {
        instance = null;
    }
}
