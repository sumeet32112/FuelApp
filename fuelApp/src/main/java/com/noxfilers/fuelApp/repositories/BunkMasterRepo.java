package com.noxfilers.fuelApp.repositories;

import com.noxfilers.fuelApp.entities.BunkMaster;
import com.noxfilers.fuelApp.entities.BunkProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BunkMasterRepo extends JpaRepository<BunkMaster,Long> {

    @Query("SELECT b.agencyName FROM BunkMaster b")
    List<String> findAllAgency();

    @Query("SELECT b FROM BunkMaster b WHERE b.agencyName=:agencyName")
    BunkMaster findBunkMaster(@Param("agencyName")String agencyName);


}
