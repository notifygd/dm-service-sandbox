package com.dm.dmservicesandbox.dbhome;

import com.dm.dmservicesandbox.domain.dbojects.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

}
