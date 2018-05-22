package com.tinnovat.app.midland.Activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
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
import com.tinnovat.app.midland.network.model.request.DataRowRequest;
import com.tinnovat.app.midland.network.model.request.FieldData;
import com.tinnovat.app.midland.network.model.request.ModelCRUD;
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

        //TODO add or change methods accordingly
//        RequestEnvelope envelope = getRequestEnvelopeGeneral();

        RequestEnvelope envelope = createCashRequest();


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

    @NonNull
    private RequestEnvelope getRequestEnvelopeGeneral() {
        RequestEnvelope envelope = new RequestEnvelope();

        DataRequestBody body = new DataRequestBody();

        final RequestData data = new RequestData();

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
        List<FieldData> fieldDataList = new ArrayList<>();

        FieldData fieldData = new FieldData("SC_Request_ID","1000225");

        fieldDataList.add(fieldData);

        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_CashRequisition_View");
        modelCRUD.setTableName("MLV_cashreq");
        //  modelCRUD.setAction("action");

        // Set modelCurdRequest
        modelCRUDRequest.setLoginRequest(loginRequest);
        modelCRUDRequest.setModelCRUD(modelCRUD);

        // Set modelCurdRequest to RequestData
        data.setCduRequest(modelCRUDRequest);

        //Set RequestData object to DataRequestBody
        body.setUsStatesRequestData(data);

        //Set DataRequestBody object to RequestEnvelope
        envelope.setBody(body);

        modelCRUDRequest.setLoginRequest(loginRequest);
        return envelope;
    }

    private RequestEnvelope createCashRequest() {

        RequestEnvelope envelope = new RequestEnvelope();

        DataRequestBody body = new DataRequestBody();

        final RequestData data = new RequestData();

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
        List<FieldData> fieldDataList = new ArrayList<>();

        fieldDataList.add(new FieldData("SC_Request_ID","1000028"));
        fieldDataList.add(new FieldData("RequestedTo","100"));
        fieldDataList.add(new FieldData("AD_ReqToRole_ID",""));
        fieldDataList.add(new FieldData("dateRequested","2018-05-27 00:00:00"));
        fieldDataList.add(new FieldData("RequisitionType_ID","1000002"));
        fieldDataList.add(new FieldData("Description","TestNew"));
        fieldDataList.add(new FieldData("VerifiedToUser","100"));
        fieldDataList.add(new FieldData("VerifiedToRole",""));
        fieldDataList.add(new FieldData("RequestedAmount","15000"));
        fieldDataList.add(new FieldData("IsActive","Y"));
        fieldDataList.add(new FieldData("UserDailyActivity_ID","1000056"));

        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_CashRequisition_View");
        modelCRUD.setTableName("MLV_cashreq");
        modelCRUD.setAction("CreateUpdate");

        // Set modelCurdRequest
        modelCRUDRequest.setLoginRequest(loginRequest);
        modelCRUDRequest.setModelCRUD(modelCRUD);

        // Set modelCurdRequest to RequestData
        data.setCduRequest(modelCRUDRequest);

        //Set RequestData object to DataRequestBody
        body.setUsStatesRequestData(data);

        //Set DataRequestBody object to RequestEnvelope
        envelope.setBody(body);

        modelCRUDRequest.setLoginRequest(loginRequest);
        return envelope;
    }

    private Data getParsedData(WindowTabData tabData) {

        if (tabData.getDataSet() != null && tabData.getDataSet().getDataRowList() != null && !tabData.getDataSet().getDataRowList().isEmpty()) {
            List rowList = new ArrayList();
            for (DataRow item : tabData.getDataSet().getDataRowList()) {
                Map<String, String> fetchedContent = new HashMap<>();
                for (FieldDataResponse mapItem : item.getFieldData()) {
                    fetchedContent.put(mapItem.getColumn(), mapItem.getVal());
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
        } else
            return null;
    }
}
