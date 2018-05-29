package com.tinnovat.app.midland;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.tinnovat.app.midland.Activity.R;

import java.util.Objects;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    Dialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        progressDialog = new Dialog(this);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.custom_dialog_progress);
        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(false);
    }
    public void startLoading(){
        progressDialog.show();
    }

    public void endLoading(){
        if(progressDialog != null)
        {
            progressDialog.cancel();
            progressDialog.hide();
        }
    }
}
