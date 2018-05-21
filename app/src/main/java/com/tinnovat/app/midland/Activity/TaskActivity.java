package com.tinnovat.app.midland.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import java.util.Objects;

public class TaskActivity extends AppCompatActivity {
    LinearLayout  userTaskAssignment;
    LinearLayout  userTaskView;
    LinearLayout  assignedTaskView;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Task");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0885C4" )));
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.color_task));

        userTaskAssignment= findViewById(R.id.userTaskAssignment);
        userTaskView= findViewById(R.id.userTaskView);
        assignedTaskView= findViewById(R.id.assignedTaskView);

        userTaskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), UserTaskViewActivity.class);
                startActivity(Intent);
            }
        });

        userTaskAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        assignedTaskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
