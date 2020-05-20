package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.authorization.AuthorizationDataController;
import org.mycompany.controllers.mail.MailController;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.InvalidEmailInputForPasswordRecoveryException;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.factory.MySqlDAOFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PasswordRecoveryController {

    private static Logger logger = Logger.getLogger(PasswordRecoveryController.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @PostMapping("/PasswordRecoveryController")
    public String getPasswordRecoveryInstruction(HttpServletRequest httpServletRequest){
        String email = httpServletRequest.getParameter("email");
        if(AuthorizationDataController.validateEmail(email)){
            ClientDAO clientDAO = new MySqlDAOFactory().createClientDAO(DBCPDataSource.getConnection());
            String token = clientDAO.createToken(email);
            MailController mailController = new MailController(email, token);
            mailController.sendPasswordRecoveryEmail();
        }else{
            try {
                throw new InvalidEmailInputForPasswordRecoveryException("Invalid email input");
            } catch (InvalidEmailInputForPasswordRecoveryException e) {
                logger.error(e.getMessage());
            }
            httpServletRequest.setAttribute("emailSend", false);
        }
        return "/WEB-INF/view/passwordRecovery.jsp";
    }

}
