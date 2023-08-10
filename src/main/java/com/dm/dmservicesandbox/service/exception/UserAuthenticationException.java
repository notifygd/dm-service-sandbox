package com.dm.dmservicesandbox.service.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public class UserAuthenticationException extends AuthenticationException {

    @Serial
    private static final long serialVersionUID = 5570981880007077317L;

    public UserAuthenticationException(final String msg) {
        super(msg);
    }
}
