package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.dao.bidDAO.DAO;
import org.mycompany.models.factory.DAOFactory;
import org.mycompany.models.repository.BidsHolderImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class AdminBidController {

    private static Logger logger = Logger.getLogger(AdminBidController.class);
    private JAXBParser jaxbParser;
    private BeanFactory beanFactory;
    private DAOFactory mySqlDAOFactory;

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public AdminBidController(JAXBParser jaxbParser,
                              BeanFactory beanFactory,
                              DAOFactory mySqlDAOFactory){
        this.jaxbParser = jaxbParser;
        this.beanFactory = beanFactory;
        this.mySqlDAOFactory = mySqlDAOFactory;
    }

    @GetMapping("/AdminBidController")
    public String crudOperations(HttpServletRequest httpServletRequest){
        String bidId = httpServletRequest.getParameter("bidId");
        String operation = httpServletRequest.getParameter("operation");
        try {
            int id = Integer.parseInt(bidId);
            DAO bidDAO = mySqlDAOFactory.createBidDAO();
            switch (operation){
                case "read":
                    Bid bid = bidDAO.read(id);
                    BidsHolderImpl bidsHolderImpl = beanFactory.getBean(BidsHolderImpl.class);
                    String lang = (String) httpServletRequest.getSession().getAttribute("lang");
                    if(lang == null){
                        lang = "en_EN";
                    }
                    bidsHolderImpl.setLang(lang);
                    try {
                        bidsHolderImpl.getForeignKeyValues(bid,
                                                           bid.getCargoType(),
                                                           bid.getSendingPoint(),
                                                           bid.getDestinationPoint(),
                                                           bid.getBidStatus(),
                                                           bid.getPaymentStatus());
                    } catch (SQLException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                    }
                    httpServletRequest.setAttribute("bid", bid);
                    break;
                case "update":
                    bidDAO.update(jaxbParser.createObjectBasedOnXML(id));
                    break;
                case "delete":
                    bidDAO.delete(jaxbParser.createObjectBasedOnXML(id));
                    break;
                default:
                    break;
            }
            httpServletRequest.setAttribute("errorInput", false);
        }catch (NumberFormatException e){
            logger.error(e.getMessage());
            httpServletRequest.setAttribute("errorInput", true);
        }
        return "adminPrivateOffice";
    }

}
