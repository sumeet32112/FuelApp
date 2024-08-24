package com.noxfilers.fuelApp.repositories;

import com.noxfilers.fuelApp.entities.RegisteredUser;
import com.noxfilers.fuelApp.entities.RegisteredVehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegisteredVehiclesRepository extends JpaRepository<RegisteredVehicles, Long> {

    boolean existsByRegistrationNumber(String registrationNumber);

    @Query("SELECT v FROM RegisteredVehicles v Where v.registrationNumber =:registrationNumber")
    RegisteredVehicles findRegisteredVehicle(@Param("registrationNumber") String registrationNumber);

    @Query("SELECT v.registrationNumber FROM RegisteredVehicles v")
    List<String> findAllRegistrationNumber();
    @Query("SELECT v FROM RegisteredVehicles v WHERE v.owner =:owner")
    List<RegisteredVehicles> findByOwner(@Param("owner") RegisteredUser owner);

    @Query("SELECT v.registrationNumber FROM RegisteredVehicles v WHERE v.owner =:owner")
    List<String> findListByNumber(@Param("owner") RegisteredUser owner);
}
