package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.authorization.AuthorizationDataController;
import org.mycompany.exceptions.AuthorizationException;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.clientDAO.DAO;
import org.mycompany.models.factory.DAOFactory;
import org.mycompany.models.factory.MySqlDAOFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthorizationController {

    private static Logger logger = Logger.getLogger(AuthorizationController.class);
    private AuthorizationDataController authorizationDataController;
    private DAOFactory mySqlDAOFactory;
    private BeanFactory beanFactory;

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public AuthorizationController(AuthorizationDataController authorizationDataController,
                                   BeanFactory beanFactory){
        this.authorizationDataController = authorizationDataController;
        this.beanFactory = beanFactory;
    }

    @PostMapping("/AuthorizationController")
    public String authorizeClient(HttpServletRequest httpServletRequest){
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        String rememberMe = httpServletRequest.getParameter("rememberMe");
        if(authorizationDataController.validateData(email, password)){
            mySqlDAOFactory = beanFactory.getBean(MySqlDAOFactory.class);
            DAO clientDAO = mySqlDAOFactory.createClientDAO();
            Client client = clientDAO.getClient(email, password);
            if(client != null){
                HttpSession httpSession = httpServletRequest.getSession(true);
                httpSession.setAttribute("client", client);
                if(rememberMe != null){
                    httpSession.setMaxInactiveInterval(0);
                }
                return client.getRole();
            }else{
                httpServletRequest.setAttribute("client", "Warning");
                return "sighIn";
            }
        }else{
            try {
                throw beanFactory.getBean(AuthorizationException.class, "Invalid credentials input to log in");
            } catch (AuthorizationException e) {
                logger.error(e.getMessage());
            }
            httpServletRequest.setAttribute("client", "IncorrectInput");
            return "sighIn";
        }
    }

}
