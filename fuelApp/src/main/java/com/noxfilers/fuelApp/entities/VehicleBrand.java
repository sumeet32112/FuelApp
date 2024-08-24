package com.noxfilers.fuelApp.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_brand")
public class VehicleBrand {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "vehicle_brand_name", length = 20)
    private String vehicleBrandName;

    @ManyToOne
    @JoinColumn( name = "vehicle_class" )
    private VehicleClass vehicleClass;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "common_details", referencedColumnName = "id")
    private CommonDetails commonDetails;

}
