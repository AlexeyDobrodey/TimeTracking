package com.example.alexey.timetracking.dialogs.timerDialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alexey.timetracking.R;
import com.example.alexey.timetracking.TaskScreenContract;
import com.example.alexey.timetracking.dialogs.BaseDialog;

import butterknife.BindView;

public class TimerDialog extends BaseDialog implements TimerContract.View {
    public static final String TAG = TimerDialog.class.getName();

    @BindView(R.id.tvTimer)
    TextView tvTimer;
    @BindView(R.id.btnFinishTimer)
    TextView btnFinishTimer;

    private long startTime = 0L;
    private Handler handler = new Handler();
    private TimerContract.Presenter presenter;
    private TaskScreenContract.ITimerListener timerListener;

    public void setTimerListener(TaskScreenContract.ITimerListener timerListener) {
        this.timerListener = timerListener;
    }


    public static TimerDialog newInstance(TaskScreenContract.ITimerListener timerListener) {
        TimerDialog timerDialog = new TimerDialog();
        timerDialog.setTimerListener(timerListener);
        return timerDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = TimerPresenterKeeper.getInstance().provideModule(timerListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_timer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnFinishTimer.setOnClickListener(v -> presenter.onFinishTimerClicked());
        presenter.onAttach(this);
    }

    @Override
    public void onDestroyView() {
        handler.removeCallbacks(updateTimer);
        super.onDestroyView();
    }

    @Override
    public void showTime(String spentTime) {
        tvTimer.setText(spentTime);
    }

    @Override
    public void startTimer(long spentTimeInMillisecondsCache) {
        startTime = SystemClock.uptimeMillis() - spentTimeInMillisecondsCache;
        handler.post(updateTimer);
    }

    @Override
    public void stopTimer() {
        handler.removeCallbacks(updateTimer);
    }

    private Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            long spentTimeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            presenter.onUpdateTimer(spentTimeInMilliseconds);
            handler.postDelayed(this, 1000);
        }
    };
}
