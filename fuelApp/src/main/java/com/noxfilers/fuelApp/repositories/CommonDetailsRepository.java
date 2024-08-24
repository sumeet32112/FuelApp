
package com.noxfilers.fuelApp.repositories;

import com.noxfilers.fuelApp.entities.CommonDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonDetailsRepository extends JpaRepository<CommonDetails,Long> {
}
