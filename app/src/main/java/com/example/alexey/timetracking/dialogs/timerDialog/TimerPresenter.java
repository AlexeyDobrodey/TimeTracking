package com.example.alexey.timetracking.dialogs.timerDialog;

import com.example.alexey.timetracking.TaskScreenContract;
import com.example.alexey.timetracking.global.Utils;

class TimerPresenter extends TimerContract.Presenter {

    private TaskScreenContract.ITimerListener timerListener;
    private long spentTimeInMillisecondsCache = 0L;

    TimerPresenter(TaskScreenContract.ITimerListener timerListener) {
        this.timerListener = timerListener;
    }

    @Override
    public void onAttach(TimerContract.View view) {
        super.onAttach(view);
        getView().startTimer(spentTimeInMillisecondsCache);
    }

    @Override
    void onFinishTimerClicked() {
        getView().stopTimer();
        timerListener.onFinishTime(Utils.convertMillisecondsToTime(spentTimeInMillisecondsCache));
    }

    @Override
    void onUpdateTimer(long spentTimeInMilliseconds) {
        spentTimeInMillisecondsCache = spentTimeInMilliseconds;
        getView().showTime(Utils.convertMillisecondsToTime(spentTimeInMillisecondsCache));
    }
}
