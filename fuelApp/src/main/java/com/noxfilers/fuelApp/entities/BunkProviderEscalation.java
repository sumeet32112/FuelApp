package com.noxfilers.fuelApp.entities;


//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.*;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name="bunk_provider_escalation")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BunkProviderEscalation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bunk_provider")
    @NotNull
    private BunkProvider bunkProvider;

    @Column(name = "escalation_level")
    @NotNull
    private Integer escalationLevel;

    @Column(name = "escalation_mail", length = 35)
    @NotNull
    private String escalationMail;

    @Column(name = "escalation_phone", length = 15)
    @NotNull
    private String escalationPhone;

    @Column(name = "designation", length = 25)
    @NotNull
    private String designation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "common_details", referencedColumnName = "id")
    private CommonDetails commonDetails;
}
