package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Redirect controller. Forward to sighIn.jsp
 */
@Controller
public class SighInPageRedirectController {

    @GetMapping("/sighIn")
    public String getSighInPage(){
        return "sighIn";
    }

}
