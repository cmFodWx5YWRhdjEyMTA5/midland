package com.tinnovat.app.midland.network.model.request.query;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */


@Root(name = "soapenv:Body", strict = false)
public class QueryDataRequestBody {

    //Todo changed
   // @Element(name = "_0:createUpdateData",required = false)
    @Element(name = "_0:queryData",required = false)
    private QueryRequestData queryRequestData;

    public QueryRequestData getUsStatesRequestData() {
        return queryRequestData;
    }

    public void setUsStatesRequestData(QueryRequestData queryRequestData) {
        this.queryRequestData = queryRequestData;
    }
}
