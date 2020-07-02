package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.factory.DAOFactory;
import org.mycompany.models.repository.BidsHolderImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Implements CRUD operations by admin. If data is correct implements specific operation,
 * otherwise send error
 */
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

    /**
     * Perform CRUD operation if input data is correct
     * set error otherwise
     * @param httpServletRequest servlet request
     * @return adminPrivateOffice jsp name
     */
    @GetMapping("/AdminBidController")
    public String crudOperations(HttpServletRequest httpServletRequest){
        String bidId = httpServletRequest.getParameter("bidId");
        String operation = httpServletRequest.getParameter("operation");
        try {
            int id = Integer.parseInt(bidId);
            BidDAO bidDAOMySql = mySqlDAOFactory.createBidDAO();
            switch (operation){
                case "read":
                    Bid bid = bidDAOMySql.read(id);
                    /*
                    BidsHolderImpl bidsHolderImpl = beanFactory.getBean(BidsHolderImpl.class);
                    String lang = (String) httpServletRequest.getSession().getAttribute("lang");
                    if(lang == null){
                        lang = "en_EN";
                    }
                    bidsHolderImpl.setLang(lang);
                    */
                    httpServletRequest.setAttribute("bid", bid);
                    break;
                case "update":
                    bidDAOMySql.update(jaxbParser.createObjectBasedOnXML(id));
                    break;
                case "delete":
                    bidDAOMySql.delete(jaxbParser.createObjectBasedOnXML(id));
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
