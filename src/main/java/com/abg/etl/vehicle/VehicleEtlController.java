package com.abg.etl.vehicle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleEtlController {

    @PostMapping(path = "/vehicle/load")
    public ResponseEntity<VehicleData> loadVehicleData(@RequestBody VehicleData vehicle) {
        return ResponseEntity.ok().body(vehicle);
    }
}
