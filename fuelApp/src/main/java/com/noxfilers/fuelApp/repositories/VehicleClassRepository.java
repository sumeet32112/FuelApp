package com.noxfilers.fuelApp.repositories;

import com.noxfilers.fuelApp.entities.VehicleClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleClassRepository extends JpaRepository<VehicleClass, Long> {
    @Query("SELECT v.id FROM VehicleClass v WHERE v.vehicleClass =:vehicleClass")
    Long findIdByVehicleClass(@Param("vehicleClass")String vehicleClass);

    @Query("SELECT v FROM VehicleClass v WHERE v.vehicleClass =:vehicleClass")
    VehicleClass findVehicleClassByVehicleClass(@Param("vehicleClass")String vehicleClass);
}
