package com.noxfilers.fuelApp.entities;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_otp")
public class UserOtp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "otp")
    private String otp;
    @OneToOne
    @JoinColumn(name = "registeredUser", referencedColumnName = "id")
    private RegisteredUser registeredUser;
}
