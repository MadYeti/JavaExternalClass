package org.mycompany.controllers.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.mycompany.models.client.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerPageRedirectControllerTest {

    @Test
    public void callGetMethodOnCustomerPageRedirectControllerWithSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        Client client = mock(Client.class);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        CustomerPageRedirectController customerPageRedirectController = new CustomerPageRedirectController();
        String result = customerPageRedirectController.getCustomerPage(httpServletRequest);
        Assert.assertEquals("customer", result);
    }

    @Test
    public void callGetMethodOnCustomerPageRedirectControllerWithoutSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(null);
        CustomerPageRedirectController customerPageRedirectController = new CustomerPageRedirectController();
        String result = customerPageRedirectController.getCustomerPage(httpServletRequest);
        Assert.assertEquals("notEnoughPrivilegesPage", result);
    }

}
