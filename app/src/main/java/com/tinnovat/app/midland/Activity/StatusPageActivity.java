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

public class StatusPageActivity extends AppCompatActivity {

    LinearLayout cashRequisitionStatus;
    LinearLayout cashUtiStatus;
    LinearLayout cashRequestStatus;
    LinearLayout availableCreditStatus;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_page);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Status");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#BAD09C" )));
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.color_status));

        cashRequisitionStatus = findViewById(R.id.cashRequisitionStatus);
        cashUtiStatus = findViewById(R.id.cashUtiStatus);
        cashRequestStatus = findViewById(R.id.cashRequestStatus);
        availableCreditStatus = findViewById(R.id.availableCreditStatus);

        cashRequisitionStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), CashRequisitionStatusActivity.class);
                startActivity(Intent);
            }
        });
        cashUtiStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), CashUtilizationStatusActivity.class);
                startActivity(Intent);
            }
        });
        cashRequestStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), CashRequestStatusActivity.class);
                startActivity(Intent);
            }
        });
        availableCreditStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), CashRequisitionStatusActivity.class);
                startActivity(Intent);
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
