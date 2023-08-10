package com.dm.dmservicesandbox.dbhome;

import com.dm.dmservicesandbox.domain.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserLogin, Long> {
    UserLogin findByEmail(String email);

    boolean existsByEmail(String email);

}
