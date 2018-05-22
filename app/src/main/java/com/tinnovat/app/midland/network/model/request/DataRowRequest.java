package com.tinnovat.app.midland.network.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "_0:DataRow", strict = false)
public class DataRowRequest {

    public List<FieldData> getField() {
        return field;
    }

    public void setField(List<FieldData> field) {
        this.field = field;
    }

    @ElementList(name = "_0:field", required = false, inline = true)
    private List<FieldData> field;
}
