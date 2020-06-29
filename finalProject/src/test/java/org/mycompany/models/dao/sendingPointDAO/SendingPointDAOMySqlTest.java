package org.mycompany.models.dao.sendingPointDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.sendingPoint.SendingPoint;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

public class SendingPointDAOMySqlTest {

    private SendingPointDAO sendingPointDAOMySql = mock(SendingPointDAOMySql.class);
    private SendingPoint sendingPoint;
    private List<SendingPoint> sendingPointList;

    @Before
    public void before(){
        sendingPointList = new ArrayList<>();
        sendingPoint = new SendingPoint();
    }

    @After
    public void after(){
        sendingPointList.remove(sendingPoint);
    }

    @Test
    public void readDestinationPoint(){
        sendingPointList.add(sendingPoint);
        doCallRealMethod().when(sendingPointDAOMySql).read(0);
        SendingPoint result = sendingPointList.get(0);
        Assert.assertTrue(result != null);
    }

}
