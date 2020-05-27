package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.registration.RegistrationDataController;
import org.mycompany.exceptions.RegistrationException;
import org.mycompany.models.dao.clientDAO.DAO;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.DAOFactory;
import org.mycompany.models.factory.MySqlDAOFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {

    private BeanFactory beanFactory;
    private ControllerFactory controllerFactory;
    private DAOFactory mySqlDAOFactory;

    private static Logger logger = Logger.getLogger(RegistrationController.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public RegistrationController(BeanFactory beanFactory,
                                  ControllerFactory controllerFactory,
                                  DAOFactory mySqlDAOFactory){
        this.beanFactory = beanFactory;
        this.controllerFactory = controllerFactory;
        this.mySqlDAOFactory = mySqlDAOFactory;
    }

    @PostMapping("/RegistrationController")
    public String registrateClient(HttpServletRequest httpServletRequest){
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        String retypedPassword = httpServletRequest.getParameter("retypedPassword");
        RegistrationDataController registrationDataController = controllerFactory.getRegistrationDataController();
        if(!registrationDataController.validateData(email, password, retypedPassword)){
            DAO clientDAO = mySqlDAOFactory.createClientDAO();
            clientDAO.addClient(email, password);
            return "index";
        }else{
            try {
                throw beanFactory.getBean(RegistrationException.class, "Invalid input data to register user");
            } catch (RegistrationException e) {
                logger.error(e.getMessage());
            }
            if(registrationDataController.getErrorEmailMessage() != null){
                httpServletRequest.setAttribute("emailError", registrationDataController.getErrorEmailMessage());
            }
            if(registrationDataController.getErrorPasswordMessage() != null){
                httpServletRequest.setAttribute("passwordError", registrationDataController.getErrorPasswordMessage());
            }
            if(registrationDataController.getErrorRetypedPasswordMessage() != null){
                httpServletRequest.setAttribute("retypedPasswordError", registrationDataController.getErrorRetypedPasswordMessage());
            }
            return "sighUp";
        }
    }

}
