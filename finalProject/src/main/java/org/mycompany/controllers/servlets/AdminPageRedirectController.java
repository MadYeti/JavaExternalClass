package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Redirect controller. Forward to admin.jsp if user is authorize, if not
 * forward to notEnoughPrivilegesPage.jsp
 */
@Controller
public class AdminPageRedirectController {

    @GetMapping("/admin")
    public String getAdminPage(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("client") != null){
            return "admin";
        }
        return "notEnoughPrivilegesPage";
    }

}
