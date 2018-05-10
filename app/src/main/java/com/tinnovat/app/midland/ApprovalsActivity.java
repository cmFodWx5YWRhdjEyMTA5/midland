package com.tinnovat.app.midland;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Objects;

public class ApprovalsActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvals);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Approvals");

        LinearLayout cashRequisitionApproval = findViewById(R.id.cashRequisitionApproval);
        LinearLayout cashUtiliApproval = findViewById(R.id.cashUtiliApproval);
        LinearLayout productRequestApproval = findViewById(R.id.productRequestApproval);
        LinearLayout purchaseOrderApproval = findViewById(R.id.purchaseOrderApproval);
        LinearLayout rescheduleApproval = findViewById(R.id.rescheduleApproval);

        cashRequisitionApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), CashRequisitionApprovalActivity.class);
                startActivity(Intent);
            }
        });

        cashUtiliApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), CashUtilisationApprovalActivity.class);
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
