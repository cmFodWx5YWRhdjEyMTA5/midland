package com.tinnovat.app.midland.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class PurchaseOrderApprovalActivity extends AppCompatActivity {

    TextView org;
    TextView docNo;
    TextView partner;
    TextView poDate;
    TextView descptn;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order_approval);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Purchase Order Approval");

        org = findViewById(R.id.org);
        docNo = findViewById(R.id.docNo);
        partner = findViewById(R.id.patner);
        poDate = findViewById(R.id.poDate);
        descptn = findViewById(R.id.descrptn);


        initiateService();
    }
    public void setData(Data responseData){
        Toast.makeText(PurchaseOrderApprovalActivity.this,"Success",Toast.LENGTH_SHORT).show();
        org.setText(responseData.getDataSet().get(0).get("organisation"));
        docNo.setText(responseData.getDataSet().get(0).get("DocumentNo"));
        partner.setText(responseData.getDataSet().get(0).get("buisnesspartner"));
        poDate.setText(responseData.getDataSet().get(0).get("DateOrdered"));
      //  descptn.setText(responseData.getDataSet().get(0).get("OrgName"));



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
                Toast.makeText(PurchaseOrderApprovalActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseEnvelope> call, Throwable t) {
                Log.e("Success","Success");
                Toast.makeText(PurchaseOrderApprovalActivity.this,"Failed",Toast.LENGTH_SHORT).show();
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

        FieldData fieldData = new FieldData("C_Order_ID","1000010");

        fieldDataList.add(fieldData);

        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_purchaceorderview_view");
        modelCRUD.setTableName("mlv_purchaceorderview_view");
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
