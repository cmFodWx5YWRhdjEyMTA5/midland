package com.tinnovat.app.midland.network.model.response.update;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "ns1:updateDataResponse", strict = false)
@Namespace(reference = "http://idempiere.org/ADInterface/1_0", prefix = "ns1")
public class UpdateResponseData {

    @Element(name = "StandardResponse", required = false)
    private StandardResponse standardResponse;

    public StandardResponse getData() {
        return standardResponse;
    }

    public void setData(StandardResponse data) {
        this.standardResponse = data;
    }
}
