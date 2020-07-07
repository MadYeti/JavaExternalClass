package org.mycompany.controllers.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.mycompany.models.client.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdminPrivateOfficeRedirectControllerTest {

    @Test
    public void callGetMethodOnAdminPrivateOfficeRedirectControllerWithSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        Client client = mock(Client.class);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        AdminPrivateOfficeRedirectController adminPrivateOfficeRedirectController = new AdminPrivateOfficeRedirectController();
        String result = adminPrivateOfficeRedirectController.getAdminPrivateOfficePage(httpServletRequest);
        Assert.assertEquals("adminPrivateOffice", result);
    }

    @Test
    public void callGetMethodOnAdminPrivateOfficeRedirectControllerWithoutSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(null);
        AdminPrivateOfficeRedirectController adminPrivateOfficeRedirectController = new AdminPrivateOfficeRedirectController();
        String result = adminPrivateOfficeRedirectController.getAdminPrivateOfficePage(httpServletRequest);
        Assert.assertEquals("notEnoughPrivilegesPage", result);
    }

}
