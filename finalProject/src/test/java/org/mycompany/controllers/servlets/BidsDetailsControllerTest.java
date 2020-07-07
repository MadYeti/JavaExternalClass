package org.mycompany.controllers.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.mycompany.models.bidsHolder.BidsHolder;
import org.mycompany.models.client.Client;
import org.mycompany.models.repository.BidsHolderImpl;
import org.springframework.beans.factory.BeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BidsDetailsControllerTest {

    @Test
    public void callGetMethodOnBidDetailsControllerWithSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        Client client = mock(Client.class);
        HttpSession httpSession = mock(HttpSession.class);
        BidsHolderImpl bidsHolderImpl = mock(BidsHolderImpl.class);
        BidsHolder bidsHolder = mock(BidsHolder.class);
        BidsDetailsController bidsDetailsController = new BidsDetailsController(beanFactory);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        when(beanFactory.getBean(BidsHolderImpl.class)).thenReturn(bidsHolderImpl);
        when(beanFactory.getBean(BidsHolder.class)).thenReturn(bidsHolder);
        String result = bidsDetailsController.getAllOrderedBids(httpServletRequest);
        Assert.assertEquals("bidsHolder", result);
    }

    @Test
    public void callGetMethodOnBidDetailsControllerWithoutSession(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        HttpSession httpSession = mock(HttpSession.class);
        BidsDetailsController bidsDetailsController = new BidsDetailsController(beanFactory);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(null);
        String result = bidsDetailsController.getAllOrderedBids(httpServletRequest);
        Assert.assertEquals("notEnoughPrivilegesPage", result);
    }

}
