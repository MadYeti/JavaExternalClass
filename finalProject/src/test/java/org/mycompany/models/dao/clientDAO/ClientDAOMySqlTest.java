package org.mycompany.models.dao.clientDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.client.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

public class ClientDAOMySqlTest {

    private ClientDAO clientDAOMySql;
    private Client client;

    @Before
    public void before(){
        clientDAOMySql = new ClientDAOMySql(DBCPDataSource.getConnection());
    }

    @After
    public void after(){
        try {
            DBCPDataSource.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getClientByEmailAndPassword(){
        String email = "ivancov13@bigmir.net";
        String password = "zzzZ1!";
        client = clientDAOMySql.getClient(email, password);
        Assert.assertNotNull(client);
    }

    @Test
    public void readClient(){
        client = clientDAOMySql.read(1);
        Assert.assertNotNull(client);
    }

    @Test
    public void addClientByEmailAndPassword(){
        String email = "ivancov123@bigmir.net";
        String password = "qqq!1Z";
        clientDAOMySql.addClient(email, password);
        client = new ClientDAOMySql(DBCPDataSource.getConnection()).getClient(email, password);
        Assert.assertNotNull(client);
    }

}
