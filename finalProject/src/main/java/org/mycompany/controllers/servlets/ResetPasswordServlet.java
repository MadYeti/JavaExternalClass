package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.registration.RegistrationController;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.ResetPasswordException;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.factory.MySqlDAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/ResetPasswordServlet", urlPatterns = "/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(ResetPasswordServlet.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doPost(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String token = httpServletRequest.getParameter("token");
        String password = httpServletRequest.getParameter("password");
        String retypedPassword = httpServletRequest.getParameter("retypedPassword");
        RegistrationController registrationController = new RegistrationController();
        if(!registrationController.validatePasswords(password, retypedPassword)){
            ClientDAO clientDAO = new MySqlDAOFactory().createClientDAO(DBCPDataSource.getConnection());
            clientDAO.resetPasswordAndDeleteToken(password, token);
            httpServletRequest.setAttribute("resetPassword", true);
        }else{
            try {
                throw new ResetPasswordException("Invalid password/retyped password in recovery password section");
            } catch (ResetPasswordException e) {
                logger.error(e.getMessage());
            }
            httpServletRequest.setAttribute("resetPassword", false);
            if(registrationController.getErrorPasswordMessage() != null){
                httpServletRequest.setAttribute("passwordError", registrationController.getErrorPasswordMessage());
            }
            if(registrationController.getErrorRetypedPasswordMessage() != null){
                httpServletRequest.setAttribute("retypedPasswordError", registrationController.getErrorRetypedPasswordMessage());
            }
        }
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/resetPassword.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
