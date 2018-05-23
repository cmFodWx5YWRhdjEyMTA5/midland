package com.tinnovat.app.midland.network.model.request.create;

import com.tinnovat.app.midland.network.model.request.query.QueryRequestData;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */


@Root(name = "soapenv:Body", strict = false)
public class CreateDataRequestBody {

    //Todo changed
   // @Element(name = "_0:createUpdateData",required = false)
    @Element(name = "_0:createUpdateData",required = false)
    private CreateRequestData queryRequestData;

    public CreateRequestData getUsStatesRequestData() {
        return queryRequestData;
    }

    public void setUsStatesRequestData(CreateRequestData queryRequestData) {
        this.queryRequestData = queryRequestData;
    }
}
