package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.creditCard.CreditCardDataController;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.exceptions.InvalidCreditCardDataException;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.DAOFactory;
import org.mycompany.models.observer.Subject;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/BuyingController")
    public String payBid(HttpServletRequest httpServletRequest){
        int id = Integer.parseInt(httpServletRequest.getParameter("bidNumber"));
        String creditCardNumber = httpServletRequest.getParameter("creditCardNumber");
        String creditCardExpirationDate = httpServletRequest.getParameter("creditCardDate");
        String creditCardCVVCode = httpServletRequest.getParameter("cvv");
        CreditCardDataController creditCardDataController = controllerFactory.getCreditCardController();
        if(!creditCardDataController.validateCreditCardData(creditCardNumber, creditCardExpirationDate, creditCardCVVCode)){
            BidDAO bidDAO = mySqlDAOFactory.createBidDAO();
            bidDAO.updateBidPaymentStatus(id);
            Bid bid = jaxbParser.createObjectBasedOnXML(id);
            //bid.setPaymentStatusId(2);
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
