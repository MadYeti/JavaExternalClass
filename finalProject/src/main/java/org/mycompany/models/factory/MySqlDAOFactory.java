package org.mycompany.models.factory;


import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
@Scope("prototype")
public class MySqlDAOFactory implements DAOFactory{

    private Connection connection;
    private BeanFactory beanFactory;

    @Autowired
    public MySqlDAOFactory(Connection connection,
                           BeanFactory beanFactory){
        this.connection = connection;
        this.beanFactory = beanFactory;
    }

    @Override
    public ClientDAO createClientDAO() {
        return beanFactory.getBean(ClientDAO.class, connection, beanFactory);
    }

    @Override
    public BidDAO createBidDAO() {
        return beanFactory.getBean(BidDAO.class, connection, beanFactory);
    }
}
