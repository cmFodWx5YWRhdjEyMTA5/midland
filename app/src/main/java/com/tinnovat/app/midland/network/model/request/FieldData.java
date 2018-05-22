package com.tinnovat.app.midland.network.model.request;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "_0:field", strict = false)
public class FieldData {

    @Attribute(name = "column", required = false)
    private String column;

    @Element(name = "_0:val", required = false)
    private String val;

    public FieldData(String column, String val) {
        this.column = column;
        this.val = val;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
