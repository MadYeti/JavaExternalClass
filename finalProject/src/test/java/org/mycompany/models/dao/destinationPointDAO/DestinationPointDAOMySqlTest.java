package org.mycompany.models.dao.destinationPointDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.destinationPoint.DestinationPoint;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

public class DestinationPointDAOMySqlTest {

    private DestinationPointDAO destinationPointDAOMySql = mock(DestinationPointDAOMySql.class);
    private DestinationPoint destinationPoint;
    private List<DestinationPoint> destinationPointList;

    @Before
    public void before(){
        destinationPointList = new ArrayList<>();
        destinationPoint = new DestinationPoint();
    }

    @After
    public void after(){
        destinationPointList.remove(destinationPoint);
    }

    @Test
    public void readDestinationPoint(){
        destinationPointList.add(destinationPoint);
        doCallRealMethod().when(destinationPointDAOMySql).read(0);
        DestinationPoint result = destinationPointList.get(0);
        Assert.assertTrue(result != null);
    }

}
