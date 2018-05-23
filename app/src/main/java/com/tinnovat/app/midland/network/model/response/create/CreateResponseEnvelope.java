package com.tinnovat.app.midland.network.model.response.create;

import com.tinnovat.app.midland.network.model.response.update.UpdateDataResponseBody;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "Envelope", strict = false)
@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap")
public class CreateResponseEnvelope {

    @Element(required = false, name = "Body")
    @Namespace(prefix = "soap")
    private CreateDataResponseBody body;

    public CreateDataResponseBody getBody() {
        return body;
    }

    public void setBody(CreateDataResponseBody body) {
        this.body = body;
    }
}
