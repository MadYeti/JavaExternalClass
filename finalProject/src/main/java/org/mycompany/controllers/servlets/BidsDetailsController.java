package org.mycompany.controllers.servlets;

import org.mycompany.models.bidsHolder.BidsHolder;
import org.mycompany.models.client.Client;
import org.mycompany.models.repository.BidsHolderImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BidsDetailsController {

    private BeanFactory beanFactory;

    @Autowired
    public BidsDetailsController(BeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    @GetMapping("/BidsDetailsController")
    public String getAllOrderedBids(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("client") != null) {
            int id = ((Client) httpSession.getAttribute("client")).getId();
            String lang = (String) httpServletRequest.getSession().getAttribute("lang");
            BidsHolderImpl bidsHolderImpl = beanFactory.getBean(BidsHolderImpl.class);
            bidsHolderImpl.setLang(lang);
            BidsHolder bidsHolder = beanFactory.getBean(BidsHolder.class);
            bidsHolder.setBidsHolder(bidsHolderImpl.getWholeBidHistory(id));
            httpSession.setAttribute("bidsHolder", bidsHolder);
            return "bidsHolder";
        }
        return "notEnoughPrivilegesPage";
    }

}
