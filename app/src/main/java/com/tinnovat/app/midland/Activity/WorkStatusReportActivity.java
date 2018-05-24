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

public class WorkStatusReportActivity extends AppCompatActivity {

    TextView org;
    TextView project;
    TextView subProject1;
    TextView subProject2;
    TextView phase;
    TextView task;
    TextView subTask;
    TextView workName;
    TextView actualAmount;
    TextView actualRate;
    TextView estimatedAmount;
    TextView rate;
    TextView totalEstMeasurementSummary;
    TextView totalActualMeasurementSummary;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_status_report);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Work Status Report");

        org = findViewById(R.id.org);
        project = findViewById(R.id.project);
        subProject1 = findViewById(R.id.subPro1);
        subProject2 = findViewById(R.id.subPro2);
        phase = findViewById(R.id.phase);
        task = findViewById(R.id.task);
        subTask = findViewById(R.id.subTask);
        workName = findViewById(R.id.workName);
        actualAmount = findViewById(R.id.actualAmount);
        actualRate = findViewById(R.id.actualRate);
        estimatedAmount = findViewById(R.id.estimatedAmount);
        rate = findViewById(R.id.rate);
        totalEstMeasurementSummary = findViewById(R.id.totalEstMeasurementSummary);
        totalActualMeasurementSummary = findViewById(R.id.totalActualMeasurementSummary);

        initiateService();
    }
    public void setData(Data responseData){
        Toast.makeText(WorkStatusReportActivity.this,"Success",Toast.LENGTH_SHORT).show();
        org.setText(responseData.getDataSet().get(0).get("organisation"));
        project.setText(responseData.getDataSet().get(0).get("project"));
        subProject1.setText(responseData.getDataSet().get(0).get("subproject1"));
        subProject2.setText(responseData.getDataSet().get(0).get("subproject2"));
        phase.setText(responseData.getDataSet().get(0).get("phase"));
        task.setText(responseData.getDataSet().get(0).get("task"));
        subTask.setText(responseData.getDataSet().get(0).get("subtask"));
        workName.setText(responseData.getDataSet().get(0).get("Name"));
        actualAmount.setText(responseData.getDataSet().get(0).get("SC_ActualAmount"));
        actualRate.setText(responseData.getDataSet().get(0).get("SC_ActualRate"));
        estimatedAmount.setText(responseData.getDataSet().get(0).get("SC_EstimatedAmount"));
        rate.setText(responseData.getDataSet().get(0).get("SC_Rate"));
        totalEstMeasurementSummary.setText(responseData.getDataSet().get(0).get("SC_TotalEstMeasurementSummary"));
        totalActualMeasurementSummary.setText(responseData.getDataSet().get(0).get("SC_TtlActualMeasurementSummary"));





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
                Toast.makeText(WorkStatusReportActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseQueryEnvelope> call, Throwable t) {
                Log.e("Success","Success");
                Toast.makeText(WorkStatusReportActivity.this,"Failed",Toast.LENGTH_SHORT).show();
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

        FieldData fieldData = new FieldData("SC_Work_ID","1000649");

        fieldDataList.add(fieldData);

        dataRow.setField(fieldDataList);

        // Set modelCurd
        modelCRUD.setDataRow(dataRow);
        modelCRUD.setServiceType("MLW_WorkStatusReport");
        modelCRUD.setTableName("mlv_WorkStatusReportview_View");
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

