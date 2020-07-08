package org.mycompany.models.dao.destinationPointDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.destinationPoint.DestinationPoint;

import java.sql.SQLException;

public class DestinationPointDAOMySqlTest {

    private DestinationPointDAO destinationPointDAOMySql;
    private DestinationPoint destinationPoint;

    @Before
    public void before(){
        destinationPointDAOMySql = new DestinationPointDAOMySql(DBCPDataSource.getConnection());
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
        destinationPoint = destinationPointDAOMySql.read(1);
        Assert.assertNotNull(destinationPoint);
    }

}
