package org.mycompany.controllers.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.mycompany.controllers.registration.RegistrationDataController;
import org.mycompany.exceptions.ResetPasswordException;
import org.mycompany.models.dao.clientDAO.ClientDAOMySql;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.DAOFactory;
import org.springframework.beans.factory.BeanFactory;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResetPasswordControllerTest {

    @Test
    public void callPostMethodOnResetPasswordControllerRightInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        RegistrationDataController registrationDataController = mock(RegistrationDataController.class);
        ClientDAOMySql clientDAOMySql = mock(ClientDAOMySql.class);
        ResetPasswordController resetPasswordController = new ResetPasswordController(beanFactory, mySqlDAOFactory, controllerFactory);
        String token = "12hu0fqwdfqf0fu2jf12";
        String password = "ok98#@Q";
        String retypedPassword = "ok98#@Q";
        when(httpServletRequest.getParameter("token")).thenReturn(token);
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(httpServletRequest.getParameter("retypedPassword")).thenReturn(retypedPassword);
        when(controllerFactory.getRegistrationDataController()).thenReturn(registrationDataController);
        when(registrationDataController.validatePasswords(password, retypedPassword)).thenReturn(false);
        when(mySqlDAOFactory.createClientDAO()).thenReturn(clientDAOMySql);
        String result = resetPasswordController.resetPassword(httpServletRequest);
        Assert.assertEquals("resetPassword", result);
    }

    @Test
    public void callPostMethodOnResetPasswordControllerWrongInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ResetPasswordException resetPasswordException = mock(ResetPasswordException.class);
        RegistrationDataController registrationDataController = mock(RegistrationDataController.class);
        ResetPasswordController resetPasswordController = new ResetPasswordController(beanFactory, mySqlDAOFactory, controllerFactory);
        String password = "o123";
        String retypedPassword = "ASD";
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(httpServletRequest.getParameter("retypedPassword")).thenReturn(retypedPassword);
        when(controllerFactory.getRegistrationDataController()).thenReturn(registrationDataController);
        when(registrationDataController.validatePasswords(password, retypedPassword)).thenReturn(true);
        when(beanFactory.getBean(ResetPasswordException.class, "Invalid password/retyped password in recovery password section")).thenReturn(resetPasswordException);
        String result = resetPasswordController.resetPassword(httpServletRequest);
        Assert.assertEquals("resetPassword", result);
    }

}
