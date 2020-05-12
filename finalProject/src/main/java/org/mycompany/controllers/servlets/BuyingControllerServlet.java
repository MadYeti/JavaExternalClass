package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.creditCard.CreditCardDataController;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.InvalidCreditCardDataException;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.factory.MySqlDAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/BuyingControllerServlet", urlPatterns = "/BuyingControllerServlet")
public class BuyingControllerServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(BuyingControllerServlet.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(httpServletRequest.getParameter("bidNumber"));
        String creditCardNumber = httpServletRequest.getParameter("creditCardNumber");
        String creditCardExpirationDate = httpServletRequest.getParameter("creditCardDate");
        String creditCardCVVCode = httpServletRequest.getParameter("cvv");
        CreditCardDataController creditCardDataController = new CreditCardDataController();
        if(!creditCardDataController.validateCreditCardData(creditCardNumber, creditCardExpirationDate, creditCardCVVCode)){
            BidDAO bidDAO = new MySqlDAOFactory().createBidDAO(DBCPDataSource.getConnection());
            bidDAO.updateBidPaymentStatus(id);
            JAXBParser jaxbParser = new JAXBParser();
            Bid bid = jaxbParser.createObjectBasedOnXML(id);
            bid.setPaymentStatus("paid");
            jaxbParser.creteXMLBasedOnObject(bid);
            httpServletRequest.setAttribute("success", true);
        }else{
            try {
                throw new InvalidCreditCardDataException("Invalid credit card data input to pay the bid");
            } catch (InvalidCreditCardDataException e) {
                logger.error(e.getMessage());
            }
            httpServletRequest.setAttribute("success", false);
            if(creditCardDataController.getErrorCreditCardNumer() != null){
                httpServletRequest.setAttribute("errorCreditCardNumber", creditCardDataController.getErrorCreditCardNumer());
            }
            if(creditCardDataController.getErrorCreditCardExpirationDate() != null){
                httpServletRequest.setAttribute("errorCreditCardExpirationDate", creditCardDataController.getErrorCreditCardExpirationDate());
            }
            if(creditCardDataController.getErrorCreditCardCVVCode() != null){
                httpServletRequest.setAttribute("errorCreditCardCVVCode", creditCardDataController.getErrorCreditCardCVVCode());
            }
        }
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/buyingPage?pay=".concat(String.valueOf(id)));
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }
}
