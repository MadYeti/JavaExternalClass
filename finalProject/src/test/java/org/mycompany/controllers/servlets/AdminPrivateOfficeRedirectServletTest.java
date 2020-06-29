package org.mycompany.controllers.servlets;

import org.junit.Test;
import org.mycompany.models.client.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class AdminPrivateOfficeRedirectServletTest {

    @Test
    public void callGetMethodOnAdminPrivateOfficeRedirectServletWithSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        Client client = mock(Client.class);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when((Client)httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        AdminPrivateOfficeRedirectServlet adminPrivateOfficeRedirectServlet = new AdminPrivateOfficeRedirectServlet();
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/adminPrivateOffice.jsp")).thenReturn(requestDispatcher);
        try {
            adminPrivateOfficeRedirectServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/adminPrivateOffice.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callGetMethodOnAdminPrivateOfficeRedirectServletWithoutSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(null);
        AdminPrivateOfficeRedirectServlet adminPrivateOfficeRedirectServlet = new AdminPrivateOfficeRedirectServlet();
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/notEnoughPrivilegesPage.jsp")).thenReturn(requestDispatcher);
        try {
            adminPrivateOfficeRedirectServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/notEnoughPrivilegesPage.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
