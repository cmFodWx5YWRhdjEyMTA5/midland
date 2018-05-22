package com.tinnovat.app.midland.Activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.tinnovat.app.midland.model.Data;
import com.tinnovat.app.midland.network.ApiClient;
import com.tinnovat.app.midland.network.ApiInterface;
import com.tinnovat.app.midland.network.model.request.ADLoginRequest;
import com.tinnovat.app.midland.network.model.request.DataRequestBody;
import com.tinnovat.app.midland.network.model.request.ModelCRUDRequest;
import com.tinnovat.app.midland.network.model.request.RequestData;
import com.tinnovat.app.midland.network.model.request.RequestEnvelope;
import com.tinnovat.app.midland.network.model.response.DataRow;
import com.tinnovat.app.midland.network.model.response.FieldDataResponse;
import com.tinnovat.app.midland.network.model.response.ResponseEnvelope;
import com.tinnovat.app.midland.network.model.response.WindowTabData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Notification");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0885C4" )));
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.color_task));
        initiateService();
    }

    private void initiateService() {
        RequestEnvelope envelope = new RequestEnvelope();

        DataRequestBody body = new DataRequestBody();

        final RequestData data = new RequestData();

        ModelCRUDRequest modelCRUDRequest = new ModelCRUDRequest();

        ADLoginRequest loginRequest = new ADLoginRequest();

        //TODO set all available data's here
        loginRequest.setClientID(1000000);
        loginRequest.setUser("SuperUser");
        loginRequest.setPass("System");
        loginRequest.setLang("en_US");
        loginRequest.setRoleID(1000000);
        loginRequest.setOrgID(1000000);
        loginRequest.setWarehouseID(1000004);
        loginRequest.setStage(0);


        modelCRUDRequest.setLoginRequest(loginRequest);
        modelCRUDRequest.setModelCRUD();


        data.setCduRequest(modelCRUDRequest);
        body.setUsStatesRequestData(data);
        envelope.setBody(body);


        Call<ResponseEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchData(envelope);


        call.enqueue(new Callback<ResponseEnvelope>() {
            @Override
            public void onResponse(Call<ResponseEnvelope> call, Response<ResponseEnvelope> response) {
                Log.e("Success","Success");

                Data responseData = getParsedData(response.body().getBody().getQueryDataResponse().getData());
                Toast.makeText(NotificationActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseEnvelope> call, Throwable t) {
                Log.e("Success","Success");
                 Toast.makeText(NotificationActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private Data getParsedData(WindowTabData tabData) {

        List rowList = new ArrayList();
        for (DataRow item : tabData.getDataSet().getDataRowList()) {
            Map<String,String> fetchedContent = new HashMap<>();
            for (FieldDataResponse mapItem : item.getFieldData()) {
                fetchedContent.put(mapItem.getColumn(),mapItem.getVal());
            }
            rowList.add(fetchedContent);
        }

        Data data = new Data();
        //  data.setNumRows(tabData.getNumRows());
        data.setRowCount(tabData.getRowCount());
        // data.setStartRow(tabData.getStartRow());
        //  data.setTotalRows(tabData.getTotalRows());
        data.setSuccess(tabData.isSuccess());
        data.setDataSet(rowList);
        return data;
    }
}
