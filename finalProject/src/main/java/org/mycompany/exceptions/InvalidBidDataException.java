package org.mycompany.exceptions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This exception is thrown when user input wrong data to order
 * the bid or get bid price
 */
@Component
@Scope("prototype")
public class InvalidBidDataException extends Exception{

    public InvalidBidDataException(String message){
        super(message);
    }

}
