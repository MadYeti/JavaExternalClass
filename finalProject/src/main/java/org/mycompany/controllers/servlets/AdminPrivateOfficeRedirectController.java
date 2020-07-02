package org.mycompany.controllers.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Redirect controller. Forward to adminPrivateOffice.jsp if user is authorize, if not
 * forward to notEnoughPrivilegesPage.jsp
 */
@Controller
public class AdminPrivateOfficeRedirectController {

    @GetMapping("/adminPrivateOffice")
    public String getAdminPrivateOfficePage(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("client") != null) {
            return "adminPrivateOffice";
        }
        return "notEnoughPrivilegesPage";
    }

}
