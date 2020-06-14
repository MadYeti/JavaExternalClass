package org.mycompany.models.factory;

import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.dao.bidStatusDAO.BidStatusDAO;
import org.mycompany.models.dao.cargoTypeDAO.CargoTypeDAO;
import org.mycompany.models.dao.clientDAO.ClientDAO;
import org.mycompany.models.dao.destinationPointDAO.DestinationPointDAO;
import org.mycompany.models.dao.paymentStatusDAO.PaymentStatusDAO;
import org.mycompany.models.dao.sendingPointDAO.SendingPointDAO;

import java.sql.Connection;

public interface DAOFactory {

    ClientDAO createClientDAO(Connection connection);
    BidDAO createBidDAO(Connection connection);
    CargoTypeDAO createCargoTypeDAO(Connection connection);
    SendingPointDAO createSendingPointDAO(Connection connection);
    DestinationPointDAO createDestinationPointDAO(Connection connection);
    BidStatusDAO createBidStatusDAO(Connection connection);
    PaymentStatusDAO createPaymentStatusDAO(Connection connection);

}
