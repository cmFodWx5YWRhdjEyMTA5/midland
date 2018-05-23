package com.tinnovat.app.midland.network.model.request.query;

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
public class QueryRequestEnvelope {

    @Element(name = "soapenv:Body", required = false)
    private QueryDataRequestBody body;

    public QueryDataRequestBody getBody() {
        return body;
    }

    public void setBody(QueryDataRequestBody body) {
        this.body = body;
    }
}
