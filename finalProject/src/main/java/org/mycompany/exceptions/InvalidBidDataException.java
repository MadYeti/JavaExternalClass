package org.mycompany.exceptions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class InvalidBidDataException extends Exception{

    public InvalidBidDataException(String message){
        super(message);
    }

}
