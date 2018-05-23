package com.tinnovat.app.midland.network.model.response.query;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "soap:Body", strict = false)
public class QueryDataResponseBody {

    @Element(name = "queryDataResponse")
    @Namespace(prefix = "ns1")
    private QueryResponseData queryDataResponse;

    public QueryResponseData getQueryDataResponse() {
        return queryDataResponse;
    }

    public void setData(QueryResponseData data) {
        this.queryDataResponse = data;
    }
}
