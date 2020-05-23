package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BuyingPageRedirectController {

    @PostMapping("/buyingPage")
    public String postDefaultBuyingPage(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("client") != null){
            return "buyingPage";
        }
        return "notEnoughPrivilegesPage";
    }

    @GetMapping("/buyingPage")
    public String getDefaultBuyingPage(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("client") != null){
            return "buyingPage";
        }
        return "notEnoughPrivilegesPage";
    }

}
