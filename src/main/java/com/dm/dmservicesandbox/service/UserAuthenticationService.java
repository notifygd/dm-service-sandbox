package com.dm.dmservicesandbox.service;

import com.dm.dmservicesandbox.service.exception.UserAuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    public boolean authenticateUser(String userName) throws UserAuthenticationException {
        //TODO Logic for authentication
        return true;
    }

}
