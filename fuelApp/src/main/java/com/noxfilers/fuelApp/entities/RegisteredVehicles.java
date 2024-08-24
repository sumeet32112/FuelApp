package com.noxfilers.fuelApp.entities;


import javax.persistence.*;
import lombok.*;
import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Service;
import com.noxfilers.fuelApp.entities.RegisteredUser;
import com.noxfilers.fuelApp.entities.VehicleBrand;
import com.noxfilers.fuelApp.entities.FuelType;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registered_vehicles")
public class RegisteredVehicles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "vehicle_class")
    private VehicleClass  vehicleClass;

    @Column(name = "registration_number", length = 15, nullable = false, unique = true)
    private String registrationNumber;


    @ManyToOne
    @JoinColumn(name = "owner")
    private RegisteredUser owner;

    @ManyToOne
    @JoinColumn(name = "vehicle_maker")
    private VehicleBrand vehicleMaker;

    @Column(name = "vehicle_model_name", length = 25, nullable = false)
    private String vehicleModelName;

    @ManyToOne
    @JoinColumn(name = "fuel_type",nullable = false)
    private FuelType fuelType;

    @Column(name = "month_manufactured", length = 15, nullable = false)
    private String monthManufactured;

    @Column(name = "year_manufactured", nullable = false)
    private int yearManufactured;

    @Column(name = "date_of_purchase", nullable = false)
    private Date dateOfPurchase;

    @Column(name = "engine_capacity", nullable = false)
    private int engineCapacity;

    @Column(name = "colour", length = 15, nullable = false)
    private String colour;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "common_details", referencedColumnName = "id")
    private CommonDetails commonDetails;
}
