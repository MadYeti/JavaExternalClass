package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.dateProccesing.DateController;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.exceptions.InvalidBidDataException;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.bidStatus.BidStatus;
import org.mycompany.models.cargoType.CargoType;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.dao.bidDAO.BidDAOHelper;
import org.mycompany.models.dao.bidStatusDAO.BidStatusDAO;
import org.mycompany.models.dao.cargoTypeDAO.CargoTypeDAO;
import org.mycompany.models.dao.destinationPointDAO.DestinationPointDAO;
import org.mycompany.models.dao.paymentStatusDAO.PaymentStatusDAO;
import org.mycompany.models.dao.sendingPointDAO.SendingPointDAO;
import org.mycompany.models.destinationPoint.DestinationPoint;
import org.mycompany.models.factory.ControllerFactory;
import org.mycompany.models.factory.DAOFactory;
import org.mycompany.models.paymentStatus.PaymentStatus;
import org.mycompany.models.sendingPoint.SendingPoint;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * This controller responsible for ordering and price calculation of bids.
 * Controller works in both cases if user is authorize or not. Calculate price/order bid
 * if data is correct, send error otherwise
 */
@Controller
public class BidOrderPriceController {

    private static Logger logger = Logger.getLogger(BidOrderPriceController.class);
    private JAXBParser jaxbParser;
    private BeanFactory beanFactory;
    private DAOFactory mySqlDAOFactory;
    private ControllerFactory controllerFactory;

    static {
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public BidOrderPriceController(JAXBParser jaxbParser,
                                   BeanFactory beanFactory,
                                   DAOFactory mySqlDAOFactory,
                                   ControllerFactory controllerFactory){
        this.jaxbParser = jaxbParser;
        this.beanFactory = beanFactory;
        this.mySqlDAOFactory = mySqlDAOFactory;
        this.controllerFactory = controllerFactory;
    }

    /**
     * Perform data validation and bid price calculation/order if data
     * is correct
     * @param httpServletRequest servlet request
     * @return index/(role) jsp name if data is correct and user is
     * not authorize/authorize or return same pages with set error if
     * data isn't correct
     */
    @GetMapping("/BidOrderPriceController")
    public String estimateOrderBid(HttpServletRequest httpServletRequest){
        String weight = httpServletRequest.getParameter("weight");
        String volume = httpServletRequest.getParameter("volume");
        String type = httpServletRequest.getParameter("type");
        String cost = httpServletRequest.getParameter("cost");
        String sendingPoint = httpServletRequest.getParameter("sendingPoint");
        String destinationPoint = httpServletRequest.getParameter("destinationPoint");
        String notes = httpServletRequest.getParameter("notes");
        boolean submit = Boolean.valueOf(httpServletRequest.getParameter("submit"));
        double weightValue = 0;
        double volumeValue = 0;
        double costValue = 0;
        double transferPrice;
        double coefficient;
        double totalPrice;
        String typeValue;
        String sendingPointValue;
        String destinationPointValue;
        boolean weightInputError = false;
        boolean volumeInputError = false;
        boolean typeInputError = false;
        boolean costInputError = false;
        boolean sendingPointInputError = false;
        boolean destinationPointInputError = false;
        boolean isSendingDestinationPointSame = false;
        HttpSession httpSession = httpServletRequest.getSession();
        if (weight != null) {
            try {
                weightValue = Double.parseDouble(weight);
                if(weightValue <= 0){
                    weightInputError = true;
                }
            } catch (NumberFormatException e) {
                logger.error(e.getMessage());
                weightInputError = true;
            }
        }
        if (volume != null) {
            try {
                volumeValue = Double.parseDouble(volume);
                if(volumeValue <= 0){
                    volumeInputError = true;
                }
            } catch (NumberFormatException e) {
                logger.error(e.getMessage());
                volumeInputError = true;
            }
        }
        if (cost != null) {
            try {
                costValue = Double.parseDouble(cost);
                if(costValue <= 0){
                    costInputError = true;
                }
            } catch (NumberFormatException e) {
                logger.error(e.getMessage());
                costInputError = true;
            }
        }
        String lang = (String) httpServletRequest.getSession().getAttribute("lang");
        if(lang == null){
            lang = "en_EN";
        }
        if (type.equals("0")) {
            typeInputError = true;
        }else{
            httpServletRequest.setAttribute("cargoType", type);
            BidDAOHelper bidDAOHelper = mySqlDAOFactory.createBidDAO();
            typeValue = bidDAOHelper.getCargoTypeValue(Integer.parseInt(type), lang);
            httpServletRequest.setAttribute("cargoTypeValue", typeValue);
        }
        if (sendingPoint.equals("0")) {
            sendingPointInputError = true;
        }else{
            httpServletRequest.setAttribute("sendingPoint", sendingPoint);
            BidDAOHelper bidDAOHelper = mySqlDAOFactory.createBidDAO();
            sendingPointValue = bidDAOHelper.getSendingPointValue(Integer.parseInt(sendingPoint), lang);
            httpServletRequest.setAttribute("sendingPointValue", sendingPointValue);
        }
        if (destinationPoint.equals("0")) {
            destinationPointInputError = true;
        }else{
            httpServletRequest.setAttribute("destinationPoint", destinationPoint);
            BidDAOHelper bidDAOHelper = mySqlDAOFactory.createBidDAO();
            destinationPointValue = bidDAOHelper.getDestinationPointValue(Integer.parseInt(destinationPoint), lang);
            httpServletRequest.setAttribute("destinationPointValue", destinationPointValue);
        }
        if (!sendingPoint.equals("0") && !destinationPoint.equals("0") && sendingPoint.equals(destinationPoint)) {
            isSendingDestinationPointSame = true;
        }
        httpServletRequest.setAttribute("weightValue", weightValue);
        httpServletRequest.setAttribute("volumeValue", volumeValue);
        httpServletRequest.setAttribute("cargoCostValue", costValue);
        if (!weightInputError && !volumeInputError && !typeInputError &&
                !costInputError && !sendingPointInputError && !destinationPointInputError &&
                !isSendingDestinationPointSame) {
            BidDAOHelper bidDAOHelperTransferPrice = mySqlDAOFactory.createBidDAO();
            transferPrice = bidDAOHelperTransferPrice.getPriceAccordingToCityDistance(Integer.parseInt(sendingPoint), Integer.parseInt(destinationPoint));
            BidDAOHelper bidDAOHelperCoefficient = mySqlDAOFactory.createBidDAO();
            coefficient = bidDAOHelperCoefficient.getCargoTypeCoefficient(Integer.parseInt(type));
            totalPrice = (weightValue + volumeValue + costValue + transferPrice) * coefficient;
            DecimalFormat decimalFormat = new DecimalFormat("##############.##");
            decimalFormat.setRoundingMode(RoundingMode.CEILING);
            httpServletRequest.setAttribute("totalPriceValue", decimalFormat.format(totalPrice));
            if(httpSession.getAttribute("client") != null && submit) {
                DateController dateController = controllerFactory.getDateController();
                CargoTypeDAO cargoTypeDAOMySql = mySqlDAOFactory.createCargoTypeDAO();
                CargoType cargoType = cargoTypeDAOMySql.read(Integer.parseInt(type));
                SendingPointDAO sendingPointDAOMySql = mySqlDAOFactory.createSendingPointDAO();
                SendingPoint sendingPointObject = sendingPointDAOMySql.read(Integer.parseInt(sendingPoint));
                DestinationPointDAO destinationPointDAOMySql = mySqlDAOFactory.createDestinationPointDAO();
                DestinationPoint destinationPointObject = destinationPointDAOMySql.read(Integer.parseInt(destinationPoint));
                BidStatusDAO bidStatusDAOMySql = mySqlDAOFactory.createBidStatusDAO();
                BidStatus bidStatus = bidStatusDAOMySql.read(1);
                PaymentStatusDAO paymentStatusDAOMySql = mySqlDAOFactory.createPaymentStatusDAO();
                PaymentStatus paymentStatus = paymentStatusDAOMySql.read(1);
                Bid bid = beanFactory.getBean(Bid.class);
                bid.addClient(((Client) httpSession.getAttribute("client")))
                        .addWeight(weightValue)
                        .addVolume(volumeValue)
                        .addCargoType(cargoType)
                        .addCargoCost(costValue)
                        .addSendingPoint(sendingPointObject)
                        .addDestinationPoint(destinationPointObject)
                        .addArrivalDate(dateController.getArrivalDate((int)transferPrice))
                        .addNotes(notes)
                        .addPrice(totalPrice)
                        .addBidStatus(bidStatus)
                        .addPaymentStatus(paymentStatus)
                        .build();
                BidDAO bidDAOMySql = mySqlDAOFactory.createBidDAO();
                bidDAOMySql.create(bid);
                BidDAOHelper bidDAOIndex = mySqlDAOFactory.createBidDAO();
                bid.setId(bidDAOIndex.getLastInsertedId());
                jaxbParser.creteXMLBasedOnObject(bid);
                String role = ((Client) httpSession.getAttribute("client")).getRole();
                httpServletRequest.setAttribute("success", true);
                return role;
            }else if(httpSession.getAttribute("client") != null && !submit){
                return ((Client) httpSession.getAttribute("client")).getRole();
            }else{
                return "index";
            }
        }else{
            try {
                throw beanFactory.getBean(InvalidBidDataException.class, "Invalid bid data to get price or format the bid");
            } catch (InvalidBidDataException e) {
                logger.error(e.getMessage());
            }
            httpServletRequest.setAttribute("weightInputError", weightInputError);
            httpServletRequest.setAttribute("volumeInputError", volumeInputError);
            httpServletRequest.setAttribute("typeInputError", typeInputError);
            httpServletRequest.setAttribute("costInputError", costInputError);
            httpServletRequest.setAttribute("sendingPointInputError", sendingPointInputError);
            httpServletRequest.setAttribute("destinationPointInputError", destinationPointInputError);
            httpServletRequest.setAttribute("isSendingDestinationPointSame", isSendingDestinationPointSame);
            if(httpSession.getAttribute("client") != null) {
                return ((Client) httpSession.getAttribute("client")).getRole();
            }else{
                return "index";
            }
        }
    }

}