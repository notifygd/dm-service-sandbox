package com.dm.dmservicesandbox.controller;

import com.dm.dmservicesandbox.component.UserVerificationComponent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class UserVerificationController {

    @Autowired
    UserVerificationComponent userVerificationComponent;

    //registration confirmation API
    private final static String REGISTER_CONF_APIPATH = "/dm/registerConfirmation";

    //redo email verification API
    private final static String REDO_VERIFY_APIPATH = "/dm/redoVerification";


    @GetMapping(path=REGISTER_CONF_APIPATH+"/{token}")
    public ResponseEntity<?> confirmRegistration(@PathVariable String token) {
        return userVerificationComponent.verifyUserToken(token);
    }

    @GetMapping(path=REDO_VERIFY_APIPATH+"/{email}")
    public ResponseEntity<?> emailValidate(@PathVariable String email) {
        return userVerificationComponent.sendRegisterConfirmation(email);
    }

}
