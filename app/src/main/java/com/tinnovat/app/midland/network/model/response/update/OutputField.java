package com.tinnovat.app.midland.network.model.response.update;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ADMIN on 5/24/2018.
 */

@Root(name = "outputField", strict = false)
public class OutputField {

    @Attribute(name = "column", required = false)
    private String column;

    @Attribute(name = "value", required = false)
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
