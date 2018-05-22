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

    @Element(name = "_0:DataRow", required = false)
    private DataRow dataRow;

    public ModelCRUD() {

        //Todo changed
        this.serviceType = "MLW_Alert";
        this.tableName = "MLV_Alert";
       // this.dataRow = new DataRow();
    }

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

    public DataRow getDataRow() {
        return dataRow;
    }

    public void setDataRow(DataRow dataRow) {
        this.dataRow = dataRow;
    }
}
