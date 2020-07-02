package org.mycompany.exceptions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This exception is thrown when user input wrong email in
 * password recovery section
 */
@Component
@Scope("prototype")
public class InvalidEmailInputForPasswordRecoveryException extends Exception{

    public InvalidEmailInputForPasswordRecoveryException(String message){
        super(message);
    }

}
