package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/LogoutController")
    public String logOut(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession != null) {
            httpSession.removeAttribute("client");
            httpSession.invalidate();
        }
        return "/WEB-INF/view/index.jsp";
    }

}
