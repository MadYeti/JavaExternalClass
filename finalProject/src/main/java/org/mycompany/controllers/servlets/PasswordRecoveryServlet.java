package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.authorization.AuthorizationController;
import org.mycompany.controllers.mail.MailController;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.InvalidEmailInputForPasswordRecoveryException;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.dao.clientDAO.ClientDAOHelper;
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
 * Validate input email. If data is correct send to email link with password
 * recovery instructions, sends error otherwise
 */
@WebServlet(name = "/PasswordRecoveryServlet", urlPatterns = "/PasswordRecoveryServlet")
public class PasswordRecoveryServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(PasswordRecoveryServlet.class);

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
        String email = httpServletRequest.getParameter("email");
        if(AuthorizationController.validateEmail(email)){
            ClientDAO clientDAO = new MySqlDAOFactory().createClientDAO(DBCPDataSource.getConnection());
            String token = ((ClientDAOHelper)clientDAO).createToken(email);
            MailController mailController = new ControllerFactoryImpl().getMailController(email, token);
            mailController.sendPasswordRecoveryEmail();
        }else{
            try {
                throw new InvalidEmailInputForPasswordRecoveryException("Invalid email input");
            } catch (InvalidEmailInputForPasswordRecoveryException e) {
                logger.error(e.getMessage());
            }
            httpServletRequest.setAttribute("emailSend", false);
        }
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/passwordRecovery.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
