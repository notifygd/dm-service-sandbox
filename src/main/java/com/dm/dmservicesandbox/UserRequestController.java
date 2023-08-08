package com.dm.dmservicesandbox;

import com.dm.dmservicesandbox.domain.User;
import com.dm.dmservicesandbox.domain.UserLocation;
import com.dm.dmservicesandbox.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("v1/user/")
public class UserRequestController {

    public UserAuthenticationService userAuthenticationService;

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

    @GetMapping("sampleUsersAndPostsForMapRendering")
    public Map<User, String> getSampleUsersAndPostsForMapRendering()  {
        User user1= User.builder().name("John").userLocation(UserLocation.builder().city("Montreal").countryOfResidence("Canada").build()).build();
        User user2= User.builder().name("Mark").userLocation(UserLocation.builder().city("London").countryOfResidence("United Kingdom").build()).build();
        User user3= User.builder().name("Jane").userLocation(UserLocation.builder().city("Mumbai").countryOfResidence("India").build()).build();


        return Map.of(user1,"Sample post by user 1 about climate change.",
                user2, "Sample post by user 2",
                user3, "Sample post by user 3");
    }

}