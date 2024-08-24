package com.noxfilers.fuelApp.apiRes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuelInfoRes {
    private Date date;
    private String registrationNumber;
    private String agency;
    private String fuelType;
    private Double density;
    private Double engineEconomyUsage;
    private Double delta;
    private Double allowedDelta;
}
