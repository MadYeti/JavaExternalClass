package org.mycompany.controllers.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class AdminBidControllerServletTest {

    @Test
    public void callGetMethodOnAdminBidControllerServlet(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        AdminBidControllerServlet adminBidControllerServlet = new AdminBidControllerServlet();
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/adminPrivateOffice.jsp")).thenReturn(requestDispatcher);
        try {
            adminBidControllerServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/adminPrivateOffice.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
