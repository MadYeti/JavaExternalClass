package org.mycompany.controllers.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.models.factory.DAOFactory;
import org.springframework.beans.factory.BeanFactory;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;

public class AdminBidControllerTest {

    @Test
    public void callGetMethodOnAdminBidController(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        AdminBidController adminBidController = new AdminBidController(jaxbParser, beanFactory, mySqlDAOFactory);
        String result = adminBidController.crudOperations(httpServletRequest);
        Assert.assertEquals("adminPrivateOffice", result);
    }

}
