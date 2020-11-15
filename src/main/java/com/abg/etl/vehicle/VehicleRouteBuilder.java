package com.abg.etl.vehicle;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleRouteBuilder extends RouteBuilder {

    @Autowired
    public VehicleRouteBuilder() {
    }

    @Override
    public void configure() {

        onException(Exception.class)
                .log("${exception.stacktrace}")
                .log("Exception Occurred! Vehicle data will no longer be processed!")
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
                .setBody(exceptionMessage());

        from("direct:start")
                .marshal().json(JsonLibrary.Jackson)
                .to("json-validator:vehicle-input-schema.json")
                .to("log:output");
    }
}
