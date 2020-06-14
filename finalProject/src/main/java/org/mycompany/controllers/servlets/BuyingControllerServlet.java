package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.creditCard.CreditCardDataController;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.InvalidCreditCardDataException;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.dao.bidDAO.BidDAOHelper;
import org.mycompany.models.dao.paymentStatusDAO.PaymentStatusDAO;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.ControllerFactoryImpl;
import org.mycompany.models.factory.MySqlDAOFactory;
import org.mycompany.models.observer.Subject;
import org.mycompany.models.paymentStatus.PaymentStatus;

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
        int id = Integer.parseInt(httpServletRequest.getParameter("bidNumber"));
        String price = httpServletRequest.getParameter("bidPrice");
        String creditCardNumber = httpServletRequest.getParameter("creditCardNumber");
        String creditCardExpirationDate = httpServletRequest.getParameter("creditCardDate");
        String creditCardCVVCode = httpServletRequest.getParameter("cvv");
        ControllerFactory controllerFactory = new ControllerFactoryImpl();
        CreditCardDataController creditCardDataController = controllerFactory.getCreditCardController();
        if(!creditCardDataController.validateCreditCardData(creditCardNumber, creditCardExpirationDate, creditCardCVVCode)){
            BidDAO bidDAOMySql = new MySqlDAOFactory().createBidDAO(DBCPDataSource.getConnection());
            ((BidDAOHelper)bidDAOMySql).updateBidPaymentStatus(id);
            JAXBParser jaxbParser = new JAXBParser();
            Bid bid = jaxbParser.createObjectBasedOnXML(id);
            PaymentStatusDAO paymentStatusDAOMySql = new MySqlDAOFactory().createPaymentStatusDAO(DBCPDataSource.getConnection());
            PaymentStatus paymentStatus = paymentStatusDAOMySql.read(2);
            bid.setPaymentStatus(paymentStatus);
            jaxbParser.creteXMLBasedOnObject(bid);
            Client client = (Client)httpServletRequest.getSession().getAttribute("client");
            if(client != null) {
                Subject subject = new Subject();
                subject.addObserver(client);
                client.setObservable(subject);
                subject.setMeasurements(id);
                client.setObservable(null);
            }
            httpServletRequest.setAttribute("success", true);
        }else{
            try {
                throw new InvalidCreditCardDataException("Invalid credit card data input to pay the bid");
            } catch (InvalidCreditCardDataException e) {
                logger.error(e.getMessage());
            }
            httpServletRequest.setAttribute("success", false);
            if(creditCardDataController.getErrorCreditCardNumber() != null){
                httpServletRequest.setAttribute("errorCreditCardNumber", creditCardDataController.getErrorCreditCardNumber());
            }
            if(creditCardDataController.getErrorCreditCardExpirationDate() != null){
                httpServletRequest.setAttribute("errorCreditCardExpirationDate", creditCardDataController.getErrorCreditCardExpirationDate());
            }
            if(creditCardDataController.getErrorCreditCardCVVCode() != null){
                httpServletRequest.setAttribute("errorCreditCardCVVCode", creditCardDataController.getErrorCreditCardCVVCode());
            }
        }
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/buyingPage?pay=".concat(String.valueOf(id)).concat("&price=").concat(price));
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }
}
