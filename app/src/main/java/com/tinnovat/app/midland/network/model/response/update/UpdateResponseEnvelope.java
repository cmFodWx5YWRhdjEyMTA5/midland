package com.tinnovat.app.midland.network.model.response.update;

import com.tinnovat.app.midland.network.model.response.query.QueryDataResponseBody;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "Envelope", strict = false)
@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap")
public class UpdateResponseEnvelope {

    @Element(required = false, name = "Body")
    @Namespace(prefix = "soap")
    private UpdateDataResponseBody body;

    public UpdateDataResponseBody getBody() {
        return body;
    }

    public void setBody(UpdateDataResponseBody body) {
        this.body = body;
    }
}
