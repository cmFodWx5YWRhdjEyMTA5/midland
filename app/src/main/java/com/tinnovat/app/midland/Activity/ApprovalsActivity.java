package com.tinnovat.app.midland.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Objects;

public class ApprovalsActivity extends AppCompatActivity {

    LinearLayout cashRequisitionApproval;
    LinearLayout cashUtiliApproval;
    LinearLayout productRequestApproval;
    LinearLayout purchaseOrderApproval;
    LinearLayout rescheduleApproval;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvals);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Approvals");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#42206F" )));
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.color_approval));


         cashRequisitionApproval = findViewById(R.id.cashRequisitionApproval);
         cashUtiliApproval = findViewById(R.id.cashUtiliApproval);
         productRequestApproval = findViewById(R.id.productRequestApproval);
         purchaseOrderApproval = findViewById(R.id.purchaseOrderApproval);
         rescheduleApproval = findViewById(R.id.rescheduleApproval);


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
        productRequestApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), ProductRequisitionApprovalActivity.class);
                startActivity(Intent);
            }
        });
        purchaseOrderApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), PurchaseOrderApprovalActivity.class);
                startActivity(Intent);
            }
        });
        rescheduleApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), UserTaskRescheduleApprovalActivity.class);
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
