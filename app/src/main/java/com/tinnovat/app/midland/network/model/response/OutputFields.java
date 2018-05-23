package com.tinnovat.app.midland.network.model.response;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ADMIN on 5/24/2018.
 */

@Root(name = "outputFields", strict = false)
public class OutputFields {

    @ElementList(name = "outputField",required = false, inline = true)
    private List<OutputField> outputFieldList;

    public List<OutputField> getOutputFieldList() {
        return outputFieldList;
    }

    public void setOutputFieldList(List<OutputField> outputFieldList) {
        this.outputFieldList = outputFieldList;
    }
}
