package com.tinnovat.app.midland.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jackandphantom.blurimage.BlurImage;
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

public class CashUtilisationApprovalActivity extends BaseActivity {

    TextView notifiedBy;
    TextView reqRecord;
    TextView prjct;
    TextView descrptn;
    TextView reqValue;
    TextView amntUsed;
    TextView approvedAmnt;
    TextView frwdUser;
    TextView frwdRole;
    CheckBox frwrd;
    TextView approve;
    TextView reject;
    private String mIsApproved = null;
    private String mIsRejected = null;
    private String mRequestId = null;
   // ImageView imageView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_utilisation_approval);
        setTitle("Cash Utilisation Approval");

        notifiedBy = findViewById(R.id.notifiedBy);
        reqRecord = findViewById(R.id.reqRecord);
        prjct = findViewById(R.id.project);
        descrptn = findViewById(R.id.description);
        reqValue = findViewById(R.id.reqValue);
        amntUsed = findViewById(R.id.amntUsed);
        approvedAmnt = findViewById(R.id.apprdAmnt);
        frwdUser = findViewById(R.id.frwdToUser);
        frwdRole = findViewById(R.id.frwdToRole);
        frwrd = findViewById(R.id.checkBoxForward);
        approve = findViewById(R.id.approve);
        reject = findViewById(R.id.reject);
        //imageView = findViewById(R.id.imageView);
      //  BlurImage.with(getApplicationContext()).load(R.drawable.bg3).intensity(100).Async(true).into(imageView);

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
                            if (listItem.getColumn().equalsIgnoreCase("SC_Resourse_Utilization_ID"))
                                mRequestId = listItem.getVal();

                        }
                    }
                }
                if (mIsApproved != null && mIsRejected != null && mRequestId != null){
                    finish();
                    Toast.makeText(CashUtilisationApprovalActivity.this,"Completed "+mIsApproved+" : " +mIsRejected,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CashUtilisationApprovalActivity.this,"Network Error",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<UpdateResponseEnvelope> call, Throwable t) {
                endLoading();
                Toast.makeText(CashUtilisationApprovalActivity.this,"Network Error ",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void setData(Data responseData){
        Toast.makeText(CashUtilisationApprovalActivity.this,"Success",Toast.LENGTH_SHORT).show();
        notifiedBy.setText(responseData.getDataSet().get(0).get("notifiedby"));
        reqRecord.setText(responseData.getDataSet().get(0).get("requisitionrecord"));
        prjct.setText(responseData.getDataSet().get(0).get("ProjectName"));
        descrptn.setText(responseData.getDataSet().get(0).get("Description"));
        reqValue.setText(responseData.getDataSet().get(0).get("requesitionvalue"));
        amntUsed.setText(responseData.getDataSet().get(0).get("AmountUsed"));
        approvedAmnt.setText(responseData.getDataSet().get(0).get("ApprovedAmount"));
        frwdUser.setText(responseData.getDataSet().get(0).get("forwardtouser"));
        frwdRole.setText(responseData.getDataSet().get(0).get("forwardtorole"));
        if (responseData.getDataSet().get(0).get("isForward")!=null){
            if (responseData.getDataSet().get(0).get("isForward").equals("Y"))
                frwrd.setChecked(true);
            else
                frwrd.setChecked(false);
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

                Data responseData = getParsedData(response.body().getBody().getQueryDataResponse().getData());
                setData(responseData);
                Toast.makeText(CashUtilisationApprovalActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseQueryEnvelope> call, Throwable t) {
                endLoading();
                Toast.makeText(CashUtilisationApprovalActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
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
        loginRequest.setUser("SuperUser");
        loginRequest.setPass("System");
        loginRequest.setLang("en_US");
        loginRequest.setRoleID(1000000);
        loginRequest.setOrgID(1000000);
        loginRequest.setWarehouseID(1000004);
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
        modelCRUD.setServiceType("MLW_CashUtilizationAprroval_Update");
        modelCRUD.setRecordID("1000080");
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

        FieldData fieldData = new FieldData("SC_Resourse_Utilization_ID","1000080");

        fieldDataList.add(fieldData);

        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_CashUtilizationApproval_Web");
        modelCRUD.setTableName("MLV_CashUtilizationApproval_View");
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
