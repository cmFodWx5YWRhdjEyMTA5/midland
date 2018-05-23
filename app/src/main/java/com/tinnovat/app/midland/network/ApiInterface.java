/*
 * Copyright 2016. Alejandro Sánchez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.tinnovat.app.midland.network;

import com.tinnovat.app.midland.network.model.request.create.CreateRequestEnvelope;
import com.tinnovat.app.midland.network.model.request.query.QueryRequestEnvelope;
import com.tinnovat.app.midland.network.model.request.update.UpdateRequestEnvelope;
import com.tinnovat.app.midland.network.model.response.query.ResponseQueryEnvelope;
import com.tinnovat.app.midland.network.model.response.update.UpdateResponseEnvelope;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 15/6/16.
 */
public interface ApiInterface {

    @Headers({
            "Content-Type: text/xml",
            "Accept-Charset: utf-8"
    })
    @POST("/ADInterface/services/ModelADService?wsdl")
    Call<ResponseQueryEnvelope> fetchQueryData(@Body QueryRequestEnvelope body);

    @Headers({
            "Content-Type: text/xml",
            "Accept-Charset: utf-8"
    })
    @POST("/ADInterface/services/ModelADService?wsdl")
    Call<ResponseQueryEnvelope> fetchCreateData(@Body CreateRequestEnvelope body);

    @Headers({
            "Content-Type: text/xml",
            "Accept-Charset: utf-8"
    })
    @POST("/ADInterface/services/ModelADService?wsdl")
    Call<UpdateResponseEnvelope> fetchUpdateData(@Body UpdateRequestEnvelope body);

}
