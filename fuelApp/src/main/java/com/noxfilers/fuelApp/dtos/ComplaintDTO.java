package com.noxfilers.fuelApp.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintDTO {
    private String email;
    private String phoneNo;
    private String date;
    private String agencyName;
    private String address;
    private String name;
    private String fuelType;
    private String fuel;
}
