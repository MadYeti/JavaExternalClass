package org.mycompany.controllers.servlets;

import org.junit.Test;
import org.mycompany.models.client.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class BidOrderPriceServletTest {

    @Test
    public void callGetMethodOnBidOrderPriceServletWrongWeightInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        BidOrderPriceServlet bidOrderPriceServlet = new BidOrderPriceServlet();
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
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp")).thenReturn(requestDispatcher);
        try {
            bidOrderPriceServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/index.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callGetMethodOnBidOrderPriceServletWrongVolumeInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        BidOrderPriceServlet bidOrderPriceServlet = new BidOrderPriceServlet();
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
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp")).thenReturn(requestDispatcher);
        try {
            bidOrderPriceServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/index.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callGetMethodOnBidOrderPriceServletWrongTypeInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        BidOrderPriceServlet bidOrderPriceServlet = new BidOrderPriceServlet();
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
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp")).thenReturn(requestDispatcher);
        try {
            bidOrderPriceServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/index.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callGetMethodOnBidOrderPriceServletWrongCostInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        BidOrderPriceServlet bidOrderPriceServlet = new BidOrderPriceServlet();
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
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp")).thenReturn(requestDispatcher);
        try {
            bidOrderPriceServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/index.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callGetMethodOnBidOrderPriceServletWrongSendingPointInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        BidOrderPriceServlet bidOrderPriceServlet = new BidOrderPriceServlet();
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
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp")).thenReturn(requestDispatcher);
        try {
            bidOrderPriceServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/index.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callGetMethodOnBidOrderPriceServletWrongDestinationPointInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        BidOrderPriceServlet bidOrderPriceServlet = new BidOrderPriceServlet();
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
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp")).thenReturn(requestDispatcher);
        try {
            bidOrderPriceServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/index.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callGetMethodOnBidOrderPriceServletWrongInputSendingAndDestinationPointsAreTheSame(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        BidOrderPriceServlet bidOrderPriceServlet = new BidOrderPriceServlet();
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
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp")).thenReturn(requestDispatcher);
        try {
            bidOrderPriceServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/index.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callGetMethodOnBidOrderPriceServletWrongInputWhereClientNotNull(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        Client client = mock(Client.class);
        BidOrderPriceServlet bidOrderPriceServlet = new BidOrderPriceServlet();
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
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        when(((Client) httpSession.getAttribute("client")).getRole()).thenReturn("customer");
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/".concat(((Client) httpSession.getAttribute("client")).getRole()).concat(".jsp"))).thenReturn(requestDispatcher);
        try {
            bidOrderPriceServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/".concat(((Client) httpSession.getAttribute("client")).getRole()).concat(".jsp"));
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callGetMethodOnBidOrderPriceServletRightInputWhereClientNotNullAndSubmitTrue(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        Client client = mock(Client.class);
        BidOrderPriceServlet bidOrderPriceServlet = new BidOrderPriceServlet();
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
        when(httpServletRequest.getSession().getAttribute("lang")).thenReturn(lang);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        when(client.getRole()).thenReturn("customer");
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/".concat(client.getRole()).concat(".jsp"))).thenReturn(requestDispatcher);
        try {
            bidOrderPriceServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/".concat(client.getRole()).concat(".jsp"));
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callGetMethodOnBidOrderPriceServletRightInputWhereClientNotNullAndSubmitFalse(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        Client client = mock(Client.class);
        BidOrderPriceServlet bidOrderPriceServlet = new BidOrderPriceServlet();
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
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(client);
        when(client.getRole()).thenReturn("customer");
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/".concat(client.getRole()).concat(".jsp"))).thenReturn(requestDispatcher);
        try {
            bidOrderPriceServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/".concat(client.getRole()).concat(".jsp"));
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callGetMethodOnBidOrderPriceServletRightInputWhereClientIsNull(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession httpSession = mock(HttpSession.class);
        BidOrderPriceServlet bidOrderPriceServlet = new BidOrderPriceServlet();
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
        when(httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp")).thenReturn(requestDispatcher);
        try {
            bidOrderPriceServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/WEB-INF/view/index.jsp");
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
