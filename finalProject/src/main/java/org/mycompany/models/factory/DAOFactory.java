package org.mycompany.models.factory;

import org.mycompany.models.dao.bidDAO.BidDAOMySql;
import org.mycompany.models.dao.bidStatusDAO.BidStatusDAOMySql;
import org.mycompany.models.dao.cargoTypeDAO.CargoTypeDAOMySql;
import org.mycompany.models.dao.clientDAO.ClientDAOMySql;
import org.mycompany.models.dao.destinationPointDAO.DestinationPointDAOMySql;
import org.mycompany.models.dao.paymentStatusDAO.PaymentStatusDAOMySql;
import org.mycompany.models.dao.sendingPointDAO.SendingPointDAOMySql;

/**
 * Basic interface for creating DAO objects
 */
public interface DAOFactory {

    ClientDAOMySql createClientDAO();
    BidDAOMySql createBidDAO();
    BidStatusDAOMySql createBidStatusDAO();
    CargoTypeDAOMySql createCargoTypeDAO();
    DestinationPointDAOMySql createDestinationPointDAO();
    PaymentStatusDAOMySql createPaymentStatusDAO();
    SendingPointDAOMySql createSendingPointDAO();

}
