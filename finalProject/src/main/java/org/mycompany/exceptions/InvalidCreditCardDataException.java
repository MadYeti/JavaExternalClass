package org.mycompany.exceptions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class InvalidCreditCardDataException extends Exception{

    public InvalidCreditCardDataException(String message){
        super(message);
    }

}
