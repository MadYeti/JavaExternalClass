package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.registration.RegistrationController;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.RegistrationException;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.factory.ControllerFactoryImpl;
import org.mycompany.models.factory.MySqlDAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Implements registration logic. Validate input data. If data is correct
 * create record in DB with such credentials, sends error otherwise
 */
@WebServlet(name = "/RegistrationServlet", urlPatterns = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(RegistrationServlet.class);

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
        String retypedPassword = httpServletRequest.getParameter("retypedPassword");
        RegistrationController registrationController = new ControllerFactoryImpl().getRegistrationController();
        if(!registrationController.validateData(email, password, retypedPassword)){
            ClientDAO clientDAO = new MySqlDAOFactory().createClientDAO(DBCPDataSource.getConnection());
            clientDAO.addClient(email, password);
            requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp");
        }else{
            try {
                throw new RegistrationException("Invalid input data to register user");
            } catch (RegistrationException e) {
                logger.error(e.getMessage());
            }
            if(registrationController.getErrorEmailMessage() != null){
                httpServletRequest.setAttribute("emailError", registrationController.getErrorEmailMessage());
            }
            if(registrationController.getErrorPasswordMessage() != null){
                httpServletRequest.setAttribute("passwordError", registrationController.getErrorPasswordMessage());
            }
            if(registrationController.getErrorRetypedPasswordMessage() != null){
                httpServletRequest.setAttribute("retypedPasswordError", registrationController.getErrorRetypedPasswordMessage());
            }
            requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/sighUp.jsp");
        }
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
