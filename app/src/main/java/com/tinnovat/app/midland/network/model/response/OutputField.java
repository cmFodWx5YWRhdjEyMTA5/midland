package com.tinnovat.app.midland.network.model.response;

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

    @Attribute(name = "Text", required = false)
    private String text;

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public String getIsRejected() {
        return isRejected;
    }

    public void setIsRejected(String isRejected) {
        this.isRejected = isRejected;
    }

    public String getRequestID() {
        return RequestID;
    }

    public void setRequestID(String requestID) {
        RequestID = requestID;
    }

    //Todo changed
    @Attribute(name = "IsApproved", required = false)
    private String isApproved;

    @Attribute(name = "SC_Rejected", required = false)
    private String isRejected;

    @Attribute(name = "SC_Request_ID", required = false)
    private String RequestID;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
