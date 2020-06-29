package org.mycompany.controllers.servlets;

import org.junit.Test;
import org.mycompany.controllers.registration.RegistrationController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class ResetPasswordServletTest {

    @Test
    public void callPostMethodOnResetPasswordServletRightInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        ResetPasswordServlet resetPasswordServlet = new ResetPasswordServlet();
        RegistrationController registrationController = mock(RegistrationController.class);
        String password = "ok98#@Q";
        String retypedPassword = "ok98#@Q";
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(httpServletRequest.getParameter("retypedPassword")).thenReturn(retypedPassword);
        when(registrationController.validatePasswords(password, retypedPassword)).thenReturn(true);
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/resetPassword.jsp")).thenReturn(requestDispatcher);
        try {
            resetPasswordServlet.doPost(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/resetPassword.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callPostMethodOnResetPasswordServletWrongInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        ResetPasswordServlet resetPasswordServlet = new ResetPasswordServlet();
        RegistrationController registrationController = mock(RegistrationController.class);
        String password = "o123";
        String retypedPassword = "ASD";
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(httpServletRequest.getParameter("retypedPassword")).thenReturn(retypedPassword);
        when(registrationController.validatePasswords(password, retypedPassword)).thenReturn(false);
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/resetPassword.jsp")).thenReturn(requestDispatcher);
        try {
            resetPasswordServlet.doPost(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/resetPassword.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
