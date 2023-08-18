package com.dm.dmservicesandbox.domain.restservice;

import lombok.Value;

@Value
public class ServiceReponse {

    private Boolean success;
    private String message;

    public ServiceReponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
