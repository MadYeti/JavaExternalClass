package org.mycompany.models.dao.bidStatusDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.bidStatus.BidStatus;

import java.sql.SQLException;

public class BidStatusDAOMySqlTest {

    private BidStatusDAO bidStatusDAOMySql;
    private BidStatus bidStatus;

    @Before
    public void before(){
        bidStatusDAOMySql = new BidStatusDAOMySql(DBCPDataSource.getConnection());
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
    public void readBidStatus(){
        bidStatus = bidStatusDAOMySql.read(1);
        Assert.assertNotNull(bidStatus);
    }

}
