package com.noxfilers.fuelApp.services;

import com.noxfilers.fuelApp.dtos.RegisterUserDTO;
import com.noxfilers.fuelApp.entities.CommonDetails;
import com.noxfilers.fuelApp.entities.RegisteredUser;
import com.noxfilers.fuelApp.repositories.BunkProviderRepo;
import com.noxfilers.fuelApp.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private BunkProviderRepo bunkProviderRepo;

    // find the user by mobile number
    public boolean findByNumber(String mobile){
        return registeredUserRepository.existsByMobile( mobile );
    }

    public RegisteredUser loadUserByNumber(String num){
        RegisteredUser user = registeredUserRepository.findByNumber(num);
        return user;
    }

    public boolean findByDisplayName( String displayName ){
        if( this.registeredUserRepository.findIdByDisplayName( displayName) == null ){
            return false;
        }
        return true;
    }
    public boolean mobileNoExist( String mobile ){
        return this.registeredUserRepository.existsByMobile( mobile );
    }
    public void SaveUser( RegisterUserDTO registerUserDTO ){

        RegisteredUser user = new RegisteredUser();

        user.setTitle( registerUserDTO.getTitle() );
        user.setFirstName( registerUserDTO.getFirstName() );
        user.setMiddleName( registerUserDTO.getMiddleName() );
        user.setLastName( registerUserDTO.getLastName() );
        user.setPreferredBunkType( registerUserDTO.getPreferredBunkType() );
        user.setMobile( registerUserDTO.getMobile() );
        user.setDisplayName( registerUserDTO.getDisplayName());
        CommonDetails commonDetails = new CommonDetails();
        commonDetails.setCreatedBy( registerUserDTO.getDisplayName() );
        commonDetails.setCreatedDate( new Date() );
        user.setCommonDetails(commonDetails);
        registeredUserRepository.save( user );
    }

    public List<String> getAllBunkProvider(){
        return this.bunkProviderRepo.getAllBunkProvider();
    }
}
