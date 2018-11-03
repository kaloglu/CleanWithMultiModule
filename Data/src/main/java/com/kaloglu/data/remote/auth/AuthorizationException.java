package com.kaloglu.data.remote.auth;

public class AuthorizationException extends Exception {

    public AuthorizationException() {
        super("BearerToken cannot be null.");
    }

}
