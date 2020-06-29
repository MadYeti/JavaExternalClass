package org.mycompany.controllers.servlets;

import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.bidsHolder.BidsHolder;
import org.mycompany.models.client.Client;
import org.mycompany.models.repository.BidsHolderImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet output all bids ordered by specific user. Output nothing
 * if user has not ordered a single bid
 */
@WebServlet(name = "/BidsDetailsServlet", urlPatterns = "/BidsDetailsServlet")
public class BidsDetailsServlet extends HttpServlet{

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
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("client") != null) {
            int id = ((Client) httpSession.getAttribute("client")).getId();
            BidsHolderImpl bidsHolderImpl = new BidsHolderImpl(DBCPDataSource.getConnection());
            BidsHolder bidsHolder = new BidsHolder();
            bidsHolder.setBidsHolder(bidsHolderImpl.getWholeBidHistory(id));
            httpSession.setAttribute("bidsHolder", bidsHolder);
            requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/bidsHolder.jsp");
        }else{
            requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/notEnoughPrivilegesPage.jsp");
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
