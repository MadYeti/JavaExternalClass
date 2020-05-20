package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BuyingPageRedirectController {

    @GetMapping("/buyingPage")
    public String getDefaultBuyingPage(){
        return "/WEB-INF/view/buyingPage.jsp";
    }

    @PostMapping("/buyingPage")
    public String postDefaultBuyingPage(){
        return "/WEB-INF/view/buyingPage.jsp";
    }

}
