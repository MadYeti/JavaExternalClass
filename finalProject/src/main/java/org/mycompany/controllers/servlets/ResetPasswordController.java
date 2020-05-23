package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.registration.RegistrationDataController;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.ResetPasswordException;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.factory.DAOFactory;
import org.mycompany.models.factory.MySqlDAOFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ResetPasswordController {

    private RegistrationDataController registrationDataController;
    private DAOFactory mySqlDAOFactory;
    private BeanFactory beanFactory;

    private static Logger logger = Logger.getLogger(ResetPasswordController.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public ResetPasswordController(RegistrationDataController registrationDataController,
                                   BeanFactory beanFactory){
        this.registrationDataController = registrationDataController;
        this.beanFactory = beanFactory;
    }

    @PostMapping("/ResetPasswordController")
    public String resetPassword(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getParameter("token");
        String password = httpServletRequest.getParameter("password");
        String retypedPassword = httpServletRequest.getParameter("retypedPassword");
        if(!registrationDataController.validatePasswords(password, retypedPassword)){
            mySqlDAOFactory = beanFactory.getBean(MySqlDAOFactory.class);
            ClientDAO clientDAO = mySqlDAOFactory.createClientDAO();
            clientDAO.resetPasswordAndDeleteToken(password, token);
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
