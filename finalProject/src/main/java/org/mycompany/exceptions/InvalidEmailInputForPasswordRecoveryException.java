package org.mycompany.exceptions;

/**
 * This exception is thrown when user input wrong email in
 * password recovery section
 */
public class InvalidEmailInputForPasswordRecoveryException extends Exception{

    public InvalidEmailInputForPasswordRecoveryException(String message){
        super(message);
    }

}
