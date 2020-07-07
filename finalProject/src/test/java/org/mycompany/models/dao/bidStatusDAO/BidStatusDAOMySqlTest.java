package org.mycompany.models.dao.bidStatusDAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.bidStatus.BidStatus;

public class BidStatusDAOMySqlTest {

    private SessionFactory sessionFactory;
    private BidStatusDAO bidStatusDAOMySql;
    private BidStatus bidStatus;

    @Before
    public void before() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        bidStatusDAOMySql = new BidStatusDAOMySql(sessionFactory);
    }

    @After
    public void after() {
        sessionFactory.close();
    }

    @Test
    public void readBidStatus(){
        bidStatus = bidStatusDAOMySql.read(1);
        Assert.assertNotNull(bidStatus);
    }

}
