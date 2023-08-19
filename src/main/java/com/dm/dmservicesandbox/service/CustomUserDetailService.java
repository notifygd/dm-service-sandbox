package com.dm.dmservicesandbox.service;

import com.dm.dmservicesandbox.dbhome.UserRepository;
import com.dm.dmservicesandbox.domain.UserDetail;
import com.dm.dmservicesandbox.domain.dbojects.User;
import com.dm.dmservicesandbox.service.exception.UserAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomUserDetailService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserAuthenticationException {


        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UserAuthenticationException("User not found");
        }
        return new UserDetail(user);
    }

}
