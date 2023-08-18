package com.dm.dmservicesandbox.controller;

import com.dm.dmservicesandbox.component.UserInvitationComponent;
import com.dm.dmservicesandbox.domain.APINames;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class UserInvitationController {

    @Autowired
    UserInvitationComponent userInvitationComponent;

    @Value("${dm.ui.reg.url}")
    private String UI_REGISTER_URL;


    @GetMapping(path=APINames.INVITE_USER_API+"/{email}")
    public ResponseEntity<?> inviteUser (@PathVariable String email) {
        return userInvitationComponent.sendUserInvite(email);
    }

    @GetMapping(path=APINames.ACCEPT_INVITE_API+"/{token}")
    public ResponseEntity<?> acceptInvite (@PathVariable String token, HttpServletResponse response) {
        ResponseEntity<?> respEntry = userInvitationComponent.verifyUserToken(token);
        if( respEntry.getStatusCode() == HttpStatus.OK ) {
            try {
                // on success, redirect to registration page
                response.sendRedirect(UI_REGISTER_URL + "?token=" + token);
            } catch (Exception e) {
                ResponseEntity.badRequest().body("Error redirecting to registration page. " + e.getMessage());
            }
        }
        return respEntry;
    }


}
