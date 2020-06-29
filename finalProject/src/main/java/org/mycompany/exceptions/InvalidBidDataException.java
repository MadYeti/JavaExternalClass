package org.mycompany.exceptions;

/**
 * This exception is thrown when user input wrong data to order
 * the bid or get bid price
 */
public class InvalidBidDataException extends Exception{

    public InvalidBidDataException(String message){
        super(message);
    }

}
