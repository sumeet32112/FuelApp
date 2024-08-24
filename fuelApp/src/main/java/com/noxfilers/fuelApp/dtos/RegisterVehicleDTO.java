package com.noxfilers.fuelApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVehicleDTO {
    private String vehicleClass;
    private String registrationNumber;
    private String owner;
    private String brand;
    private String model;
    private String fuelType;
    private String monthManufactured;
    private int yearManufactured;
    private Date dateOfPurchase;
    private int engineCapacity;
    private String colour;
}
