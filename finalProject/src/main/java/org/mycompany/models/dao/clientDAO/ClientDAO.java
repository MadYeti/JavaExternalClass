package org.mycompany.models.dao.clientDAO;

import org.mycompany.models.client.Client;

/**
 * Basic interface that contains methods to get and add clients to DB
 */
public interface ClientDAO {

    Client getClient(String email, String password);
    Client read(int id);
    void addClient(String email, String password);

}
