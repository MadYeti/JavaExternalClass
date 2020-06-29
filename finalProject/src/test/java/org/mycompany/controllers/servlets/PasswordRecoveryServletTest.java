package org.mycompany.controllers.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class PasswordRecoveryServletTest {

    @Test
    public void callPostMethodOnPasswordRecoveryServletRightInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        PasswordRecoveryServlet passwordRecoveryServlet = new PasswordRecoveryServlet();
        String email = "ivancov13@bigmir.net";
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/passwordRecovery.jsp")).thenReturn(requestDispatcher);
        try {
            passwordRecoveryServlet.doPost(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/passwordRecovery.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callPostMethodOnPasswordRecoveryServletWrongInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        PasswordRecoveryServlet passwordRecoveryServlet = new PasswordRecoveryServlet();
        String email = "fg98asb4cx#h1";
        when(httpServletRequest.getParameter("email")).thenReturn(email);
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/passwordRecovery.jsp")).thenReturn(requestDispatcher);
        try {
            passwordRecoveryServlet.doPost(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/passwordRecovery.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
