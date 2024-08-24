package com.noxfilers.fuelApp.repositories;

import com.noxfilers.fuelApp.entities.BunkProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BunkProviderRepo extends JpaRepository<BunkProvider,Long> {

    @Query("SELECT b.providerName FROM BunkProvider b")
    List<String> getAllBunkProvider();
}
