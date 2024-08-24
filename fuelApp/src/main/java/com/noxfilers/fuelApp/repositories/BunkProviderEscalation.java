package com.noxfilers.fuelApp.repositories;

import com.noxfilers.fuelApp.entities.BunkProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BunkProviderEscalation extends JpaRepository<com.noxfilers.fuelApp.entities.BunkProviderEscalation,Long> {
    @Query("SELECT bpe.escalationMail FROM BunkProviderEscalation bpe " +
            "WHERE bpe.bunkProvider = :bunkProvider AND bpe.escalationLevel = :escalationLevel")
    String findEscalationMail(BunkProvider bunkProvider, Integer escalationLevel);
}
