package com.bsool.glasses.glassesshop.config.security;

import org.springframework.security.core.AuthenticationException;

public class ApiAuthenticationException extends AuthenticationException {
    public ApiAuthenticationException(String msg) {
        super(msg);
    }
}
