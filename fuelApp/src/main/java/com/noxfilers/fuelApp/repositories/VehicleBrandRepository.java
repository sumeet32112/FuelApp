package com.noxfilers.fuelApp.repositories;

import com.noxfilers.fuelApp.entities.VehicleBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleBrandRepository extends JpaRepository<VehicleBrand, Long> {
    @Query("SELECT b.id FROM VehicleBrand b WHERE b.vehicleBrandName =:vehicleBrandName")
    Long findIdByVehicleBrandName(@Param("vehicleBrandName")String vehicleBrandName);

    @Query("SELECT b FROM VehicleBrand b WHERE b.vehicleBrandName =:vehicleBrandName")
    VehicleBrand findBrandByVehicleBrandName(@Param("vehicleBrandName")String vehicleBrandName);
    @Query("SELECT b.vehicleBrandName FROM VehicleBrand b")
    List<String> findAllVehicleBrand();
    @Query("SELECT b.vehicleBrandName FROM VehicleBrand b WHERE b.id=:id")
    String findVehicleBrandNameById(@Param("id") Long id);
}
