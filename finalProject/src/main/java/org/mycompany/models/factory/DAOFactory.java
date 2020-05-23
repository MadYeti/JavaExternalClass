package org.mycompany.models.factory;


import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.dao.clientDAO.ClientDAO;

import java.sql.Connection;

public interface DAOFactory {

    ClientDAO createClientDAO();
    BidDAO createBidDAO();

}
