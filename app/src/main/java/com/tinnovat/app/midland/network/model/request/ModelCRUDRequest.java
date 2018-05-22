package com.tinnovat.app.midland.network.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "_0:ModelCRUDRequest", strict = false)
public class ModelCRUDRequest {

    @Element(name = "_0:ADLoginRequest", required = false)
    private ADLoginRequest loginRequest;

    @Element(name = "_0:ModelCRUD", required = false)
    private ModelCRUD modelCRUD;

    public ADLoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(ADLoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }

    public ModelCRUD getModelCRUD() {
        return modelCRUD;
    }

    public void setModelCRUD(ModelCRUD modelCRUD) {
        this.modelCRUD = modelCRUD;
    }

    public void setModelCRUD() {
        this.modelCRUD = new ModelCRUD();
    }
}
