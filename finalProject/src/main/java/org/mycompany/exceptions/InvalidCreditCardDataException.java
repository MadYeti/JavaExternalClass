package org.mycompany.exceptions;

/**
 * This exception is thrown when user input wrong credit card data
 */
public class InvalidCreditCardDataException extends Exception{

    public InvalidCreditCardDataException(String message){
        super(message);
    }

}
