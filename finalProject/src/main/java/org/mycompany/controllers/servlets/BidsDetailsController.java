package org.mycompany.controllers.servlets;

import org.mycompany.models.bidsHolder.BidsHolder;
import org.mycompany.models.client.Client;
import org.mycompany.models.repository.BidsHolderImpl;
import org.mycompany.models.repository.Repository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BidsDetailsController {

    private Repository bidsHolderImpl;
    private BidsHolder bidsHolder;
    private BeanFactory beanFactory;

    @Autowired
    public BidsDetailsController(BidsHolder bidsHolder,
                                 BeanFactory beanFactory){
        this.bidsHolder = bidsHolder;
        this.beanFactory = beanFactory;
    }

    @GetMapping("/BidsDetailsController")
    public String getAllOrderedBids(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        int id = ((Client)httpSession.getAttribute("client")).getId();
        bidsHolderImpl = beanFactory.getBean(BidsHolderImpl.class);
        bidsHolder.setBidsHolder(bidsHolderImpl.getWholeBidHistory(id));
        httpSession.setAttribute("bidsHolder", bidsHolder);
        return "bidsHolder";
    }

}
