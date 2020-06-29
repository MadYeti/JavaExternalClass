package org.mycompany.models.dao.clientDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.client.Client;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

public class ClientDAOMySqlTest {

    private ClientDAO clientDAOMySql = mock(ClientDAOMySql.class);
    private Client client;
    private List<Client> clientList;

    @Before
    public void before(){
        clientList = new ArrayList<>();
        client = new Client();
    }

    @After
    public void after(){
        clientList.remove(client);
    }

    @Test
    public void getClientByEmailAndPassword(){
        String email = "ivancov13@bigmir.net";
        String password = "zzzZ1!";
        Client result = null;
        client.setEmail("ivancov13@bigmir.net");
        client.setPassword("zzzZ1!");
        clientList.add(client);
        doCallRealMethod().when(clientDAOMySql).getClient(email, password);
        for(Client clients: clientList){
            if(clients.getEmail().equals(email) && clients.getPassword().equals(password)){
                result = clients;
            }
        }
        Assert.assertTrue(result != null);
    }

    @Test
    public void readClient(){
        clientList.add(client);
        doCallRealMethod().when(clientDAOMySql).read(0);
        Client result = clientList.get(0);
        Assert.assertTrue(result != null);
    }

    @Test
    public void addClientByEmailAndPassword(){
        String email = "ivancov13@bigmir.net";
        String password = "zzzZ1!";
        doCallRealMethod().when(clientDAOMySql).addClient(email, password);
        client.setEmail("ivancov13@bigmir.net");
        client.setPassword("zzzZ1!");
        clientList.add(client);
        Assert.assertTrue(clientList.size() == 1);
    }

}
