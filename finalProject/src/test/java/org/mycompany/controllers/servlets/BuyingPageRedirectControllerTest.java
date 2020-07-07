package org.mycompany.controllers.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.mycompany.models.client.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuyingPageRedirectControllerTest {

    @Test
    public void callGetMethodOnBuyingPageRedirectControllerWithSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        Client client = mock(Client.class);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        BuyingPageRedirectController buyingPageRedirectController = new BuyingPageRedirectController();
        String result = buyingPageRedirectController.getDefaultBuyingPage(httpServletRequest);
        Assert.assertEquals("buyingPage", result);
    }

    @Test
    public void callGetMethodOnBuyingPageRedirectControllerWithoutSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(null);
        BuyingPageRedirectController buyingPageRedirectController = new BuyingPageRedirectController();
        String result = buyingPageRedirectController.getDefaultBuyingPage(httpServletRequest);
        Assert.assertEquals("notEnoughPrivilegesPage", result);
    }

    @Test
    public void callPostMethodOnBuyingPageRedirectControllerWithSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        Client client = mock(Client.class);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        BuyingPageRedirectController buyingPageRedirectController = new BuyingPageRedirectController();
        String result = buyingPageRedirectController.postDefaultBuyingPage(httpServletRequest);
        Assert.assertEquals("buyingPage", result);
    }

    @Test
    public void callPostMethodOnBuyingPageRedirectControllerWithoutSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(null);
        BuyingPageRedirectController buyingPageRedirectController = new BuyingPageRedirectController();
        String result = buyingPageRedirectController.postDefaultBuyingPage(httpServletRequest);
        Assert.assertEquals("notEnoughPrivilegesPage", result);
    }

}
