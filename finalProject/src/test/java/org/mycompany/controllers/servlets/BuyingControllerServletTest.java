package org.mycompany.controllers.servlets;

import org.junit.Test;
import org.mycompany.controllers.creditCard.CreditCardDataController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class BuyingControllerServletTest {

    @Test
    public void callGetMethodOnBuyingControllerServletRightInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        CreditCardDataController creditCardDataController = mock(CreditCardDataController.class);
        HttpSession httpSession = mock(HttpSession.class);
        BuyingControllerServlet buyingControllerServlet = new BuyingControllerServlet();
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
        when(creditCardDataController.validateCreditCardData(creditCardNumber, creditCardExpirationDate, creditCardCVVCode)).thenReturn(true);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getSession().getAttribute("client")).thenReturn(null);
        when(httpServletRequest.getRequestDispatcher("/buyingPage?pay=".concat(String.valueOf(id)).concat("&price=").concat(price))).thenReturn(requestDispatcher);
        try {
            buyingControllerServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/buyingPage?pay=".concat(String.valueOf(id)).concat("&price=").concat(price));
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callGetMethodOnBuyingControllerServletWrongInput(){
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        CreditCardDataController creditCardDataController = mock(CreditCardDataController.class);
        BuyingControllerServlet buyingControllerServlet = new BuyingControllerServlet();
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
        when(creditCardDataController.validateCreditCardData(creditCardNumber, creditCardExpirationDate, creditCardCVVCode)).thenReturn(true);
        when(httpServletRequest.getRequestDispatcher("/buyingPage?pay=".concat(String.valueOf(id)).concat("&price=").concat(price))).thenReturn(requestDispatcher);
        try {
            buyingControllerServlet.doGet(httpServletRequest, httpServletResponse);
            verify(httpServletRequest, times(1)).getRequestDispatcher("/buyingPage?pay=".concat(String.valueOf(id)).concat("&price=").concat(price));
            verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
