package com.tinnovat.app.midland.network.model.response.query;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "field", strict = false)
@Namespace(reference = "0", prefix = "StartRow")
public class FieldDataResponse {

    @Attribute(name = "column", required = false)
    private String column;

    @Element(name = "val", required = false)
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

}
