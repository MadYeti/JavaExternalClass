package org.mycompany.models.dao.sendingPointDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.sendingPoint.SendingPoint;

import java.sql.SQLException;

public class SendingPointDAOMySqlTest {

    private SendingPointDAO sendingPointDAOMySql;
    private SendingPoint sendingPoint;

    @Before
    public void before(){
        sendingPointDAOMySql = new SendingPointDAOMySql(DBCPDataSource.getConnection());
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
        sendingPoint = sendingPointDAOMySql.read(1);
        Assert.assertNotNull(sendingPoint);
    }

}
