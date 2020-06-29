package org.mycompany.exceptions;

/**
 * This exception is thrown when user input wrong password
 * in reset password section
 */
public class ResetPasswordException extends Exception{

    public ResetPasswordException(String message){
        super(message);
    }

}
