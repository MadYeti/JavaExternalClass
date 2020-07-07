package org.mycompany.controllers.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.mycompany.controllers.dateProccesing.DateController;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.exceptions.InvalidBidDataException;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.bidDAO.BidDAOMySql;
import org.mycompany.models.dao.bidStatusDAO.BidStatusDAOMySql;
import org.mycompany.models.dao.cargoTypeDAO.CargoTypeDAOMySql;
import org.mycompany.models.dao.destinationPointDAO.DestinationPointDAOMySql;
import org.mycompany.models.dao.paymentStatusDAO.PaymentStatusDAOMySql;
import org.mycompany.models.dao.sendingPointDAO.SendingPointDAOMySql;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.DAOFactory;
import org.springframework.beans.factory.BeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BidOrderPriceControllerTest {

    @Test
    public void callGetMethodOnBidOrderPriceControllerWrongWeightInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        InvalidBidDataException invalidBidDataException = mock(InvalidBidDataException.class);
        BidDAOMySql bidDAOHelper = mock(BidDAOMySql.class);
        BidOrderPriceController bidOrderPriceController = new BidOrderPriceController(jaxbParser, beanFactory, mySqlDAOFactory, controllerFactory);
        String weight = "abc";
        String volume = "145.9";
        String type = "2";
        String cost = "101.2";
        String sendingPoint = "7";
        String destinationPoint = "3";
        String notes = "Main street 1, building #47";
        String lang = "en_EN";
        when(httpServletRequest.getParameter("weight")).thenReturn(weight);
        when(httpServletRequest.getParameter("volume")).thenReturn(volume);
        when(httpServletRequest.getParameter("type")).thenReturn(type);
        when(httpServletRequest.getParameter("cost")).thenReturn(cost);
        when(httpServletRequest.getParameter("sendingPoint")).thenReturn(sendingPoint);
        when(httpServletRequest.getParameter("destinationPoint")).thenReturn(destinationPoint);
        when(httpServletRequest.getParameter("notes")).thenReturn(notes);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("lang")).thenReturn(lang);
        when(mySqlDAOFactory.createBidDAO()).thenReturn(bidDAOHelper);
        when(beanFactory.getBean(InvalidBidDataException.class, "Invalid bid data to get price or format the bid")).thenReturn(invalidBidDataException);
        String result = bidOrderPriceController.estimateOrderBid(httpServletRequest);
        Assert.assertEquals("index" , result);
    }

    @Test
    public void callGetMethodOnBidOrderPriceControllerWrongVolumeInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        InvalidBidDataException invalidBidDataException = mock(InvalidBidDataException.class);
        BidDAOMySql bidDAOHelper = mock(BidDAOMySql.class);
        BidOrderPriceController bidOrderPriceController = new BidOrderPriceController(jaxbParser, beanFactory, mySqlDAOFactory, controllerFactory);
        String weight = "89.4";
        String volume = "5t4r";
        String type = "2";
        String cost = "101.2";
        String sendingPoint = "7";
        String destinationPoint = "3";
        String notes = "Main street 1, building #47";
        String lang = "en_EN";
        when(httpServletRequest.getParameter("weight")).thenReturn(weight);
        when(httpServletRequest.getParameter("volume")).thenReturn(volume);
        when(httpServletRequest.getParameter("type")).thenReturn(type);
        when(httpServletRequest.getParameter("cost")).thenReturn(cost);
        when(httpServletRequest.getParameter("sendingPoint")).thenReturn(sendingPoint);
        when(httpServletRequest.getParameter("destinationPoint")).thenReturn(destinationPoint);
        when(httpServletRequest.getParameter("notes")).thenReturn(notes);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("lang")).thenReturn(lang);
        when(mySqlDAOFactory.createBidDAO()).thenReturn(bidDAOHelper);
        when(beanFactory.getBean(InvalidBidDataException.class, "Invalid bid data to get price or format the bid")).thenReturn(invalidBidDataException);
        String result = bidOrderPriceController.estimateOrderBid(httpServletRequest);
        Assert.assertEquals("index" , result);
    }

    @Test
    public void callGetMethodOnBidOrderPriceControllerWrongTypeInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        InvalidBidDataException invalidBidDataException = mock(InvalidBidDataException.class);
        BidDAOMySql bidDAOHelper = mock(BidDAOMySql.class);
        BidOrderPriceController bidOrderPriceController = new BidOrderPriceController(jaxbParser, beanFactory, mySqlDAOFactory, controllerFactory);
        String weight = "89.4";
        String volume = "429.90";
        String type = "0";
        String cost = "101.2";
        String sendingPoint = "7";
        String destinationPoint = "3";
        String notes = "Main street 1, building #47";
        String lang = "en_EN";
        when(httpServletRequest.getParameter("weight")).thenReturn(weight);
        when(httpServletRequest.getParameter("volume")).thenReturn(volume);
        when(httpServletRequest.getParameter("type")).thenReturn(type);
        when(httpServletRequest.getParameter("cost")).thenReturn(cost);
        when(httpServletRequest.getParameter("sendingPoint")).thenReturn(sendingPoint);
        when(httpServletRequest.getParameter("destinationPoint")).thenReturn(destinationPoint);
        when(httpServletRequest.getParameter("notes")).thenReturn(notes);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("lang")).thenReturn(lang);
        when(mySqlDAOFactory.createBidDAO()).thenReturn(bidDAOHelper);
        when(beanFactory.getBean(InvalidBidDataException.class, "Invalid bid data to get price or format the bid")).thenReturn(invalidBidDataException);
        String result = bidOrderPriceController.estimateOrderBid(httpServletRequest);
        Assert.assertEquals("index" , result);
    }

    @Test
    public void callGetMethodOnBidOrderPriceControllerWrongCostInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        InvalidBidDataException invalidBidDataException = mock(InvalidBidDataException.class);
        BidDAOMySql bidDAOHelper = mock(BidDAOMySql.class);
        BidOrderPriceController bidOrderPriceController = new BidOrderPriceController(jaxbParser, beanFactory, mySqlDAOFactory, controllerFactory);
        String weight = "89.4";
        String volume = "429.90";
        String type = "2";
        String cost = "$R2";
        String sendingPoint = "7";
        String destinationPoint = "3";
        String notes = "Main street 1, building #47";
        String lang = "en_EN";
        when(httpServletRequest.getParameter("weight")).thenReturn(weight);
        when(httpServletRequest.getParameter("volume")).thenReturn(volume);
        when(httpServletRequest.getParameter("type")).thenReturn(type);
        when(httpServletRequest.getParameter("cost")).thenReturn(cost);
        when(httpServletRequest.getParameter("sendingPoint")).thenReturn(sendingPoint);
        when(httpServletRequest.getParameter("destinationPoint")).thenReturn(destinationPoint);
        when(httpServletRequest.getParameter("notes")).thenReturn(notes);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("lang")).thenReturn(lang);
        when(mySqlDAOFactory.createBidDAO()).thenReturn(bidDAOHelper);
        when(beanFactory.getBean(InvalidBidDataException.class, "Invalid bid data to get price or format the bid")).thenReturn(invalidBidDataException);
        String result = bidOrderPriceController.estimateOrderBid(httpServletRequest);
        Assert.assertEquals("index" , result);
    }

    @Test
    public void callGetMethodOnBidOrderPriceControllerWrongSendingPointInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        InvalidBidDataException invalidBidDataException = mock(InvalidBidDataException.class);
        BidDAOMySql bidDAOHelper = mock(BidDAOMySql.class);
        BidOrderPriceController bidOrderPriceController = new BidOrderPriceController(jaxbParser, beanFactory, mySqlDAOFactory, controllerFactory);
        String weight = "89.4";
        String volume = "429.90";
        String type = "2";
        String cost = "56.76";
        String sendingPoint = "0";
        String destinationPoint = "3";
        String notes = "Main street 1, building #47";
        String lang = "en_EN";
        when(httpServletRequest.getParameter("weight")).thenReturn(weight);
        when(httpServletRequest.getParameter("volume")).thenReturn(volume);
        when(httpServletRequest.getParameter("type")).thenReturn(type);
        when(httpServletRequest.getParameter("cost")).thenReturn(cost);
        when(httpServletRequest.getParameter("sendingPoint")).thenReturn(sendingPoint);
        when(httpServletRequest.getParameter("destinationPoint")).thenReturn(destinationPoint);
        when(httpServletRequest.getParameter("notes")).thenReturn(notes);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("lang")).thenReturn(lang);
        when(mySqlDAOFactory.createBidDAO()).thenReturn(bidDAOHelper);
        when(beanFactory.getBean(InvalidBidDataException.class, "Invalid bid data to get price or format the bid")).thenReturn(invalidBidDataException);
        String result = bidOrderPriceController.estimateOrderBid(httpServletRequest);
        Assert.assertEquals("index" , result);
    }

    @Test
    public void callGetMethodOnBidOrderPriceControllerWrongDestinationPointInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        InvalidBidDataException invalidBidDataException = mock(InvalidBidDataException.class);
        BidDAOMySql bidDAOHelper = mock(BidDAOMySql.class);
        BidOrderPriceController bidOrderPriceController = new BidOrderPriceController(jaxbParser, beanFactory, mySqlDAOFactory, controllerFactory);
        String weight = "89.4";
        String volume = "429.90";
        String type = "2";
        String cost = "56.76";
        String sendingPoint = "6";
        String destinationPoint = "0";
        String notes = "Main street 1, building #47";
        String lang = "en_EN";
        when(httpServletRequest.getParameter("weight")).thenReturn(weight);
        when(httpServletRequest.getParameter("volume")).thenReturn(volume);
        when(httpServletRequest.getParameter("type")).thenReturn(type);
        when(httpServletRequest.getParameter("cost")).thenReturn(cost);
        when(httpServletRequest.getParameter("sendingPoint")).thenReturn(sendingPoint);
        when(httpServletRequest.getParameter("destinationPoint")).thenReturn(destinationPoint);
        when(httpServletRequest.getParameter("notes")).thenReturn(notes);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("lang")).thenReturn(lang);
        when(mySqlDAOFactory.createBidDAO()).thenReturn(bidDAOHelper);
        when(beanFactory.getBean(InvalidBidDataException.class, "Invalid bid data to get price or format the bid")).thenReturn(invalidBidDataException);
        String result = bidOrderPriceController.estimateOrderBid(httpServletRequest);
        Assert.assertEquals("index" , result);
    }

    @Test
    public void callGetMethodOnBidOrderPriceControllerWrongInputSendingAndDestinationPointsAreTheSame(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        InvalidBidDataException invalidBidDataException = mock(InvalidBidDataException.class);
        BidDAOMySql bidDAOHelper = mock(BidDAOMySql.class);
        BidOrderPriceController bidOrderPriceController = new BidOrderPriceController(jaxbParser, beanFactory, mySqlDAOFactory, controllerFactory);
        String weight = "89.4";
        String volume = "429.90";
        String type = "2";
        String cost = "56.76";
        String sendingPoint = "6";
        String destinationPoint = "6";
        String notes = "Main street 1, building #47";
        String lang = "en_EN";
        when(httpServletRequest.getParameter("weight")).thenReturn(weight);
        when(httpServletRequest.getParameter("volume")).thenReturn(volume);
        when(httpServletRequest.getParameter("type")).thenReturn(type);
        when(httpServletRequest.getParameter("cost")).thenReturn(cost);
        when(httpServletRequest.getParameter("sendingPoint")).thenReturn(sendingPoint);
        when(httpServletRequest.getParameter("destinationPoint")).thenReturn(destinationPoint);
        when(httpServletRequest.getParameter("notes")).thenReturn(notes);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("lang")).thenReturn(lang);
        when(mySqlDAOFactory.createBidDAO()).thenReturn(bidDAOHelper);
        when(beanFactory.getBean(InvalidBidDataException.class, "Invalid bid data to get price or format the bid")).thenReturn(invalidBidDataException);
        String result = bidOrderPriceController.estimateOrderBid(httpServletRequest);
        Assert.assertEquals("index" , result);
    }

    @Test
    public void callGetMethodOnBidOrderPriceControllerWrongInputWhereClientNotNull(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        InvalidBidDataException invalidBidDataException = mock(InvalidBidDataException.class);
        BidDAOMySql bidDAOHelper = mock(BidDAOMySql.class);
        Client client = mock(Client.class);
        BidOrderPriceController bidOrderPriceController = new BidOrderPriceController(jaxbParser, beanFactory, mySqlDAOFactory, controllerFactory);
        String weight = "abc";
        String volume = "145.9";
        String type = "2";
        String cost = "101.2";
        String sendingPoint = "7";
        String destinationPoint = "3";
        String notes = "Main street 1, building #47";
        String lang = "en_EN";
        when(httpServletRequest.getParameter("weight")).thenReturn(weight);
        when(httpServletRequest.getParameter("volume")).thenReturn(volume);
        when(httpServletRequest.getParameter("type")).thenReturn(type);
        when(httpServletRequest.getParameter("cost")).thenReturn(cost);
        when(httpServletRequest.getParameter("sendingPoint")).thenReturn(sendingPoint);
        when(httpServletRequest.getParameter("destinationPoint")).thenReturn(destinationPoint);
        when(httpServletRequest.getParameter("notes")).thenReturn(notes);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("lang")).thenReturn(lang);
        when(mySqlDAOFactory.createBidDAO()).thenReturn(bidDAOHelper);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        when(((Client) httpSession.getAttribute("client")).getRole()).thenReturn("customer");
        when(beanFactory.getBean(InvalidBidDataException.class, "Invalid bid data to get price or format the bid")).thenReturn(invalidBidDataException);
        String result = bidOrderPriceController.estimateOrderBid(httpServletRequest);
        Assert.assertEquals(client.getRole() , result);
    }

    @Test
    public void callGetMethodOnBidOrderPriceControllerRightInputWhereClientNotNullAndSubmitTrue(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        BidDAOMySql bidDAOHelper = mock(BidDAOMySql.class);
        Client client = mock(Client.class);
        DateController dateController = mock(DateController.class);
        Bid bid = new Bid();
        CargoTypeDAOMySql cargoTypeDAOMySql = mock(CargoTypeDAOMySql.class);
        SendingPointDAOMySql sendingPointDAOMySql = mock(SendingPointDAOMySql.class);
        DestinationPointDAOMySql destinationPointDAOMySql = mock(DestinationPointDAOMySql.class);
        BidStatusDAOMySql bidStatusDAOMySql = mock(BidStatusDAOMySql.class);
        PaymentStatusDAOMySql paymentStatusDAOMySql = mock(PaymentStatusDAOMySql.class);
        BidOrderPriceController bidOrderPriceController = new BidOrderPriceController(jaxbParser, beanFactory, mySqlDAOFactory, controllerFactory);
        String weight = "75.3";
        String volume = "145.9";
        String type = "2";
        String cost = "101.2";
        String sendingPoint = "7";
        String destinationPoint = "3";
        String notes = "Main street 1, building #47";
        String lang = "en_EN";
        when(httpServletRequest.getParameter("weight")).thenReturn(weight);
        when(httpServletRequest.getParameter("volume")).thenReturn(volume);
        when(httpServletRequest.getParameter("type")).thenReturn(type);
        when(httpServletRequest.getParameter("cost")).thenReturn(cost);
        when(httpServletRequest.getParameter("sendingPoint")).thenReturn(sendingPoint);
        when(httpServletRequest.getParameter("destinationPoint")).thenReturn(destinationPoint);
        when(httpServletRequest.getParameter("notes")).thenReturn(notes);
        when(httpServletRequest.getParameter("submit")).thenReturn("true");
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(mySqlDAOFactory.createCargoTypeDAO()).thenReturn(cargoTypeDAOMySql);
        when(mySqlDAOFactory.createSendingPointDAO()).thenReturn(sendingPointDAOMySql);
        when(mySqlDAOFactory.createDestinationPointDAO()).thenReturn(destinationPointDAOMySql);
        when(mySqlDAOFactory.createBidStatusDAO()).thenReturn(bidStatusDAOMySql);
        when(mySqlDAOFactory.createPaymentStatusDAO()).thenReturn(paymentStatusDAOMySql);
        when(controllerFactory.getDateController()).thenReturn(dateController);
        when(beanFactory.getBean(Bid.class)).thenReturn(bid);
        when(httpServletRequest.getSession().getAttribute("lang")).thenReturn(lang);
        when(mySqlDAOFactory.createBidDAO()).thenReturn(bidDAOHelper);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        when(((Client) httpSession.getAttribute("client")).getRole()).thenReturn("customer");
        String result = bidOrderPriceController.estimateOrderBid(httpServletRequest);
        Assert.assertEquals(client.getRole() , result);
    }

    @Test
    public void callGetMethodOnBidOrderPriceControllerRightInputWhereClientNotNullAndSubmitFalse(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        BidDAOMySql bidDAOHelper = mock(BidDAOMySql.class);
        Client client = mock(Client.class);
        BidOrderPriceController bidOrderPriceController = new BidOrderPriceController(jaxbParser, beanFactory, mySqlDAOFactory, controllerFactory);
        String weight = "75.3";
        String volume = "145.9";
        String type = "2";
        String cost = "101.2";
        String sendingPoint = "7";
        String destinationPoint = "3";
        String notes = "Main street 1, building #47";
        String lang = "en_EN";
        when(httpServletRequest.getParameter("weight")).thenReturn(weight);
        when(httpServletRequest.getParameter("volume")).thenReturn(volume);
        when(httpServletRequest.getParameter("type")).thenReturn(type);
        when(httpServletRequest.getParameter("cost")).thenReturn(cost);
        when(httpServletRequest.getParameter("sendingPoint")).thenReturn(sendingPoint);
        when(httpServletRequest.getParameter("destinationPoint")).thenReturn(destinationPoint);
        when(httpServletRequest.getParameter("notes")).thenReturn(notes);
        when(httpServletRequest.getParameter("submit")).thenReturn("false");
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("lang")).thenReturn(lang);
        when(mySqlDAOFactory.createBidDAO()).thenReturn(bidDAOHelper);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        when(((Client) httpSession.getAttribute("client")).getRole()).thenReturn("customer");
        String result = bidOrderPriceController.estimateOrderBid(httpServletRequest);
        Assert.assertEquals(client.getRole() , result);
    }

    @Test
    public void callGetMethodOnBidOrderPriceControllerRightInputWhereClientIsNull(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpSession httpSession = mock(HttpSession.class);
        JAXBParser jaxbParser = mock(JAXBParser.class);
        BeanFactory beanFactory = mock(BeanFactory.class);
        DAOFactory mySqlDAOFactory = mock(DAOFactory.class);
        ControllerFactory controllerFactory = mock(ControllerFactory.class);
        BidDAOMySql bidDAOHelper = mock(BidDAOMySql.class);
        BidOrderPriceController bidOrderPriceController = new BidOrderPriceController(jaxbParser, beanFactory, mySqlDAOFactory, controllerFactory);
        String weight = "75.3";
        String volume = "145.9";
        String type = "2";
        String cost = "101.2";
        String sendingPoint = "7";
        String destinationPoint = "3";
        String notes = "Main street 1, building #47";
        String lang = "en_EN";
        when(httpServletRequest.getParameter("weight")).thenReturn(weight);
        when(httpServletRequest.getParameter("volume")).thenReturn(volume);
        when(httpServletRequest.getParameter("type")).thenReturn(type);
        when(httpServletRequest.getParameter("cost")).thenReturn(cost);
        when(httpServletRequest.getParameter("sendingPoint")).thenReturn(sendingPoint);
        when(httpServletRequest.getParameter("destinationPoint")).thenReturn(destinationPoint);
        when(httpServletRequest.getParameter("notes")).thenReturn(notes);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("lang")).thenReturn(lang);
        when(httpSession.getAttribute("client")).thenReturn(null);
        when(mySqlDAOFactory.createBidDAO()).thenReturn(bidDAOHelper);
        String result = bidOrderPriceController.estimateOrderBid(httpServletRequest);
        Assert.assertEquals("index" , result);
    }

}
