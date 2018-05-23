package com.tinnovat.app.midland.network.model.request.update;

import com.tinnovat.app.midland.network.model.request.ModelCRUDRequest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "_0:updateData", strict = false)
public class UpdateRequestData {

    @Element(name = "_0:ModelCRUDRequest", required = false)
    private ModelCRUDRequest cduRequest;

    public ModelCRUDRequest getCduRequest() {
        return cduRequest;
    }

    public void setCduRequest(ModelCRUDRequest cduRequest) {
        this.cduRequest = cduRequest;
    }
}
