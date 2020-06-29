package org.mycompany.exceptions;

/**
 * This exception is thrown when user input wrong credentials to authorize
 */
public class AuthorizationException extends Exception{

    public AuthorizationException(String message){
        super(message);
    }

}
