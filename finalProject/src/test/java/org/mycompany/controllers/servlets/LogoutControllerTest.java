package org.mycompany.controllers.servlets;

import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogoutControllerTest {

    @Test
    public void callGetMethodOnLogoutControllerWithSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        LogoutController logoutController = new LogoutController();
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        String result = logoutController.logOut(httpServletRequest);
        Assert.assertEquals("index", result);
    }

    @Test
    public void callGetMethodOnLogoutControllerWithoutSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        LogoutController logoutController = new LogoutController();
        when(httpServletRequest.getSession()).thenReturn(null);
        String result = logoutController.logOut(httpServletRequest);
        Assert.assertEquals("index", result);
    }

}
