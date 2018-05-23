package com.tinnovat.app.midland.network.model.request.update;

import com.tinnovat.app.midland.network.model.request.query.QueryRequestData;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */


@Root(name = "soapenv:Body", strict = false)
public class UpdateDataRequestBody {

    //Todo changed
   // @Element(name = "_0:createUpdateData",required = false)
    @Element(name = "_0:updateData",required = false)
    private UpdateRequestData queryRequestData;

    public UpdateRequestData getUsStatesRequestData() {
        return queryRequestData;
    }

    public void setUsStatesRequestData(UpdateRequestData queryRequestData) {
        this.queryRequestData = queryRequestData;
    }
}
