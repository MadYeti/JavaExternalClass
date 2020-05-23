package org.mycompany.controllers.servlets;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.dateProccesing.DateController;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.InvalidBidDataException;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.dao.bidDAO.DAO;
import org.mycompany.models.factory.DAOFactory;
import org.mycompany.models.factory.MySqlDAOFactory;
import org.mycompany.resourceBundle.ResourceBundleConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.ResourceBundle;

@Controller
public class BidOrderPriceController {

    private static Properties cityDistanceProperties = new Properties();
    private static Properties cargoTypeProperties = new Properties();
    private static Logger logger = Logger.getLogger(BidOrderPriceController.class);
    private DAOFactory mySqlDAOFactory;
    private JAXBParser jaxbParser;
    private Bid bid;
    private BeanFactory beanFactory;

    static {
        try {
            cityDistanceProperties.load(new FileReader(new File("src/main/resources/cityDistance.properties")));
            cargoTypeProperties.load(new FileReader(new File("src/main/resources/cargoType.properties")));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public BidOrderPriceController(JAXBParser jaxbParser,
                                   Bid bid,
                                   BeanFactory beanFactory){
        this.jaxbParser = jaxbParser;
        this.bid = bid;
        this.beanFactory = beanFactory;
    }

    @GetMapping("/BidOrderPriceController")
    public String estimateOrderBid(HttpServletRequest httpServletRequest){
        String[] parameters = httpServletRequest.getQueryString().split("&");
        String weight = null;
        String volume = null;
        String type = null;
        String cost = null;
        String sendingPoint = null;
        String destinationPoint = null;
        String notes = null;
        boolean submit = false;
        for(String parameter: parameters){
            try {
                if (parameter.contains("weight")) {
                    weight = URLDecoder.decode(parameter.substring(parameter.indexOf("=") + 1), "UTF-8");
                } else if (parameter.contains("volume")) {
                    volume = URLDecoder.decode(parameter.substring(parameter.indexOf("=") + 1), "UTF-8");
                } else if (parameter.contains("type")) {
                    type = URLDecoder.decode(parameter.substring(parameter.indexOf("=") + 1), "UTF-8");
                } else if (parameter.contains("cost")) {
                    cost = URLDecoder.decode(parameter.substring(parameter.indexOf("=") + 1), "UTF-8");
                } else if (parameter.contains("sendingPoint")) {
                    sendingPoint = URLDecoder.decode(parameter.substring(parameter.indexOf("=") + 1), "UTF-8");
                } else if (parameter.contains("destinationPoint")) {
                    destinationPoint = URLDecoder.decode(parameter.substring(parameter.indexOf("=") + 1), "UTF-8");
                } else if (parameter.contains("notes")) {
                    notes = URLDecoder.decode(parameter.substring(parameter.indexOf("=") + 1), "UTF-8");
                } else if (parameter.contains("submit")) {
                    submit = Boolean.valueOf(URLDecoder.decode(parameter.substring(parameter.indexOf("=") + 1), "UTF-8"));
                }
            }catch (UnsupportedEncodingException e){
                logger.error(e.getMessage());
            }
        }
        double weightValue = 0;
        double volumeValue = 0;
        double costValue = 0;
        double totalPrice;
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
        //ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle((String) httpServletRequest.getSession().getAttribute("lang"), "messages");
        ResourceBundle resourceBundle = beanFactory.getBean(ResourceBundleConfig.class).getResourceBundle((String) httpServletRequest.getSession().getAttribute("lang"), "messages");
        if (type.equals(resourceBundle.getString("msg.cargoType.field"))) {
            typeInputError = true;
        }
        if (sendingPoint.equals(resourceBundle.getString("msg.sendingPoint.field"))) {
            sendingPointInputError = true;
        }
        if (destinationPoint.equals(resourceBundle.getString("msg.destinationPoint.field"))) {
            destinationPointInputError = true;
        }
        if (sendingPoint.equals(destinationPoint)) {
            isSendingDestinationPointSame = true;
        }
        if (!weightInputError && !volumeInputError && !typeInputError &&
                !costInputError && !sendingPointInputError && !destinationPointInputError &&
                !isSendingDestinationPointSame) {
            String cityDistance = sendingPoint.concat("-").concat(destinationPoint);
            totalPrice = (weightValue + volumeValue + costValue +
                    Integer.parseInt(cityDistanceProperties.getProperty(cityDistance))) *
                    Double.parseDouble(cargoTypeProperties.getProperty(type.replace(" ", "_")));
            httpServletRequest.setAttribute("weightValue", weightValue);
            httpServletRequest.setAttribute("volumeValue", volumeValue);
            httpServletRequest.setAttribute("cargoTypeValue", type);
            httpServletRequest.setAttribute("cargoCostValue", cost);
            httpServletRequest.setAttribute("sendingPointValue", sendingPoint);
            httpServletRequest.setAttribute("destinationPointValue", destinationPoint);
            httpServletRequest.setAttribute("totalPriceValue", totalPrice);
            if(httpSession.getAttribute("client") != null && submit) {
                bid.addClientId(((Client) httpSession.getAttribute("client")).getId())
                        .addWeight(weightValue)
                        .addVolume(volumeValue)
                        .addCargoType(type)
                        .addCargoCost(costValue)
                        .addSendingPoint(sendingPoint)
                        .addDestinationPoint(destinationPoint)
                        .addArrivalDate(DateController.getArrivalDate(Integer.parseInt(cityDistanceProperties.getProperty(cityDistance))))
                        .addNotes(notes)
                        .addPrice(totalPrice)
                        .addBidStatus(resourceBundle.getString("msg.bidStatus.processing.label"))
                        .addPaymentStatus(resourceBundle.getString("msg.paymentStatus.notPaid.label"))
                        .build();
                mySqlDAOFactory = beanFactory.getBean(MySqlDAOFactory.class);
                DAO bidDAO = mySqlDAOFactory.createBidDAO();
                bidDAO.create(bid);
                mySqlDAOFactory = beanFactory.getBean(MySqlDAOFactory.class);
                BidDAO bidDAOIndex = mySqlDAOFactory.createBidDAO();
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
