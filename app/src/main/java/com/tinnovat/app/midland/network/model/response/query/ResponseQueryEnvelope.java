package com.tinnovat.app.midland.network.model.response.query;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "Envelope", strict = false)
@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap")
public class ResponseQueryEnvelope {

    @Element(required = false, name = "Body")
    @Namespace(prefix = "soap")
    private QueryDataResponseBody body;

    public QueryDataResponseBody getBody() {
        return body;
    }

    public void setBody(QueryDataResponseBody body) {
        this.body = body;
    }
}
