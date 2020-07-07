package org.mycompany.controllers.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.mycompany.controllers.authorization.AuthorizationDataController;
import org.mycompany.exceptions.AuthorizationException;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.clientDAO.ClientDAOMySql;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.DAOFactory;
import org.springframework.beans.factory.BeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthorizationControllerTest {

    @Test
    public void callPostMethodOnAuthorizationControllerWithWrongCredentials(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        AuthorizationException authorizationException = mock(AuthorizationException.class);
        AuthorizationDataController authorizationDataController = mock(AuthorizationDataController.class);
        AuthorizationController authorizationController = new AuthorizationController(beanFactory, mySqlDAOFactory, controllerFactory);
        String email = "qwerty";
        String password = "12345";
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(controllerFactory.getAuthorizationDataController()).thenReturn(authorizationDataController);
        when(authorizationDataController.validateData(email, password)).thenReturn(false);
        when(beanFactory.getBean(AuthorizationException.class, "Invalid credentials input to log in")).thenReturn(authorizationException);
        String result = authorizationController.authorizeClient(httpServletRequest);
        Assert.assertEquals("sighIn", result);
    }

    @Test
    public void callPostMethodOnAuthorizationControllerWithRightCredentialsAndExistedUser(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        AuthorizationDataController authorizationDataController = mock(AuthorizationDataController.class);
        HttpSession httpSession = mock(HttpSession.class);
        Client client = mock(Client.class);
        ClientDAOMySql clientDAOMySql = mock(ClientDAOMySql.class);
        AuthorizationController authorizationController = new AuthorizationController(beanFactory, mySqlDAOFactory, controllerFactory);
        String email = "ivancov13@bigmir.net";
        String password = "zzzZ1!";
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(controllerFactory.getAuthorizationDataController()).thenReturn(authorizationDataController);
        when(authorizationDataController.validateData(email, password)).thenReturn(true);
        when(mySqlDAOFactory.createClientDAO()).thenReturn(clientDAOMySql);
        when(clientDAOMySql.getClient(email, password)).thenReturn(client);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(client.getRole()).thenReturn("customer");
        String result = authorizationController.authorizeClient(httpServletRequest);
        Assert.assertEquals(client.getRole(), result);
    }

    @Test
    public void callPostMethodOnAuthorizationControllerWithRightCredentialsAndNonExistedUser(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        AuthorizationDataController authorizationDataController = mock(AuthorizationDataController.class);
        ClientDAOMySql clientDAOMySql = mock(ClientDAOMySql.class);
        AuthorizationController authorizationController = new AuthorizationController(beanFactory, mySqlDAOFactory, controllerFactory);
        String email = "ivancov13@bigmir.net";
        String password = "yui@2D";
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(controllerFactory.getAuthorizationDataController()).thenReturn(authorizationDataController);
        when(authorizationDataController.validateData(email, password)).thenReturn(true);
        when(mySqlDAOFactory.createClientDAO()).thenReturn(clientDAOMySql);
        when(clientDAOMySql.getClient(email, password)).thenReturn(null);
        String result = authorizationController.authorizeClient(httpServletRequest);
        Assert.assertEquals("sighIn", result);
    }

}
