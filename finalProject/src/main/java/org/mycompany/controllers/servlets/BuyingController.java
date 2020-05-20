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
import org.mycompany.models.factory.MySqlDAOFactory;
import org.mycompany.models.observer.Subject;
import org.mycompany.resourceBundle.ResourceBundleConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

@Controller
public class BuyingController {

    private static Logger logger = Logger.getLogger(BuyingController.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @PostMapping("/BuyingController")
    public String payBid(HttpServletRequest httpServletRequest){
        int id = Integer.parseInt(httpServletRequest.getParameter("bidNumber"));
        String creditCardNumber = httpServletRequest.getParameter("creditCardNumber");
        String creditCardExpirationDate = httpServletRequest.getParameter("creditCardDate");
        String creditCardCVVCode = httpServletRequest.getParameter("cvv");
        CreditCardDataController creditCardDataController = new CreditCardDataController();
        ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle((String) httpServletRequest.getSession().getAttribute("lang"), "messages");
        if(!creditCardDataController.validateCreditCardData(creditCardNumber, creditCardExpirationDate, creditCardCVVCode)){
            BidDAO bidDAO = new MySqlDAOFactory().createBidDAO(DBCPDataSource.getConnection());
            bidDAO.updateBidPaymentStatus(id);
            JAXBParser jaxbParser = new JAXBParser();
            Bid bid = jaxbParser.createObjectBasedOnXML(id);
            bid.setPaymentStatus(resourceBundle.getString("msg.paymentStatus.paid.label"));
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
        return "forward:/buyingPage?pay=".concat(String.valueOf(id));
    }

}
