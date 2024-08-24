package com.noxfilers.fuelApp.repositories;

import com.noxfilers.fuelApp.entities.FuelInfo;
import com.noxfilers.fuelApp.entities.RegisteredVehicles;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelInfoRepository extends JpaRepository<FuelInfo, Long> {
    @Query("SELECT f FROM FuelInfo f WHERE f.vehicle=:vehicle")
    List<FuelInfo> findByVehicle(@Param("vehicle") RegisteredVehicles vehicle);
}