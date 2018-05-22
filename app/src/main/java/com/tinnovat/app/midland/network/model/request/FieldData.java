package com.tinnovat.app.midland.network.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "_0:field", strict = false)
public class FieldData {

    @Element(name = "_0:column", required = false)
    private String column;

    @Element(name = "_0:val", required = false)
    private String val;

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

    public FieldData() {
        this.column = "SC_UserTask_ID";
        this.val = "1000013";
    }
}
