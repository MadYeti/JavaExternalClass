package org.mycompany.models.dao.paymentStatusDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mycompany.models.paymentStatus.PaymentStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

public class PaymentStatusDAOMySqlTest {

    private PaymentStatusDAO paymentStatusDAOMySql = mock(PaymentStatusDAOMySql.class);
    private PaymentStatus paymentStatus;
    private List<PaymentStatus> paymentStatusList;

    @Before
    public void before(){
        paymentStatusList = new ArrayList<>();
        paymentStatus = new PaymentStatus();
    }

    @After
    public void after(){
        paymentStatusList.remove(paymentStatus);
    }

    @Test
    public void readDestinationPoint(){
        paymentStatusList.add(paymentStatus);
        doCallRealMethod().when(paymentStatusDAOMySql).read(0);
        PaymentStatus result = paymentStatusList.get(0);
        Assert.assertTrue(result != null);
    }

}
