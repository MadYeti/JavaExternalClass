package org.mycompany.controllers.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.mycompany.controllers.creditCard.CreditCardDataController;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.exceptions.InvalidCreditCardDataException;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.bidDAO.BidDAOMySql;
import org.mycompany.models.dao.paymentStatusDAO.PaymentStatusDAOMySql;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.DAOFactory;
import org.mycompany.models.observer.Subject;
import org.springframework.beans.factory.BeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuyingControllerTest {

    @Test
    public void callPostMethodOnBuyingControllerRightInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        Subject subject = mock(Subject.class);
        HttpSession httpSession = mock(HttpSession.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        CreditCardDataController creditCardDataController = mock(CreditCardDataController.class);
        BidDAOMySql bidDAOMySql = mock(BidDAOMySql.class);
        Bid bid = mock(Bid.class);
        PaymentStatusDAOMySql paymentStatusDAOMySql = mock(PaymentStatusDAOMySql.class);
        Client client = mock(Client.class);
        BuyingController buyingController = new BuyingController(jaxbParser, subject, beanFactory, mySqlDAOFactory, controllerFactory);
        String id = "52";
        String price = "105.7";
        String creditCardNumber = "1234567890123456";
        String creditCardExpirationDate = "10/23";
        String creditCardCVVCode = "395";
        when(httpServletRequest.getParameter("bidNumber")).thenReturn(id);
        when(httpServletRequest.getParameter("bidPrice")).thenReturn(price);
        when(httpServletRequest.getParameter("creditCardNumber")).thenReturn(creditCardNumber);
        when(httpServletRequest.getParameter("creditCardDate")).thenReturn(creditCardExpirationDate);
        when(httpServletRequest.getParameter("cvv")).thenReturn(creditCardCVVCode);
        when(controllerFactory.getCreditCardController()).thenReturn(creditCardDataController);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(creditCardDataController.validateCreditCardData(creditCardNumber, creditCardExpirationDate, creditCardCVVCode)).thenReturn(false);
        when(mySqlDAOFactory.createBidDAO()).thenReturn(bidDAOMySql);
        when(jaxbParser.createObjectBasedOnXML(52)).thenReturn(bid);
        when(mySqlDAOFactory.createPaymentStatusDAO()).thenReturn(paymentStatusDAOMySql);
        when((Client)httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        String result = buyingController.payBid(httpServletRequest);
        Assert.assertEquals("forward:/buyingPage?pay=".concat(String.valueOf(id)).concat("&price=").concat(price), result);
    }

    @Test
    public void callPostMethodOnBuyingControllerWrongInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        Subject subject = mock(Subject.class);
        HttpSession httpSession = mock(HttpSession.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        InvalidCreditCardDataException invalidCreditCardDataException = mock(InvalidCreditCardDataException.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        CreditCardDataController creditCardDataController = mock(CreditCardDataController.class);
        BuyingController buyingController = new BuyingController(jaxbParser, subject, beanFactory, mySqlDAOFactory, controllerFactory);
        String id = "52";
        String price = "105.7";
        String creditCardNumber = "12frf44sASD231ads@!";
        String creditCardExpirationDate = "10/23";
        String creditCardCVVCode = "395";
        when(httpServletRequest.getParameter("bidNumber")).thenReturn(id);
        when(httpServletRequest.getParameter("bidPrice")).thenReturn(price);
        when(httpServletRequest.getParameter("creditCardNumber")).thenReturn(creditCardNumber);
        when(httpServletRequest.getParameter("creditCardDate")).thenReturn(creditCardExpirationDate);
        when(httpServletRequest.getParameter("cvv")).thenReturn(creditCardCVVCode);
        when(controllerFactory.getCreditCardController()).thenReturn(creditCardDataController);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(creditCardDataController.validateCreditCardData(creditCardNumber, creditCardExpirationDate, creditCardCVVCode)).thenReturn(true);
        when(beanFactory.getBean(InvalidCreditCardDataException.class, "Invalid credit card data input to pay the bid")).thenReturn(invalidCreditCardDataException);
        String result = buyingController.payBid(httpServletRequest);
        Assert.assertEquals("forward:/buyingPage?pay=".concat(String.valueOf(id)).concat("&price=").concat(price), result);
    }

}
