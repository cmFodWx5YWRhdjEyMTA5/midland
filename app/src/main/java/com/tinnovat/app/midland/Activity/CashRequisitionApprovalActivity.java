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

import com.tinnovat.app.midland.model.Data;
import com.tinnovat.app.midland.network.ApiClient;
import com.tinnovat.app.midland.network.ApiInterface;
import com.tinnovat.app.midland.network.model.request.ADLoginRequest;
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

public class CashRequisitionApprovalActivity extends AppCompatActivity {

    TextView reqBy;
    TextView docNo;
    TextView reqType;
    TextView projct;
    TextView descrp;
    TextView reqAmount;
    TextView reqDate;
    TextView balanceUtiAmount;
    TextView approvalAmnt;
    TextView frwdToUser;
    TextView frwdToRole;
    TextView approve;
    TextView reject;
    CheckBox isVerified;
    CheckBox isFrwd;
    private String mIsApproved = null;
    private String mRequestId;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_requisition_approval);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Cash Requisition Approval");

        reqBy = findViewById(R.id.requestedBy);
        docNo = findViewById(R.id.docNo);
        reqType = findViewById(R.id.reqType);
        projct = findViewById(R.id.project);
        descrp = findViewById(R.id.description);
        reqAmount = findViewById(R.id.reqAmount);
        reqDate = findViewById(R.id.reqDate);
        balanceUtiAmount = findViewById(R.id.balanceUtiAmount);
        approvalAmnt = findViewById(R.id.approvalAmount);
        frwdToUser = findViewById(R.id.frwdToUser);
        frwdToRole = findViewById(R.id.frwdToRole);
        isVerified = findViewById(R.id.checkBoxVerified);
        isFrwd = findViewById(R.id.checkBoxForward);
        approve = findViewById(R.id.approve);
        reject = findViewById(R.id.reject);


        initiateService();

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approve();
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reject();
            }
        });
    }

    private void approve(){
        //TODO add or change methods accordingly
        UpdateRequestEnvelope envelope = getUpdateRequestEnvelopeGeneral("IsApproved","Y");
      //  QueryRequestEnvelope envelope = getRequestEnvelopeGeneral();

        Call<UpdateResponseEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchUpdateData(envelope);
        call.enqueue(new Callback<UpdateResponseEnvelope>() {

            @Override
            public void onResponse(Call<UpdateResponseEnvelope> call, Response<UpdateResponseEnvelope> response) {
               // response.body().getBody().getQueryDataResponse().getData().getRecordId()

                StandardResponse data = response.body().getBody().getQueryDataResponse().getData();
                if (data.getFieldData() != null) {
                    List<OutputField> list = data.getFieldData().getOutputFieldList();
                    if (list != null && !list.isEmpty()) {
                        for (OutputField listItem : list) {
                            if (listItem.getColumn().equalsIgnoreCase("IsApproved"))
                                mIsApproved = listItem.getVal();
                            if (listItem.getColumn().equalsIgnoreCase("SC_Request_ID"))
                                mRequestId = listItem.getVal();

                        }
                    }
                }
                Log.e("Success","Success");
            }

            @Override
            public void onFailure(Call<UpdateResponseEnvelope> call, Throwable t) {
                Log.e("Success","Success");
            }
        });
    }

    private void reject(){
        //TODO add or change methods accordingly
        UpdateRequestEnvelope envelope = getUpdateRequestEnvelopeGeneral("SC_Rejected","Y");
        //  QueryRequestEnvelope envelope = getRequestEnvelopeGeneral();

        Call<UpdateResponseEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchUpdateData(envelope);
        call.enqueue(new Callback<UpdateResponseEnvelope>() {
            @Override
            public void onResponse(Call<UpdateResponseEnvelope> call, Response<UpdateResponseEnvelope> response) {
                Log.e("Success","Success");
            }

            @Override
            public void onFailure(Call<UpdateResponseEnvelope> call, Throwable t) {
                Log.e("Success","Success");
            }
        });
    }

    private void initiateService() {

        //TODO add or change methods accordingly
        QueryRequestEnvelope envelope = getRequestEnvelopeGeneral();

        Call<ResponseQueryEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchQueryData(envelope);
        call.enqueue(new Callback<ResponseQueryEnvelope>() {
            @Override
            public void onResponse(Call<ResponseQueryEnvelope> call, Response<ResponseQueryEnvelope> response) {
                Log.e("Success","Success");

                Data responseData = getParsedData(response.body().getBody().getQueryDataResponse().getData());
                setData(responseData);
                Toast.makeText(CashRequisitionApprovalActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseQueryEnvelope> call, Throwable t) {
                Log.e("Success","Success");
                Toast.makeText(CashRequisitionApprovalActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setData(Data responseData){
        Toast.makeText(CashRequisitionApprovalActivity.this,"Success",Toast.LENGTH_SHORT).show();
        reqBy.setText((responseData.getDataSet().get(0).get("requestedby")));
       // docNo .setText((responseData.getDataSet().get(0).get("requestedby")));
        reqType.setText((responseData.getDataSet().get(0).get("rqtype")));
        projct.setText((responseData.getDataSet().get(0).get("org")));
        descrp.setText((responseData.getDataSet().get(0).get("Description")));
        reqAmount.setText((responseData.getDataSet().get(0).get("reqamount")));
        reqDate.setText((responseData.getDataSet().get(0).get("reqdate")));
        balanceUtiAmount .setText((responseData.getDataSet().get(0).get("blncutilamount")));
        approvalAmnt.setText((responseData.getDataSet().get(0).get("ApprovedAmount")));
        frwdToUser .setText((responseData.getDataSet().get(0).get("BPartner")));
        frwdToRole .setText((responseData.getDataSet().get(0).get("BPartner")));

        if (responseData.getDataSet().get(0).get("verified")!=null){
            if (responseData.getDataSet().get(0).get("verified").equals("Y"))
            isVerified.setChecked(true);
            else
                isVerified.setChecked(false);
        }
        else{
            isVerified.setVisibility(View.INVISIBLE);
        }


        if (responseData.getDataSet().get(0).get("forward")!=null){
            if (responseData.getDataSet().get(0).get("forward").equals("Y"))
                isFrwd.setChecked(true);
            else
                isFrwd.setChecked(false);
        }
        else
            isFrwd.setVisibility(View.INVISIBLE);
        }

    @NonNull
    private UpdateRequestEnvelope getUpdateRequestEnvelopeGeneral(String approve , String val) {

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

       FieldData fieldData = new FieldData(approve,val);

        fieldDataList.add(fieldData);

        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_CashRequisitionAprroval_Update");
        modelCRUD.setRecordID("1000236");
       // modelCRUD.setTableName("MLV_cashreq");
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

        FieldData fieldData = new FieldData("SC_Request_ID","1000229");

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
