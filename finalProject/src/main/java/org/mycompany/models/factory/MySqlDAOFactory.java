package org.mycompany.models.factory;


import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.dao.bidStatusDAO.BidStatusDAO;
import org.mycompany.models.dao.cargoTypeDAO.CargoTypeDAO;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.dao.destinationPointDAO.DestinationPointDAO;
import org.mycompany.models.dao.paymentStatusDAO.PaymentStatusDAO;
import org.mycompany.models.dao.sendingPointDAO.SendingPointDAO;
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

    @Override
    public BidStatusDAO createBidStatusDAO() {
        return beanFactory.getBean(BidStatusDAO.class);
    }

    @Override
    public CargoTypeDAO createCargoTypeDAO() {
        return beanFactory.getBean(CargoTypeDAO.class);
    }

    @Override
    public DestinationPointDAO createDestinationPointDAO() {
        return beanFactory.getBean(DestinationPointDAO.class);
    }

    @Override
    public PaymentStatusDAO createPaymentStatusDAO() {
        return beanFactory.getBean(PaymentStatusDAO.class);
    }

    @Override
    public SendingPointDAO createSendingPointDAO() {
        return beanFactory.getBean(SendingPointDAO.class);
    }
}
