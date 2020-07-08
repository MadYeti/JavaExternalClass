package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.dateProccesing.DateController;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.exceptions.InvalidBidDataException;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.bidStatus.BidStatus;
import org.mycompany.models.cargoType.CargoType;
import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.dao.bidDAO.BidDAOHelper;
import org.mycompany.models.client.Client;
import org.mycompany.models.dao.bidStatusDAO.BidStatusDAO;
import org.mycompany.models.dao.cargoTypeDAO.CargoTypeDAO;
import org.mycompany.models.dao.destinationPointDAO.DestinationPointDAO;
import org.mycompany.models.dao.paymentStatusDAO.PaymentStatusDAO;
import org.mycompany.models.dao.sendingPointDAO.SendingPointDAO;
import org.mycompany.models.destinationPoint.DestinationPoint;
import org.mycompany.models.factory.ControllerFactoryImpl;
import org.mycompany.models.factory.MySqlDAOFactory;
import org.mycompany.models.paymentStatus.PaymentStatus;
import org.mycompany.models.sendingPoint.SendingPoint;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * This servlet responsible for ordering and price calculation of bids.
 * Servlet works in both cases if user is authorize or not. Calculate price/order bid
 * if data is correct, send error otherwise
 */
@WebServlet(name = "/BidOrderPriceServlet", urlPatterns = "/BidOrderPriceServlet")
public class BidOrderPriceServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(BidOrderPriceServlet.class);

    static {
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param httpServletRequest servlet request
     * @param httpServletResponse servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
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
            BidDAO bidDAOHelper = new MySqlDAOFactory().createBidDAO(DBCPDataSource.getConnection());
            typeValue = ((BidDAOHelper)bidDAOHelper).getCargoTypeValue(Integer.parseInt(type), lang);
            httpServletRequest.setAttribute("cargoTypeValue", typeValue);
        }
        if (sendingPoint.equals("0")) {
            sendingPointInputError = true;
        }else {
            httpServletRequest.setAttribute("sendingPoint", sendingPoint);
            BidDAO bidDAOHelper = new MySqlDAOFactory().createBidDAO(DBCPDataSource.getConnection());
            sendingPointValue = ((BidDAOHelper)bidDAOHelper).getSendingPointValue(Integer.parseInt(sendingPoint), lang);
            httpServletRequest.setAttribute("sendingPointValue", sendingPointValue);
        }
        if (destinationPoint.equals("0")) {
            destinationPointInputError = true;
        }else{
            httpServletRequest.setAttribute("destinationPoint", destinationPoint);
            BidDAO bidDAOHelper = new MySqlDAOFactory().createBidDAO(DBCPDataSource.getConnection());
            destinationPointValue = ((BidDAOHelper)bidDAOHelper).getDestinationPointValue(Integer.parseInt(destinationPoint), lang);
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
            BidDAO bidDAOHelperTransferPrice = new MySqlDAOFactory().createBidDAO(DBCPDataSource.getConnection());
            transferPrice = ((BidDAOHelper)bidDAOHelperTransferPrice).getPriceAccordingToCityDistance(Integer.parseInt(sendingPoint), Integer.parseInt(destinationPoint));
            BidDAO bidDAOHelperCoefficient = new MySqlDAOFactory().createBidDAO(DBCPDataSource.getConnection());
            coefficient = ((BidDAOHelper)bidDAOHelperCoefficient).getCargoTypeCoefficient(Integer.parseInt(type));
            totalPrice = (weightValue + volumeValue + costValue + transferPrice) * coefficient;
            DecimalFormat decimalFormat = new DecimalFormat("############.##");
            decimalFormat.setRoundingMode(RoundingMode.CEILING);
            httpServletRequest.setAttribute("totalPriceValue", decimalFormat.format(totalPrice));
            if(httpSession.getAttribute("client") != null && submit) {
                DateController dateController = new ControllerFactoryImpl().getDateController();
                CargoTypeDAO cargoTypeDAOMySql = new MySqlDAOFactory().createCargoTypeDAO(DBCPDataSource.getConnection());
                CargoType cargoType = cargoTypeDAOMySql.read(Integer.parseInt(type));
                SendingPointDAO sendingPointDAOMySql = new MySqlDAOFactory().createSendingPointDAO(DBCPDataSource.getConnection());
                SendingPoint sendingPointObject = sendingPointDAOMySql.read(Integer.parseInt(sendingPoint));
                DestinationPointDAO destinationPointDAOMySql = new MySqlDAOFactory().createDestinationPointDAO(DBCPDataSource.getConnection());
                DestinationPoint destinationPointObject = destinationPointDAOMySql.read(Integer.parseInt(destinationPoint));
                BidStatusDAO bidStatusDAOMySql = new MySqlDAOFactory().createBidStatusDAO(DBCPDataSource.getConnection());
                BidStatus bidStatus = bidStatusDAOMySql.read(1);
                PaymentStatusDAO paymentStatusDAOMySql = new MySqlDAOFactory().createPaymentStatusDAO(DBCPDataSource.getConnection());
                PaymentStatus paymentStatus = paymentStatusDAOMySql.read(1);
                Bid bid = new Bid();
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
                BidDAO bidDAOMySql = new MySqlDAOFactory().createBidDAO(DBCPDataSource.getConnection());
                bidDAOMySql.create(bid);
                BidDAO bidDAOIndex = new MySqlDAOFactory().createBidDAO(DBCPDataSource.getConnection());
                bid.setId(((BidDAOHelper)bidDAOIndex).getLastInsertedId());
                JAXBParser jaxbParser = new JAXBParser();
                jaxbParser.creteXMLBasedOnObject(bid);
                String role = ((Client) httpSession.getAttribute("client")).getRole();
                httpServletRequest.setAttribute("success", true);
                requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/" + role + ".jsp");
            }else if(httpSession.getAttribute("client") != null && !submit){
                requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/".concat(((Client) httpSession.getAttribute("client")).getRole()).concat(".jsp"));
            }else{
                requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp");
            }
        }else{
            try {
                throw new InvalidBidDataException("Invalid bid data to get price or format the bid");
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
                requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/".concat(((Client) httpSession.getAttribute("client")).getRole()).concat(".jsp"));
            }else{
                requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp");
            }
        }
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param httpServletRequest servlet request
     * @param httpServletResponse servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }
}
