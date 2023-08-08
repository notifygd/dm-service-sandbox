package com.dm.dmservicesandbox.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserSession {
    private boolean isSignedIn;
    private Timestamp lastActiveTimestamp;
}
