package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.dao.bidDAO.DAO;
import org.mycompany.models.factory.DAOFactory;
import org.mycompany.models.factory.MySqlDAOFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminBidController {

    private static Logger logger = Logger.getLogger(AdminBidController.class);
    private DAOFactory mySqlDAOFactory;
    private JAXBParser jaxbParser;
    private BeanFactory beanFactory;

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public AdminBidController(JAXBParser jaxbParser,
                              BeanFactory beanFactory){
        this.jaxbParser = jaxbParser;
        this.beanFactory = beanFactory;
    }

    @GetMapping("/AdminBidController")
    public String crudOperations(HttpServletRequest httpServletRequest){
        String bidId = httpServletRequest.getParameter("bidId");
        String operation = httpServletRequest.getParameter("operation");
        try {
            int id = Integer.parseInt(bidId);
            mySqlDAOFactory = beanFactory.getBean(MySqlDAOFactory.class);
            DAO bidDAO = mySqlDAOFactory.createBidDAO();
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
        return "adminPrivateOffice";
    }

}
