package org.mycompany.exceptions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class InvalidEmailInputForPasswordRecoveryException extends Exception{

    public InvalidEmailInputForPasswordRecoveryException(String message){
        super(message);
    }

}
