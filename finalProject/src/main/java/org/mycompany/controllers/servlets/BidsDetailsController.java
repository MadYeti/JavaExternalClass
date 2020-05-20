package org.mycompany.controllers.servlets;

import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.bidsHolder.BidsHolder;
import org.mycompany.models.client.Client;
import org.mycompany.models.repository.BidsHolderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BidsDetailsController {

    @GetMapping("/BidsDetailsController")
    public String getAllOrderedBids(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        int id = ((Client)httpSession.getAttribute("client")).getId();
        BidsHolderImpl bidsHolderImpl = new BidsHolderImpl(DBCPDataSource.getConnection());
        BidsHolder bidsHolder = new BidsHolder();
        bidsHolder.setBidsHolder(bidsHolderImpl.getWholeBidHistory(id));
        httpSession.setAttribute("bidsHolder", bidsHolder);
        return "/WEB-INF/view/bidsHolder.jsp";
    }

}
