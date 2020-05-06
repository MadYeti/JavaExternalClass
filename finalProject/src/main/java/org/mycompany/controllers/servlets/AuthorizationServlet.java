package org.mycompany.controllers.servlets;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.authorization.AuthorizationController;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.AuthorizationException;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.dao.clientDAO.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "/AuthorizationServlet", urlPatterns = "/AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(AuthorizationServlet.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doPost(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession httpSession;
        Client client;
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        String rememberMe = httpServletRequest.getParameter("rememberMe");
        AuthorizationController authorizationController = new AuthorizationController();
        if(authorizationController.validateData(email, password)){
            DAO clientDAO = new ClientDAO(DBCPDataSource.getConnection());
            client = clientDAO.getClient(email, password);
            if(client != null){
                httpSession = httpServletRequest.getSession(true);
                httpSession.setAttribute("client", client);
                if(rememberMe != null){
                    httpSession.setMaxInactiveInterval(0);
                }
                requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/".concat(client.getRole()).concat(".jsp"));
            }else{
                httpServletRequest.setAttribute("client", "Warning");
                requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/sighIn.jsp");
            }
        }else{
            try {
                throw new AuthorizationException("Invalid credentials input to log in");
            } catch (AuthorizationException e) {
                logger.error(e.getMessage());
            }
            httpServletRequest.setAttribute("client", "IncorrectInput");
            requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/sighIn.jsp");
        }
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
