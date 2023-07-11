package com.dm.dmservicesandbox.domain;

import lombok.Data;

@Data
public class User {
    private String name;
    private ResidenceLocation residenceLocation;
    private String registeredEmail;
    private boolean isActiveAccount;
    private boolean isSignedIn;
}