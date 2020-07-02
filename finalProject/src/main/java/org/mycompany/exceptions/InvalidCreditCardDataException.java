package org.mycompany.exceptions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This exception is thrown when user input wrong credit card data
 */
@Component
@Scope("prototype")
public class InvalidCreditCardDataException extends Exception{

    public InvalidCreditCardDataException(String message){
        super(message);
    }

}
