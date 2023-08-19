package com.dm.dmservicesandbox.controller;



import com.dm.dmservicesandbox.domain.UserToken;
import com.dm.dmservicesandbox.domain.restservice.ServiceReponse;
import com.dm.dmservicesandbox.domain.restservice.UserLoginRequest;
import com.dm.dmservicesandbox.domain.restservice.UserSignUpRequest;
import com.dm.dmservicesandbox.service.UserAuthenticationService;
import com.dm.dmservicesandbox.service.exception.UserAuthenticationException;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/dm/userAuth")
public class UserAuthController {

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    AuthenticationManager authenticationManager;


     @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignUpRequest userSignUpRequest) {
        try {
            userAuthenticationService.registerNewUser(userSignUpRequest);
        } catch (UserAuthenticationException e) {
            return new ResponseEntity<>(new ServiceReponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }
        UserToken userToken;

        return ResponseEntity.ok().body(new ServiceReponse(true, "User registration success"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok().body(new ServiceReponse(true, "User Login success"));

    }

    @GetMapping("ping")
    public String ping() {
        return String.format("The app is running. Ping Successful");
    }
}
