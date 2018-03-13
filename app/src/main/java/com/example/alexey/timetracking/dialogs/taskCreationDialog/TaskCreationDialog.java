package com.example.alexey.timetracking.dialogs.taskCreationDialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.alexey.timetracking.R;
import com.example.alexey.timetracking.TaskScreenContract;
import com.example.alexey.timetracking.dialogs.BaseDialog;

import butterknife.BindView;

public class TaskCreationDialog extends BaseDialog implements TaskCreationContract.View {
    public static final String TAG = TaskCreationDialog.class.getName();

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etDescription)
    EditText etDescription;
    @BindView(R.id.tilName)
    TextInputLayout tilName;
    @BindView(R.id.tilDescription)
    TextInputLayout tilDescription;
    @BindView(R.id.btnStartTimer)
    Button btnStartTimer;

    private TaskCreationContract.Presenter presenter;


    private TaskScreenContract.ICreateTaskListener taskCreateListener;

    public void setCreateTaskListener(TaskScreenContract.ICreateTaskListener taskCreateListener) {
        this.taskCreateListener = taskCreateListener;
    }

    public static TaskCreationDialog newInstance(TaskScreenContract.ICreateTaskListener createrTaskListener) {
        TaskCreationDialog taskCreationDialog = new TaskCreationDialog();
        taskCreationDialog.setCreateTaskListener(createrTaskListener);
        return taskCreationDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = TaskPresenterKeeper.getInstance().provideModule(taskCreateListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_creation_task, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnStartTimer.setOnClickListener(v -> presenter.onStartTimerClicked());
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tilName.setError(null);
                tilName.setErrorEnabled(false);
            }
        });
        etDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tilDescription.setError(null);
                tilDescription.setErrorEnabled(false);
            }
        });
        presenter.onAttach(this);
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public String getNameTask() {
        return etName.getText().toString();
    }

    @Override
    public String getDescriptionTask() {
        return etDescription.getText().toString();
    }

    @Override
    public void showErrorEmptyName() {
        tilName.setError(getString(R.string.error_empty_field));
    }

    @Override
    public void showErrorEmptyDescription() {
        tilDescription.setError(getString(R.string.error_empty_field));
    }
}
