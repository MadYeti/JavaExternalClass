package org.mycompany.models.dao.clientDAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.client.Client;
import org.mycompany.models.client.Customer;
import org.springframework.beans.factory.BeanFactory;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientDAOMySqlTest {

    private SessionFactory sessionFactory;
    private ClientDAO clientDAOMySql;
    private Client client;
    private BeanFactory beanFactory = mock(BeanFactory.class);

    @Before
    public void before() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        clientDAOMySql = new ClientDAOMySql(beanFactory, sessionFactory);
        client = new Customer();
    }

    @After
    public void after() {
        sessionFactory.close();
    }

    @Test
    public void getClientByEmailAndPassword(){
        String email = "ivancov13@bigmir.net";
        String password = "zzzZ1!";
        client = clientDAOMySql.getClient(email, password);
        Assert.assertNotNull(client);
    }

    /**
     * Delete record in DB before run test
     */
    @Test
    public void addClientByEmailAndPassword(){
        String email = "ivancov321@bigmir.net";
        String password = "qqq!1Z";
        when(beanFactory.getBean(Customer.class)).thenReturn((Customer) client);
        clientDAOMySql.addClient(email, password);
        client = clientDAOMySql.getClient(email, password);
        Assert.assertNotNull(client);
    }

}
