package com.dm.dmservicesandbox.service;



import com.dm.dmservicesandbox.domain.UserLogin;
import com.dm.dmservicesandbox.domain.UserSignUpRequest;
import com.dm.dmservicesandbox.service.exception.UserAuthenticationException;


import java.util.Optional;

public interface UserAuthenticationService {
    public UserLogin registerNewUser(UserSignUpRequest signUpRequest) throws UserAuthenticationException;

    UserLogin findUserByEmail(String email);

    Optional<UserLogin> findUserById(Long id);

}


