package com.dm.dmservicesandbox;

import com.dm.dmservicesandbox.service.UserAuthenticationServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/user/")
public class UserRequestController {

    public UserAuthenticationServiceImpl userAuthenticationService;

    @GetMapping("getUserInfo")
    public String getUserInfo(@RequestParam(value = "userName") String userName) {

        return String.format("Welcome %s!", userName);
    }

    @GetMapping("ping")
    public String ping() {
        return String.format("The app is running. Ping Successful");
    }


    @GetMapping("register")
    public String register(@RequestParam(value = "userName") String userName) {
        //TODO logic to send an email to registered email
        //TODO Check if the user is already registered
        return "Operation is not supported. Stay tuned "+ userName + " !";
    }


    @GetMapping("authenticate")
    public boolean authenticate(@RequestParam(value = "userName") String userName)  {
        //TODO logic to send an email to registered email
        //TODO Check if the user is already registered
        return true;
    }

}