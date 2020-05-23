package org.mycompany.exceptions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ResetPasswordException extends Exception{

    public ResetPasswordException(String message){
        super(message);
    }

}
