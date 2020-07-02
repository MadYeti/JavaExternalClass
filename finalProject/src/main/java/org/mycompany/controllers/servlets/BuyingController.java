package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.creditCard.CreditCardDataController;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.exceptions.InvalidCreditCardDataException;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.bidDAO.BidDAOHelper;
import org.mycompany.models.dao.paymentStatusDAO.PaymentStatusDAO;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.DAOFactory;
import org.mycompany.models.observer.Subject;
import org.mycompany.models.paymentStatus.PaymentStatus;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Implements buying logic. Controller contains credit cards input field and validate
 * data after confirm. If data is correct bids payment status is changed from 'not
 * paid' to 'paid' and mail with confirmation is sent to user email, sends error
 * otherwise
 */
@Controller
public class BuyingController {

    private JAXBParser jaxbParser;
    private Subject subject;
    private BeanFactory beanFactory;
    private DAOFactory mySqlDAOFactory;
    private ControllerFactory controllerFactory;

    private static Logger logger = Logger.getLogger(BuyingController.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }


    @Autowired
    public BuyingController(JAXBParser jaxbParser,
                            Subject subject,
                            BeanFactory beanFactory,
                            DAOFactory mySqlDAOFactory,
                            ControllerFactory controllerFactory){
        this.jaxbParser = jaxbParser;
        this.subject = subject;
        this.beanFactory = beanFactory;
        this.mySqlDAOFactory = mySqlDAOFactory;
        this.controllerFactory = controllerFactory;
    }

    /**
     * Perform input credit card data validation. If data is correct
     * perform buying logic, sets error otherwise
     * @param httpServletRequest servlet request
     * @return buyingPage jsp name with parameter 'pay' and 'price'
     */
    @PostMapping("/BuyingController")
    public String payBid(HttpServletRequest httpServletRequest){
        int id = Integer.parseInt(httpServletRequest.getParameter("bidNumber"));
        String price = httpServletRequest.getParameter("bidPrice");
        String creditCardNumber = httpServletRequest.getParameter("creditCardNumber");
        String creditCardExpirationDate = httpServletRequest.getParameter("creditCardDate");
        String creditCardCVVCode = httpServletRequest.getParameter("cvv");
        CreditCardDataController creditCardDataController = controllerFactory.getCreditCardController();
        if(!creditCardDataController.validateCreditCardData(creditCardNumber, creditCardExpirationDate, creditCardCVVCode)){
            BidDAOHelper bidDAOMySql = mySqlDAOFactory.createBidDAO();
            bidDAOMySql.updateBidPaymentStatus(id);
            Bid bid = jaxbParser.createObjectBasedOnXML(id);
            PaymentStatusDAO paymentStatusDAOMySql = mySqlDAOFactory.createPaymentStatusDAO();
            PaymentStatus paymentStatus = paymentStatusDAOMySql.read(2);
            bid.setPaymentStatus(paymentStatus);
            jaxbParser.creteXMLBasedOnObject(bid);
            Client client = (Client)httpServletRequest.getSession().getAttribute("client");
            if(client != null) {
                subject.addObserver(client);
                client.setObservable(subject);
                subject.setMeasurements(id);
                client.setObservable(null);
            }
            httpServletRequest.setAttribute("success", true);
        }else{
            try {
                throw beanFactory.getBean(InvalidCreditCardDataException.class, "Invalid credit card data input to pay the bid");
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
        return "forward:/buyingPage?pay=".concat(String.valueOf(id)).concat("&price=").concat(price);
    }

}
