package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.registration.RegistrationDataController;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.ResetPasswordException;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.factory.MySqlDAOFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ResetPasswordController {

    private static Logger logger = Logger.getLogger(ResetPasswordController.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @PostMapping("/ResetPasswordController")
    public String resetPassword(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getParameter("token");
        String password = httpServletRequest.getParameter("password");
        String retypedPassword = httpServletRequest.getParameter("retypedPassword");
        RegistrationDataController registrationDataController = new RegistrationDataController();
        if(!registrationDataController.validatePasswords(password, retypedPassword)){
            ClientDAO clientDAO = new MySqlDAOFactory().createClientDAO(DBCPDataSource.getConnection());
            clientDAO.resetPasswordAndDeleteToken(password, token);
            httpServletRequest.setAttribute("resetPassword", true);
        }else{
            try {
                throw new ResetPasswordException("Invalid password/retyped password in recovery password section");
            } catch (ResetPasswordException e) {
                logger.error(e.getMessage());
            }
            httpServletRequest.setAttribute("resetPassword", false);
            if(registrationDataController.getErrorPasswordMessage() != null){
                httpServletRequest.setAttribute("passwordError", registrationDataController.getErrorPasswordMessage());
            }
            if(registrationDataController.getErrorRetypedPasswordMessage() != null){
                httpServletRequest.setAttribute("retypedPasswordError", registrationDataController.getErrorRetypedPasswordMessage());
            }
        }
        return "/WEB-INF/view/resetPassword.jsp";
    }

}
