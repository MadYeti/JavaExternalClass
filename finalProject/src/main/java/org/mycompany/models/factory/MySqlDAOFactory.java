package org.mycompany.models.factory;


import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class MySqlDAOFactory implements DAOFactory{

    private BeanFactory beanFactory;

    @Autowired
    public MySqlDAOFactory(BeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    @Override
    public ClientDAO createClientDAO() {
        return beanFactory.getBean(ClientDAO.class);
    }

    @Override
    public BidDAO createBidDAO() {
        return beanFactory.getBean(BidDAO.class);
    }
}
