package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SighUpPageRedirectController {

    @GetMapping("/sighUp")
    public String getSighUpPage(){
        return "sighUp";
    }

}
