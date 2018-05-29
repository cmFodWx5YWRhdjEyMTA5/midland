package com.tinnovat.app.midland.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.tinnovat.app.midland.BaseActivity;
import com.tinnovat.app.midland.model.Data;
import com.tinnovat.app.midland.network.ApiClient;
import com.tinnovat.app.midland.network.ApiInterface;
import com.tinnovat.app.midland.network.model.request.ADLoginRequest;
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
import com.tinnovat.app.midland.network.model.response.OutputField;
import com.tinnovat.app.midland.network.model.response.StandardResponse;
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

public class UserTaskRescheduleApprovalActivity extends BaseActivity {

    CheckBox reschedule;
    CheckBox complete;
    TextView rescheduleDetails;
    TextView status;
    TextView startDate;
    TextView completedDate;
    TextView approve;
    TextView reject;
    private String mIsApproved = null;
    private String mIsRejected = null;
    private String mRequestId = null;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_task_reschedule_approval);
        setTitle("User Task Reschedule Approval");

        reschedule = findViewById(R.id.checkBoxReschedule);
        complete = findViewById(R.id.checkBoxComplete);
        rescheduleDetails = findViewById(R.id.rescheduleDetails);
        status = findViewById(R.id.status);
        startDate = findViewById(R.id.startDate);
        completedDate = findViewById(R.id.completedDate);
        approve = findViewById(R.id.approve);
        reject = findViewById(R.id.reject);

        initiateService();

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action("Y" ,"N");
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action("N","Y");
            }
        });
    }

    private void action( String approveVal , String rejectVal){
        startLoading();
        //TODO add or change methods accordingly
        UpdateRequestEnvelope envelope = getUpdateRequestEnvelopeGeneral("IsApproved",approveVal,"SC_Rejected" ,rejectVal);

        Call<UpdateResponseEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchUpdateData(envelope);
        call.enqueue(new Callback<UpdateResponseEnvelope>() {

            @Override
            public void onResponse(Call<UpdateResponseEnvelope> call, Response<UpdateResponseEnvelope> response) {
                endLoading();

                StandardResponse data = response.body().getBody().getQueryDataResponse().getData();
                if (response.body().getBody().getQueryDataResponse().getData().getErrorMessage() == null && data.getFieldData() != null) {
                    List<OutputField> list = data.getFieldData().getOutputFieldList();
                    if (list != null && !list.isEmpty()) {
                        for (OutputField listItem : list) {
                            if (listItem.getColumn().equalsIgnoreCase("IsApproved"))
                                mIsApproved = listItem.getVal();
                            if (listItem.getColumn().equalsIgnoreCase("SC_Rejected"))
                                mIsRejected = listItem.getVal();
                            if (listItem.getColumn().equalsIgnoreCase("SC_UserTask_ID"))
                                mRequestId = listItem.getVal();

                        }
                    }
                }else {
                    Toast.makeText(UserTaskRescheduleApprovalActivity.this,"Network Error ",Toast.LENGTH_SHORT).show();

                }
                if (mIsApproved != null && mIsRejected != null && mRequestId != null){
                    finish();
                    Toast.makeText(UserTaskRescheduleApprovalActivity.this,"Completed "+mIsApproved+" : " +mIsRejected,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UserTaskRescheduleApprovalActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateResponseEnvelope> call, Throwable t) {
                endLoading();
                Toast.makeText(UserTaskRescheduleApprovalActivity.this,"Failed ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setData(Data responseData){
        Toast.makeText(UserTaskRescheduleApprovalActivity.this,"Success",Toast.LENGTH_SHORT).show();
        //rescheduleDetails.setText(responseData.getDataSet().get(0).get("organisation"));
        status.setText(responseData.getDataSet().get(0).get("StatusComment"));
        startDate.setText(responseData.getDataSet().get(0).get("EstimatedDate"));
        completedDate.setText(responseData.getDataSet().get(0).get("EstimatedCompleteDate"));

        if (responseData.getDataSet().get(0).get("isReschedule")!=null){
            if (responseData.getDataSet().get(0).get("isReschedule").equals("Y"))
                reschedule.setChecked(true);
            else
                reschedule.setChecked(false);
        }
        else{
            reschedule.setVisibility(View.INVISIBLE);
        }

        if (responseData.getDataSet().get(0).get("IsCompleted")!=null){
            if (responseData.getDataSet().get(0).get("IsCompleted").equals("Y"))
                complete.setChecked(true);
            else
                complete.setChecked(false);
        }
        else{
            complete.setVisibility(View.INVISIBLE);
        }






    }
    private void initiateService() {
        startLoading();

        //TODO add or change methods accordingly
        QueryRequestEnvelope envelope = getRequestEnvelopeGeneral();

        Call<ResponseQueryEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchQueryData(envelope);
        call.enqueue(new Callback<ResponseQueryEnvelope>() {
            @Override
            public void onResponse(Call<ResponseQueryEnvelope> call, Response<ResponseQueryEnvelope> response) {
                endLoading();
                Log.e("Success","Success");

                Data responseData = getParsedData(response.body().getBody().getQueryDataResponse().getData());
                setData(responseData);
                Toast.makeText(UserTaskRescheduleApprovalActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseQueryEnvelope> call, Throwable t) {
                endLoading();
                Log.e("Success","Success");
                Toast.makeText(UserTaskRescheduleApprovalActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @NonNull
    private UpdateRequestEnvelope getUpdateRequestEnvelopeGeneral(String approve , String approveVal,String reject , String rejectVal) {

        UpdateRequestEnvelope envelope = new UpdateRequestEnvelope();
        UpdateDataRequestBody body = new UpdateDataRequestBody();
        final UpdateRequestData data = new UpdateRequestData();
        ModelCRUDRequest modelCRUDRequest = new ModelCRUDRequest();

        ADLoginRequest loginRequest = new ADLoginRequest();

        ModelCRUD modelCRUD = new ModelCRUD();

        DataRowRequest dataRow = new DataRowRequest();


        //TODO set all available data's here
        loginRequest.setClientID(1000000);
        loginRequest.setUser("MidlandAdmin");
        loginRequest.setPass("MidlandAdmin");
        loginRequest.setLang("en_US");
        loginRequest.setRoleID(1000000);
        loginRequest.setOrgID(1000000);
        loginRequest.setWarehouseID(1000000);
        loginRequest.setStage(0);

        // Params inside dataRow as list (can use a loop or simply add objects to list
        List<FieldData> fieldDataList = new ArrayList<>();

      /* FieldData fieldDataApprove = new FieldData(approve,approveVal);
       FieldData fieldDataReject = new FieldData(reject,rejectVal);*/

        fieldDataList.add(new FieldData(approve,approveVal));
        fieldDataList.add(new FieldData(reject,rejectVal));

        // fieldDataList.add(fieldDataReject);

        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_Update_User_Task_Reschedule_Approval");
        modelCRUD.setRecordID("1000013");
       // modelCRUD.setTableName("SC_UserTask");
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

    @NonNull
    private QueryRequestEnvelope getRequestEnvelopeGeneral() {
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

        FieldData fieldData = new FieldData("SC_UserTask_ID","1000028");

        fieldDataList.add(fieldData);

        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_UserTaskRescheduleApproval_Web");
        modelCRUD.setTableName("SC_UserTask");
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
