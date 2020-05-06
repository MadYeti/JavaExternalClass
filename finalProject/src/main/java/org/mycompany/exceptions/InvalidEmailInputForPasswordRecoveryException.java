package org.mycompany.exceptions;


public class InvalidEmailInputForPasswordRecoveryException extends Exception{

    public InvalidEmailInputForPasswordRecoveryException(String message){
        super(message);
    }

}
