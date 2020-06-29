package org.mycompany.controllers.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class ResetPasswordRedirectServletTest {

    @Test
    public void callPostMethodOnResetPasswordRedirectServlet(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        ResetPasswordRedirectServlet resetPasswordRedirectServlet = new ResetPasswordRedirectServlet();
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/resetPassword.jsp")).thenReturn(requestDispatcher);
        try {
            resetPasswordRedirectServlet.doPost(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/resetPassword.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
