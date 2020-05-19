package org.mycompany.controllers.servlets;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.registration.RegistrationController;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.RegistrationException;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.dao.clientDAO.DAO;
import org.mycompany.models.factory.MySqlDAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/RegistrationServlet", urlPatterns = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(RegistrationServlet.class);

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
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        String retypedPassword = httpServletRequest.getParameter("retypedPassword");
        RegistrationController registrationController = new RegistrationController();
        if(!registrationController.validateData(email, password, retypedPassword)){
            DAO clientDAO = new MySqlDAOFactory().createClientDAO(DBCPDataSource.getConnection());
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
