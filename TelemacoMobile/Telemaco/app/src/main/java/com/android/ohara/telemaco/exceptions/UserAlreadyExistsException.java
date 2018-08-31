package com.android.ohara.telemaco.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException () {
        super();
    }

    public UserAlreadyExistsException (String msg) {
        super(msg);
    }
}
