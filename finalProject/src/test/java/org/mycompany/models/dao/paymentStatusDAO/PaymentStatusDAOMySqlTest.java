package org.mycompany.models.dao.paymentStatusDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.paymentStatus.PaymentStatus;

import java.sql.SQLException;

public class PaymentStatusDAOMySqlTest {

    private PaymentStatusDAO paymentStatusDAOMySql;
    private PaymentStatus paymentStatus;

    @Before
    public void before(){
        paymentStatusDAOMySql = new PaymentStatusDAOMySql(DBCPDataSource.getConnection());
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
    public void readDestinationPoint(){
        paymentStatus = paymentStatusDAOMySql.read(1);
        Assert.assertNotNull(paymentStatus);
    }

}
