package org.mycompany.controllers.mail;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MailController {

    private static Logger logger = Logger.getLogger(MailController.class);
    private String emailTo;
    private String emailFrom;
    private String emailSenderPassword;
    private String token;
    private static Properties mailConfigProperties;
    private static Properties mailCredentialsProperties;

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
        mailConfigProperties = new Properties();
        mailCredentialsProperties = new Properties();
        try {
            mailConfigProperties.load(new FileReader(new File("src/main/resources/mailConfig.properties")));
            mailCredentialsProperties.load(new FileReader(new File("src/main/resources/mailCredentials.properties")));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public MailController(String emailTo, String token){
        this.emailTo = emailTo;
        this.emailFrom = mailCredentialsProperties.getProperty("mail.address.from");
        this.emailSenderPassword = mailCredentialsProperties.getProperty("mail.address.password");
        this.token = token;
    }

    public void sendEmail(){
        Session session = Session.getDefaultInstance(mailConfigProperties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, emailSenderPassword);
            }

        });
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(emailFrom));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mimeMessage.setSubject("Password recovery");
            mimeMessage.setText("You receive this letter because it was indicated " +
                                "for password recovery by address http://localhost:8888/ . " +
                                "Follow the next link to reset password: " +
                                "http://localhost:8888/resetPassword?token=" + token +
                                " . If it wasn't you just ignore this letter.");
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error(e.getMessage());
        }
    }

}
