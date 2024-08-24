package com.noxfilers.fuelApp.entities;

import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_class")
public class VehicleClass {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "vehicle_class", length = 15, unique = true)
    private String vehicleClass;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "common_details", referencedColumnName = "id")
    private CommonDetails commonDetails;

}
