package org.mycompany.controllers.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.mycompany.controllers.registration.RegistrationDataController;
import org.mycompany.exceptions.RegistrationException;
import org.mycompany.models.dao.clientDAO.ClientDAOMySql;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.DAOFactory;
import org.springframework.beans.factory.BeanFactory;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegistrationControllerTest {

    @Test
    public void callPostMethodOnRegistrationControllerRightInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        RegistrationDataController registrationDataController = mock(RegistrationDataController.class);
        ClientDAOMySql clientDAOMySql = mock(ClientDAOMySql.class);
        RegistrationController registrationController = new RegistrationController(beanFactory, controllerFactory, mySqlDAOFactory);
        String email = "baracuda75@gmail.com";
        String password = "f3r7t0#Q";
        String retypedPassword = "f3r7t0#Q";
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(httpServletRequest.getParameter("retypedPassword")).thenReturn(retypedPassword);
        when(controllerFactory.getRegistrationDataController()).thenReturn(registrationDataController);
        when(registrationDataController.validateData(email, password, retypedPassword)).thenReturn(false);
        when(mySqlDAOFactory.createClientDAO()).thenReturn(clientDAOMySql);
        String result = registrationController.registrateClient(httpServletRequest);
        Assert.assertEquals("index", result);
    }

    @Test
    public void callPostMethodOnRegistrationControllerWrongInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        RegistrationException registrationException = mock(RegistrationException.class);
        RegistrationDataController registrationDataController = mock(RegistrationDataController.class);
        RegistrationController registrationController = new RegistrationController(beanFactory, controllerFactory, mySqlDAOFactory);
        String email = "ba2131231";
        String password = "qwerty";
        String retypedPassword = "abc";
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(httpServletRequest.getParameter("retypedPassword")).thenReturn(retypedPassword);
        when(controllerFactory.getRegistrationDataController()).thenReturn(registrationDataController);
        when(registrationDataController.validateData(email, password, retypedPassword)).thenReturn(true);
        when(beanFactory.getBean(RegistrationException.class, "Invalid input data to register user")).thenReturn(registrationException);
        String result = registrationController.registrateClient(httpServletRequest);
        Assert.assertEquals("sighUp", result);
    }

}
