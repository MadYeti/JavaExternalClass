package org.mycompany.models.dao.clientDAO;

import org.mycompany.models.client.Client;


public interface DAO {

    Client getClient(String email, String password);
    void addClient(String email, String password);

}
