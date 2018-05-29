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

import com.tinnovat.app.midland.BaseActivity;
import com.tinnovat.app.midland.model.Data;
import com.tinnovat.app.midland.network.ApiClient;
import com.tinnovat.app.midland.network.ApiInterface;
import com.tinnovat.app.midland.network.model.request.ADLoginRequest;
import com.tinnovat.app.midland.network.model.request.create.CreateDataRequestBody;
import com.tinnovat.app.midland.network.model.request.create.CreateRequestData;
import com.tinnovat.app.midland.network.model.request.create.CreateRequestEnvelope;
import com.tinnovat.app.midland.network.model.request.query.QueryDataRequestBody;
import com.tinnovat.app.midland.network.model.request.DataRowRequest;
import com.tinnovat.app.midland.network.model.request.FieldData;
import com.tinnovat.app.midland.network.model.request.ModelCRUD;
import com.tinnovat.app.midland.network.model.request.ModelCRUDRequest;
import com.tinnovat.app.midland.network.model.request.query.QueryRequestData;
import com.tinnovat.app.midland.network.model.request.query.QueryRequestEnvelope;
import com.tinnovat.app.midland.network.model.request.update.UpdateDataRequestBody;
import com.tinnovat.app.midland.network.model.request.update.UpdateRequestData;
import com.tinnovat.app.midland.network.model.request.update.UpdateRequestEnvelope;
import com.tinnovat.app.midland.network.model.response.create.CreateResponseEnvelope;
import com.tinnovat.app.midland.network.model.response.query.DataRow;
import com.tinnovat.app.midland.network.model.response.query.FieldDataResponse;
import com.tinnovat.app.midland.network.model.response.query.ResponseQueryEnvelope;
import com.tinnovat.app.midland.network.model.response.query.WindowTabData;
import com.tinnovat.app.midland.network.model.response.update.UpdateResponseEnvelope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends BaseActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setTitle("Notification");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0885C4" )));
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.color_task));
        initiateService();
    }

    private void initiateService() {

        //TODO add or change methods accordingly
//        RequestEnvelope envelope = getRequestEnvelopeGeneral();

//        processQueryRequest();
        processCreateRequest();
//        processUpdateRequest();


    }

    private void processQueryRequest() {
        QueryRequestEnvelope envelope = queryRequestEnvelope();


        Call<ResponseQueryEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchQueryData(envelope);


        call.enqueue(new Callback<ResponseQueryEnvelope>() {
            @Override
            public void onResponse(Call<ResponseQueryEnvelope> call, Response<ResponseQueryEnvelope> response) {
                Log.e("Success","Success");

                Data responseData = getParsedData(response.body().getBody().getQueryDataResponse().getData());
                Toast.makeText(NotificationActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseQueryEnvelope> call, Throwable t) {
                Log.e("Success","Success");
                Toast.makeText(NotificationActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processCreateRequest() {
        CreateRequestEnvelope envelope = createCashRequest();

        Call<CreateResponseEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchCreateData(envelope);


        call.enqueue(new Callback<CreateResponseEnvelope>() {
            @Override
            public void onResponse(Call<CreateResponseEnvelope> call, Response<CreateResponseEnvelope> response) {
                Log.e("Success","Success");

//                Data responseData = getParsedData(response.body().getBody().getQueryDataResponse().getData());
                Toast.makeText(NotificationActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CreateResponseEnvelope> call, Throwable t) {
                Log.e("Success","Success");
                Toast.makeText(NotificationActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processUpdateRequest() {
        UpdateRequestEnvelope envelope = getRequestEnvelopeGeneral();

        Call<UpdateResponseEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchUpdateData(envelope);


        call.enqueue(new Callback<UpdateResponseEnvelope>() {
            @Override
            public void onResponse(Call<UpdateResponseEnvelope> call, Response<UpdateResponseEnvelope> response) {
                Log.e("Success","Success");

//                Data responseData = getParsedData(response.body().getBody().getUsStatesRequestData().getData());
                Toast.makeText(NotificationActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UpdateResponseEnvelope> call, Throwable t) {
                Log.e("Success","Success");
                Toast.makeText(NotificationActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private CreateRequestEnvelope createCashRequest() {
        CreateRequestEnvelope envelope = new CreateRequestEnvelope();

        CreateDataRequestBody body = new CreateDataRequestBody();

        final CreateRequestData data = new CreateRequestData();

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

        fieldDataList.add(new FieldData("C_BPartner_ID","1000010"));
        fieldDataList.add(new FieldData("AD_User_ID","100"));
        fieldDataList.add(new FieldData("IsActive","Y"));

       /*
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
        fieldDataList.add(new FieldData("UserDailyActivity_ID","1000056"));*/

        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_CreateUserDailyCashRequesy_Web");
        modelCRUD.setTableName("UserDailyActivity");
        modelCRUD.setAction("CreateUpdate");

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

    @NonNull
    private UpdateRequestEnvelope getRequestEnvelopeGeneral() {
        UpdateRequestEnvelope envelope = new UpdateRequestEnvelope();

        UpdateDataRequestBody body = new UpdateDataRequestBody();

        final UpdateRequestData data = new UpdateRequestData();

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

        FieldData fieldData = new FieldData("IsApproved","Y");

        fieldDataList.add(fieldData);

        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_ProductRequisition_Update");
        modelCRUD.setTableName("M_Requisition");
        modelCRUD.setRecordID("1000032");
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

    private QueryRequestEnvelope queryRequestEnvelope() {

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
        List<FieldData> fieldDataList = new ArrayList<>();


        fieldDataList.add(new FieldData("SC_UserTask_ID","1000013"));
        fieldDataList.add(new FieldData("isReschedule","Y"));

        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_Assigned_Task_View");
        modelCRUD.setTableName("mlv_AssgnTask_view");
//        modelCRUD.setAction("CreateUpdate");

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
