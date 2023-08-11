package com.dm.dmservicesandbox.controller;


import com.dm.dmservicesandbox.domain.ServiceReponse;
import com.dm.dmservicesandbox.domain.UserSignUpRequest;
import com.dm.dmservicesandbox.service.UserAuthenticationService;
import com.dm.dmservicesandbox.service.exception.UserAuthenticationException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/dm/userAuth")
public class UserAuthController {

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignUpRequest userSignUpRequest) {
        try {
            userAuthenticationService.registerNewUser(userSignUpRequest);
        } catch (UserAuthenticationException e) {
            return new ResponseEntity<>(new ServiceReponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new ServiceReponse(true, "User registration success"));
    }

    @GetMapping("ping")
    public String ping() {
        return String.format("The app is running. Ping Successful");
    }
}
