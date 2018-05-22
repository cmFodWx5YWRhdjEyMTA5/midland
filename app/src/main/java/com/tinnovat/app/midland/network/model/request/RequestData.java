package com.tinnovat.app.midland.network.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "_0:queryData", strict = false)
public class RequestData {

    @Element(name = "_0:ModelCRUDRequest", required = false)
    private ModelCRUDRequest cduRequest;

    public ModelCRUDRequest getCduRequest() {
        return cduRequest;
    }

    public void setCduRequest(ModelCRUDRequest cduRequest) {
        this.cduRequest = cduRequest;
    }
}
