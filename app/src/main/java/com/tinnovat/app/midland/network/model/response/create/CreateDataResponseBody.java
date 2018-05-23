package com.tinnovat.app.midland.network.model.response.create;

import com.tinnovat.app.midland.network.model.response.update.UpdateResponseData;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "soap:Body", strict = false)
public class CreateDataResponseBody {

    @Element(name = "createUpdateDataResponse")
    @Namespace(prefix = "ns1")
    private CreateResponseData queryDataResponse;

    public CreateResponseData getQueryDataResponse() {
        return queryDataResponse;
    }

    public void setData(CreateResponseData data) {
        this.queryDataResponse = data;
    }
}
