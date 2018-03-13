package com.example.alexey.timetracking.dialogs.timerDialog;

import com.example.alexey.timetracking.BasePresenter;

public interface TimerContract {
    interface View {
        void showTime(String spentTime);

        void startTimer(long spentTimeInMillisecondsCache);

        void stopTimer();
    }

    abstract class Presenter extends BasePresenter<View> {
        abstract void onFinishTimerClicked();

        abstract void onUpdateTimer(long spentTimeInMilliseconds);
    }
}
