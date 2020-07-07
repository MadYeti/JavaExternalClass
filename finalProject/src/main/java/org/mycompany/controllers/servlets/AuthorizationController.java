package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.authorization.AuthorizationDataController;
import org.mycompany.exceptions.AuthorizationException;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.DAOFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Implements authorization logic. Validate input data. If data is correct
 * authorize user and sets session, sends error otherwise
 */
@Controller
public class AuthorizationController {

    private static Logger logger = Logger.getLogger(AuthorizationController.class);
    private ControllerFactory controllerFactory;
    private BeanFactory beanFactory;
    private DAOFactory mySqlDAOFactory;

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public AuthorizationController(BeanFactory beanFactory,
                                   DAOFactory mySqlDAOFactory,
                                   ControllerFactory controllerFactory){
        this.beanFactory = beanFactory;
        this.mySqlDAOFactory = mySqlDAOFactory;
        this.controllerFactory = controllerFactory;
    }

    /**
     * Perform authorization if data ic correct sets error otherwise
     * @param httpServletRequest servlet request
     * @return sighIn jsp name
     */
    @PostMapping("/AuthorizationController")
    public String authorizeClient(HttpServletRequest httpServletRequest){
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        String rememberMe = httpServletRequest.getParameter("rememberMe");
        AuthorizationDataController authorizationDataController = controllerFactory.getAuthorizationDataController();
        if(authorizationDataController.validateData(email, password)){
            ClientDAO clientDAOMySql = mySqlDAOFactory.createClientDAO();
            Client client = clientDAOMySql.getClient(email, password);
            if(client != null){
                HttpSession httpSession = httpServletRequest.getSession();
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
