package com.tinnovat.app.midland.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.tinnovat.app.midland.R;

import java.util.Objects;

public class StatusPageActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_page);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Status");
        LinearLayout cashRequisitionStatus = findViewById(R.id.cashRequisitionStatus);
        LinearLayout cashUtiStatus = findViewById(R.id.cashUtiStatus);
        LinearLayout cashRequestStatus = findViewById(R.id.cashRequestStatus);
        LinearLayout availableCreditStatus = findViewById(R.id.availableCreditStatus);

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
