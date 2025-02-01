package com.example.todolistapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView taskListView = findViewById(R.id.task_list_view);
        Button addTaskButton = findViewById(R.id.add_task_button);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);
        taskListView.setAdapter(taskAdapter);

        addTaskButton.setOnClickListener(view -> showAddTaskDialog());
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_task, null);
        builder.setView(dialogView);

        EditText taskInput = dialogView.findViewById(R.id.task_input);
        Button saveButton = dialogView.findViewById(R.id.save_task_button);

        AlertDialog dialog = builder.create();

        saveButton.setOnClickListener(view -> {
            String task = taskInput.getText().toString().trim();
            if (!task.isEmpty()) {
                taskList.add(task);
                taskAdapter.notifyDataSetChanged();
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    public void editTask(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_task, null);
        builder.setView(dialogView);

        EditText taskInput = dialogView.findViewById(R.id.task_input);
        Button saveButton = dialogView.findViewById(R.id.save_task_button);

        taskInput.setText(taskList.get(position));
        AlertDialog dialog = builder.create();

        saveButton.setOnClickListener(view -> {
            String updatedTask = taskInput.getText().toString().trim();
            if (!updatedTask.isEmpty()) {
                taskList.set(position, updatedTask);
                taskAdapter.notifyDataSetChanged();
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    public void deleteTask(int position) {
        taskList.remove(position);
        taskAdapter.notifyDataSetChanged();
    }
}
