package com.noxfilers.fuelApp.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuelInfoDTO {
    private Double fuelDensity;
    private Double pricePerLitre;
    private Double filledQuantity;
    private Double vehicleMileage;
    private Double distanceCovered;
    private Double allowedDelta;
    private String timeTravelled;
    private String vehicle;
    private String agency;
    private Date dateOfFueling;
    private String comments;
}

