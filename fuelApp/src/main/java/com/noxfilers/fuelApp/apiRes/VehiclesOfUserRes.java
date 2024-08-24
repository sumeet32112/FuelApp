package com.noxfilers.fuelApp.apiRes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehiclesOfUserRes {
    private String registrationNumber;
    private String vehicleOwner;
    private String vehicleBrand;
    private String fuelType;
    private String monthManufactured;
    private int yearManufactured;
    private int engineCapacity;
    private String colour;

}
