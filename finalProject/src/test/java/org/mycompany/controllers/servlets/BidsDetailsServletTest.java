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

public class BidsDetailsServletTest {

    @Test
    public void callGetMethodOnBidsDetailsServletWithSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        Client client = mock(Client.class);
        HttpSession httpSession = mock(HttpSession.class);
        BidsDetailsServlet bidsDetailsServlet = new BidsDetailsServlet();
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/bidsHolder.jsp")).thenReturn(requestDispatcher);
        try {
            bidsDetailsServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/bidsHolder.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callGetMethodOnBidsDetailsServletWithoutSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        BidsDetailsServlet bidsDetailsServlet = new BidsDetailsServlet();
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(null);
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/notEnoughPrivilegesPage.jsp")).thenReturn(requestDispatcher);
        try {
            bidsDetailsServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/notEnoughPrivilegesPage.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
