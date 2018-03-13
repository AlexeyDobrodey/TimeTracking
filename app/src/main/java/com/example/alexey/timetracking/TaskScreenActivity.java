package com.example.alexey.timetracking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.alexey.timetracking.adapters.TaskAdapter;
import com.example.alexey.timetracking.database.Task;
import com.example.alexey.timetracking.dialogs.taskCreationDialog.TaskCreationDialog;
import com.example.alexey.timetracking.dialogs.taskCreationDialog.TaskPresenterKeeper;
import com.example.alexey.timetracking.dialogs.timerDialog.TimerDialog;
import com.example.alexey.timetracking.dialogs.timerDialog.TimerPresenterKeeper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TaskScreenActivity extends AppCompatActivity implements TaskScreenContract.View {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvTasks)
    RecyclerView rvTasks;
    @BindView(R.id.fabAddTask)
    FloatingActionButton fabAddTask;
    private Unbinder unbinder;

    private TaskScreenContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        fabAddTask.setOnClickListener(v -> presenter.onCreateTaskClicked());

        presenter = TaskScreenKeeper.getInstance().provideModule(new TaskScreenModel());
        presenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share: {
                presenter.onShareClicked();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showTasks(@NonNull List<Task> tasks) {
        if (rvTasks.getAdapter() == null) {
            rvTasks.setAdapter(new TaskAdapter(tasks));
        } else {
            ((TaskAdapter) rvTasks.getAdapter()).changeDataSet(tasks);
        }
    }

    @Override
    public void runTaskCreationDialog() {
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(TaskCreationDialog.TAG);
        if (dialogFragment == null) {
            dialogFragment = TaskCreationDialog.newInstance(presenter);
            dialogFragment.show(getSupportFragmentManager(), TaskCreationDialog.TAG);
        }
    }

    @Override
    public void closeTaskCreationDialog() {
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(TaskCreationDialog.TAG);
        if (dialogFragment != null) {
            dialogFragment.dismiss();
            TaskPresenterKeeper.destroyProvider();
        }
    }

    @Override
    public void runTimerDialog() {
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(TimerDialog.TAG);
        if (dialogFragment == null) {
            dialogFragment = TimerDialog.newInstance(presenter);
            dialogFragment.show(getSupportFragmentManager(), TimerDialog.TAG);
        }
    }

    @Override
    public void closeTimerDialog() {
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(TimerDialog.TAG);
        if (dialogFragment != null) {
            dialogFragment.dismiss();
            TimerPresenterKeeper.destroyProvider();
        }
    }

    @Override
    public void shareTasks(String tasksData) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Tasks");
        emailIntent.putExtra(Intent.EXTRA_TEXT, tasksData);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}
