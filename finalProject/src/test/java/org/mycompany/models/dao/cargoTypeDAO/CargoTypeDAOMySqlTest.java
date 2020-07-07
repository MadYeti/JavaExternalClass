package org.mycompany.models.dao.cargoTypeDAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.cargoType.CargoType;

public class CargoTypeDAOMySqlTest {

    private SessionFactory sessionFactory;
    private CargoTypeDAO cargoTypeDAOMySql;
    private CargoType cargoType;

    @Before
    public void before() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        cargoTypeDAOMySql = new CargoTypeDAOMySql(sessionFactory);
    }

    @After
    public void after() {
        sessionFactory.close();
    }

    @Test
    public void readCargoType(){
        cargoType = cargoTypeDAOMySql.read(1);
        Assert.assertNotNull(cargoType);
    }

}
