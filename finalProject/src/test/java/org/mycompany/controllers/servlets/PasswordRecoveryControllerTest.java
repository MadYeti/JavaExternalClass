package org.mycompany.controllers.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.mycompany.controllers.authorization.AuthorizationDataController;
import org.mycompany.controllers.mail.MailController;
import org.mycompany.exceptions.InvalidEmailInputForPasswordRecoveryException;
import org.mycompany.models.dao.clientDAO.ClientDAOMySql;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.DAOFactory;
import org.springframework.beans.factory.BeanFactory;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PasswordRecoveryControllerTest {

    @Test
    public void callPostMethodOnPasswordRecoveryControllerRightInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        AuthorizationDataController authorizationDataController = mock(AuthorizationDataController.class);
        String email = "ivancov13@bigmir.net";
        ClientDAOMySql clientDAOMySql = mock(ClientDAOMySql.class);
        String token = "j901rhj1r1rwqjhfkqd89h41ei190c";
        MailController mailController = mock(MailController.class);
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(mySqlDAOFactory.createClientDAO()).thenReturn(clientDAOMySql);
        when(clientDAOMySql.createToken(email)).thenReturn(token);
        when(controllerFactory.getMailController(email, token)).thenReturn(mailController);
        when(controllerFactory.getAuthorizationDataController()).thenReturn(authorizationDataController);
        when(authorizationDataController.validateEmail(email)).thenReturn(true);
        PasswordRecoveryController passwordRecoveryController = new PasswordRecoveryController(beanFactory, mySqlDAOFactory, controllerFactory);
        String result = passwordRecoveryController.getPasswordRecoveryInstruction(httpServletRequest);
        Assert.assertEquals("passwordRecovery", result);
    }

    @Test
    public void callPostMethodOnPasswordRecoveryControllerWrongInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        InvalidEmailInputForPasswordRecoveryException invalidEmailInputForPasswordRecoveryException = mock(InvalidEmailInputForPasswordRecoveryException.class);
        AuthorizationDataController authorizationDataController = mock(AuthorizationDataController.class);
        String email = "fg98asb4cx#h1";
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(controllerFactory.getAuthorizationDataController()).thenReturn(authorizationDataController);
        when(authorizationDataController.validateEmail(email)).thenReturn(false);
        when(beanFactory.getBean(InvalidEmailInputForPasswordRecoveryException.class, "Invalid email input")).thenReturn(invalidEmailInputForPasswordRecoveryException);
        PasswordRecoveryController passwordRecoveryController = new PasswordRecoveryController(beanFactory, mySqlDAOFactory, controllerFactory);
        String result = passwordRecoveryController.getPasswordRecoveryInstruction(httpServletRequest);
        Assert.assertEquals("passwordRecovery", result);
    }

}
