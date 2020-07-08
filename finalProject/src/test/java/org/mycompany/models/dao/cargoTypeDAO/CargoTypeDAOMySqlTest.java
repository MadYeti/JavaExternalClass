package org.mycompany.models.dao.cargoTypeDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.dbConnect.DBCPDataSource;
import org.mycompany.models.cargoType.CargoType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

public class CargoTypeDAOMySqlTest {

    private CargoTypeDAO cargoTypeDAOMySql;
    private CargoType cargoType;

    @Before
    public void before(){
        cargoTypeDAOMySql = new CargoTypeDAOMySql(DBCPDataSource.getConnection());
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
    public void readCargoType(){
        cargoType = cargoTypeDAOMySql.read(1);
        Assert.assertNotNull(cargoType);
    }

}
