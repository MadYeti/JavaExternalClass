package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Implements logout logic. Invalidate session, remove its attributes
 * and forward user to index.jsp
 */
@Controller
public class LogoutController {

    /**
     * Invalidate session, remove its attributes
     * @param httpServletRequest servlet request
     * @return index jsp name
     */
    @GetMapping("/LogoutController")
    public String logOut(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession != null) {
            httpSession.removeAttribute("client");
            httpSession.invalidate();
        }
        return "index";
    }

}
