package com.tinnovat.app.midland.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.tinnovat.app.midland.BaseActivity;
import com.tinnovat.app.midland.NotificationView;
import com.tinnovat.app.midland.adapter.NotificationAdapter;
import com.tinnovat.app.midland.network.ApiClient;
import com.tinnovat.app.midland.network.ApiInterface;
import com.tinnovat.app.midland.network.model.request.ADLoginRequest;
import com.tinnovat.app.midland.network.model.request.DataRowRequest;
import com.tinnovat.app.midland.network.model.request.ModelCRUD;
import com.tinnovat.app.midland.network.model.request.ModelCRUDRequest;
import com.tinnovat.app.midland.network.model.request.query.QueryDataRequestBody;
import com.tinnovat.app.midland.network.model.request.query.QueryRequestData;
import com.tinnovat.app.midland.network.model.request.query.QueryRequestEnvelope;
import com.tinnovat.app.midland.network.model.response.query.ContentDataSet;
import com.tinnovat.app.midland.network.model.response.query.DataRow;
import com.tinnovat.app.midland.network.model.response.query.FieldDataResponse;
import com.tinnovat.app.midland.network.model.response.query.ResponseQueryEnvelope;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends BaseActivity {

    private List<NotificationView> notificationViewList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NotificationAdapter mAdapter;
    NotificationView notificationView;
    String name = null;
    String message = null;
    String wType = null;
    Button btn;



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setTitle("Notification");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0885C4" )));
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.color_task));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new NotificationAdapter(notificationViewList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("Notification");
            }
        });

       //

    }
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Toast.makeText(NotificationActivity.this,"Done",Toast.LENGTH_SHORT).show();
        Intent refresh = new Intent(this, NotificationActivity.class);
        startActivity(refresh);
        finish();
    }


    @NonNull
    private QueryRequestEnvelope getNotificationData() {
        QueryRequestEnvelope envelope = new QueryRequestEnvelope();

        QueryDataRequestBody body = new QueryDataRequestBody();

        final QueryRequestData data = new QueryRequestData();

        ModelCRUDRequest modelCRUDRequest = new ModelCRUDRequest();

        ADLoginRequest loginRequest = new ADLoginRequest();

        ModelCRUD modelCRUD = new ModelCRUD();

        DataRowRequest dataRow = new DataRowRequest();


        //TODO set all available data's here
        loginRequest.setClientID(1000000);
        loginRequest.setUser("SuperUser");
        loginRequest.setPass("System");
        loginRequest.setLang("en_US");
        loginRequest.setRoleID(1000000);
        loginRequest.setOrgID(1000000);
        loginRequest.setWarehouseID(1000004);
        loginRequest.setStage(0);


        // Params inside dataRow as list (can use a loop or simply add objects to list
        /*List<FieldData> fieldDataList = new ArrayList<>();

        FieldData fieldData = new FieldData("SC_Request_ID","1000235");

        fieldDataList.add(fieldData);

        dataRow.setField(fieldDataList);*/

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_Alert");
        modelCRUD.setTableName("MLV_Alert");
        //  modelCRUD.setAction("action");

        // Set modelCurdRequest
        modelCRUDRequest.setLoginRequest(loginRequest);
        modelCRUDRequest.setModelCRUD(modelCRUD);

        // Set modelCurdRequest to QueryRequestData
        data.setCduRequest(modelCRUDRequest);

        //Set QueryRequestData object to QueryDataRequestBody
        body.setUsStatesRequestData(data);

        //Set QueryDataRequestBody object to RequestEnvelope
        envelope.setBody(body);

        modelCRUDRequest.setLoginRequest(loginRequest);
        return envelope;
    }

    private void prepareMovieData() {
        startLoading();
        //TODO add or change methods accordingly
        QueryRequestEnvelope envelope = getNotificationData();

        Call<ResponseQueryEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchQueryData(envelope);
        call.enqueue(new Callback<ResponseQueryEnvelope>() {
            @Override
            public void onResponse(Call<ResponseQueryEnvelope> call, Response<ResponseQueryEnvelope> response) {
                Log.e("Success","Success");

                if (response != null && response.body().getBody() != null && response.body().getBody().getQueryDataResponse() != null &&
                        response.body().getBody().getQueryDataResponse() != null) {
                    ContentDataSet dataSet = response.body().getBody().getQueryDataResponse().getData().getDataSet();
                    if (dataSet != null && dataSet.getDataRowList() != null && !dataSet.getDataRowList().isEmpty()) {
                        for (DataRow dataRowElement : dataSet.getDataRowList()) {
                            if (dataRowElement != null && dataRowElement.getFieldData() != null && !dataRowElement.getFieldData().isEmpty())
                                for (FieldDataResponse filedElement : dataRowElement.getFieldData()) {

                                    if (filedElement.getColumn().equals("Name") ) {
                                        name = filedElement.getVal();
                                    }
                                    if ( filedElement.getColumn().equals("Message") ) {
                                        message = filedElement.getVal();
                                    }
                                    if ( filedElement.getColumn().equals("WType") ) {
                                        wType = filedElement.getVal();
                                    }
                                    if (name != null && message != null && wType != null) {
                                        notificationView = new NotificationView(name, message, wType);
                                        notificationViewList.add(notificationView);
                                    }
                                }
                        }
                    }

                }

                mAdapter.notifyDataSetChanged();
                endLoading();
                Toast.makeText(NotificationActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseQueryEnvelope> call, Throwable t) {
                endLoading();
                Toast.makeText(NotificationActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.notifyDataSetChanged();
    }
}
