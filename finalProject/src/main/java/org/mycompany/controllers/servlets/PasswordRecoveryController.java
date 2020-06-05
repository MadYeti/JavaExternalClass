package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.authorization.AuthorizationDataController;
import org.mycompany.controllers.mail.MailController;
import org.mycompany.exceptions.InvalidEmailInputForPasswordRecoveryException;
import org.mycompany.models.dao.clientDAO.ClientDAOHelper;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.DAOFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PasswordRecoveryController {

    private BeanFactory beanFactory;
    private DAOFactory mySqlDAOFactory;
    private ControllerFactory controllerFactory;

    private static Logger logger = Logger.getLogger(PasswordRecoveryController.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public PasswordRecoveryController(BeanFactory beanFactory,
                                      DAOFactory mySqlDAOFactory,
                                      ControllerFactory controllerFactory){
        this.beanFactory = beanFactory;
        this.mySqlDAOFactory = mySqlDAOFactory;
        this.controllerFactory = controllerFactory;
    }

    @PostMapping("/PasswordRecoveryController")
    public String getPasswordRecoveryInstruction(HttpServletRequest httpServletRequest){
        String email = httpServletRequest.getParameter("email");
        AuthorizationDataController authorizationDataController = controllerFactory.getAuthorizationDataController();
        if(authorizationDataController.validateEmail(email)){
            ClientDAOHelper clientDAOMySql = mySqlDAOFactory.createClientDAO();
            String token = clientDAOMySql.createToken(email);
            MailController mailController = controllerFactory.getMailController(email, token);
            mailController.sendPasswordRecoveryEmail();
        }else{
            try {
                throw beanFactory.getBean(InvalidEmailInputForPasswordRecoveryException.class, "Invalid email input");
            } catch (InvalidEmailInputForPasswordRecoveryException e) {
                logger.error(e.getMessage());
            }
            httpServletRequest.setAttribute("emailSend", false);
        }
        return "passwordRecovery";
    }

}
