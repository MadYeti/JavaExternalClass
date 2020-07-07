package org.mycompany.controllers.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.mycompany.models.client.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdminPageRedirectControllerTest {

    @Test
    public void callGetMethodOnAdminPageRedirectControllerWithSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        Client client = mock(Client.class);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        AdminPageRedirectController adminPageRedirectController = new AdminPageRedirectController();
        String result = adminPageRedirectController.getAdminPage(httpServletRequest);
        Assert.assertEquals("admin", result);
    }

    @Test
    public void callGetMethodOnAdminPageRedirectControllerWithoutSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(null);
        AdminPageRedirectController adminPageRedirectController = new AdminPageRedirectController();
        String result = adminPageRedirectController.getAdminPage(httpServletRequest);
        Assert.assertEquals("notEnoughPrivilegesPage", result);
    }

}
