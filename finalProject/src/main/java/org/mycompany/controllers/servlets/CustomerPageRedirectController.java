package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerPageRedirectController {

    @GetMapping("/customer")
    public String getCustomerPage(){
        return "/WEB-INF/view/customer.jsp";
    }

}
