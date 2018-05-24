package com.tinnovat.app.midland.Activity;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.tinnovat.app.midland.model.Data;
import com.tinnovat.app.midland.network.ApiClient;
import com.tinnovat.app.midland.network.ApiInterface;
import com.tinnovat.app.midland.network.model.request.ADLoginRequest;
import com.tinnovat.app.midland.network.model.request.DataRowRequest;
import com.tinnovat.app.midland.network.model.request.FieldData;
import com.tinnovat.app.midland.network.model.request.ModelCRUD;
import com.tinnovat.app.midland.network.model.request.ModelCRUDRequest;
import com.tinnovat.app.midland.network.model.request.query.QueryDataRequestBody;
import com.tinnovat.app.midland.network.model.request.query.QueryRequestData;
import com.tinnovat.app.midland.network.model.request.query.QueryRequestEnvelope;
import com.tinnovat.app.midland.network.model.response.query.DataRow;
import com.tinnovat.app.midland.network.model.response.query.FieldDataResponse;
import com.tinnovat.app.midland.network.model.response.query.ResponseQueryEnvelope;
import com.tinnovat.app.midland.network.model.response.query.WindowTabData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentApprovalActivity extends AppCompatActivity {

    TextView docNo;
    TextView org;
    TextView bankAc;
    TextView amount;
    TextView partner;
    TextView paymentDate;
    TextView modeOfPayment;
    TextView description;
    TextView paymentId;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_approval);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Payment Approval");

        paymentId = findViewById(R.id.paymentId);
        docNo = findViewById(R.id.docNo);
        org = findViewById(R.id.org);
        bankAc = findViewById(R.id.bankAc);
        amount = findViewById(R.id.amount);
        partner = findViewById(R.id.partner);
        paymentDate = findViewById(R.id.paymentDate);
        modeOfPayment = findViewById(R.id.modeOfPayment);
        description = findViewById(R.id.description);

        initiateService();
    }

    public void setData(Data responseData){
        Toast.makeText(PaymentApprovalActivity.this,"Success",Toast.LENGTH_SHORT).show();
        paymentId.setText((responseData.getDataSet().get(0).get("C_Payment_ID")));
        docNo.setText((responseData.getDataSet().get(0).get("DocumentNo")));
        org.setText((responseData.getDataSet().get(0).get("organization")));
        bankAc.setText((responseData.getDataSet().get(0).get("bankacc")));
        amount.setText((responseData.getDataSet().get(0).get("PayAmt")));
        partner.setText((responseData.getDataSet().get(0).get("businesspartner")));
        paymentDate.setText((responseData.getDataSet().get(0).get("DateTrx")));
        modeOfPayment.setText((responseData.getDataSet().get(0).get("modeofpayment")));
        description.setText((responseData.getDataSet().get(0).get("Description")));
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
                Toast.makeText(PaymentApprovalActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseQueryEnvelope> call, Throwable t) {
                Log.e("Success","Success");
                Toast.makeText(PaymentApprovalActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });

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

        FieldData fieldData = new FieldData("C_Payment_ID","1000039");

        fieldDataList.add(fieldData);

        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_Payment_Approval_View");
        modelCRUD.setTableName("MLV_PaymentApproval_View");
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

