package com.noxfilers.fuelApp.dtos;

import com.noxfilers.fuelApp.entities.BunkProvider;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserDTO {
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String displayName;
    private BunkProvider preferredBunkType;
    private String mobile;

}
