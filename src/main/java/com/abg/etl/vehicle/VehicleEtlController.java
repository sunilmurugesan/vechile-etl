package com.abg.etl.vehicle;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleEtlController {

    private CamelContext camelContext;
    private ProducerTemplate producerTemplate;

    @Autowired
    public VehicleEtlController(CamelContext camelContext, ProducerTemplate producerTemplate) {
        this.camelContext = camelContext;
        this.producerTemplate = producerTemplate;
    }

    @PostMapping(path = "/vehicle/load")
    public ResponseEntity<VehicleData> loadVehicleData(@RequestBody VehicleData vehicle) {
        Exchange exchangeRequest = ExchangeBuilder.anExchange(camelContext)
                .withBody(vehicle).build();
        Exchange exchangeResponse = producerTemplate.send("direct:start", exchangeRequest);

        return ResponseEntity.ok().body(vehicle);
    }
}
