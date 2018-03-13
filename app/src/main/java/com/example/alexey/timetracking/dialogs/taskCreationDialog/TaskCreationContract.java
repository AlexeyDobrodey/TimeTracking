package com.example.alexey.timetracking.dialogs.taskCreationDialog;

import com.example.alexey.timetracking.BasePresenter;

public interface TaskCreationContract {
    interface View {
        String getNameTask();

        String getDescriptionTask();

        void showErrorEmptyName();

        void showErrorEmptyDescription();
    }

    abstract class Presenter extends BasePresenter<View> {
        abstract void onStartTimerClicked();
    }
}
