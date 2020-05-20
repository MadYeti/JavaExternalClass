package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.registration.RegistrationDataController;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.RegistrationException;
import org.mycompany.models.dao.clientDAO.DAO;
import org.mycompany.models.factory.MySqlDAOFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {

    private static Logger logger = Logger.getLogger(RegistrationController.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @PostMapping("/RegistrationController")
    public String registrateClient(HttpServletRequest httpServletRequest){
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        String retypedPassword = httpServletRequest.getParameter("retypedPassword");
        RegistrationDataController registrationDataController = new RegistrationDataController();
        if(!registrationDataController.validateData(email, password, retypedPassword)){
            DAO clientDAO = new MySqlDAOFactory().createClientDAO(DBCPDataSource.getConnection());
            clientDAO.addClient(email, password);
            return "/WEB-INF/view/index.jsp";
        }else{
            try {
                throw new RegistrationException("Invalid input data to register user");
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
            return "/WEB-INF/view/sighUp.jsp";
        }
    }

}
