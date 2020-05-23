package org.mycompany.exceptions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AuthorizationException extends Exception{

    public AuthorizationException(String message){
        super(message);
    }

}
