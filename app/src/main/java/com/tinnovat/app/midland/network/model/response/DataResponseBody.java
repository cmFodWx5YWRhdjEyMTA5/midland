package com.tinnovat.app.midland.network.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;

/**
 * Created by ADMIN on 5/11/2018.
 */

//@Root(name = "soap:Body", strict = false)
public class DataResponseBody {

    @Element(name = "queryDataResponse")
    @Namespace(prefix = "ns1")
    private ResponseData queryDataResponse;

    public ResponseData getQueryDataResponse() {
        return queryDataResponse;
    }

    public void setData(ResponseData data) {
        this.queryDataResponse = data;
    }
}
