package com.dm.dmservicesandbox.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String name;
    private UserLocation userLocation;
    private String registeredEmail;
    private boolean isAccountDisabled;
    private UserSession userSession;
}