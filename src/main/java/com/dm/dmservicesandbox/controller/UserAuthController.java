package com.dm.dmservicesandbox.controller;


import com.dm.dmservicesandbox.domain.ServiceReponse;
import com.dm.dmservicesandbox.domain.UserSignUpRequest;
import com.dm.dmservicesandbox.service.UserAuthenticationService;
import com.dm.dmservicesandbox.service.exception.UserAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/dm/userAuth")
public class UserAuthController {

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignUpRequest userSignUpRequest) {
        try {
            userAuthenticationService.registerNewUser(userSignUpRequest);
        } catch (UserAuthenticationException e) {
            return new ResponseEntity<>(new ServiceReponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new ServiceReponse(true, "User registration success"));
    }
}
