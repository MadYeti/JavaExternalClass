package org.mycompany.controllers.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class LogoutServletTest {

    @Test
    public void callPostMethodOnLogoutServletWithSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        LogoutServlet logoutServlet = new LogoutServlet();
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp")).thenReturn(requestDispatcher);
        try {
            logoutServlet.doPost(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/index.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callPostMethodOnLogoutServletWithoutSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(httpServletRequest.getSession()).thenReturn(null);
        LogoutServlet logoutServlet = new LogoutServlet();
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp")).thenReturn(requestDispatcher);
        try {
            logoutServlet.doPost(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/index.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
