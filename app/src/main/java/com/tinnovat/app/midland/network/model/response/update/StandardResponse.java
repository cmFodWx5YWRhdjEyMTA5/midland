package com.tinnovat.app.midland.network.model.response.update;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ADMIN on 5/24/2018.
 */

@Root(name = "StandardResponse", strict = false)
@Namespace(reference = "http://idempiere.org/ADInterface/1_0")
public class StandardResponse {

    @Attribute(name = "RecordID", required = false)
    private String recordId;

    @Element(name = "outputFields", required = false)
    private OutputFields fieldData;

    @Element(name = "Error", required = false)
    private String errorMessage;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public OutputFields getFieldData() {
        return fieldData;
    }

    public void setFieldData(OutputFields fieldData) {
        this.fieldData = fieldData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
