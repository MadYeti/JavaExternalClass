package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PasswordRecoveryPageRedirectController {

    @PostMapping("/passwordRecovery")
    public String postPasswordRecoveryPage(){
        return "/WEB-INF/view/passwordRecovery.jsp";
    }

}
