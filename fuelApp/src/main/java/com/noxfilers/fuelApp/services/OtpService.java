package com.noxfilers.fuelApp.services;

import com.noxfilers.fuelApp.dtos.LoginDTO;
import com.noxfilers.fuelApp.dtos.OtpDTO;
import com.noxfilers.fuelApp.entities.RegisteredUser;
import com.noxfilers.fuelApp.entities.UserOtp;
import com.noxfilers.fuelApp.repositories.RegisteredUserRepository;
import com.noxfilers.fuelApp.repositories.UserOtpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {
    @Autowired
    private UserOtpRepo userOtpRepo;
    @Autowired
    private RegisteredUserRepository registeredUserRepository;
    public void generateAndStoreOtp(LoginDTO loginDTO){
        UserOtp existingOtp = userOtpRepo.findUserOtpByRegisteredUser(registeredUserRepository.findRegisteredUserByMobile(loginDTO.getNumber()));
        if (existingOtp != null) {
            userOtpRepo.delete(existingOtp);
        }
        String otp = generateOtp();
        UserOtp userOtp = new UserOtp();
        userOtp.setOtp(otp);
        userOtp.setRegisteredUser(registeredUserRepository.findRegisteredUserByMobile(loginDTO.getNumber()));
        userOtpRepo.save(userOtp);
        System.out.println(otp);
    }
    public boolean verifyOtp(OtpDTO otpDTO){
        String storedOtp = userOtpRepo.findOtpByRegisteredUser(registeredUserRepository.findRegisteredUserByMobile(otpDTO.getNumber()));
        if(storedOtp == null) return false;
        return (storedOtp.equals(otpDTO.getOtp()));
    }

    private String generateOtp(){
        Random rnd = new Random();
        String otp = "" + (10000 + rnd.nextInt(90000));
        return otp;
    }

}
