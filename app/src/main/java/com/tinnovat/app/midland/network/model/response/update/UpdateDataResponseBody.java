package com.tinnovat.app.midland.network.model.response.update;

import com.tinnovat.app.midland.network.model.response.query.QueryResponseData;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "soap:Body", strict = false)
public class UpdateDataResponseBody {

    @Element(name = "updateDataResponse")
    @Namespace(prefix = "ns1")
    private UpdateResponseData queryDataResponse;

    public UpdateResponseData getQueryDataResponse() {
        return queryDataResponse;
    }

    public void setData(UpdateResponseData data) {
        this.queryDataResponse = data;
    }
}
