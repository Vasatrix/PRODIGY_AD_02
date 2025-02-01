package com.example.todolistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> taskList;
    private MainActivity mainActivity;

    public TaskAdapter(Context context, ArrayList<String> taskList) {
        this.context = context;
        this.taskList = taskList;
        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        }

        TextView taskTextView = convertView.findViewById(R.id.task_text);
        Button editButton = convertView.findViewById(R.id.edit_button);
        Button deleteButton = convertView.findViewById(R.id.delete_button);

        taskTextView.setText(taskList.get(position));

        editButton.setOnClickListener(view -> {
            if (mainActivity != null) {
                mainActivity.editTask(position);
            }
        });

        deleteButton.setOnClickListener(view -> {
            if (mainActivity != null) {
                mainActivity.deleteTask(position);
            }
        });

        return convertView;
    }
}
