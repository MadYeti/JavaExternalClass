package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.dao.bidDAO.DAO;
import org.mycompany.models.factory.MySqlDAOFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminBidController {

    private static Logger logger = Logger.getLogger(AdminBidController.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @GetMapping("/AdminBidController")
    public String crudOperations(HttpServletRequest httpServletRequest){
        String bidId = httpServletRequest.getParameter("bidId");
        String operation = httpServletRequest.getParameter("operation");
        try {
            int id = Integer.parseInt(bidId);
            DAO bidDAO = new MySqlDAOFactory().createBidDAO(DBCPDataSource.getConnection());
            JAXBParser jaxbParser = new JAXBParser();
            switch (operation){
                case "read":
                    Bid bid = bidDAO.read(id);
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
        return "/WEB-INF/view/adminPrivateOffice.jsp";
    }

}
