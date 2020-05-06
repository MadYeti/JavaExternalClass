package org.mycompany.exceptions;


public class InvalidCreditCardDataException extends Exception{

    public InvalidCreditCardDataException(String message){
        super(message);
    }

}
