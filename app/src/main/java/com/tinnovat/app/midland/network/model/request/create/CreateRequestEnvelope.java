package com.tinnovat.app.midland.network.model.request.create;

import com.tinnovat.app.midland.network.model.request.query.QueryDataRequestBody;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "soapenv:Envelope")
@NamespaceList({
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soapenv"),
        @Namespace(reference = "http://idempiere.org/ADInterface/1_0", prefix = "_0"),
})
public class CreateRequestEnvelope {

    @Element(name = "soapenv:Body", required = false)
    private CreateDataRequestBody body;

    public CreateDataRequestBody getBody() {
        return body;
    }

    public void setBody(CreateDataRequestBody body) {
        this.body = body;
    }
}
