package com.tinnovat.app.midland.network.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "_0:DataRow", strict = false)
class DataRow {

    public FieldData getField() {
        return field;
    }

    public void setField(FieldData field) {
        this.field = field;
    }

    @Element(name = "_0:field", required = false)
    private FieldData field;

    public DataRow() {
        this.field = new FieldData();
    }
}
