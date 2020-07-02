package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Redirect controller. Forward to passwordRecovery.jsp
 */
@Controller
public class PasswordRecoveryPageRedirectController {

    @PostMapping("/passwordRecovery")
    public String postPasswordRecoveryPage(){
        return "passwordRecovery";
    }

    @GetMapping("/passwordRecovery")
    public String getPasswordRecoveryPage(){
        return "passwordRecovery";
    }

}
