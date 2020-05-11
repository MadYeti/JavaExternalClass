package org.mycompany.models.factory;


import org.mycompany.models.dao.bidDAO.BidDAO;
import org.mycompany.models.dao.clientDAO.ClientDAO;

import java.sql.Connection;

public class MySqlDAOFactory implements DAOFactory{

    @Override
    public ClientDAO createClientDAO(Connection connection) {
        return new ClientDAO(connection);
    }

    @Override
    public BidDAO createBidDAO(Connection connection) {
        return new BidDAO(connection);
    }
}
