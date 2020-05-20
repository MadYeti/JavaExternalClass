package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResetPasswordRedirectController {

    @PostMapping("/resetPassword")
    public String getResetPasswordPage(){
        return "/WEB-INF/view/resetPassword.jsp";
    }

}
