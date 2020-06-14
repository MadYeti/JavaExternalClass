package org.mycompany.models.factory;

import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.dao.bidDAO.BidDAOMySql;
import org.mycompany.models.dao.bidStatusDAO.BidStatusDAO;
import org.mycompany.models.dao.bidStatusDAO.BidStatusDAOMySql;
import org.mycompany.models.dao.cargoTypeDAO.CargoTypeDAO;
import org.mycompany.models.dao.cargoTypeDAO.CargoTypeDAOMySql;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.dao.clientDAO.ClientDAOMySql;
import org.mycompany.models.dao.destinationPointDAO.DestinationPointDAO;
import org.mycompany.models.dao.destinationPointDAO.DestinationPointDAOMySql;
import org.mycompany.models.dao.paymentStatusDAO.PaymentStatusDAO;
import org.mycompany.models.dao.paymentStatusDAO.PaymentStatusDAOMySql;
import org.mycompany.models.dao.sendingPointDAO.SendingPointDAO;
import org.mycompany.models.dao.sendingPointDAO.SendingPointDAOMySql;

import java.sql.Connection;

public class MySqlDAOFactory implements DAOFactory{

    @Override
    public ClientDAO createClientDAO(Connection connection) {
        return new ClientDAOMySql(connection);
    }

    @Override
    public BidDAO createBidDAO(Connection connection) {
        return new BidDAOMySql(connection);
    }

    @Override
    public CargoTypeDAO createCargoTypeDAO(Connection connection) {
        return new CargoTypeDAOMySql(connection);
    }

    @Override
    public SendingPointDAO createSendingPointDAO(Connection connection) {
        return new SendingPointDAOMySql(connection);
    }

    @Override
    public DestinationPointDAO createDestinationPointDAO(Connection connection) {
        return new DestinationPointDAOMySql(connection);
    }

    @Override
    public BidStatusDAO createBidStatusDAO(Connection connection) {
        return new BidStatusDAOMySql(connection);
    }

    @Override
    public PaymentStatusDAO createPaymentStatusDAO(Connection connection) {
        return new PaymentStatusDAOMySql(connection);
    }
}
