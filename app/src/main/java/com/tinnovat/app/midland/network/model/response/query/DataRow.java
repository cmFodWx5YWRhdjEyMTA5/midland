package com.tinnovat.app.midland.network.model.response.query;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "DataRow", strict = false)
public class DataRow {

    @ElementList(name = "field", required = false,  inline = true)
    private List<FieldDataResponse> fieldData;

    public List<FieldDataResponse> getFieldData() {
        return fieldData;
    }

    public void setFieldData(List<FieldDataResponse> fieldData) {
        this.fieldData = fieldData;
    }
}
