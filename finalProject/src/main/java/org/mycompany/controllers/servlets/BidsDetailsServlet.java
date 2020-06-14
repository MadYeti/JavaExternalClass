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

@WebServlet(name = "/BidsDetailsServlet", urlPatterns = "/BidsDetailsServlet")
public class BidsDetailsServlet extends HttpServlet{

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

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }
}
