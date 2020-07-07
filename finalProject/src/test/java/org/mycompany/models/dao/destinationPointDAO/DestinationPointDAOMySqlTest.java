package org.mycompany.models.dao.destinationPointDAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.destinationPoint.DestinationPoint;

public class DestinationPointDAOMySqlTest {

    private SessionFactory sessionFactory;
    private DestinationPointDAO destinationPointDAOMySql;
    private DestinationPoint destinationPoint;

    @Before
    public void before() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        destinationPointDAOMySql = new DestinationPointDAOMySql(sessionFactory);
    }

    @After
    public void after() {
        sessionFactory.close();
    }

    @Test
    public void readDestinationPoint(){
        destinationPoint = destinationPointDAOMySql.read(1);
        Assert.assertNotNull(destinationPoint);
    }

}
