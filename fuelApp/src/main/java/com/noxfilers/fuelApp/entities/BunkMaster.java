package com.noxfilers.fuelApp.entities;

//import jakarta.persistence.*;
//import jakarta.persistence.Entity;
import javax.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="bunk_master")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BunkMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn (name = "bunk_provider")
    @NotNull
    private BunkProvider bunkProvider;

    @Column(name = "agency_name", length = 50,unique = true)
    @NotNull
    private String agencyName;

    @Column(name = "owner_manager_name", length = 50)
    @NotNull
    private String ownerManagerName;

    @Column(name = "contact_mail", length = 35)
    @NotNull
    private String contactMail;

    @Column(name = "contact_phone", length = 15)
    @NotNull
    private String contactPhone;

    @Column(name = "gps_coordinates_latitude", length = 25)
    @NotNull
    private String gpsCoordinatesLatitude;

    @Column(name = "gps_coordinates_longitude", length = 25)
    @NotNull
    private String gpsCoordinatesLongitude;

    @Column(name = "agency_area", length = 25)
    @NotNull
    private String agencyArea;

    @Column(name = "agency_address", columnDefinition = "TEXT")
    @NotNull
    private String agencyAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "common_details", referencedColumnName = "id")
    private CommonDetails commonDetails;

}
