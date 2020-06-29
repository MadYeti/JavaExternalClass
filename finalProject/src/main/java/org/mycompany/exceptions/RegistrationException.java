package org.mycompany.exceptions;

/**
 * This exception is thrown when user input wrong credentials
 * to sigh up
 */
public class RegistrationException extends Exception{

    public RegistrationException(String message){
        super(message);
    }

}
