package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.registration.RegistrationDataController;
import org.mycompany.exceptions.ResetPasswordException;
import org.mycompany.models.dao.clientDAO.ClientDAOHelper;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.DAOFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Implements reset password logic. Validate input data. If data is correct reset
 * password to a new one, sends error otherwise
 */
@Controller
public class ResetPasswordController {

    private BeanFactory beanFactory;
    private DAOFactory mySqlDAOFactory;
    private ControllerFactory controllerFactory;

    private static Logger logger = Logger.getLogger(ResetPasswordController.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public ResetPasswordController(BeanFactory beanFactory,
                                   DAOFactory mySqlDAOFactory,
                                   ControllerFactory controllerFactory){
        this.beanFactory = beanFactory;
        this.mySqlDAOFactory = mySqlDAOFactory;
        this.controllerFactory = controllerFactory;
    }

    /**
     * Validate input data. If data is correct sets password to a new one,
     * sets error otherwise
     * @param httpServletRequest servlet request
     * @return resetPassword jsp name
     */
    @PostMapping("/ResetPasswordController")
    public String resetPassword(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getParameter("token");
        String password = httpServletRequest.getParameter("password");
        String retypedPassword = httpServletRequest.getParameter("retypedPassword");
        RegistrationDataController registrationDataController = controllerFactory.getRegistrationDataController();
        if(!registrationDataController.validatePasswords(password, retypedPassword)){
            ClientDAOHelper clientDAOMySql = mySqlDAOFactory.createClientDAO();
            clientDAOMySql.resetPasswordAndDeleteToken(password, token);
            httpServletRequest.setAttribute("resetPassword", true);
        }else{
            try {
                throw beanFactory.getBean(ResetPasswordException.class, "Invalid password/retyped password in recovery password section");
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
        return "resetPassword";
    }

}