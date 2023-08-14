package com.dm.dmservicesandbox.dbhome;

import com.dm.dmservicesandbox.domain.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<UserLocation, Long> {

    UserLocation findByMapBoxId(String mapBoxId);
}