package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Redirect controller. Forward to resetPassword.jsp
 */
@Controller
public class ResetPasswordRedirectController {

    @GetMapping("/resetPassword")
    public String getResetPasswordPage(){
        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public String postResetPasswordPage(){
        return "resetPassword";
    }

}
