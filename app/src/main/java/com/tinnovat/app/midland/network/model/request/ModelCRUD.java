package com.tinnovat.app.midland.network.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "_0:ADLoginRequest", strict = false)
public class ModelCRUD {

    @Element(name = "_0:serviceType", required = false)
    private String serviceType;

    @Element(name = "_0:TableName", required = false)
    private String tableName;

    @Element(name = "_0:Action", required = false)
    private String action;

    @Element(name = "_0:DataRow", required = false)
    private DataRowRequest dataRow;

    @Element(name = "_0:RecordID", required = false)
    private String recordID;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public DataRowRequest getDataRow() {
        return dataRow;
    }

    public void setDataRow(DataRowRequest dataRow) {
        this.dataRow = dataRow;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getRecordID() {
        return recordID;
    }
}
