package com.noxfilers.fuelApp.entities;

//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="bunk_provider")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BunkProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "provider_code", length = 4,unique = true)
    @NotNull
    private String providerCode;

    @Column(name = "provider_name", length = 35,unique = true)
    @NotNull
    private String providerName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "common_details", referencedColumnName = "id")
    private CommonDetails commonDetails;
}
