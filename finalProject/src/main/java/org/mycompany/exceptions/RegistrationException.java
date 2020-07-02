package org.mycompany.exceptions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This exception is thrown when user input wrong credentials
 * to sigh up
 */
@Component
@Scope("prototype")
public class RegistrationException extends Exception{

    public RegistrationException(String message){
        super(message);
    }

}
