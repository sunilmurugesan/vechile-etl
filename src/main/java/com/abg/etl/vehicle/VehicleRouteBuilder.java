package com.abg.etl.vehicle;

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
        from("direct:start")
                .marshal().json(JsonLibrary.Jackson)
                .to("json-validator:vehicle-input-schema.json")
                .to("log:output");
    }
}
