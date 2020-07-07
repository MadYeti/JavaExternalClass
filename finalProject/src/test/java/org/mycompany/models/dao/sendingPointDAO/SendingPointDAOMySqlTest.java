package org.mycompany.models.dao.sendingPointDAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.sendingPoint.SendingPoint;

public class SendingPointDAOMySqlTest {

    private SessionFactory sessionFactory;
    private SendingPointDAO sendingPointDAOMySql;
    private SendingPoint sendingPoint;

    @Before
    public void before() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        sendingPointDAOMySql = new SendingPointDAOMySql(sessionFactory);
    }

    @After
    public void after() {
        sessionFactory.close();
    }

    @Test
    public void readSendingPoint(){
        sendingPoint = sendingPointDAOMySql.read(1);
        Assert.assertNotNull(sendingPoint);
    }

}
