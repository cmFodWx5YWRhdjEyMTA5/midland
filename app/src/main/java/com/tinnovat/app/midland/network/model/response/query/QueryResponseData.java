package com.tinnovat.app.midland.network.model.response.query;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "ns1:queryDataResponse", strict = false)
@Namespace(reference = "http://idempiere.org/ADInterface/1_0", prefix = "ns1")
public class QueryResponseData {

    @Element(name = "WindowTabData", required = false)
    private com.tinnovat.app.midland.network.model.response.query.WindowTabData WindowTabData;

    public WindowTabData getData() {
        return WindowTabData;
    }

    public void setData(WindowTabData data) {
        this.WindowTabData = data;
    }
}
