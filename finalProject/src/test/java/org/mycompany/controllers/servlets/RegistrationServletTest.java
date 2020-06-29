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

public class RegistrationServletTest {

    @Test
    public void callPostMethodOnRegistrationServletRightInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        RegistrationServlet registrationServlet = new RegistrationServlet();
        RegistrationController registrationController = mock(RegistrationController.class);
        String email = "baracuda75@gmail.com";
        String password = "f3r7t0#Q";
        String retypedPassword = "f3r7t0#Q";
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(httpServletRequest.getParameter("retypedPassword")).thenReturn(retypedPassword);
        when(registrationController.validateData(email, password, retypedPassword)).thenReturn(true);
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp")).thenReturn(requestDispatcher);
        try {
            registrationServlet.doPost(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/index.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callPostMethodOnRegistrationServletWrongInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        RegistrationServlet registrationServlet = new RegistrationServlet();
        RegistrationController registrationController = mock(RegistrationController.class);
        String email = "ba2131231";
        String password = "qwerty";
        String retypedPassword = "abc";
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(httpServletRequest.getParameter("retypedPassword")).thenReturn(retypedPassword);
        when(registrationController.validateData(email, password, retypedPassword)).thenReturn(false);
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/sighUp.jsp")).thenReturn(requestDispatcher);
        try {
            registrationServlet.doPost(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/sighUp.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
