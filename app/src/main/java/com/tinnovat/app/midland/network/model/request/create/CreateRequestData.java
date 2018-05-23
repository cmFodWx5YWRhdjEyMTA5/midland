package com.tinnovat.app.midland.network.model.request.create;

import com.tinnovat.app.midland.network.model.request.ModelCRUDRequest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "_0:createUpdateData", strict = false)
public class CreateRequestData {

    @Element(name = "_0:ModelCRUDRequest", required = false)
    private ModelCRUDRequest cduRequest;

    public ModelCRUDRequest getCduRequest() {
        return cduRequest;
    }

    public void setCduRequest(ModelCRUDRequest cduRequest) {
        this.cduRequest = cduRequest;
    }
}
