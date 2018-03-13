package com.example.alexey.timetracking.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alexey.timetracking.R;
import com.example.alexey.timetracking.database.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<Task> items;

    public TaskAdapter(List<Task> items) {
        this.items = new ArrayList<>(items);
    }

    public void changeDataSet(List<Task> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
        notifyItemChanged(0);
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        Task task = items.get(position);
        holder.bindData(task, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.llHeader)
        LinearLayout llHeader;
        @BindView(R.id.tvName)
        TextView nameTask;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.tvTime)
        TextView tvTime;

        TaskHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindData(Task task, int position) {
            llHeader.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
            nameTask.setText(task.getName());
            tvDescription.setText(task.getDescription());
            tvTime.setText(task.getTime());
        }
    }
}
