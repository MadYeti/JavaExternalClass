package org.mycompany.exceptions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This exception is thrown when user input wrong password
 * in reset password section
 */
@Component
@Scope("prototype")
public class ResetPasswordException extends Exception{

    public ResetPasswordException(String message){
        super(message);
    }

}
