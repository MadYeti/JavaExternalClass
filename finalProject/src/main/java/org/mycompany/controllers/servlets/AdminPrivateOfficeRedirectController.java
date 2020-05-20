package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPrivateOfficeRedirectController {

    @GetMapping("/adminPrivateOffice")
    public String getAdminPrivateOfficePage(){
        return "/WEB-INF/view/adminPrivateOffice.jsp";
    }

}
