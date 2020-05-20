package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.authorization.AuthorizationDataController;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.AuthorizationException;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.clientDAO.DAO;
import org.mycompany.models.factory.MySqlDAOFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthorizationController {

    private static Logger logger = Logger.getLogger(AuthorizationController.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @PostMapping("/AuthorizationController")
    public String authorizeClient(HttpServletRequest httpServletRequest){
        HttpSession httpSession;
        Client client;
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        String rememberMe = httpServletRequest.getParameter("rememberMe");
        AuthorizationDataController authorizationDataController = new AuthorizationDataController();
        if(authorizationDataController.validateData(email, password)){
            DAO clientDAO = new MySqlDAOFactory().createClientDAO(DBCPDataSource.getConnection());
            client = clientDAO.getClient(email, password);
            if(client != null){
                httpSession = httpServletRequest.getSession(true);
                httpSession.setAttribute("client", client);
                if(rememberMe != null){
                    httpSession.setMaxInactiveInterval(0);
                }
                return "/WEB-INF/view/".concat(client.getRole()).concat(".jsp");
            }else{
                httpServletRequest.setAttribute("client", "Warning");
                return "/WEB-INF/view/sighIn.jsp";
            }
        }else{
            try {
                throw new AuthorizationException("Invalid credentials input to log in");
            } catch (AuthorizationException e) {
                logger.error(e.getMessage());
            }
            httpServletRequest.setAttribute("client", "IncorrectInput");
            return "/WEB-INF/view/sighIn.jsp";
        }
    }

}
