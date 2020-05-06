package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.authorization.AuthorizationController;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.InvalidEmailInputForPasswordRecoveryException;
import org.mycompany.models.dao.clientDAO.ClientDAO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "/PasswordRecoveryServlet", urlPatterns = "/PasswordRecoveryServlet")
public class PasswordRecoveryServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(PasswordRecoveryServlet.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doPost(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Properties mailConfigProperties = new Properties();
        Properties mailCredentialsProperties = new Properties();
        mailConfigProperties.load(new FileReader(new File("src/main/resources/mailConfig.properties")));
        mailCredentialsProperties.load(new FileReader(new File("src/main/resources/mailCredentials.properties")));
        String email = httpServletRequest.getParameter("email");
        if(AuthorizationController.validateEmail(email)){
            ClientDAO clientDAO = new ClientDAO(DBCPDataSource.getConnection());
            String token = clientDAO.createToken(email);
            String to = email;
            String from = mailCredentialsProperties.getProperty("mail.address.from");
            String senderPassword = mailCredentialsProperties.getProperty("mail.address.password");
            Session session = Session.getDefaultInstance(mailConfigProperties, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, senderPassword);
                }

            });
            MimeMessage mimeMessage = new MimeMessage(session);
            try {
                mimeMessage.setFrom(new InternetAddress(from));
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setSubject("Password recovery");
                mimeMessage.setText("You receive this letter because it was indicated " +
                                    "for password recovery by address http://localhost:8888/ . " +
                                    "Follow the next link to reset password: " +
                                    "http://localhost:8888/resetPassword?token=" + token +
                                    " . If it wasn't you just ignore this letter.");
                Transport.send(mimeMessage);
                httpServletRequest.setAttribute("emailSend", true);
            } catch (MessagingException e) {
                logger.error(e.getMessage());
            }
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
