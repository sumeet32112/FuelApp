package com.noxfilers.fuelApp.repositories;

import com.noxfilers.fuelApp.entities.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType,Long> {
    @Query("SELECT f.id FROM FuelType f WHERE f.fuelType =:fuelType")
    Long findIdByFuelType(@Param("fuelType")String fuelType);

    @Query("SELECT f FROM FuelType f WHERE f.fuelType =:fuelType")
    FuelType findFuelByFuelType(@Param("fuelType")String fuelType);
    @Query("SELECT f.fuelType FROM FuelType f WHERE f.id=:id")
    String findFuelTypeById(@Param("id") Long id);
}
