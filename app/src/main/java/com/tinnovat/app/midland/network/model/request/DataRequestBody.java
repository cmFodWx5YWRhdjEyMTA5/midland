package com.tinnovat.app.midland.network.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */


@Root(name = "soapenv:Body", strict = false)
public class DataRequestBody {

    //Todo changed
   // @Element(name = "_0:createUpdateData",required = false)
    @Element(name = "_0:queryData",required = false)
    private RequestData requestData;

    public RequestData getUsStatesRequestData() {
        return requestData;
    }

    public void setUsStatesRequestData(RequestData requestData) {
        this.requestData = requestData;
    }
}
