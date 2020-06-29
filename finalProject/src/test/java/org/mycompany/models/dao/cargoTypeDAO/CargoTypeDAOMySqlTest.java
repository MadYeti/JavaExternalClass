package org.mycompany.models.dao.cargoTypeDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.cargoType.CargoType;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

public class CargoTypeDAOMySqlTest {

    private CargoTypeDAO cargoTypeDAOMySql = mock(CargoTypeDAOMySql.class);
    private CargoType cargoType;
    private List<CargoType> cargoTypeList;

    @Before
    public void before(){
        cargoTypeList = new ArrayList<>();
        cargoType = new CargoType();
    }

    @After
    public void after(){
        cargoTypeList.remove(cargoType);
    }

    @Test
    public void readBidStatus(){
        cargoTypeList.add(cargoType);
        doCallRealMethod().when(cargoTypeDAOMySql).read(0);
        CargoType result = cargoTypeList.get(0);
        Assert.assertTrue(result != null);
    }

}
