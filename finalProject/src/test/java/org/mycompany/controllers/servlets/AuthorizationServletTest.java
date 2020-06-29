package org.mycompany.controllers.servlets;

import org.junit.Test;
import org.mycompany.controllers.authorization.AuthorizationController;
import org.mycompany.models.client.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class AuthorizationServletTest {

    @Test
    public void callPostMethodOnAuthorizationServletWithWrongCredentials(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        AuthorizationController authorizationController = mock(AuthorizationController.class);
        AuthorizationServlet authorizationServlet = new AuthorizationServlet();
        String email = "qwerty";
        String password = "12345";
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(authorizationController.validateData(email, password)).thenReturn(false);
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/sighIn.jsp")).thenReturn(requestDispatcher);
        try {
            authorizationServlet.doPost(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/sighIn.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callPostMethodOnAuthorizationServletWithRightCredentialsAndExistedUser(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        AuthorizationController authorizationController = mock(AuthorizationController.class);
        HttpSession httpSession = mock(HttpSession.class);
        Client client = mock(Client.class);
        AuthorizationServlet authorizationServlet = new AuthorizationServlet();
        String email = "ivancov13@bigmir.net";
        String password = "zzzZ1!";
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(authorizationController.validateData(email, password)).thenReturn(true);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(client.getRole()).thenReturn("customer");
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/".concat(client.getRole()).concat(".jsp"))).thenReturn(requestDispatcher);
        try {
            authorizationServlet.doPost(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/".concat(client.getRole()).concat(".jsp"));
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callPostMethodOnAuthorizationServletWithRightCredentialsAndNonExistedUser(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        AuthorizationController authorizationController = mock(AuthorizationController.class);
        AuthorizationServlet authorizationServlet = new AuthorizationServlet();
        String email = "ivancov13@bigmir.net";
        String password = "yui@2D";
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(httpServletRequest.getParameter("password")).thenReturn(password);
        when(authorizationController.validateData(email, password)).thenReturn(true);
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/sighIn.jsp")).thenReturn(requestDispatcher);
        try {
            authorizationServlet.doPost(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/sighIn.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
