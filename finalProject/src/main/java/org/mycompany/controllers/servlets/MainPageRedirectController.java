package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Redirect controller. Forward to index.jsp
 */
@Controller
public class MainPageRedirectController {

    @GetMapping("/MainPage")
    public String getMainPage(){
        return "index";
    }

}
