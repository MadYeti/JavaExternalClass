package org.mycompany.exceptions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class RegistrationException extends Exception{

    public RegistrationException(String message){
        super(message);
    }

}
