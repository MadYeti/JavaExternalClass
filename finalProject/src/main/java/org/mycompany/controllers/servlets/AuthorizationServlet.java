package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.authorization.AuthorizationController;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.AuthorizationException;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.factory.ControllerFactoryImpl;
import org.mycompany.models.factory.MySqlDAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Implements authorization logic. Validate input data. If data is correct
 * authorize user and sets session, sends error otherwise
 */
@WebServlet(name = "/AuthorizationServlet", urlPatterns = "/AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(AuthorizationServlet.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param httpServletRequest servlet request
     * @param httpServletResponse servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doPost(httpServletRequest, httpServletResponse);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param httpServletRequest servlet request
     * @param httpServletResponse servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        String rememberMe = httpServletRequest.getParameter("rememberMe");
        AuthorizationController authorizationController = new ControllerFactoryImpl().getAuthorizationController();
        if(authorizationController.validateData(email, password)){
            ClientDAO clientDAOMySql = new MySqlDAOFactory().createClientDAO(DBCPDataSource.getConnection());
            Client client = clientDAOMySql.getClient(email, password);
            if(client != null){
                HttpSession httpSession = httpServletRequest.getSession();
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
