package com.noxfilers.fuelApp.repositories;

import com.noxfilers.fuelApp.entities.RegisteredUser;
import com.noxfilers.fuelApp.entities.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserOtpRepo extends JpaRepository<UserOtp,Long> {
    @Query("SELECT u.otp FROM UserOtp u WHERE u.registeredUser=:registeredUser")
    String findOtpByRegisteredUser(@Param("registeredUser") RegisteredUser registeredUser);

    void deleteByRegisteredUser(RegisteredUser registeredUser);


    @Query("SELECT u FROM UserOtp u WHERE u.registeredUser=:registeredUser")
    UserOtp findUserOtpByRegisteredUser(@Param("registeredUser") RegisteredUser registeredUser);
}
