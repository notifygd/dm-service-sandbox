package com.dm.dmservicesandbox.service;



import com.dm.dmservicesandbox.domain.dbojects.User;
import com.dm.dmservicesandbox.domain.restservice.UserSignUpRequest;
import com.dm.dmservicesandbox.service.exception.UserAuthenticationException;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

public interface UserAuthenticationService {
    public User registerNewUser(UserSignUpRequest signUpRequest) throws UserAuthenticationException;

    User findUserByEmail(String email);

    Optional<User> findUserById(Long id);

}


