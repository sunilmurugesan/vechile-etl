package com.abg.etl.vehicle;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VehicleData {
    private String category;
    private String make;
    private String model;
    private int mileage;
    private int year;

    public void VehicleData() {

    }
}

