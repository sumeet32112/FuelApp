package com.noxfilers.fuelApp.repositories;
import com.noxfilers.fuelApp.entities.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser,Long> {


    @Query("select u from RegisteredUser u where u.mobile =:mobile")
    RegisteredUser findByNumber(String mobile);

    boolean existsByDisplayName(String displayName);

    boolean existsByMobile(String mobile);

    @Query("SELECT u.id FROM RegisteredUser u WHERE u.displayName =:displayName")
    Long findIdByDisplayName(@Param("displayName")String displayName);

    @Query("SELECT u FROM RegisteredUser u WHERE u.displayName =:displayName")
    RegisteredUser findRegisteredUserByDisplayName(@Param("displayName")String displayName);
    @Query("SELECT u FROM RegisteredUser u WHERE u.mobile=:mobile")
    RegisteredUser findRegisteredUserByMobile(@Param("mobile")String mobile);
    @Query("SELECT u.displayName FROM RegisteredUser u WHERE u.id =:id")
    String findDisplayNameById(@Param("id") Long id);
}
