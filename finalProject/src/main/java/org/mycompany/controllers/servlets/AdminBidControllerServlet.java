package org.mycompany.controllers.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.xmlParser.JAXBParser;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.bid.Bid;
import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.dao.bidDAO.DAO;
import org.mycompany.models.factory.MySqlDAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/AdminBidControllerServlet", urlPatterns = "/AdminBidControllerServlet")
public class AdminBidControllerServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(AdminBidControllerServlet.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher;
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
        requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/adminPrivateOffice.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }
}
