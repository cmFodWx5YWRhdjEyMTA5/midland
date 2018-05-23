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
    CheckBox isVerified;
    CheckBox isFrwd;


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


        initiateService();
    }
    private void initiateService() {

        //TODO add or change methods accordingly
        RequestEnvelope envelope = getRequestEnvelopeGeneral();

        Call<ResponseEnvelope> call = ApiClient.getApiClient().create(ApiInterface.class).fetchData(envelope);
        call.enqueue(new Callback<ResponseEnvelope>() {
            @Override
            public void onResponse(Call<ResponseEnvelope> call, Response<ResponseEnvelope> response) {
                Log.e("Success","Success");

                Data responseData = getParsedData(response.body().getBody().getQueryDataResponse().getData());
                setData(responseData);
                Toast.makeText(CashRequisitionApprovalActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseEnvelope> call, Throwable t) {
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
