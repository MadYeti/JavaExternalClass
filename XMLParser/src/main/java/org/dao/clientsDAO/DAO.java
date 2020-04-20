package org.dao.clientsDAO;

import org.clients.Client;

/**
 * Created by MadYeti on 10.04.2020.
 */
public interface DAO {

    Client getClient(String login, String password);
    int insertClient(String login, String password);

}
