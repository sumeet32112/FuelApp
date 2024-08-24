package com.noxfilers.fuelApp.entities;


import javax.persistence.*;
import lombok.*;
import java.util.Date;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import com.noxfilers.fuelApp.entities.RegisteredVehicles;
import com.noxfilers.fuelApp.entities.BunkMaster;



@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "fuel_info")
public class FuelInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle")
    private RegisteredVehicles vehicle;

    @Column(name = "fuel_date", nullable = false)
    private Date fuelDate;

    @ManyToOne
    @JoinColumn(name = "bunk")
    private BunkMaster bunk;

    @Column(name = "fuel_density", precision = 6, scale = 2, nullable = false)
    private Double fuelDensity;

    @Column(name = "price_per_litre", precision = 6, scale = 2, nullable = false)
    private Double pricePerLitre;

    @Column(name = "filled_quantity", precision = 5, scale = 2, nullable = false)
    private Double filledQuantity;

    @Column(name = "total_amount", precision = 9, scale = 4, nullable = false)
    private Double totalAmount;

    @Column(name = "distance_covered", precision = 6, scale = 2, nullable = false)
    private Double distanceCovered;

    @Column(name = "vehicle_mileage", precision = 5, scale = 2, nullable = false)
    private Double vehicleMileage;

    @Column(name = "time_travelled", length = 10, nullable = false)
    private String timeTravelled;


    @Column(name = "engine_economy_usage", precision = 5, scale = 2, nullable = false)
    private Double engineEconomyUsage;

    @Column(name = "bunk_economy_usage", precision = 5, scale = 2, nullable = false)
    private Double bunkEconomyUsage;

    @Column(name = "delta", precision = 5, scale = 2, nullable = false)
    private Double delta;

    @Column(name = "allowed_delta", precision = 5, scale = 2, nullable = false)
    private Double allowedDelta;

    @Column(name = "comments", length = 200)
    private String comments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "common_details", referencedColumnName = "id")
    private CommonDetails commonDetails;

}
