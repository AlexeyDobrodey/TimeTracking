package com.example.alexey.timetracking.dialogs.taskCreationDialog;

import com.example.alexey.timetracking.TaskScreenContract;
import com.example.alexey.timetracking.database.Task;

class TaskCreationPresenter extends TaskCreationContract.Presenter {

    private TaskScreenContract.ICreateTaskListener taskCreateListener;

    TaskCreationPresenter(TaskScreenContract.ICreateTaskListener taskCreateListener) {
        this.taskCreateListener = taskCreateListener;
    }

    @Override
    public void onAttach(TaskCreationContract.View view) {
        super.onAttach(view);
    }

    @Override
    void onStartTimerClicked() {
        if (!isViewAttached()) return;
        if (getView().getNameTask() == null || getView().getNameTask().isEmpty()) {
            getView().showErrorEmptyName();
        } else if (getView().getDescriptionTask() == null || getView().getDescriptionTask().isEmpty()) {
            getView().showErrorEmptyDescription();
        } else {
            Task task = new Task();
            task.setName(getView().getNameTask());
            task.setDescription(getView().getDescriptionTask());
            taskCreateListener.onTaskCreated(task);
        }
    }
}
