package org.mycompany.models.dao.paymentStatusDAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.paymentStatus.PaymentStatus;

public class PaymentStatusDAOMySqlTest {

    private SessionFactory sessionFactory;
    private PaymentStatusDAO paymentStatusDAOMySql;
    private PaymentStatus paymentStatus;

    @Before
    public void before() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        paymentStatusDAOMySql = new PaymentStatusDAOMySql(sessionFactory);
    }

    @After
    public void after() {
        sessionFactory.close();
    }

    @Test
    public void readPaymentStatus(){
        paymentStatus = paymentStatusDAOMySql.read(1);
        Assert.assertNotNull(paymentStatus);
    }
}
