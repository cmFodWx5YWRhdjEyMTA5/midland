package com.tinnovat.app.midland.Activity;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tinnovat.app.midland.BaseActivity;
import com.tinnovat.app.midland.model.Data;
import com.tinnovat.app.midland.network.ApiClient;
import com.tinnovat.app.midland.network.ApiInterface;
import com.tinnovat.app.midland.network.model.request.ADLoginRequest;
import com.tinnovat.app.midland.network.model.request.DataRowRequest;
import com.tinnovat.app.midland.network.model.request.FieldData;
import com.tinnovat.app.midland.network.model.request.ModelCRUD;
import com.tinnovat.app.midland.network.model.request.ModelCRUDRequest;
import com.tinnovat.app.midland.network.model.request.create.CreateDataRequestBody;
import com.tinnovat.app.midland.network.model.request.create.CreateRequestData;
import com.tinnovat.app.midland.network.model.request.create.CreateRequestEnvelope;
import com.tinnovat.app.midland.network.model.request.query.QueryDataRequestBody;
import com.tinnovat.app.midland.network.model.request.query.QueryRequestData;
import com.tinnovat.app.midland.network.model.request.query.QueryRequestEnvelope;
import com.tinnovat.app.midland.network.model.response.OutputField;
import com.tinnovat.app.midland.network.model.response.OutputFields;
import com.tinnovat.app.midland.network.model.response.StandardResponse;
import com.tinnovat.app.midland.network.model.response.create.CreateResponseEnvelope;
import com.tinnovat.app.midland.network.model.response.query.ResponseQueryEnvelope;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserTaskAssignmentActivity extends BaseActivity {

    EditText assignedTo;
    EditText assignedDate;
    EditText estdStartDate;
    EditText estdEndDate;
    EditText description;
    LinearLayout layoutButtons;
    LinearLayout layoutTask;
    TextView submit;
    TextView cancel;
    TextView createTask;
    String recordID = null;
    Spinner spinnerAssignedTo;
    String[] country = { "Supervisor1", "Purchase User", "Inventory User", "Supervisor", "Admin User9","MidlandUser","Rinshad"  };


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_task_assignment);
        setTitle("User Task Assignment");

        assignedTo = findViewById(R.id.assignedTo);
        assignedDate = findViewById(R.id.assignedDate);
        estdStartDate = findViewById(R.id.estdStartDate);
        estdEndDate = findViewById(R.id.estdEndDate);
        description = findViewById(R.id.description);
        layoutButtons = findViewById(R.id.layoutButtons);
        layoutTask = findViewById(R.id.layoutTask);
        submit = findViewById(R.id.submit);
        cancel = findViewById(R.id.cancel);
        createTask = findViewById(R.id.createTask);
        spinnerAssignedTo = findViewById(R.id.spinner);

        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignedUserTask();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              submitTask();
          }
      });
        cancel.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });

        spinnerElements();
    }

    private void spinnerElements() {

        //TODO add or change methods accordingly
        QueryRequestEnvelope envelope = getSpinnerData();

        Call<ResponseQueryEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchQueryData(envelope);
        call.enqueue(new Callback<ResponseQueryEnvelope>() {
            @Override
            public void onResponse(Call<ResponseQueryEnvelope> call, Response<ResponseQueryEnvelope> response) {
                Log.e("Success","Success");
                spinnerAssignedTo.setAdapter(new ArrayAdapter<String>(UserTaskAssignmentActivity.this, android.R.layout.simple_spinner_dropdown_item, country));
             /*  List<OutputFields> list =  response.body().getBody().getQueryDataResponse().getData().getDataSet().getDataRowList().get(0).getFieldData();
               if (list != null && !list.isEmpty()) {
                    for ( listItem : response.body().getBody().getQueryDataResponse().getData().getDataSet().getDataRowList()) {
                      if (listItem.getColumn().equalsIgnoreCase("IsApproved"))
                            mIsApproved = listItem.getVal();
                        if (listItem.getColumn().equalsIgnoreCase("SC_Rejected"))
                            mIsRejected = listItem.getVal();
                        if (listItem.getColumn().equalsIgnoreCase("SC_Request_ID"))
                            mRequestId = listItem.getVal();*//*

                    }
               }*/


                Toast.makeText(UserTaskAssignmentActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseQueryEnvelope> call, Throwable t) {
                Log.e("Success","Success");
                Toast.makeText(UserTaskAssignmentActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @NonNull
    private QueryRequestEnvelope getSpinnerData() {
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
        modelCRUD.setServiceType("MLW_UserQueryData");
        modelCRUD.setTableName("AD_User");
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

    private void assignedUserTask() {
        startLoading();

        //TODO add or change methods accordingly

        CreateRequestEnvelope envelope = getRequestEnvelopeGeneral();
        Call<CreateResponseEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchCreateData(envelope);

        call.enqueue(new Callback<CreateResponseEnvelope>() {
            @Override
            public void onResponse(Call<CreateResponseEnvelope> call, Response<CreateResponseEnvelope> response) {
                endLoading();


                StandardResponse data = response.body().getBody().getQueryDataResponse().getData();
                if (response.body().getBody().getQueryDataResponse().getData().getErrorMessage() == null && data.getFieldData() != null) {
                    layoutTask.setVisibility(View.GONE);
                    layoutButtons.setVisibility(View.VISIBLE);
                    recordID = response.body().getBody().getQueryDataResponse().getData().getRecordId();
                    Toast.makeText(UserTaskAssignmentActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    List<OutputField> list = data.getFieldData().getOutputFieldList();
                    if (list != null && !list.isEmpty()) {
                        for (OutputField listItem : list) {
                            if (listItem.getColumn().equalsIgnoreCase("AssignedTo")) {
                                spinnerAssignedTo.setVisibility(View.GONE);
                                assignedTo.setVisibility(View.VISIBLE);
                                assignedTo.setText(listItem.getVal());
                            }
                            if (listItem.getColumn().equalsIgnoreCase("Date1")) {
                                assignedDate.setText(listItem.getVal());
                                assignedDate.setFocusable(false);
                                assignedDate.setFocusableInTouchMode(false);
                                assignedDate.setClickable(false);
                            }
                            if (listItem.getColumn().equalsIgnoreCase("Description")) {
                                description.setText(listItem.getVal());
                                description.setFocusable(false);
                                description.setFocusableInTouchMode(false);
                                description.setClickable(false);
                            }
                            if (listItem.getColumn().equalsIgnoreCase("EstimatedCompleteDate")) {
                                estdStartDate.setText(listItem.getVal());
                                estdStartDate.setFocusable(false);
                                estdStartDate.setFocusableInTouchMode(false);
                                estdStartDate.setClickable(false);
                            }
                            if (listItem.getColumn().equalsIgnoreCase("EstimatedDate")) {
                                estdEndDate.setText(listItem.getVal());
                                estdEndDate.setFocusable(false);
                                estdEndDate.setFocusableInTouchMode(false);
                                estdEndDate.setClickable(false);
                            }


                        }
                    }

                }
                else {
                    Toast.makeText(UserTaskAssignmentActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateResponseEnvelope> call, Throwable t) {
                endLoading();
                Toast.makeText(UserTaskAssignmentActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submitTask() {
        startLoading();

        //TODO add or change methods accordingly

        QueryRequestEnvelope envelope = getRequestEnvelopeSubmit();
        Call<ResponseQueryEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchQueryData(envelope);

        call.enqueue(new Callback<ResponseQueryEnvelope>() {
            @Override
            public void onResponse(Call<ResponseQueryEnvelope> call, Response<ResponseQueryEnvelope> response) {
                endLoading();
                Toast.makeText(UserTaskAssignmentActivity.this,"Task success ",Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void onFailure(Call<ResponseQueryEnvelope> call, Throwable t) {
                endLoading();
                Toast.makeText(UserTaskAssignmentActivity.this,"task Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @NonNull
    private CreateRequestEnvelope getRequestEnvelopeGeneral() {

        CreateRequestEnvelope envelope = new CreateRequestEnvelope();

        CreateDataRequestBody body = new CreateDataRequestBody();

        final CreateRequestData data = new CreateRequestData();

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





        // fieldDataList.add(fieldDataReject);

      //  dataRow.setField(fieldDataList);

        // Params inside dataRow as list (can use a loop or simply add objects to list
        List<FieldData> fieldDataList = new ArrayList<>();

        fieldDataList.add(new FieldData("AssignedTo","100"));
        fieldDataList.add(new FieldData("Date1","2018-05-08 00:00:00"));
        fieldDataList.add(new FieldData("EstimatedCompleteDate","2018-05-27 00:00:00"));
        fieldDataList.add(new FieldData("EstimatedDate","2018-05-10 00:00:00"));
        fieldDataList.add(new FieldData("Description","TestNew"));


        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_AssignedUserTask_Web");
        modelCRUD.setTableName("SC_UserTask");
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
    private QueryRequestEnvelope getRequestEnvelopeSubmit() {

        QueryRequestEnvelope envelope = new QueryRequestEnvelope();

        QueryDataRequestBody body = new QueryDataRequestBody();

        final QueryRequestData data = new QueryRequestData();

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





        // fieldDataList.add(fieldDataReject);

        //  dataRow.setField(fieldDataList);

        // Params inside dataRow as list (can use a loop or simply add objects to list
        List<FieldData> fieldDataList = new ArrayList<>();

        fieldDataList.add(new FieldData("IsSubmitted","Y"));



        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_UserTaskSubmit_Update");
        modelCRUD.setRecordID(recordID);

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
}
