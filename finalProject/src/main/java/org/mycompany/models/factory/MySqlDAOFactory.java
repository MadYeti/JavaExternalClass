package org.mycompany.models.factory;

import org.mycompany.models.dao.bidDAO.BidDAOMySql;
import org.mycompany.models.dao.bidStatusDAO.BidStatusDAOMySql;
import org.mycompany.models.dao.cargoTypeDAO.CargoTypeDAOMySql;
import org.mycompany.models.dao.clientDAO.ClientDAOMySql;
import org.mycompany.models.dao.destinationPointDAO.DestinationPointDAOMySql;
import org.mycompany.models.dao.paymentStatusDAO.PaymentStatusDAOMySql;
import org.mycompany.models.dao.sendingPointDAO.SendingPointDAOMySql;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of DAO factory objects
 */
@Component
public class MySqlDAOFactory implements DAOFactory{

    private BeanFactory beanFactory;

    @Autowired
    public MySqlDAOFactory(BeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    @Override
    public ClientDAOMySql createClientDAO() {
        return beanFactory.getBean(ClientDAOMySql.class);
    }

    @Override
    public BidDAOMySql createBidDAO() {
        return beanFactory.getBean(BidDAOMySql.class);
    }

    @Override
    public BidStatusDAOMySql createBidStatusDAO() {
        return beanFactory.getBean(BidStatusDAOMySql.class);
    }

    @Override
    public CargoTypeDAOMySql createCargoTypeDAO() {
        return beanFactory.getBean(CargoTypeDAOMySql.class);
    }

    @Override
    public DestinationPointDAOMySql createDestinationPointDAO() {
        return beanFactory.getBean(DestinationPointDAOMySql.class);
    }

    @Override
    public PaymentStatusDAOMySql createPaymentStatusDAO() {
        return beanFactory.getBean(PaymentStatusDAOMySql.class);
    }

    @Override
    public SendingPointDAOMySql createSendingPointDAO() {
        return beanFactory.getBean(SendingPointDAOMySql.class);
    }
}
